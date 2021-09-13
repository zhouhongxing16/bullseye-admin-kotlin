drop procedure if exists createAccount;
delimiter $$
create procedure createAccount() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_account_role') then
        /*==============================================================*/
        /* Table: b_account_role                                       */
        /*==============================================================*/
        create table b_account_role
        (
            id                   varchar(40) not null comment '唯一标识',
            role_id              varchar(40) comment '角色外键',
            account_id           varchar(40) comment '用户外键',
            status               int(2) comment '状态',
            user_id              varchar(40) comment '创建人',
            created              timestamp not null default CURRENT_TIMESTAMP comment '创建日期',
            primary key (id)
        )
            ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账号角色';


    end if;
end $$
delimiter ;
call createAccount();
drop procedure if exists createAccount;


