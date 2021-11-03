drop procedure if exists createBizFile;
delimiter $$
create procedure createBizFile() begin

    if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_biz_file') then

        CREATE TABLE `b_biz_file`
        (
            `id`                 VARCHAR(50) NOT NULL COLLATE 'utf8_general_ci',
            `organization_id`    VARCHAR(50) NULL DEFAULT NULL COMMENT '组织' COLLATE 'utf8_general_ci',
            `department_id`      VARCHAR(50) NULL DEFAULT NULL COMMENT '部门' COLLATE 'utf8_general_ci',
            `bucket_name`        VARCHAR(50) NULL DEFAULT NULL COMMENT 'bucketname' COLLATE 'utf8_general_ci',
            `storage_type`       VARCHAR(20) NULL DEFAULT NULL COMMENT '存储类型' COLLATE 'utf8_general_ci',
            `original_file_name` VARCHAR(255) NULL DEFAULT NULL COMMENT '原始文件名' COLLATE 'utf8_general_ci',
            `thumbnail_path`     VARCHAR(255) NULL DEFAULT NULL COMMENT '略缩图' COLLATE 'utf8_general_ci',
            `size`               BIGINT(20) NULL DEFAULT NULL COMMENT '文件大小',
            `suffix`             VARCHAR(20) NULL DEFAULT NULL COMMENT '后缀' COLLATE 'utf8_general_ci',
            `width`              INT(10) NULL DEFAULT NULL COMMENT '图片宽度',
            `height`             INT(10) NULL DEFAULT NULL COMMENT '图片高度',
            `relative_path`      VARCHAR(255) NULL DEFAULT NULL COMMENT '文件相对路径' COLLATE 'utf8_general_ci',
            `domain`             VARCHAR(255) NULL DEFAULT NULL COMMENT '域名' COLLATE 'utf8_general_ci',
            `file_hash`          VARCHAR(255) NULL DEFAULT NULL COMMENT '文件hash' COLLATE 'utf8_general_ci',
            `upload_start_time`  DATETIME NULL DEFAULT NULL COMMENT '上传开始时间',
            `upload_end_time`    DATETIME NULL DEFAULT NULL COMMENT '上传结束时间',
            `status`             INT(11) NULL DEFAULT NULL COMMENT '状态',
            `creator_id`         VARCHAR(50) NULL DEFAULT NULL COMMENT '创建人ID' COLLATE 'utf8_general_ci',
            `creator_name`       VARCHAR(50) NULL DEFAULT NULL COMMENT '创建人' COLLATE 'utf8_general_ci',
            `create_time`        DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
            `update_time`        DATETIME NULL DEFAULT NULL COMMENT '更新时间',
            PRIMARY KEY (`id`) USING BTREE
        ) COLLATE='utf8_general_ci'
        ENGINE=InnoDB;

end if;
end $$
delimiter;
call createBizFile();
drop procedure if exists createBizFile;


