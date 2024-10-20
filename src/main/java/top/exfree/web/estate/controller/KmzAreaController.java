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
import top.exfree.web.estate.domain.KmzArea;
import top.exfree.web.estate.service.IKmzAreaService;
import top.exfree.web.common.core.controller.BaseController;
import top.exfree.web.common.core.domain.AjaxResult;
import top.exfree.web.common.utils.poi.ExcelUtil;
import top.exfree.web.common.core.page.TableDataInfo;

/**
 * 投放地区Controller
 * 
 * @author kmz
 * @date 2024-08-21
 */
@Controller
@RequestMapping("/estate/KmzArea")
public class KmzAreaController extends BaseController
{
    private String prefix = "estate/KmzArea";

    @Autowired
    private IKmzAreaService kmzAreaService;

    @RequiresPermissions("estate:KmzArea:view")
    @GetMapping()
    public String KmzArea()
    {
        return prefix + "/KmzArea";
    }

    /**
     * 查询投放地区列表
     */
    @RequiresPermissions("estate:KmzArea:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KmzArea kmzArea)
    {
        startPage();
        List<KmzArea> list = kmzAreaService.selectKmzAreaList(kmzArea);
        return getDataTable(list);
    }

    /**
     * 导出投放地区列表
     */
    @RequiresPermissions("estate:KmzArea:export")
    @Log(title = "投放地区", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KmzArea kmzArea)
    {
        List<KmzArea> list = kmzAreaService.selectKmzAreaList(kmzArea);
        ExcelUtil<KmzArea> util = new ExcelUtil<KmzArea>(KmzArea.class);
        return util.exportExcel(list, "投放地区数据");
    }

    /**
     * 新增投放地区
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存投放地区
     */
    @RequiresPermissions("estate:KmzArea:add")
    @Log(title = "投放地区", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KmzArea kmzArea)
    {
        return toAjax(kmzAreaService.add(kmzArea));
    }

    /**
     * 修改投放地区
     */
    @RequiresPermissions("estate:KmzArea:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KmzArea kmzArea = kmzAreaService.getById(id);
        mmap.put("kmzArea", kmzArea);
        return prefix + "/edit";
    }

    /**
     * 修改保存投放地区
     */
    @RequiresPermissions("estate:KmzArea:edit")
    @Log(title = "投放地区", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KmzArea kmzArea)
    {
        return toAjax(kmzAreaService.updateById(kmzArea));
    }

    /**
     * 删除投放地区
     */
    @RequiresPermissions("estate:KmzArea:remove")
    @Log(title = "投放地区", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kmzAreaService.removeById(ids));
    }
}
