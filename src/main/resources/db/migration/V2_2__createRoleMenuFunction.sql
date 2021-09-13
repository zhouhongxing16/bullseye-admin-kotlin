drop procedure if exists createRoleMenuFunction;
delimiter $$
create procedure createRoleMenuFunction() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'kb_role_menu_function') then
        /*==============================================================*/
        /* Table: kb_role_menu_function                                 */
        /*==============================================================*/
        create table kb_role_menu_function
        (
            id                   varchar(40) not null comment '唯一标识',
            role_id              varchar(40) comment '角色ID',
            menu_function_id     varchar(40) comment '授权路径ID',
            user_id              varchar(40) comment '操作人',
            status               int default 0 comment '状态',
            created              timestamp not null default CURRENT_TIMESTAMP comment '创建日期',
            primary key (id)
        )
            ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单权限';

        alter table kb_role_menu_function add constraint FK_Reference_12 foreign key (role_id)
            references kb_roles (id) on delete restrict on update restrict;

        alter table kb_role_menu_function add constraint FK_Reference_13 foreign key (menu_function_id)
            references kb_menu_function (id) on delete restrict on update restrict;

end if;
end $$
delimiter ;
call createRoleMenuFunction();
drop procedure if exists createRoleMenuFunction;


