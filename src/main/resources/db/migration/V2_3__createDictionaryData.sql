drop procedure if exists createAccount;
delimiter $$
create procedure createAccount() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_dictionary_data') then

        /*==============================================================*/
        /* Table: b_dictionary_data                                    */
        /*==============================================================*/
        create table b_dictionary_data
        (
            id                   varchar(40) not null comment '唯一标识',
            type_id              varchar(40) comment '类型标识',
            code                 varchar(20) comment '字典编码',
            name                 varchar(100) comment '字典名称',
            remark               varchar(200) comment '字典备注',
            sequence             int comment '排序',
            status               int default 0 comment '状态',
            created              timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
            user_id              varchar(40) comment '创建人',
            primary key (id)
        )
            ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

        alter table b_dictionary_data comment '字典数据';

    end if;
end $$
delimiter ;
call createAccount();
drop procedure if exists createAccount;


