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
import top.exfree.web.estate.domain.KmzBike;
import top.exfree.web.estate.service.IKmzBikeService;
import top.exfree.web.common.core.controller.BaseController;
import top.exfree.web.common.core.domain.AjaxResult;
import top.exfree.web.common.utils.poi.ExcelUtil;
import top.exfree.web.common.core.page.TableDataInfo;

/**
 * 车辆列Controller
 * 
 * @author kmz
 * @date 2024-08-23
 */
@Controller
@RequestMapping("/estate/KmzBike")
public class KmzBikeController extends BaseController
{
    private String prefix = "estate/KmzBike";

    @Autowired
    private IKmzBikeService kmzBikeService;

    @RequiresPermissions("estate:KmzBike:view")
    @GetMapping()
    public String KmzBike()
    {
        return prefix + "/KmzBike";
    }

    /**
     * 查询车辆列列表
     */
    @RequiresPermissions("estate:KmzBike:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KmzBike kmzBike)
    {
        startPage();
        List<KmzBike> list = kmzBikeService.selectKmzBikeList(kmzBike);
        return getDataTable(list);
    }

    /**
     * 导出车辆列列表
     */
    @RequiresPermissions("estate:KmzBike:export")
    @Log(title = "车辆列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KmzBike kmzBike)
    {
        List<KmzBike> list = kmzBikeService.selectKmzBikeList(kmzBike);
        ExcelUtil<KmzBike> util = new ExcelUtil<KmzBike>(KmzBike.class);
        return util.exportExcel(list, "车辆列数据");
    }

    /**
     * 新增车辆列
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存车辆列
     */
    @RequiresPermissions("estate:KmzBike:add")
    @Log(title = "车辆列", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KmzBike kmzBike)
    {
        return toAjax(kmzBikeService.save(kmzBike));
    }

    /**
     * 修改车辆列
     */
    @RequiresPermissions("estate:KmzBike:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KmzBike kmzBike = kmzBikeService.getById(id);
        mmap.put("kmzBike", kmzBike);
        return prefix + "/edit";
    }

    /**
     * 修改保存车辆列
     */
    @RequiresPermissions("estate:KmzBike:edit")
    @Log(title = "车辆列", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KmzBike kmzBike)
    {
        return toAjax(kmzBikeService.updateById(kmzBike));
    }

    /**
     * 删除车辆列
     */
    @RequiresPermissions("estate:KmzBike:remove")
    @Log(title = "车辆列", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kmzBikeService.removeById(ids));
    }
}
