-- 创建表demo，使用存储过程进行判断，用完后删除
drop procedure if exists createOrganization;
delimiter $$
create procedure createOrganization() begin
  if not exists (select * FROM information_schema.columns WHERE table_schema = DATABASE()  AND table_name = 'b_organization') then
        CREATE TABLE `b_organization`
        (
            `id`            VARCHAR(40) NOT NULL COMMENT '唯一标识' COLLATE 'utf8_general_ci',
            `logo`          VARCHAR(300) NULL DEFAULT NULL COMMENT 'LOGO' COLLATE 'utf8_general_ci',
            `code`          VARCHAR(30) NULL DEFAULT NULL COMMENT '代码' COLLATE 'utf8_general_ci',
            `name`          VARCHAR(100) NULL DEFAULT NULL COMMENT '名称' COLLATE 'utf8_general_ci',
            `contact_name`  VARCHAR(200) NULL DEFAULT NULL COMMENT '联系人' COLLATE 'utf8_general_ci',
            `contact_phone` VARCHAR(200) NULL DEFAULT NULL COMMENT '联系电话' COLLATE 'utf8_general_ci',
            `brief`         LONGTEXT NULL DEFAULT NULL COMMENT '简介' COLLATE 'utf8_general_ci',
            `creator_id`    VARCHAR(40) NULL DEFAULT NULL COMMENT '创建人' COLLATE 'utf8_general_ci',
            `creator_name`  VARCHAR(40) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
            `status`        INT(11) NULL DEFAULT NULL COMMENT '状态',
            `create_time`   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
            PRIMARY KEY (`id`) USING BTREE
        ) COMMENT='组织表'
        COLLATE='utf8_general_ci'
        ENGINE=InnoDB;

end if;
end $$
delimiter ;
call createOrganization();
drop procedure if exists createOrganization;
