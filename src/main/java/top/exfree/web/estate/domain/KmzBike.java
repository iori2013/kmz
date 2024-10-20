package top.exfree.web.estate.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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

import java.util.Date;

/**
 * 车辆列对象 kmz_bike
 * 
 * @author kmz
 * @date 2024-08-23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("kmz_bike")
public class KmzBike
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    @TableField("no")
    private String no;

    /** 类型 */
    @Excel(name = "类型")
    @TableField("type")
    private Integer type;

    /** 品牌 */
    @Excel(name = "品牌")
    @TableField("brand")
    private String brand;

    /** 车牌号 */
    @Excel(name = "车牌号")
    @TableField("bike_no")
    private String bikeNo;

    /** 0正常1使用2故障 */
    @Excel(name = "0正常1使用2故障")
    @TableField("state")
    private Integer state;

    /** 价格id */
    @Excel(name = "价格id")
    @TableField("price_id")
    private Long priceId;

    /** 地址 */
    @Excel(name = "地址")
    @TableField("address")
    private String address;

    /** 区域Id */
    @Excel(name = "区域Id")
    @TableField("area_id")
    private Long areaId;

    /** 区域名称 */
    @Excel(name = "区域名称")
    @TableField("area_name")
    private String areaName;

    /** 当前订单号 */
    @Excel(name = "当前订单号")
    @TableField("order_no")
    private String orderNo;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    @TableField("create_time")
    private Date createTime;


}
