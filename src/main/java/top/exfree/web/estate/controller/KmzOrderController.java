package top.exfree.web.estate.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.exfree.web.common.annotation.Log;
import top.exfree.web.common.enums.BusinessType;
import top.exfree.web.estate.domain.KmzOrder;
import top.exfree.web.estate.service.IKmzOrderService;
import top.exfree.web.common.core.controller.BaseController;
import top.exfree.web.common.core.domain.AjaxResult;
import top.exfree.web.common.utils.poi.ExcelUtil;
import top.exfree.web.common.core.page.TableDataInfo;

/**
 * 车辆订单Controller
 * 
 * @author kmz
 * @date 2024-08-23
 */
@Controller
@RequestMapping("/estate/KmzOrder")
public class KmzOrderController extends BaseController
{
    private String prefix = "estate/KmzOrder";

    @Autowired
    private IKmzOrderService kmzOrderService;

    @RequiresPermissions("estate:KmzOrder:view")
    @GetMapping()
    public String KmzOrder()
    {
        return prefix + "/KmzOrder";
    }

    /**
     * 查询车辆订单列表
     */
    @RequiresPermissions("estate:KmzOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KmzOrder kmzOrder)
    {
        startPage();
        List<KmzOrder> list = kmzOrderService.selectKmzOrderList(kmzOrder);
        return getDataTable(list);
    }

    /**
     * 导出车辆订单列表
     */
    @RequiresPermissions("estate:KmzOrder:export")
    @Log(title = "车辆订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KmzOrder kmzOrder)
    {
        List<KmzOrder> list = kmzOrderService.selectKmzOrderList(kmzOrder);
        ExcelUtil<KmzOrder> util = new ExcelUtil<KmzOrder>(KmzOrder.class);
        return util.exportExcel(list, "车辆订单数据");
    }

    /**
     * 新增车辆订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存车辆订单
     */
    @RequiresPermissions("estate:KmzOrder:add")
    @Log(title = "车辆订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KmzOrder kmzOrder)
    {
        return toAjax(kmzOrderService.save(kmzOrder));
    }

    /**
     * 修改车辆订单
     */
    @RequiresPermissions("estate:KmzOrder:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KmzOrder kmzOrder = kmzOrderService.getById(id);
        mmap.put("kmzOrder", kmzOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存车辆订单
     */
    @RequiresPermissions("estate:KmzOrder:edit")
    @Log(title = "车辆订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KmzOrder kmzOrder)
    {
        return toAjax(kmzOrderService.updateById(kmzOrder));
    }

    /**
     * 删除车辆订单
     */
    @RequiresPermissions("estate:KmzOrder:remove")
    @Log(title = "车辆订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kmzOrderService.removeById(ids));
    }
}
