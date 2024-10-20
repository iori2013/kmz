package top.exfree.web.estate.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import top.exfree.web.common.annotation.Excel;
import top.exfree.web.common.core.domain.BaseEntity;

/**
 * 投放地区对象 kmz_area
 * 
 * @author kmz
 * @date 2024-08-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("kmz_area")
public class KmzArea
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /** 编号 */
    @TableField("no")
    @Excel(name = "编号")
    private String no;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contact;

}
