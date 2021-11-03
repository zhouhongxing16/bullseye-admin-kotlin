drop procedure if exists createAccount;
delimiter $$
create procedure createAccount() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_dictionary_data') then

        CREATE TABLE `b_dictionary_data`
        (
            `id`           VARCHAR(40) NOT NULL COMMENT '唯一标识' COLLATE 'utf8_general_ci',
            `type_id`      VARCHAR(40) NULL DEFAULT NULL COMMENT '类型标识' COLLATE 'utf8_general_ci',
            `code`         VARCHAR(20) NULL DEFAULT NULL COMMENT '字典编码' COLLATE 'utf8_general_ci',
            `name`         VARCHAR(100) NULL DEFAULT NULL COMMENT '字典名称' COLLATE 'utf8_general_ci',
            `remark`       VARCHAR(200) NULL DEFAULT NULL COMMENT '字典备注' COLLATE 'utf8_general_ci',
            `sort`         INT(11) NULL DEFAULT NULL COMMENT '排序',
            `status`       INT(11) NULL DEFAULT '0' COMMENT '状态',
            `create_time`  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
            `creator_id`   VARCHAR(40) NULL DEFAULT NULL COMMENT '创建人' COLLATE 'utf8_general_ci',
            `creator_name` VARCHAR(40) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
            PRIMARY KEY (`id`) USING BTREE
        ) COMMENT='字典数据'
        COLLATE='utf8_general_ci'
        ENGINE=InnoDB;


end if;
end $$
delimiter ;
call createAccount();
drop procedure if exists createAccount;


