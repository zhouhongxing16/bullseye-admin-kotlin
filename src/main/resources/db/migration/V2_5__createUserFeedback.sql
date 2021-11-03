drop procedure if exists createUserFeedback;
delimiter $$
create procedure createUserFeedback() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_user_feedback') then

    CREATE TABLE `b_user_feedback`
    (
        `id`            VARCHAR(40) NOT NULL COMMENT '唯一标识' COLLATE 'utf8_general_ci',
        `contact_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '联系人' COLLATE 'utf8_general_ci',
        `contact_phone` VARCHAR(20) NULL DEFAULT NULL COMMENT '联系电话' COLLATE 'utf8_general_ci',
        `content`       TEXT NULL DEFAULT NULL COMMENT '反馈内容' COLLATE 'utf8_general_ci',
        `attach`        VARCHAR(255) NULL DEFAULT NULL COMMENT '附件' COLLATE 'utf8_general_ci',
        `create_time`   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
        `status`        INT(11) NULL DEFAULT '0' COMMENT '状态',
        `creator_id`    VARCHAR(40) NULL DEFAULT NULL COMMENT '用户标识' COLLATE 'utf8_general_ci',
        `creator_name`  VARCHAR(40) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
        PRIMARY KEY (`id`) USING BTREE
    ) COMMENT='用户反馈'
    COLLATE='utf8_general_ci'
    ENGINE=InnoDB;

end if;
end $$
delimiter ;
call createUserFeedback();
drop procedure if exists createUserFeedback;


