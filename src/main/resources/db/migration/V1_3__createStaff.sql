drop procedure if exists createStaff;
delimiter $$
create procedure createStaff() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_staff') then
        /*==============================================================*/
        /* Table: b_staff                                             */
        /*==============================================================*/
        create table b_staff
        (
            id                   varchar(40) not null comment 'ID',
            serial_no            varchar(20) comment '工号',
            name                 varchar(20) comment '姓名',
            gender_id            varchar(40) comment '性别',
            mobile               varchar(20) comment '电话号码',
            email                varchar(50) comment '邮箱',
            organization_id      varchar(40) comment '所属组织',
            department_id        varchar(40) comment '所属部门',
            major_id             varchar(40) comment '所属专业',
            birthday             varchar(20) comment '生日',
            academic_id          varchar(40) comment '学位',
            degree_id            varchar(40) comment '学历',
            position_id          varchar(40) comment '职位',
            title_id             varchar(40) comment '职称',
            type_id              varchar(40) comment '人员类型',
            identify_type_id     varchar(40) comment '证件类型',
            identify_no          varchar(20) comment '证件号码',
            province_id          varchar(40) comment '所属省份',
            city_id              varchar(40) comment '所属城市',
            policy               varchar(40) comment '政治面貌',
            nation_id            varchar(40) comment '民族',
            join_date            varchar(20) comment '入职日期',
            remark               varchar(200) comment '备注',
            user_id              varchar(40) comment '创建人',
            img                  varchar(200) comment '照片',
            status               int(11) default 0 comment '状态',
            created              timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
            primary key (id)
        )
            ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员信息表';

        alter table b_staff comment '员工信息';


end if;
end $$
delimiter ;
call createStaff();
drop procedure if exists createStaff;


