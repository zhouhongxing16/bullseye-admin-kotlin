drop procedure if exists createAccount;
delimiter $$
create procedure createAccount() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'kb_account_role') then
        /*==============================================================*/
        /* Table: kb_account_role                                       */
        /*==============================================================*/
        create table kb_account_role
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

        alter table kb_account_role add constraint FK_Reference_7 foreign key (account_id)
            references kb_accounts (id) on delete restrict on update restrict;

        alter table kb_account_role add constraint FK_Reference_8 foreign key (role_id)
            references kb_roles (id) on delete restrict on update restrict;

    end if;
end $$
delimiter ;
call createAccount();
drop procedure if exists createAccount;


