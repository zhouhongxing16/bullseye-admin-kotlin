package com.chris.bullseye.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.pojo.Order
import org.apache.ibatis.annotations.Mapper
import java.util.Map

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-21 13:54
 */
@Mapper
interface OrderMapper: BaseMapper<Order> {
}