drop procedure if exists createStaff;
delimiter $$
create procedure createStaff() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_staff') then
        CREATE TABLE `b_staff`
        (
            `id`               VARCHAR(40) NOT NULL COMMENT 'ID' COLLATE 'utf8_general_ci',
            `serial_no`        VARCHAR(20) NULL DEFAULT NULL COMMENT '工号' COLLATE 'utf8_general_ci',
            `name`             VARCHAR(20) NULL DEFAULT NULL COMMENT '姓名' COLLATE 'utf8_general_ci',
            `gender_id`        VARCHAR(40) NULL DEFAULT NULL COMMENT '性别' COLLATE 'utf8_general_ci',
            `mobile`           VARCHAR(20) NULL DEFAULT NULL COMMENT '电话号码' COLLATE 'utf8_general_ci',
            `email`            VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱' COLLATE 'utf8_general_ci',
            `organization_id`  VARCHAR(40) NULL DEFAULT NULL COMMENT '所属组织' COLLATE 'utf8_general_ci',
            `department_id`    VARCHAR(40) NULL DEFAULT NULL COMMENT '所属部门' COLLATE 'utf8_general_ci',
            `major_id`         VARCHAR(40) NULL DEFAULT NULL COMMENT '所属专业' COLLATE 'utf8_general_ci',
            `birthday`         VARCHAR(20) NULL DEFAULT NULL COMMENT '生日' COLLATE 'utf8_general_ci',
            `academic_id`      VARCHAR(40) NULL DEFAULT NULL COMMENT '学位' COLLATE 'utf8_general_ci',
            `degree_id`        VARCHAR(40) NULL DEFAULT NULL COMMENT '学历' COLLATE 'utf8_general_ci',
            `position_id`      VARCHAR(40) NULL DEFAULT NULL COMMENT '职位' COLLATE 'utf8_general_ci',
            `title_id`         VARCHAR(40) NULL DEFAULT NULL COMMENT '职称' COLLATE 'utf8_general_ci',
            `type_id`          VARCHAR(40) NULL DEFAULT NULL COMMENT '人员类型' COLLATE 'utf8_general_ci',
            `identify_type_id` VARCHAR(40) NULL DEFAULT NULL COMMENT '证件类型' COLLATE 'utf8_general_ci',
            `identify_no`      VARCHAR(20) NULL DEFAULT NULL COMMENT '证件号码' COLLATE 'utf8_general_ci',
            `province_id`      VARCHAR(40) NULL DEFAULT NULL COMMENT '所属省份' COLLATE 'utf8_general_ci',
            `city_id`          VARCHAR(40) NULL DEFAULT NULL COMMENT '所属城市' COLLATE 'utf8_general_ci',
            `policy`           VARCHAR(40) NULL DEFAULT NULL COMMENT '政治面貌' COLLATE 'utf8_general_ci',
            `nation_id`        VARCHAR(40) NULL DEFAULT NULL COMMENT '民族' COLLATE 'utf8_general_ci',
            `join_date`        VARCHAR(20) NULL DEFAULT NULL COMMENT '入职日期' COLLATE 'utf8_general_ci',
            `remark`           VARCHAR(200) NULL DEFAULT NULL COMMENT '备注' COLLATE 'utf8_general_ci',
            `creator_id`       VARCHAR(40) NULL DEFAULT NULL COMMENT '创建人' COLLATE 'utf8_general_ci',
            `creator_name`     VARCHAR(40) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
            `img`              VARCHAR(200) NULL DEFAULT NULL COMMENT '照片' COLLATE 'utf8_general_ci',
            `status`           INT(11) NULL DEFAULT '0' COMMENT '状态',
            `create_time`      TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
            PRIMARY KEY (`id`) USING BTREE
        ) COMMENT='员工信息'
        COLLATE='utf8_general_ci'
        ENGINE=InnoDB;



end if;
end $$
delimiter ;
call createStaff();
drop procedure if exists createStaff;


