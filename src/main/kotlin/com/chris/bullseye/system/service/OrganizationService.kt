package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.mapper.OrganizationMapper
import com.chris.bullseye.system.pojo.Organization
import org.springframework.stereotype.Service

/**
 * @author Chris
 * @date 2020-12-11 17:15
 */

@Service
class OrganizationService(var organizationMapper: OrganizationMapper):BaseService<Organization>() {

    override fun getMapper(): MPBaseMapper<Organization> {
        return organizationMapper
    }
}