drop procedure if exists createRoleMenuFunction;
delimiter $$
create procedure createRoleMenuFunction() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_role_menu_function') then
        CREATE TABLE `b_role_menu_function`
        (
            `id`               VARCHAR(40) NOT NULL COMMENT '唯一标识' COLLATE 'utf8_general_ci',
            `role_id`          VARCHAR(40) NULL DEFAULT NULL COMMENT '角色ID' COLLATE 'utf8_general_ci',
            `menu_function_id` VARCHAR(40) NULL DEFAULT NULL COMMENT '授权路径ID' COLLATE 'utf8_general_ci',
            `creator_id`       VARCHAR(40) NULL DEFAULT NULL COMMENT '操作人' COLLATE 'utf8_general_ci',
            `creator_name`     VARCHAR(40) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
            `status`           INT(11) NULL DEFAULT '0' COMMENT '状态',
            `create_time`      DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
            PRIMARY KEY (`id`) USING BTREE
        ) COMMENT='角色菜单权限'
        COLLATE='utf8_general_ci'
        ENGINE=InnoDB;


end if;
end $$
delimiter ;
call createRoleMenuFunction();
drop procedure if exists createRoleMenuFunction;


