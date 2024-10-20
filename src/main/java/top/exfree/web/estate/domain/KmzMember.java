package top.exfree.web.estate.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.exfree.web.common.annotation.Excel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 会员管理对象 kmz_member
 * 
 * @author kmz
 * @date 2024-08-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("kmz_member")
public class KmzMember
{
    private static final long serialVersionUID = 1L;

    /**  */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    @NotBlank(message = "用户名不能为空")
    @TableField("username")
    private String username;

    /** 密码 */
    @Excel(name = "密码")
    @TableField("password")
    @Min(value = 6,message = "密码不能少于6位")
    private String password;

    /** 类型 */
    @Excel(name = "类型")
    @TableField("type")
    private Long type;

    /** 令牌 */
    @Excel(name = "令牌")
    @TableField("token")
    private String token;

    /** 手机号 */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp="^1[3-9]\\d{9}$", message="手机号格式不正确")
    @TableField("mobile")
    private String mobile;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;




}
