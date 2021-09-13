drop procedure if exists createLogs;
delimiter $$
create procedure createLogs() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'kb_logs') then

        /*==============================================================*/
        /* Table: kb_logs                                               */
        /*==============================================================*/
        create table kb_logs
        (
            id                   varchar(40) not null comment 'ID',
            organization_id      varchar(40) comment '所属组织',
            option_name          varchar(50) comment '操作类',
            option_type          varchar(20) comment '操作方法名',
            method               varchar(200) comment '操作方法',
            params               text comment '参数',
            user_id              varchar(40) comment '操作人',
            ip                   varchar(50) comment 'IP',
            execution_time       int default 0 comment '执行时长',
            status               int default 0 comment '状态',
            remark               varchar(200) comment '备注',
            created              timestamp not null default CURRENT_TIMESTAMP comment '操作时间',
            primary key (id)
        )
            ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';



end if;
end $$
delimiter ;
call createLogs();
drop procedure if exists createLogs;


