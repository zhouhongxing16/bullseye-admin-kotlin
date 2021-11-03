drop procedure if exists createMenu;
delimiter $$
create procedure createMenu() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_menu_function') then
        CREATE TABLE `b_menu_function`
        (
            `id`          VARCHAR(40) NOT NULL COMMENT 'id' COLLATE 'utf8_general_ci',
            `menu_id`     VARCHAR(40) NULL DEFAULT NULL COMMENT '菜单id' COLLATE 'utf8_general_ci',
            `code`        VARCHAR(50) NULL DEFAULT NULL COMMENT '编码' COLLATE 'utf8_general_ci',
            `name`        VARCHAR(50) NULL DEFAULT NULL COMMENT '名称' COLLATE 'utf8_general_ci',
            `path`        VARCHAR(50) NULL DEFAULT NULL COMMENT '路径' COLLATE 'utf8_general_ci',
            `status`      INT(11) NULL DEFAULT '0' COMMENT '状态',
            `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
            PRIMARY KEY (`id`) USING BTREE
        ) COMMENT='菜单权限'
            COLLATE='utf8_general_ci'
            ENGINE=InnoDB;




end if;
end $$
delimiter ;
call createMenu();
drop procedure if exists createMenu;


