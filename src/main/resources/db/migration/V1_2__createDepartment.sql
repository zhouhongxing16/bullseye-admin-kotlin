drop procedure if exists createDepartment;
delimiter $$
create procedure createDepartment()
begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_department') then

    /*==============================================================*/
    /* Table: b_department                                        */
    /*==============================================================*/
    create table b_department
    (
        id              varchar(40) not null comment '唯一标识',
        organization_id varchar(40) comment '组织标识',
        code            varchar(30) comment '代码',
        parent_id       varchar(40) comment '父级ID',
        name            varchar(100) comment '名称',
        type_id         varchar(40) comment '类型',
        contact_name    varchar(200) comment '联系人',
        contact_phone   varchar(200) comment '联系电话',
        user_id         varchar(40) comment '创建人',
        status          int(11) default 0 comment '状态',
        created         timestamp   not null default CURRENT_TIMESTAMP comment '创建日期',
        remark          varchar(255) comment '描述',
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='科室/部门';



end if;
end $$
delimiter ;
call createDepartment();
drop procedure if exists createDepartment;


