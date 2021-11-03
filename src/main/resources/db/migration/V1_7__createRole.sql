drop procedure if exists createRole;
delimiter $$
create procedure createRole() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_role') then
        CREATE TABLE `b_role`
        (
            `id`             VARCHAR(40) NOT NULL COMMENT '唯一标识' COLLATE 'utf8_general_ci',
            `code`           VARCHAR(50) NULL DEFAULT NULL COMMENT '角色代码' COLLATE 'utf8_general_ci',
            `name`           VARCHAR(50) NULL DEFAULT NULL COMMENT '角色名称' COLLATE 'utf8_general_ci',
            `data_auth_flag` VARCHAR(30) NULL DEFAULT 'personal' COMMENT '个人（pesonal），部门（department）,组织（organization）,系统（system）' COLLATE 'utf8_general_ci',
            `remark`         VARCHAR(100) NULL DEFAULT NULL COMMENT '角色描述' COLLATE 'utf8_general_ci',
            `creator_id`     VARCHAR(50) NULL DEFAULT NULL COMMENT '创建人' COLLATE 'utf8_general_ci',
            `creator_name`   VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
            `status`         INT(11) NULL DEFAULT NULL COMMENT '状态',
            `create_time`    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
            PRIMARY KEY (`id`) USING BTREE
        ) COMMENT='角色表'
        COLLATE='utf8_general_ci'
        ENGINE=InnoDB;

end if;
end $$
delimiter ;
call createRole();
drop procedure if exists createRole;


