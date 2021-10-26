package com.chris.bullseye.system.mapper

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.pojo.Organization
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date 2020-12-11 17:13
 */
@Mapper
interface OrganizationMapper:MPBaseMapper<Organization> {
}