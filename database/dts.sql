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
  `defect_name` char(20) NOT NULL COMMENT '缺陷标题',
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `defect` */

insert  into `defect`(`defect_id`,`defect_name`,`defect_description`,`priority`,`probability`,`project_version`,`defect_creator`,`designated_person`,`defect_module`,`defect_type`,`start_date`,`finish_date`,`progress`,`associated_defects`,`defect_document`,`project_id`,`defect_state`,`creation_time`,`defect_record`,`update_time`) values (1,'home路径错误2','home路径错误啦啦啦','最高','100%','v0.8','cs','xiaoguan','home','接口问题','2022-03-30','2022-03-31','30%',NULL,NULL,1,'进行中',NULL,NULL,'2022-04-19 16:30:18'),(3,'defect_test2','测试修改缺陷','较高','30%',NULL,'xiaomin','xiaoguan',NULL,'接口问题',NULL,NULL,'0',NULL,NULL,1,'进行中',NULL,NULL,NULL),(7,'测试是否能输入项目id','测试是否能输入项目id','最高','100%',NULL,'xiaomin','xiaomin',NULL,'功能问题',NULL,NULL,'0',NULL,NULL,3,'新建',NULL,NULL,NULL),(9,'测试新建项目22','测试新建项目22','较高','100%',NULL,'xiaomin','xiaomin',NULL,'功能问题',NULL,NULL,'0',NULL,NULL,2,'新建',NULL,NULL,NULL),(14,'测试创建时间','测试创建时间\r\n                            ','普通','40%',NULL,'xiaomin','xiaoguan',NULL,'接口问题',NULL,NULL,'0',NULL,NULL,1,'新建',NULL,NULL,NULL),(15,'测试创建时间2','测试创建时间2','最高','90%',NULL,'xiaomin','xiaomin',NULL,'数据请求问题',NULL,NULL,'0',NULL,NULL,1,'新建',NULL,NULL,NULL),(16,'测试创建时间3','测试创建时间3\r\n                            ','最高','100%',NULL,'xiaomin','xiaoguan',NULL,'数据请求问题',NULL,NULL,'0',NULL,NULL,1,'新建','',NULL,''),(17,'测试创建时间4','测试创建时间4','最高','100%',NULL,'xiaomin','xiaomin',NULL,'数据请求问题',NULL,NULL,'0',NULL,NULL,1,'新建','',NULL,''),(18,'测试创建时间5','测试是否能更新日期','最高','100%',NULL,'xiaomin','xiaomin',NULL,'数据请求问题','2022-04-19','2022-04-21','0',NULL,NULL,1,'新建','2022-04-19 00:37:12',NULL,'2022-04-19 01:15:18'),(19,'测试新建缺陷2','测试新建缺陷2','较高','50%',NULL,'xiaomin','xiaoguan',NULL,'数据请求问题','2022-04-19','2022-04-27','0',NULL,NULL,1,'新建','2022-04-19 01:36:20',NULL,'2022-04-19 01:36:51'),(20,'测试新建缺陷4','测试新建缺陷444','较高','30%',NULL,'xiaomin','xiaoguan',NULL,'数据请求问题','2022-04-19','2022-04-20','10%',NULL,NULL,3,'进行中','2022-04-19 15:56:26',NULL,'2022-04-19 15:57:31');

/*Table structure for table `defect_and_file_path` */

DROP TABLE IF EXISTS `defect_and_file_path`;

CREATE TABLE `defect_and_file_path` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '缺陷和文件路径关系id',
  `defect_id` int(10) NOT NULL COMMENT '缺陷id',
  `file_path` char(100) NOT NULL COMMENT '文件路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `defect_and_file_path` */

insert  into `defect_and_file_path`(`id`,`defect_id`,`file_path`) values (1,12,'http://localhost:8080/2022/04/17/e8643661-b80b-42c2-9b36-b0e9aced9813.jpg'),(2,13,'http://localhost:8080/2022/04/17/06f697c9-1847-433a-bcf4-ca2527ec55b2.jpg'),(3,20,'http://localhost:8080/2022/04/19/31906bea-8165-4898-bd4e-4a5a1773b1be.jpg');

