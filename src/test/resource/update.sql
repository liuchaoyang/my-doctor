ALTER TABLE `yhhbsz`.`tb_business_order`
ADD COLUMN `addr_province` varchar(255) NULL AFTER `pay`,
ADD COLUMN `addr_detail` varchar(1000) NULL AFTER `addr_province`;