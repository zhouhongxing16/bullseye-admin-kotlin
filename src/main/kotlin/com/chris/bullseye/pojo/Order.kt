package com.chris.bullseye.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-21 13:54
 * 订单
 */
@Table(name = "kb_orders")
open class Order (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "订单号")
    @Column(name = "order_no")
    var orderNo: String? = null,

    @ApiModelProperty(name = "订单金额")
    @Column(name = "total")
    var total: Int? = null,

    @ApiModelProperty(name = "1、升级会员；2、购买课程、3、购买视频、4、充值")
    @Column(name = "type_id")
    var typeId: String? = null,

    @ApiModelProperty(name = "支付宝、微信")
    @Column(name = "pay_type_id")
    var payTypeId: String? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "支付时间")
    @Column(name = "pay_date")
    var payDate: Date? = null,

    @ApiModelProperty(name = "交易单号")
    @Column(name = "trade_no")
    var tradeNo: String? = null,

    @ApiModelProperty(name = "交易金额")
    @Column(name = "trade_amount")
    var tradeAmount: Int? = null,

    @ApiModelProperty(name = "交易数量")
    @Column(name = "trade_num")
    var tradeNum: Int? = null,

    @ApiModelProperty(name = "创建人")
    @Column(name = "user_id")
    var userId: String? = null,

    @ApiModelProperty(name = "备注")
    @Column(name = "remark")
    var remark: String? = null,

    @ApiModelProperty(name = "订单状态")
    @Column(name = "status")
    var status: Int? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "创建日期")
    @Column(name = "created")
    var created: Date? = null,

    

)