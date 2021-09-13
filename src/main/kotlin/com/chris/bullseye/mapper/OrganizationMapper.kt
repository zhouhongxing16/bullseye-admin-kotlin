package com.chris.bullseye.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.pojo.Organization
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date 2020-12-11 17:13
 */
@Mapper
interface OrganizationMapper:BaseMapper<Organization> {
}