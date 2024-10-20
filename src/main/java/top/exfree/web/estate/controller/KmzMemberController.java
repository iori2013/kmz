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
import top.exfree.web.estate.domain.KmzMember;
import top.exfree.web.estate.service.IKmzMemberService;
import top.exfree.web.common.core.controller.BaseController;
import top.exfree.web.common.core.domain.AjaxResult;
import top.exfree.web.common.utils.poi.ExcelUtil;
import top.exfree.web.common.core.page.TableDataInfo;

/**
 * 会员管理Controller
 * 
 * @author kmz
 * @date 2024-08-21
 */
@Controller
@RequestMapping("/estate/KmzMember")
public class KmzMemberController extends BaseController
{
    private String prefix = "estate/KmzMember";

    @Autowired
    private IKmzMemberService kmzMemberService;

    @RequiresPermissions("estate:KmzMember:view")
    @GetMapping()
    public String KmzMember()
    {
        return prefix + "/KmzMember";
    }

    /**
     * 查询会员管理列表
     */
    @RequiresPermissions("estate:KmzMember:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KmzMember kmzMember)
    {
        startPage();
        List<KmzMember> list = kmzMemberService.selectKmzMemberList(kmzMember);
        return getDataTable(list);
    }

    /**
     * 导出会员管理列表
     */
    @RequiresPermissions("estate:KmzMember:export")
    @Log(title = "会员管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KmzMember kmzMember)
    {
        List<KmzMember> list = kmzMemberService.selectKmzMemberList(kmzMember);
        ExcelUtil<KmzMember> util = new ExcelUtil<KmzMember>(KmzMember.class);
        return util.exportExcel(list, "会员管理数据");
    }

    /**
     * 新增会员管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存会员管理
     */
    @RequiresPermissions("estate:KmzMember:add")
    @Log(title = "会员管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KmzMember kmzMember)
    {
        return toAjax(kmzMemberService.save(kmzMember));
    }

    /**
     * 修改会员管理
     */
    @RequiresPermissions("estate:KmzMember:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KmzMember kmzMember = kmzMemberService.getById(id);
        mmap.put("kmzMember", kmzMember);
        return prefix + "/edit";
    }

    /**
     * 修改保存会员管理
     */
    @RequiresPermissions("estate:KmzMember:edit")
    @Log(title = "会员管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KmzMember kmzMember)
    {
        return toAjax(kmzMemberService.updateById(kmzMember));
    }

    /**
     * 删除会员管理
     */
    @RequiresPermissions("estate:KmzMember:remove")
    @Log(title = "会员管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kmzMemberService.removeById(ids));
    }
}
