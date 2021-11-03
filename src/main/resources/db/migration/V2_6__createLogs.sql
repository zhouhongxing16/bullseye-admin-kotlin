drop procedure if exists createLogs;
delimiter $$
create procedure createLogs() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_logs') then

        CREATE TABLE `b_logs`
        (
            `id`              VARCHAR(40) NOT NULL COMMENT 'ID' COLLATE 'utf8_general_ci',
            `organization_id` VARCHAR(40) NULL DEFAULT NULL COMMENT '所属组织' COLLATE 'utf8_general_ci',
            `option_name`     VARCHAR(50) NULL DEFAULT NULL COMMENT '操作类' COLLATE 'utf8_general_ci',
            `option_type`     VARCHAR(20) NULL DEFAULT NULL COMMENT '操作方法名' COLLATE 'utf8_general_ci',
            `method`          VARCHAR(200) NULL DEFAULT NULL COMMENT '操作方法' COLLATE 'utf8_general_ci',
            `params`          TEXT NULL DEFAULT NULL COMMENT '参数' COLLATE 'utf8_general_ci',
            `creator_id`      VARCHAR(40) NULL DEFAULT NULL COMMENT '操作人' COLLATE 'utf8_general_ci',
            `creator_name`    VARCHAR(40) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
            `ip`              VARCHAR(50) NULL DEFAULT NULL COMMENT 'IP' COLLATE 'utf8_general_ci',
            `execution_time`  INT(11) NULL DEFAULT '0' COMMENT '执行时长',
            `status`          INT(11) NULL DEFAULT '0' COMMENT '状态',
            `remark`          VARCHAR(200) NULL DEFAULT NULL COMMENT '备注' COLLATE 'utf8_general_ci',
            `create_time`     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
            PRIMARY KEY (`id`) USING BTREE
        ) COMMENT='操作日志'
        COLLATE='utf8_general_ci'
        ENGINE=InnoDB;



end if;
end $$
delimiter ;
call createLogs();
drop procedure if exists createLogs;


