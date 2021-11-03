drop procedure if exists createLoginRecord;
delimiter $$
create procedure createLoginRecord() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_login_record') then

        CREATE TABLE `b_login_record`
        (
            `id`             VARCHAR(40) NOT NULL COMMENT '访问ID' COLLATE 'utf8_general_ci',
            `username`       VARCHAR(40) NULL DEFAULT NULL COMMENT '登录账号' COLLATE 'utf8_general_ci',
            `ip`             VARCHAR(50) NULL DEFAULT NULL COMMENT '登录IP地址' COLLATE 'utf8_general_ci',
            `login_location` VARCHAR(255) NULL DEFAULT NULL COMMENT '登录地点' COLLATE 'utf8_general_ci',
            `browser`        VARCHAR(50) NULL DEFAULT NULL COMMENT '浏览器类型' COLLATE 'utf8_general_ci',
            `os`             VARCHAR(50) NULL DEFAULT NULL COMMENT '操作系统' COLLATE 'utf8_general_ci',
            `status`         INT(11) NULL DEFAULT '0' COMMENT '登录状态（1成功 0失败）',
            `message`        VARCHAR(255) NULL DEFAULT NULL COMMENT '提示消息' COLLATE 'utf8_general_ci',
            `create_time`    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '访问时间',
            PRIMARY KEY (`id`) USING BTREE
        ) COMMENT='系统访问记录'
        COLLATE='utf8_general_ci'
        ENGINE=InnoDB;

end if;
end $$
delimiter ;
call createLoginRecord();
drop procedure if exists createLoginRecord;


