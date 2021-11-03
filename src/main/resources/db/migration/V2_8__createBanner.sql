drop procedure if exists createBanner;
delimiter $$
create procedure createBanner() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_banner') then

        CREATE TABLE `b_banner` (
                                    `id` VARCHAR(40) NOT NULL COMMENT '唯一标识' COLLATE 'utf8_general_ci',
                                    `name` VARCHAR(50) NULL DEFAULT NULL COMMENT '名称' COLLATE 'utf8_general_ci',
                                    `path` VARCHAR(255) NULL DEFAULT NULL COMMENT '图片地址' COLLATE 'utf8_general_ci',
                                    `link` VARCHAR(255) NULL DEFAULT NULL COMMENT '跳转地址' COLLATE 'utf8_general_ci',
                                    `sort` INT(11) NULL DEFAULT '1' COMMENT '显示顺序',
                                    `status` INT(11) NULL DEFAULT '0' COMMENT '状态',
                                    `creator_id` VARCHAR(40) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                                    `creator_name` CHAR(10) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
                                    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    PRIMARY KEY (`id`) USING BTREE
        )
            COMMENT='轮播图'
        COLLATE='utf8_general_ci'
        ENGINE=InnoDB;


end if;
end $$
delimiter ;
call createBanner();
drop procedure if exists createBanner;


