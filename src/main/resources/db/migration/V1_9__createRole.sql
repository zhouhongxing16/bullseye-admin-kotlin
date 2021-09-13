drop procedure if exists createRole;
delimiter $$
create procedure createRole() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'kb_roles') then
        /*==============================================================*/
        /* Table: kb_roles                                              */
        /*==============================================================*/
        create table kb_roles
        (
            id                   varchar(40) not null comment '唯一标识',
            code                 varchar(50) comment '角色代码',
            name                 varchar(50) comment '角色名称',
            data_auth_flag       varchar(30) default 'personal' comment '个人（pesonal），部门（department）,组织（organization）,系统（system）',
            remark               varchar(100) comment '角色描述',
            user_id              varchar(50) comment '创建人',
            status               int comment '状态',
            created              timestamp not null default CURRENT_TIMESTAMP comment '创建日期',
            primary key (id)
        )
            ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
    end if;
end $$
delimiter ;
call createRole();
drop procedure if exists createRole;


