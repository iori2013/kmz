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

import java.math.BigDecimal;
import java.util.Date;

/**
 * 车辆订单对象 kmz_order
 * 
 * @author kmz
 * @date 2024-08-23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("kmz_order")
public class KmzOrder
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    @TableField("order_sn")
    private String orderSn;

    /** 类型 */
    @Excel(name = "类型")
    @TableField("member_id")
    private Long memberId;

    /** 会员名称 */
    @Excel(name = "会员名称")
    @TableField("member_name")
    private String memberName;

    /** 会员手机号 */
    @Excel(name = "会员手机号")
    @TableField("member_mobile")
    private String memberMobile;

    /** 会员类型 */
    @Excel(name = "会员类型")
    @TableField("member_type")
    private Long memberType;

    /** 车辆id */
    @Excel(name = "车辆id")
    @TableField("bike_no")
    private String bikeNo;

    /** 车辆类型 */
    @Excel(name = "车辆类型")
    @TableField("bike_type")
    private Integer bikeType;

    /** 价格id */
    @Excel(name = "价格id")
    @TableField("price_id")
    private Long priceId;

    /** 价格备注 */
    @Excel(name = "价格备注")
    @TableField("price_remark")
    private String priceRemark;

    /** 订单价格 */
    @Excel(name = "订单价格")
    @TableField("order_price")
    private BigDecimal orderPrice;

    /** 区域Id */
    @Excel(name = "区域Id")
    @TableField("area_id")
    private Long areaId;

    /** 区域名称 */
    @Excel(name = "区域名称")
    @TableField("area_name")
    private String areaName;

    /** 出发时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "出发时间", width = 30, dateFormat = "yyyy-MM-dd")
    @TableField("start_time")
    private Date startTime;

    /** 出发地点 */
    @Excel(name = "出发地点")
    @TableField("start_place")
    private String startPlace;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    @TableField("back_time")
    private Date backTime;

    /** 还车地点 */
    @Excel(name = "还车地点")
    @TableField("back_place")
    private String backPlace;

    /** 0开始1已结束2已付款 */
    @Excel(name = "0开始1已结束2已付款")
    @TableField("state")
    private Integer state;




}
