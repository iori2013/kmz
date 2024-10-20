package top.exfree.web.estate.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import top.exfree.web.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.exfree.web.common.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 价格设置对象 kmz_price
 * 
 * @author kmz
 * @date 2024-08-23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("kmz_price")
public class KmzPrice
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /** 车辆类型 */
    @Excel(name = "车辆类型")
    @TableField("bike_type")
    private Long bikeType;

    /** 地区id */
    @Excel(name = "地区id")
    @TableField("area_id")
    private Long areaId;

    /** 地区名称 */
    @Excel(name = "地区名称")
    @TableField("area_name")
    private String areaName;

    /** 第一段时间 */
    @Excel(name = "第一段时间")
    @TableField("part_minute1")
    private Integer partMinute1;

    /** 第一段价格 */
    @Excel(name = "第一段价格")
    @TableField("part_price1")
    private BigDecimal partPrice1;

    /** 递增时间 */
    @Excel(name = "递增时间")
    @TableField("next_minute")
    private Integer nextMinute;

    /** 递增价格 */
    @Excel(name = "递增价格")
    @TableField("next_price")
    private BigDecimal nextPrice;

    @TableField("remark")
    private String remark;



}
