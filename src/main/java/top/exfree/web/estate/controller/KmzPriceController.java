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
import top.exfree.web.estate.domain.KmzPrice;
import top.exfree.web.estate.service.IKmzPriceService;
import top.exfree.web.common.core.controller.BaseController;
import top.exfree.web.common.core.domain.AjaxResult;
import top.exfree.web.common.utils.poi.ExcelUtil;
import top.exfree.web.common.core.page.TableDataInfo;

/**
 * 价格设置Controller
 * 
 * @author kmz
 * @date 2024-08-23
 */
@Controller
@RequestMapping("/estate/KmzPrice")
public class KmzPriceController extends BaseController
{
    private String prefix = "estate/KmzPrice";

    @Autowired
    private IKmzPriceService kmzPriceService;

    @RequiresPermissions("estate:KmzPrice:view")
    @GetMapping()
    public String KmzPrice()
    {
        return prefix + "/KmzPrice";
    }

    /**
     * 查询价格设置列表
     */
    @RequiresPermissions("estate:KmzPrice:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KmzPrice kmzPrice)
    {
        startPage();
        List<KmzPrice> list = kmzPriceService.selectKmzPriceList(kmzPrice);
        return getDataTable(list);
    }

    /**
     * 导出价格设置列表
     */
    @RequiresPermissions("estate:KmzPrice:export")
    @Log(title = "价格设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KmzPrice kmzPrice)
    {
        List<KmzPrice> list = kmzPriceService.selectKmzPriceList(kmzPrice);
        ExcelUtil<KmzPrice> util = new ExcelUtil<KmzPrice>(KmzPrice.class);
        return util.exportExcel(list, "价格设置数据");
    }

    /**
     * 新增价格设置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存价格设置
     */
    @RequiresPermissions("estate:KmzPrice:add")
    @Log(title = "价格设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KmzPrice kmzPrice)
    {
        return toAjax(kmzPriceService.save(kmzPrice));
    }

    /**
     * 修改价格设置
     */
    @RequiresPermissions("estate:KmzPrice:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KmzPrice kmzPrice = kmzPriceService.getById(id);
        mmap.put("kmzPrice", kmzPrice);
        return prefix + "/edit";
    }

    /**
     * 修改保存价格设置
     */
    @RequiresPermissions("estate:KmzPrice:edit")
    @Log(title = "价格设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KmzPrice kmzPrice)
    {
        return toAjax(kmzPriceService.updateById(kmzPrice));
    }

    /**
     * 删除价格设置
     */
    @RequiresPermissions("estate:KmzPrice:remove")
    @Log(title = "价格设置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kmzPriceService.removeById(ids));
    }
}
