
drop procedure if exists createOrganization;
delimiter $$
create procedure createOrganization() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'kb_organizations') then

    /*==============================================================*/
    /* Table: kb_organizations                                      */
    /*==============================================================*/
    create table kb_organizations
    (
        id                   varchar(40) not null comment '唯一标识',
        logo                 varchar(300) comment 'LOGO',
        code                 varchar(30) comment '代码',
        name                 varchar(100) comment '名称',
        contact_name         varchar(200) comment '联系人',
        contact_phone        varchar(200) comment '联系电话',
        brief                text comment '简介',
        user_id              varchar(40) comment '创建人',
        status               int(11) comment '状态',
        created              timestamp not null default CURRENT_TIMESTAMP comment '创建日期',
        primary key (id)
    )
        ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织表';


end if;
end $$
delimiter ;
call createOrganization();
drop procedure if exists createOrganization;


