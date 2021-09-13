drop procedure if exists createBizFile;
delimiter $$
create procedure createBizFile() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_biz_file') then

        create table b_biz_file
        (
            id                   varchar(50) not null,
            user_id              varchar(50) comment '创建人',
            organization_id      varchar(50) comment '组织',
            department_id        varchar(50) comment '部门',
            bucket_name          varchar(50) comment 'bucketname',
            storage_type         varchar(20) comment '存储类型',
            original_file_name   varchar(255) comment '原始文件名',
            thumbnail            varchar(255) comment '略缩图',
            size                 bigint(20) default NULL comment '文件大小',
            suffix               varchar(20) comment '后缀',
            width                int(10)  default NULL comment '图片宽度',
            height               int(10)  default NULL comment '图片高度',
            file_path            varchar(255) comment '文件相对路径',
            full_file_path       varchar(255) comment '文件完整路径',
            file_hash            varchar(255) comment '文件hash',
            upload_start_time    datetime comment '上传开始时间',
            upload_end_time      datetime comment '上传结束时间',
            created              timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
            updated              datetime comment '更新时间',
            status               int(11) default NULL comment '状态',
            primary key (id)
        );
    end if;
end $$
delimiter;
call createBizFile();
drop procedure if exists createBizFile;


