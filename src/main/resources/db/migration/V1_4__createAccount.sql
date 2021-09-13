drop procedure if exists createAccount;
delimiter $$
create procedure createAccount() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_account') then

        /*==============================================================*/
        /* Table: b_account                                           */
        /*==============================================================*/
        create table b_account
        (
            id                   varchar(40) not null comment '唯一标识',
            username             varchar(50) comment '账户',
            nick_name            varchar(50) comment '昵称',
            password             varchar(128) comment '密码',
            email                varchar(50) comment '邮箱',
            mobile               varchar(20) comment '手机号码',
            mobile_login_flag    bit default 0 comment '是否开通手机号登录',
            account_locked       bit default 0 comment '是否锁定',
            account_expired      bit default 0 comment '是否过期',
            type_flag            int default 0 comment '0:个人，1：机构',
            level_id             varchar(40) comment '账号等级标识',
            staff_id             varchar(40) comment '人员标识',
            organization_id      varchar(40) comment '组织标识',
            wx_openid            varchar(64) comment '微信OpenId',
            alipay_openid        varchar(64) comment '支付宝OpenId',
            avatar               varchar(255) comment '头像',
            status               int(11) default 0 comment '状态',
            remark               varchar(200) comment '备注',
            user_id              varchar(40) comment '创建人',
            modified             datetime comment '修改时间',
            expired_date         datetime comment '账号过期时间',
            created              timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
            primary key (id)
        )
            ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

        alter table b_account comment '账号';


end if;
end $$
delimiter ;
call createAccount();
drop procedure if exists createAccount;


