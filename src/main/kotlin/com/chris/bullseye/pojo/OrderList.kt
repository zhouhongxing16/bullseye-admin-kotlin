package com.chris.bullseye.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 18:10
 * 订单详情
 */
@Table(name = "kb_order_list")
open class OrderList (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "订单标识")
    @Column(name = "order_id")
    var orderId: String? = null,

    @ApiModelProperty(name = "商品ID")
    @Column(name = "goods_id")
    var goodsId: String? = null,

    @ApiModelProperty(name = "商品名称")
    @Column(name = "goods_name")
    var goodsName: String? = null,

    @ApiModelProperty(name = "商品图片")
    @Column(name = "goods_img")
    var goodsImg: String? = null,

    @ApiModelProperty(name = "商品类型")
    @Column(name = "goods_type")
    var goodsType: String? = null,

    @ApiModelProperty(name = "单价")
    @Column(name = "price")
    var price: Int? = null,

    @ApiModelProperty(name = "数量")
    @Column(name = "nums")
    var nums: Int? = null,

    @ApiModelProperty(name = "总价")
    @Column(name = "total")
    var total: Int? = null,

    @ApiModelProperty(name = "支付金额")
    @Column(name = "pay_total")
    var payTotal: Int? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "创建时间")
    @Column(name = "created")
    var created: Date? = null,

    

)