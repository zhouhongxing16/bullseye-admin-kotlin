drop procedure if exists createAccount;
delimiter $$
create procedure createAccount() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_account') then

        CREATE TABLE `b_account`
        (
            `id`                VARCHAR(40) NOT NULL COMMENT '唯一标识' COLLATE 'utf8_general_ci',
            `name`              VARCHAR(50) NULL DEFAULT NULL COMMENT '姓名' COLLATE 'utf8_general_ci',
            `username`          VARCHAR(50) NULL DEFAULT NULL COMMENT '账户' COLLATE 'utf8_general_ci',
            `nick_name`         VARCHAR(50) NULL DEFAULT NULL COMMENT '昵称' COLLATE 'utf8_general_ci',
            `password`          VARCHAR(128) NULL DEFAULT NULL COMMENT '密码' COLLATE 'utf8_general_ci',
            `email`             VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱' COLLATE 'utf8_general_ci',
            `mobile`            VARCHAR(20) NULL DEFAULT NULL COMMENT '手机号码' COLLATE 'utf8_general_ci',
            `mobile_login_flag` TINYINT(1) NULL DEFAULT NULL COMMENT '是否开通手机号登录',
            `account_locked`    TINYINT(1) NULL DEFAULT NULL COMMENT '是否锁定',
            `account_expired`   TINYINT(1) NULL DEFAULT NULL COMMENT '是否过期',
            `type_flag`         TINYINT(1) NULL DEFAULT NULL COMMENT '0:个人，1：机构',
            `level_id`          VARCHAR(40) NULL DEFAULT NULL COMMENT '账号等级标识' COLLATE 'utf8_general_ci',
            `staff_id`          VARCHAR(40) NULL DEFAULT NULL COMMENT '人员标识' COLLATE 'utf8_general_ci',
            `organization_id`   VARCHAR(40) NULL DEFAULT NULL COMMENT '组织标识' COLLATE 'utf8_general_ci',
            `wx_openid`         VARCHAR(64) NULL DEFAULT NULL COMMENT '微信OpenId' COLLATE 'utf8_general_ci',
            `alipay_openid`     VARCHAR(64) NULL DEFAULT NULL COMMENT '支付宝OpenId' COLLATE 'utf8_general_ci',
            `avatar`            VARCHAR(255) NULL DEFAULT NULL COMMENT '头像' COLLATE 'utf8_general_ci',
            `status`            INT(11) NULL DEFAULT '0' COMMENT '状态',
            `remark`            VARCHAR(200) NULL DEFAULT NULL COMMENT '备注' COLLATE 'utf8_general_ci',
            `creator_id`        VARCHAR(40) NULL DEFAULT NULL COMMENT '创建人ID' COLLATE 'utf8_general_ci',
            `creator_name`      VARCHAR(40) NULL DEFAULT NULL COMMENT '创建人姓名' COLLATE 'utf8_general_ci',
            `update_time`       DATETIME NULL DEFAULT NULL COMMENT '修改时间',
            `expired_date`      DATETIME NULL DEFAULT NULL COMMENT '账号过期时间',
            `create_time`       DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
            PRIMARY KEY (`id`) USING BTREE
        ) COMMENT='账号'
        COLLATE='utf8_general_ci'
        ENGINE=InnoDB;

end if;
end $$
delimiter ;
call createAccount();
drop procedure if exists createAccount;


