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
 * 故障单对象 kmz_bad_order
 * 
 * @author kmz
 * @date 2024-08-23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("kmz_bad_order")
public class KmzBadOrder
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

    /** 车辆编号 */
    @Excel(name = "车辆编号")
    @TableField("bike_no")
    private String bikeNo;

    /** 车辆类型 */
    @Excel(name = "车辆类型")
    @TableField("bike_type")
    private Integer bikeType;

    /** 故障地点 */
    @Excel(name = "故障地点")
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


    @TableField("remark")
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

}
