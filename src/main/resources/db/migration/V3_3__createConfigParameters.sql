drop procedure if exists createConfigParameters;
delimiter $$
create procedure createConfigParameters() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'kb_config_parameters') then
        /*==============================================================*/
        /* Table: kb_config_parameters                                  */
        /*==============================================================*/
        create table kb_config_parameters
        (
            id                   varchar(40) not null comment '唯一标识',
            data_id              varchar(40) comment '播放设置、富文本设置、课程设置、安全设置',
            code                 varchar(50) comment '编码',
            name                 varchar(50) comment '名称',
            display_type         int default 0 comment '单选、多选、文本输入',
            default_value        varchar(50) comment '默认值',
            config_value         varchar(50) comment '设置值',
            status               int default 0 comment '状态',
            created              timestamp default CURRENT_TIMESTAMP comment '创建时间',
            primary key (id)
        );

        alter table kb_config_parameters comment '系统参数';

        alter table kb_config_parameters add constraint FK_Reference_19 foreign key (data_id)
            references kb_dictionary_data (id) on delete restrict on update restrict;


end if;
end $$
delimiter ;
call createConfigParameters();
drop procedure if exists createConfigParameters;


