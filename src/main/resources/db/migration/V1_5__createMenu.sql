drop procedure if exists createMenu;
delimiter $$
create procedure createMenu() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_menu') then

        /*==============================================================*/
        /* Table: b_menu                                              */
        /*==============================================================*/
        create table b_menu
        (
            id                   varchar(40) not null comment '唯一标识',
            parent_id            varchar(40) comment '父菜单id',
            code                 varchar(40) comment '代码',
            title                varchar(50) comment '名称',
            icon                 varchar(50) comment '图标',
            path                 varchar(255) comment '路径',
            user_id              varchar(40) comment '创建人',
            status               int(2) comment '状态',
            sequence             int default 0 comment '显示顺序',
            created              timestamp not null default CURRENT_TIMESTAMP comment '               ',
            primary key (id)
        )
            ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';


end if;
end $$
delimiter ;
call createMenu();
drop procedure if exists createMenu;


