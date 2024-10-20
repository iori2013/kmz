package top.exfree.web.controller.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.exfree.web.common.core.domain.AjaxResult;
import top.exfree.web.estate.absolute.TokenService;
import top.exfree.web.estate.domain.KmzMember;
import top.exfree.web.estate.service.IKmzMemberService;

import javax.validation.Valid;

/**
 * 通用请求处理
 * 
 * @author kmz
 */
@RestController
@RequestMapping("/onload")
@Api(tags = "启动接口")
public class OnloadController
{


    @Autowired
    private IKmzMemberService kmzMemberService;

    @Autowired
    private TokenService tokenService;

    @GetMapping()
    public AjaxResult KmzArea()
    {
        return AjaxResult.success("ni hao");
    }



    /**
     * 注册用户
     */
    @ApiOperation(value = "注册信息")
    @PostMapping("/register")
    @ResponseBody
    public AjaxResult register(@Validated KmzMember kmzMember)
    {
        kmzMember.setId(null);
        kmzMemberService.save(kmzMember);
        tokenService.createToken(kmzMember);
        return AjaxResult.success(kmzMember);
    }

    @ApiOperation(value = "会员登录")
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(@RequestParam("username") @Valid String username, @RequestParam("password") @Valid String password)
    {
        KmzMember kmzMember = kmzMemberService.getOne(new QueryWrapper<KmzMember>().eq("username", username).eq("password", password), false);
        if (kmzMember == null)
        {
            return AjaxResult.error("用户名或密码错误");
        }
        return AjaxResult.success(kmzMember);
    }
}
