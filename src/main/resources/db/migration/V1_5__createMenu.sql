drop procedure if exists createMenu;
delimiter $$
create procedure createMenu() begin
    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_menu') then

    CREATE TABLE `b_menu`
    (
        `id`           VARCHAR(40) NOT NULL COMMENT '唯一标识' COLLATE 'utf8_general_ci',
        `parent_id`    VARCHAR(40) NULL DEFAULT NULL COMMENT '父菜单id' COLLATE 'utf8_general_ci',
        `code`         VARCHAR(40) NULL DEFAULT NULL COMMENT '代码' COLLATE 'utf8_general_ci',
        `title`        VARCHAR(50) NULL DEFAULT NULL COMMENT '名称' COLLATE 'utf8_general_ci',
        `icon`         VARCHAR(50) NULL DEFAULT NULL COMMENT '图标' COLLATE 'utf8_general_ci',
        `path`         VARCHAR(255) NULL DEFAULT NULL COMMENT '路径' COLLATE 'utf8_general_ci',
        `creator_id`   VARCHAR(40) NULL DEFAULT NULL COMMENT '创建人' COLLATE 'utf8_general_ci',
        `creator_name` VARCHAR(40) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
        `status`       INT(2) NULL DEFAULT NULL COMMENT '状态',
        `sort`         INT(11) NULL DEFAULT '0' COMMENT '显示顺序',
        `create_time`  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '               ',
        PRIMARY KEY (`id`) USING BTREE
    ) COMMENT='菜单表'
    COLLATE='utf8_general_ci'
    ENGINE=InnoDB;

end if;
end $$
delimiter ;
call createMenu();
drop procedure if exists createMenu;


