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
import top.exfree.web.estate.domain.KmzBadOrder;
import top.exfree.web.estate.service.IKmzBadOrderService;
import top.exfree.web.common.core.controller.BaseController;
import top.exfree.web.common.core.domain.AjaxResult;
import top.exfree.web.common.utils.poi.ExcelUtil;
import top.exfree.web.common.core.page.TableDataInfo;

/**
 * 故障单Controller
 * 
 * @author kmz
 * @date 2024-08-23
 */
@Controller
@RequestMapping("/estate/KmzBadOrder")
public class KmzBadOrderController extends BaseController
{
    private String prefix = "estate/KmzBadOrder";

    @Autowired
    private IKmzBadOrderService kmzBadOrderService;

    @RequiresPermissions("estate:KmzBadOrder:view")
    @GetMapping()
    public String KmzBadOrder()
    {
        return prefix + "/KmzBadOrder";
    }

    /**
     * 查询故障单列表
     */
    @RequiresPermissions("estate:KmzBadOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KmzBadOrder kmzBadOrder)
    {
        startPage();
        List<KmzBadOrder> list = kmzBadOrderService.selectKmzBadOrderList(kmzBadOrder);
        return getDataTable(list);
    }

    /**
     * 导出故障单列表
     */
    @RequiresPermissions("estate:KmzBadOrder:export")
    @Log(title = "故障单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KmzBadOrder kmzBadOrder)
    {
        List<KmzBadOrder> list = kmzBadOrderService.selectKmzBadOrderList(kmzBadOrder);
        ExcelUtil<KmzBadOrder> util = new ExcelUtil<KmzBadOrder>(KmzBadOrder.class);
        return util.exportExcel(list, "故障单数据");
    }

    /**
     * 新增故障单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存故障单
     */
    @RequiresPermissions("estate:KmzBadOrder:add")
    @Log(title = "故障单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KmzBadOrder kmzBadOrder)
    {
        return toAjax(kmzBadOrderService.save(kmzBadOrder));
    }

    /**
     * 修改故障单
     */
    @RequiresPermissions("estate:KmzBadOrder:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KmzBadOrder kmzBadOrder = kmzBadOrderService.getById(id);
        mmap.put("kmzBadOrder", kmzBadOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存故障单
     */
    @RequiresPermissions("estate:KmzBadOrder:edit")
    @Log(title = "故障单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KmzBadOrder kmzBadOrder)
    {
        return toAjax(kmzBadOrderService.updateById(kmzBadOrder));
    }

    /**
     * 删除故障单
     */
    @RequiresPermissions("estate:KmzBadOrder:remove")
    @Log(title = "故障单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kmzBadOrderService.removeById(ids));
    }
}
