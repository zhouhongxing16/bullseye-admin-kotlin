drop procedure if exists createBanner;
delimiter $$
create procedure createBanner() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_banner') then

       /*==============================================================*/
        /* Table: b_banner                                            */
        /*==============================================================*/
        create table b_banner
        (
            id                   varchar(40) not null comment '唯一标识',
            name                 varchar(50) comment '名称',
            path                 varchar(255) comment '图片地址',
            link                 varchar(255) comment '跳转地址',
            sequence             int default 1 comment '显示顺序',
            status               int default 0 comment '状态',
            user_id              varchar(40),
            created              timestamp default CURRENT_TIMESTAMP,
            Column_9             char(10),
            primary key (id)
        );

        alter table b_banner comment '轮播图';

end if;
end $$
delimiter ;
call createBanner();
drop procedure if exists createBanner;


