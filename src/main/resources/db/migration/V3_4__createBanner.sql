drop procedure if exists createBanner;
delimiter $$
create procedure createBanner() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'kb_banners') then

       /*==============================================================*/
        /* Table: kb_banners                                            */
        /*==============================================================*/
        create table kb_banners
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

        alter table kb_banners comment '轮播图';

end if;
end $$
delimiter ;
call createBanner();
drop procedure if exists createBanner;