/*Table structure for table `demand` */

DROP TABLE IF EXISTS `demand`;

CREATE TABLE `demand` (
  `demand_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '需求id',
  `project_id` int(20) NOT NULL COMMENT '所属项目的id',
  `demand_name` char(20) NOT NULL COMMENT '需求标题',
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `demand` */

insert  into `demand`(`demand_id`,`project_id`,`demand_name`,`demand_description`,`designated_person`,`demand_state`,`priority`,`progress`,`start_date`,`finish_date`,`creation_time`,`demand_record`,`update_time`,`demand_creator`,`demand_document`) values (1,2,'测试是否显示列表','测试是否显示列表','xiaomin',NULL,NULL,'0',NULL,NULL,'2022-04-19 15:35:26',NULL,'2022-04-19 15:35:26','xiaomin',NULL),(2,1,'测试新建需求',NULL,'xiaoguan',NULL,'较高','10%','','','2022-04-19 15:45:36',NULL,'2022-04-19 15:45:36','xiaomin',NULL),(3,1,'测试新建需求',NULL,'xiaoguan',NULL,'普通','10%','2022-04-19','2022-04-25','2022-04-19 15:35:26',NULL,'2022-04-19 15:35:26','xiaomin',NULL),(5,3,'测试新建需求22','测试修改需求333                           ','xiaoguan','进行中','普通','20%','2022-04-19','2022-04-20','2022-04-19 15:45:36',NULL,'2022-04-19 16:04:55','xiaomin',NULL),(6,3,'测试新建需求3','测试新建需求3333                           ','xiaoguan','进行中','较高','35%','2022-04-19','2022-04-19','2022-04-19 16:05:32',NULL,'2022-04-19 16:06:08','xiaomin',NULL);

/*Table structure for table `demand_and_file_path` */

DROP TABLE IF EXISTS `demand_and_file_path`;

CREATE TABLE `demand_and_file_path` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '需求和文件路径关系id',
  `demand_id` int(20) NOT NULL COMMENT '需求id',
  `file_path` char(100) DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `demand_and_file_path` */

insert  into `demand_and_file_path`(`id`,`demand_id`,`file_path`) values (1,3,'http://localhost:8080/2022/04/19/07b33ea2-d963-42b4-9ed4-9539666eec1b.jpg'),(2,4,'http://localhost:8080/2022/04/19/ad6b252a-491e-47de-a9e4-4852e00adc38.jpg'),(3,5,'http://localhost:8080/2022/04/19/b4ff232a-9885-40aa-b495-28725eb84a08.jpg'),(4,6,'http://localhost:8080/2022/04/19/9353e73d-70e6-4911-906a-cae5423118f0.jpg');

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `project_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `project_name` char(50) NOT NULL COMMENT '项目名称',
  `creator` char(20) NOT NULL COMMENT '创建人',
  `creation_date` char(20) DEFAULT NULL COMMENT '创建时间',
  `project_description` char(30) DEFAULT NULL COMMENT '项目描述',
  `project_member` char(50) DEFAULT NULL COMMENT '项目成员',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `project` */

insert  into `project`(`project_id`,`project_name`,`creator`,`creation_date`,`project_description`,`project_member`) values (1,'project1','xiaomin','2022-03-25','xiaomin的第一个项目','xiaomin'),(2,'project2','xiaoguan','2022-03-25','','xiaoguan'),(3,'project3','xiaomin','2022-03-25','xiaomin的第二个项目','xiaomin'),(4,'project4','xiaomin','2022-03-28','xiaomin的第三个项目','xiaomin'),(5,'project5','xiaomin','2022-03-28','xiaomin的第四个项目','xiaomin'),(6,'project6','xiaoguan','2022-03-28','xiaoguan的第二个项目','xiaoguan'),(7,'lalal','xiaomin',NULL,'测试sql语句','xiaomin'),(8,'测试新建项目2','xiaomin',NULL,'测试新建项目2','xiaomin'),(9,'测试修改项目id','xiaomin',NULL,'测试修改项目id','xiaomin'),(10,'测试user_and_project表是否能插入数据','xiaomin',NULL,'测试user_and_project表是否能插入数据','xiaomin'),(11,'测试user_and_project表是否能插入数据2','xiaomin',NULL,'测试user_and_project表是否能插入数据2','xiaomin'),(12,'测试user_and_project表是否能插入数据2','xiaomin',NULL,'测试user_and_project表是否能插入数据2','xiaomin'),(13,'测试user_and_project表是否能插入数据2','xiaomin',NULL,'project_id','xiaomin'),(14,'111','xiaomin',NULL,'111','xiaomin'),(15,'aaaa','xiaomin',NULL,'aaaa','xiaomin'),(16,'bbb','xiaomin',NULL,'bbb','xiaomin'),(17,'ccc','xiaomin',NULL,'ccc','xiaomin'),(18,'ddd','xiaomin',NULL,'ddd','xiaomin'),(19,'测试能否获取时间22','xiaomin','2022-04-15','测试能否获取时间22','xiaomin'),(21,'测试创建时间1','xiaomin','2022-04-19 h:m:s','测试创建时间1','xiaomin'),(22,'测试创建时间2','xiaomin','2022-04-19 h:m:s','测试创建时间2','xiaomin');

/*Table structure for table `project_version` */

DROP TABLE IF EXISTS `project_version`;

CREATE TABLE `project_version` (
  `id` int(10) NOT NULL COMMENT '版本id',
  `project_id` int(10) DEFAULT NULL COMMENT '项目id',
  `version` char(20) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `project_version` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` char(20) NOT NULL COMMENT '用户名（登录时使用）',
  `nickname` char(20) DEFAULT NULL COMMENT '昵称',
  `password` char(20) NOT NULL COMMENT '密码',
  `user_role` char(10) NOT NULL COMMENT '用户角色',
  `head_img` char(20) DEFAULT NULL COMMENT '头像',
  `company` char(20) DEFAULT NULL COMMENT '公司',
  `user_position` char(20) DEFAULT NULL COMMENT '职位',
  `employee_number` varchar(10) DEFAULT NULL COMMENT '工号',
  `email` char(20) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`nickname`,`password`,`user_role`,`head_img`,`company`,`user_position`,`employee_number`,`email`) values (2,'xiaomin',NULL,'0000','项目经理',NULL,NULL,'项目经理','66666','2352322148@qq.com'),(3,'xiaoguan',NULL,'0000','开发',NULL,NULL,'开发工程师',NULL,'2352322148@qq.com'),(4,'cs',NULL,'0000','测试',NULL,NULL,'测试工程师',NULL,'2352322148@qq.com'),(5,'lucy','露西','0000','项目经理',NULL,NULL,'项目经理',NULL,'2352322148@qq.com'),(6,'kkk','tom','0000','测试',NULL,NULL,'测试工程师',NULL,'2352322148@qq.com'),(10,'lalala','lalala','0000','项目经理',NULL,NULL,'项目经理',NULL,'2352322148@qq.com'),(11,'admin','admin','0000','管理员',NULL,NULL,'管理员',NULL,'2352322148@qq.com'),(13,'zzz','zzz','0000','项目经理',NULL,NULL,NULL,NULL,'2352322148@qq.com');

/*Table structure for table `user_and_project` */

DROP TABLE IF EXISTS `user_and_project`;

CREATE TABLE `user_and_project` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户与项目关系id',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `project_id` int(10) NOT NULL COMMENT '项目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `user_and_project` */

insert  into `user_and_project`(`id`,`user_id`,`project_id`) values (1,2,1),(2,2,3),(3,3,2),(7,2,15),(8,2,16),(9,2,17),(10,2,18),(11,2,19),(12,2,20),(13,2,21),(14,2,22);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
