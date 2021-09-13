drop procedure if exists createRoleMenu;
delimiter $$
create procedure createRoleMenu() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'kb_role_menu') then
        /*==============================================================*/
        /* Table: kb_role_menu                                          */
        /*==============================================================*/
        create table kb_role_menu
        (
            id                   varchar(40) not null comment '唯一标识',
            role_id              varchar(40) comment '角色外键',
            menu_id              varchar(40) not null comment '菜单外键',
            status               int(11) default 0 comment '状态',
            created              timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建日期',
            primary key (id)
        )
            ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

        alter table kb_role_menu add constraint FK_Reference_10 foreign key (role_id)
            references kb_roles (id) on delete restrict on update restrict;

        alter table kb_role_menu add constraint FK_Reference_9 foreign key (menu_id)
            references kb_menus (id) on delete restrict on update restrict;



end if;
end $$
delimiter ;
call createRoleMenu();
drop procedure if exists createRoleMenu;


