package com.chris.bullseye.service

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.mapper.OrganizationMapper
import com.chris.bullseye.pojo.Organization
import org.springframework.stereotype.Service

/**
 * @author Chris
 * @date 2020-12-11 17:15
 */

@Service
class OrganizationService(var organizationMapper: OrganizationMapper):BaseService<Organization>() {

    override fun getMapper(): BaseMapper<Organization> {
        return organizationMapper
    }
}