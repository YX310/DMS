/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.23 : Database - dts
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dts` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dts`;

/*Table structure for table `defect` */

DROP TABLE IF EXISTS `defect`;

CREATE TABLE `defect` (
  `defect_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '缺陷id',
  `defect_name` char(50) NOT NULL COMMENT '缺陷标题',
  `defect_description` longtext NOT NULL COMMENT '缺陷描述',
  `priority` char(20) DEFAULT NULL COMMENT '优先级',
  `probability` varchar(10) DEFAULT NULL COMMENT '概率（几率）',
  `project_version` char(10) DEFAULT NULL COMMENT '项目版本',
  `defect_creator` char(10) DEFAULT NULL COMMENT '创建人',
  `designated_person` char(10) DEFAULT NULL COMMENT '指派人',
  `defect_module` char(20) DEFAULT NULL COMMENT '缺陷所属模块',
  `defect_type` char(20) DEFAULT NULL COMMENT '缺陷类型',
  `start_date` date DEFAULT NULL COMMENT '计划开始日期',
  `finish_date` date DEFAULT NULL COMMENT '计划完成日期',
  `progress` char(10) DEFAULT '0' COMMENT '进度',
  `associated_defects` char(20) DEFAULT NULL COMMENT '关联已有缺陷',
  `defect_document` char(50) DEFAULT NULL COMMENT '上传的文件',
  `project_id` int(10) DEFAULT NULL COMMENT '所属项目的id',
  `defect_state` char(10) DEFAULT NULL COMMENT '缺陷状态',
  `creation_time` char(20) DEFAULT NULL COMMENT '创建时间',
  `defect_record` longtext COMMENT '记录修改内容',
  `update_time` char(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`defect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `defect` */

/*Table structure for table `demand` */

DROP TABLE IF EXISTS `demand`;

CREATE TABLE `demand` (
  `demand_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '需求id',
  `project_id` int(20) NOT NULL COMMENT '所属项目的id',
  `demand_name` char(50) NOT NULL COMMENT '需求标题',
  `demand_description` longtext COMMENT '需求描述',
  `designated_person` char(20) DEFAULT NULL COMMENT '指派人',
  `demand_state` char(20) DEFAULT NULL COMMENT '需求状态',
  `priority` char(20) DEFAULT NULL COMMENT '优先级',
  `progress` char(10) DEFAULT '0' COMMENT '进度',
  `start_date` char(20) DEFAULT NULL COMMENT '计划开始日期',
  `finish_date` char(20) DEFAULT NULL COMMENT '计划完成日期',
  `creation_time` char(20) DEFAULT NULL COMMENT '创建时间',
  `demand_record` longtext COMMENT '记录修改内容',
  `update_time` char(20) DEFAULT NULL COMMENT '记录修改时间',
  `demand_creator` char(20) DEFAULT NULL COMMENT '创建人',
  `demand_document` char(50) DEFAULT NULL COMMENT '上传的文件',
  PRIMARY KEY (`demand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `demand` */

insert  into `demand`(`demand_id`,`project_id`,`demand_name`,`demand_description`,`designated_person`,`demand_state`,`priority`,`progress`,`start_date`,`finish_date`,`creation_time`,`demand_record`,`update_time`,`demand_creator`,`demand_document`) values (24,48,'用户管理功能','1.登录注册\r\n2. 前台用户管理（查看个人信息、修改个人信息）\r\n3. 后台用户管理（查看用户信息、修改用户信息、删除用户信息）\r\n','xiaoguan','新建','普通','0',NULL,NULL,'2022-05-12 23:23:13',NULL,'2022-05-12 23:23:13','xiaomin',NULL),(25,48,'项目管理功能','查看我的工作台、新建、查看、编辑、删除项目','xiaoguan','新建','普通','0',NULL,NULL,'2022-05-12 23:24:19',NULL,'2022-05-12 23:24:19','xiaomin',NULL),(26,48,'需求管理功能','新建、查看、编辑、删除需求','xiaoguan','新建','普通','0',NULL,NULL,'2022-05-12 23:25:55',NULL,'2022-05-12 23:25:55','xiaomin',NULL),(27,48,'缺陷管理功能','新建、查看、编辑、删除缺陷','xiaoguan','新建','普通','0',NULL,NULL,'2022-05-12 23:27:24',NULL,'2022-05-12 23:27:24','xiaomin',NULL),(28,48,'全局搜索功能','1. 可搜索用户名\r\n2. 可搜索项目名\r\n3. 可搜索需求标题\r\n4. 可搜索缺陷标题\r\n','xiaoguan','新建','普通','0',NULL,NULL,'2022-05-12 23:30:46',NULL,'2022-05-12 23:30:46','xiaomin',NULL);

/*Table structure for table `file_upload` */

DROP TABLE IF EXISTS `file_upload`;

CREATE TABLE `file_upload` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `assoc_id` int(11) NOT NULL COMMENT '缺陷或需求的id',
  `file_path` char(100) NOT NULL COMMENT '文件路径',
  `file_name` char(200) DEFAULT NULL COMMENT '文件名',
  `is_defect` tinyint(1) NOT NULL COMMENT '是否是缺陷',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `file_upload` */

insert  into `file_upload`(`id`,`assoc_id`,`file_path`,`file_name`,`is_defect`) values (3,24,'http://localhost:80/2022/05/12/f18d6c33-e678-4f19-b2eb-f672d303c9af.jpg','缺陷10.jpg',0),(4,25,'http://localhost:80/2022/05/12/5790e843-99ec-4d7f-b3d7-dd1814dbd8c7.PNG','缺陷03.PNG',0),(5,27,'http://localhost:80/2022/05/12/f91854be-e005-469a-a942-24897f7e207a.PNG','缺陷08.PNG',0),(6,28,'http://localhost:80/2022/05/12/817ad2ef-c5a3-4878-a438-b939228d7b42.PNG','完善无活动时的ui.PNG',0);

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `project_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `project_name` char(50) NOT NULL COMMENT '项目名称',
  `creator` char(20) NOT NULL COMMENT '创建人',
  `creation_date` char(20) DEFAULT NULL COMMENT '创建时间',
  `project_description` longtext COMMENT '项目描述',
  `project_member` char(50) DEFAULT NULL COMMENT '项目成员',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

/*Data for the table `project` */

insert  into `project`(`project_id`,`project_name`,`creator`,`creation_date`,`project_description`,`project_member`) values (46,'UI界面测试','xiaomin','2022-05-12 17:07:04','UI界面测试','xiaomin;cs;xiaoguan'),(47,'音频测试','xiaomin','2022-05-12 17:07:58','音频测试','xiaomin;cs;xiaoguan'),(48,'缺陷跟踪管理系统（v1.0）','xiaomin','2022-05-12 17:15:28','提高软件开发过程中对缺陷进行跟踪和管理的效率，并实时查看和监督缺陷的系统。提升软件质量与推动软件迭代开发。\r\n','xiaomin;xiaoguan;cs'),(49,'动漫网站','xiaomin','2022-05-12 23:06:17','动漫网站:\r\n二次元动漫展示','xiaomin;cs;xiaoguan');

/*Table structure for table `update_record` */

DROP TABLE IF EXISTS `update_record`;

CREATE TABLE `update_record` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '唯一识别id',
  `assoc_id` int(20) NOT NULL COMMENT '关联id',
  `update_time` char(30) NOT NULL COMMENT '更新时间',
  `record_content` longtext COMMENT '变更内容',
  `update_person` char(20) DEFAULT NULL COMMENT '更新人',
  `is_defect` tinyint(1) NOT NULL COMMENT '是否是缺陷',
  `project_id` int(11) NOT NULL COMMENT '项目id',
  `assoc_title` char(30) NOT NULL COMMENT '关联标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `update_record` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` char(20) NOT NULL COMMENT '用户名（登录时使用）',
  `nickname` char(20) DEFAULT NULL COMMENT '昵称',
  `password` char(20) NOT NULL COMMENT '密码',
  `user_role` char(10) NOT NULL COMMENT '用户角色',
  `head_img` char(50) DEFAULT '../imgs/headimgs/headimg01.jpg' COMMENT '头像',
  `company` char(20) DEFAULT NULL COMMENT '公司',
  `user_position` char(20) DEFAULT NULL COMMENT '职位',
  `employee_number` varchar(10) DEFAULT NULL COMMENT '工号',
  `email` char(20) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`nickname`,`password`,`user_role`,`head_img`,`company`,`user_position`,`employee_number`,`email`) values (2,'xiaomin','小小小敏','0000','项目经理','../imgs/headimgs/headimg04.jpg',NULL,'项目经理','66666','2352322148@qq.com'),(3,'xiaoguan','小官','0000','开发','../imgs/headimgs/headimg02.jpg',NULL,'开发工程师',NULL,'2352322148@qq.com'),(4,'cs','测试酱','0000','测试','../imgs/headimgs/headimg03.jpg',NULL,'测试工程师',NULL,'2352322148@qq.com'),(11,'admin','admin','0000','管理员','../imgs/headimgs/headimg03.jpg',NULL,'管理员','0001','2352322148@qq.com'),(14,'yczhou','周某人','0000','开发','../imgs/headimgs/headimg07.jpg',NULL,'开发工程师',NULL,'1057433699@qq.com');

/*Table structure for table `user_and_project` */

DROP TABLE IF EXISTS `user_and_project`;

CREATE TABLE `user_and_project` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户与项目关系id',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `project_id` int(10) NOT NULL COMMENT '项目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

/*Data for the table `user_and_project` */

insert  into `user_and_project`(`id`,`user_id`,`project_id`) values (74,2,46),(75,4,46),(76,3,46),(77,2,47),(78,4,47),(79,3,47),(85,2,49),(86,4,49),(87,3,49),(91,2,48),(92,3,48),(93,4,48);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
