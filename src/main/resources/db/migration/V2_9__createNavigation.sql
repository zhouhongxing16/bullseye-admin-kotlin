drop procedure if exists createNavigation;
delimiter $$
create procedure createNavigation() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_navigation') then

        /*==============================================================*/
        /* Table: b_navigation                                        */
        /*==============================================================*/
        create table b_navigation
        (
            id                   varchar(40) not null comment '唯一标识',
            name                 varchar(50) comment '名称',
            level                int default 1 comment '级别',
            parent_id            varchar(40) comment '父标识',
            sequence             int default 1 comment '显示顺序',
            path                 varchar(100) comment '访问路径',
            status               int default 0 comment '状态',
            created              timestamp default CURRENT_TIMESTAMP comment '创建日期',
            user_id              varchar(40) comment '创建人',
            primary key (id)
        );

        alter table b_navigation comment '导航栏';

end if;
end $$
delimiter ;
call createNavigation();
drop procedure if exists createNavigation;


