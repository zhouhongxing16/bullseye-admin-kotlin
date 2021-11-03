drop procedure if exists createNavigation;
delimiter $$
create procedure createNavigation() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_navigation') then

        CREATE TABLE `b_navigation`
        (
            `id`           VARCHAR(40) NOT NULL COMMENT '唯一标识' COLLATE 'utf8_general_ci',
            `name`         VARCHAR(50) NULL DEFAULT NULL COMMENT '名称' COLLATE 'utf8_general_ci',
            `level`        INT(11) NULL DEFAULT '1' COMMENT '级别',
            `parent_id`    VARCHAR(40) NULL DEFAULT NULL COMMENT '父标识' COLLATE 'utf8_general_ci',
            `sort`         INT(11) NULL DEFAULT '1' COMMENT '显示顺序',
            `path`         VARCHAR(100) NULL DEFAULT NULL COMMENT '访问路径' COLLATE 'utf8_general_ci',
            `status`       INT(11) NULL DEFAULT '0' COMMENT '状态',
            `create_time`  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
            `creator_id`   VARCHAR(40) NULL DEFAULT NULL COMMENT '创建人' COLLATE 'utf8_general_ci',
            `creator_name` VARCHAR(40) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
            PRIMARY KEY (`id`) USING BTREE
        ) COMMENT='导航栏'
        COLLATE='utf8_general_ci'
        ENGINE=InnoDB;


end if;
end $$
delimiter ;
call createNavigation();
drop procedure if exists createNavigation;


