drop procedure if exists createAccount;
delimiter $$
create procedure createAccount() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_dictionary_type') then

        CREATE TABLE `b_dictionary_type`
        (
            `id`           VARCHAR(40) NOT NULL COMMENT '唯一标识' COLLATE 'utf8_general_ci',
            `code`         VARCHAR(30) NULL DEFAULT NULL COMMENT '类型代码' COLLATE 'utf8_general_ci',
            `name`         VARCHAR(50) NULL DEFAULT NULL COMMENT '类型名称' COLLATE 'utf8_general_ci',
            `remark`       VARCHAR(200) NULL DEFAULT NULL COMMENT '备注' COLLATE 'utf8_general_ci',
            `status`       INT(11) NULL DEFAULT NULL COMMENT '状态',
            `created`      DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
            `creator_id`   VARCHAR(40) NULL DEFAULT NULL COMMENT '创建人' COLLATE 'utf8_general_ci',
            `creator_name` VARCHAR(40) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
            PRIMARY KEY (`id`) USING BTREE
        ) COMMENT='字典类型'
        COLLATE='utf8_general_ci'
        ENGINE=InnoDB;


end if;
end $$
delimiter ;
call createAccount();
drop procedure if exists createAccount;


