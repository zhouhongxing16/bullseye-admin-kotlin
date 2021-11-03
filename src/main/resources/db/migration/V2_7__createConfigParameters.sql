drop procedure if exists createConfigParameters;
delimiter $$
create procedure createConfigParameters() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_config_parameter') then

        CREATE TABLE `b_config_parameter`
        (
            `id`            VARCHAR(40) NOT NULL COMMENT '唯一标识' COLLATE 'utf8_general_ci',
            `data_id`       VARCHAR(40) NULL DEFAULT NULL COMMENT '播放设置、富文本设置、课程设置、安全设置' COLLATE 'utf8_general_ci',
            `code`          VARCHAR(50) NULL DEFAULT NULL COMMENT '编码' COLLATE 'utf8_general_ci',
            `name`          VARCHAR(50) NULL DEFAULT NULL COMMENT '名称' COLLATE 'utf8_general_ci',
            `display_type`  INT(11) NULL DEFAULT '0' COMMENT '单选、多选、文本输入',
            `default_value` VARCHAR(50) NULL DEFAULT NULL COMMENT '默认值' COLLATE 'utf8_general_ci',
            `config_value`  VARCHAR(50) NULL DEFAULT NULL COMMENT '设置值' COLLATE 'utf8_general_ci',
            `status`        INT(11) NULL DEFAULT '0' COMMENT '状态',
            `create_time`   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
            PRIMARY KEY (`id`) USING BTREE
        ) COMMENT='系统参数'
        COLLATE='utf8_general_ci'
        ENGINE=InnoDB;




end if;
end $$
delimiter ;
call createConfigParameters();
drop procedure if exists createConfigParameters;


