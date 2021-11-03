drop procedure if exists createRoleMenu;
delimiter $$
create procedure createRoleMenu() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_role_menu') then

        CREATE TABLE `b_role_menu`
        (
            `id`          VARCHAR(40) NOT NULL COMMENT '唯一标识' COLLATE 'utf8_general_ci',
            `role_id`     VARCHAR(40) NULL DEFAULT NULL COMMENT '角色外键' COLLATE 'utf8_general_ci',
            `menu_id`     VARCHAR(40) NOT NULL COMMENT '菜单外键' COLLATE 'utf8_general_ci',
            `status`      INT(11) NULL DEFAULT '0' COMMENT '状态',
            `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
            PRIMARY KEY (`id`) USING BTREE
        ) COMMENT='角色菜单表'
        COLLATE='utf8_general_ci'
        ENGINE=InnoDB;

end if;
end $$
delimiter ;
call createRoleMenu();
drop procedure if exists createRoleMenu;


