drop procedure if exists createRoleMenuFunction;
delimiter $$
create procedure createRoleMenuFunction() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_role_menu_function') then
        /*==============================================================*/
        /* Table: b_role_menu_function                                 */
        /*==============================================================*/
        create table b_role_menu_function
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


end if;
end $$
delimiter ;
call createRoleMenuFunction();
drop procedure if exists createRoleMenuFunction;


