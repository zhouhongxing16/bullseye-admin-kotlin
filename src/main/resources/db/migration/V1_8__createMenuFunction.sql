drop procedure if exists createMenu;
delimiter $$
create procedure createMenu() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'kb_menu_function') then
        /*==============================================================*/
        /* Table: kb_menu_function                                      */
        /*==============================================================*/
        create table kb_menu_function
        (
            id                   varchar(40) not null comment 'id',
            menu_id              varchar(40) comment '菜单id',
            code                 varchar(50) comment '编码',
            name                 varchar(50) comment '名称',
            path                 varchar(50) comment '路径',
            status               int default 0 comment '状态',
            created              timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
            primary key (id)
        )
            ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

        alter table kb_menu_function comment '菜单权限';

        alter table kb_menu_function add constraint FK_Reference_11 foreign key (menu_id)
            references kb_menus (id) on delete restrict on update restrict;



end if;
end $$
delimiter ;
call createMenu();
drop procedure if exists createMenu;


