CREATE DATABASE mytest;

CREATE TABLE t_user(
  user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_name VARCHAR(255) NOT NULL ,
  password VARCHAR(255) NOT NULL ,
  mobile VARCHAR(255) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

insert into t_user values(5, 'qian', '555', '13691156270');


CREATE TABLE `user_operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_no` varchar(32) NOT NULL COMMENT '操作人ID',
  `operation_name` varchar(32) NOT NULL COMMENT '操作名称',
  `params` varchar(1000) NULL COMMENT '查询参数',
  `app_id` varchar(32) NOT NULL COMMENT 'appID',
  `sub_app_id` int(11) NOT NULL COMMENT '子应用ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户操作日志';
