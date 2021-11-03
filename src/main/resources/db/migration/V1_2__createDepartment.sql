drop procedure if exists createDepartment;
delimiter $$
create procedure createDepartment() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_department') then

    CREATE TABLE `b_department`
    (
        `id`              VARCHAR(40) NOT NULL COMMENT '唯一标识' COLLATE 'utf8_general_ci',
        `organization_id` VARCHAR(40) NULL DEFAULT NULL COMMENT '组织标识' COLLATE 'utf8_general_ci',
        `code`            VARCHAR(30) NULL DEFAULT NULL COMMENT '代码' COLLATE 'utf8_general_ci',
        `parent_id`       VARCHAR(40) NULL DEFAULT NULL COMMENT '父级ID' COLLATE 'utf8_general_ci',
        `name`            VARCHAR(100) NULL DEFAULT NULL COMMENT '名称' COLLATE 'utf8_general_ci',
        `type_id`         VARCHAR(40) NULL DEFAULT NULL COMMENT '类型' COLLATE 'utf8_general_ci',
        `contact_name`    VARCHAR(200) NULL DEFAULT NULL COMMENT '联系人' COLLATE 'utf8_general_ci',
        `contact_phone`   VARCHAR(200) NULL DEFAULT NULL COMMENT '联系电话' COLLATE 'utf8_general_ci',
        `creator_id`      VARCHAR(40) NULL DEFAULT NULL COMMENT '创建人' COLLATE 'utf8_general_ci',
        `creator_name`    VARCHAR(40) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
        `status`          INT(11) NULL DEFAULT '0' COMMENT '状态',
        `create_time`     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
        `remark`          VARCHAR(255) NULL DEFAULT NULL COMMENT '描述' COLLATE 'utf8_general_ci',
        PRIMARY KEY (`id`) USING BTREE
    ) COMMENT='科室/部门'
    COLLATE='utf8_general_ci'
    ENGINE=InnoDB;


end if;
end $$
delimiter ;
call createDepartment();
drop procedure if exists createDepartment;


