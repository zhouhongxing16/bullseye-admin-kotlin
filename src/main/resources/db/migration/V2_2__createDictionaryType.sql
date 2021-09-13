drop procedure if exists createAccount;
delimiter $$
create procedure createAccount() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_dictionary_type') then
        /*==============================================================*/
        /* Table: b_dictionary_type                                    */
        /*==============================================================*/
        create table b_dictionary_type
        (
            id                   varchar(40) not null comment '唯一标识',
            code                 varchar(30) comment '类型代码',
            name                 varchar(50) comment '类型名称',
            remark               varchar(200) comment '备注',
            status               int(11) comment '状态',
            created              timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
            user_id              varchar(40) comment '创建人',
            primary key (id)
        )
            ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典类型';

end if;
end $$
delimiter ;
call createAccount();
drop procedure if exists createAccount;


