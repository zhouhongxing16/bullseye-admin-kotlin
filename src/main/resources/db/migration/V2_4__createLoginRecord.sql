drop procedure if exists createLoginRecord;
delimiter $$
create procedure createLoginRecord() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_login_record') then
        /*==============================================================*/
        /* Table: b_login_record                                       */
        /*==============================================================*/
        create table b_login_record
        (
            id                   varchar(40) not null comment '访问ID',
            username             varchar(40) comment '登录账号',
            ip                   varchar(50) comment '登录IP地址',
            login_location       varchar(255) comment '登录地点',
            browser              varchar(50) comment '浏览器类型',
            os                   varchar(50) comment '操作系统',
            status               int default 0 comment '登录状态（1成功 0失败）',
            message              varchar(255) comment '提示消息',
            created              timestamp not null default CURRENT_TIMESTAMP comment '访问时间',
            primary key (id)
        )
            ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统访问记录';


end if;
end $$
delimiter ;
call createLoginRecord();
drop procedure if exists createLoginRecord;


