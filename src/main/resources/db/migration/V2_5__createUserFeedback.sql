drop procedure if exists createUserFeedback;
delimiter $$
create procedure createUserFeedback() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_user_feedback') then
        /*==============================================================*/
        /* Table: b_user_feedback                                      */
        /*==============================================================*/
        create table b_user_feedback
        (
            id                   varchar(40) not null comment '唯一标识',
            user_id              varchar(40) comment '用户标识',
            contact_name         varchar(20) comment '联系人',
            contact_phone        varchar(20) comment '联系电话',
            content              text comment '反馈内容',
            attach               varchar(255) comment '附件',
            created              timestamp default CURRENT_TIMESTAMP comment '创建日期',
            status               int default 0 comment '状态',
            primary key (id)
        );

        alter table b_user_feedback comment '用户反馈';

    end if;
end $$
delimiter ;
call createUserFeedback();
drop procedure if exists createUserFeedback;


