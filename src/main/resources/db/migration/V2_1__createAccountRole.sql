drop procedure if exists createAccount;
delimiter $$
create procedure createAccount() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_account_role') then

        CREATE TABLE `b_account_role`
        (
            `id`           VARCHAR(40) NOT NULL COMMENT '唯一标识' COLLATE 'utf8_general_ci',
            `role_id`      VARCHAR(40) NULL DEFAULT NULL COMMENT '角色外键' COLLATE 'utf8_general_ci',
            `account_id`   VARCHAR(40) NULL DEFAULT NULL COMMENT '用户外键' COLLATE 'utf8_general_ci',
            `status`       INT(2) NULL DEFAULT NULL COMMENT '状态',
            `creator_id`   VARCHAR(40) NULL DEFAULT NULL COMMENT '创建人' COLLATE 'utf8_general_ci',
            `creator_name` VARCHAR(40) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
            `create_time`  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
            PRIMARY KEY (`id`) USING BTREE
        ) COMMENT='账号角色'
        COLLATE='utf8_general_ci'
        ENGINE=InnoDB;

end if;
end $$
delimiter ;
call createAccount();
drop procedure if exists createAccount;


