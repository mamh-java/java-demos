
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sp_department
-- ----------------------------
DROP TABLE IF EXISTS `sp_department`;
CREATE TABLE `sp_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

