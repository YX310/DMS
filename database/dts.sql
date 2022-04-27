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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `defect` */

insert  into `defect`(`defect_id`,`defect_name`,`defect_description`,`priority`,`probability`,`project_version`,`defect_creator`,`designated_person`,`defect_module`,`defect_type`,`start_date`,`finish_date`,`progress`,`associated_defects`,`defect_document`,`project_id`,`defect_state`,`creation_time`,`defect_record`,`update_time`) values (1,'home路径错误2','home路径错误啦啦啦哈哈哈','最高','100%','v0.8','cs','xiaoguan','home','接口问题','2022-04-27','2022-04-27','90%',NULL,NULL,1,'新建',NULL,NULL,'2022-04-28 01:10:33'),(3,'defect_test2','测试修改缺陷1111','较高','30%',NULL,'xiaomin','xiaoguan',NULL,'接口问题','2022-04-19','2022-04-20','100%',NULL,NULL,1,'已关闭',NULL,NULL,'2022-04-23 00:15:16'),(7,'测试是否能输入项目id','测试是否能输入项目id22222','最高','60%',NULL,'xiaomin','xiaomin',NULL,'功能问题','2022-04-19','2022-04-25','10%',NULL,NULL,3,'进行中',NULL,NULL,'2022-04-25 14:52:20'),(9,'测试新建项目22','测试新建项目22','较高','100%',NULL,'xiaomin','xiaomin',NULL,'功能问题','2022-04-23','2022-04-24','0',NULL,NULL,2,'新建',NULL,NULL,'2022-04-23 00:24:02'),(14,'测试创建时间','测试描述是否有空行22','普通','40%',NULL,'xiaomin','xiaoguan',NULL,'接口问题','2022-04-20','2022-04-21','0',NULL,NULL,1,'新建',NULL,NULL,'2022-04-23 00:24:22'),(15,'测试创建时间2','测试创建时间2','最高','90%',NULL,'xiaomin','xiaomin',NULL,'数据请求问题',NULL,NULL,'0',NULL,NULL,1,'新建',NULL,NULL,NULL),(16,'测试创建时间3','测试base类1','最高','100%',NULL,'xiaomin','xiaoguan',NULL,'数据请求问题','2022-04-20','2022-04-22','10%',NULL,NULL,1,'进行中','',NULL,'2022-04-28 01:07:28'),(17,'测试创建时间4','测试创建时间4','最高','100%',NULL,'xiaomin','xiaomin',NULL,'数据请求问题','2022-04-20','2022-04-21','0',NULL,NULL,1,'新建','',NULL,'2022-04-24 21:42:05'),(18,'测试创建时间5','测试是否能更新日期','最高','100%',NULL,'xiaomin','xiaomin',NULL,'数据请求问题','2022-04-19','2022-04-21','0',NULL,NULL,1,'新建','2022-04-19 00:37:12',NULL,'2022-04-19 01:15:18'),(19,'测试新建缺陷2','测试新建缺陷2','较高','50%',NULL,'xiaomin','xiaoguan',NULL,'数据请求问题','2022-04-19','2022-04-27','0',NULL,NULL,1,'新建','2022-04-19 01:36:20',NULL,'2022-04-19 01:36:51'),(20,'测试新建缺陷4','测试新建缺陷111119993333','最高','100%',NULL,'xiaomin','xiaoguan',NULL,'数据请求问题','2022-04-19','2022-04-21','90%',NULL,NULL,3,'进行中','2022-04-19 15:56:26',NULL,'2022-04-20 09:59:03'),(21,'测试更新记录','测试更新记录test101222555','较高','100%',NULL,'xiaomin','xiaoguan',NULL,'UI界面问题','2022-04-21','2022-04-26','90%',NULL,NULL,1,'进行中','2022-04-20 10:05:36',NULL,'2022-04-20 13:21:01'),(22,'测试变更记录样式','测试变更记录样式22','普通','10%',NULL,'xiaomin','xiaoguan',NULL,'UI界面问题','2022-04-20','2022-04-21','10%',NULL,NULL,1,'进行中','2022-04-20 15:07:21',NULL,'2022-04-20 15:12:04'),(23,'今日份测试','今日份测试啦啦啦','普通','10%',NULL,'xiaomin','xiaoguan',NULL,'UI界面问题','2022-04-21','2022-04-22','10%',NULL,NULL,28,'新建','2022-04-21 15:30:40',NULL,'2022-04-21 15:36:21'),(24,'项目中无该成员时，仍可添加该成员','                                [操作步骤]\r\n                                [预期结果]\r\n                                [实际结果]\r\n                                实际结果:\r\n                            ','普通','100%',NULL,'xiaomin','xiaoguan',NULL,'功能问题',NULL,NULL,'0',NULL,NULL,34,'新建','2022-04-25 20:21:52',NULL,'2022-04-25 20:21:52'),(25,'上传文件未显示文件个数和文件名','1. 上传文件未显示文件个数和文件名；\r\n2. 删除添加多余的文件。\r\n','普通','100%',NULL,'xiaomin','yczhou',NULL,'功能问题','2022-04-25','2022-04-26','100%',NULL,NULL,34,'已解决','2022-04-25 21:24:35',NULL,'2022-04-27 00:11:20'),(26,'测试上传文件','测试上传文件','普通','10%',NULL,'xiaomin','xiaoguan',NULL,'功能问题','2022-04-25','2022-04-26','0',NULL,NULL,1,'新建','2022-04-25 21:42:49',NULL,'2022-04-27 23:49:37'),(27,'sda','                                [操作步骤]\r\n                                [预期结果]\r\n                                [实际结果]\r\n                                实际结果:\r\n                            ','普通','10%',NULL,'xiaomin','xiaoguan',NULL,'功能问题',NULL,NULL,'0',NULL,NULL,1,'新建','2022-04-28 00:17:19',NULL,'2022-04-28 00:17:19');

/*Table structure for table `defect_and_file_path` */

DROP TABLE IF EXISTS `defect_and_file_path`;

CREATE TABLE `defect_and_file_path` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '缺陷和文件路径关系id',
  `defect_id` int(10) NOT NULL COMMENT '缺陷id',
  `file_path` char(100) NOT NULL COMMENT '文件路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `defect_and_file_path` */

insert  into `defect_and_file_path`(`id`,`defect_id`,`file_path`) values (1,12,'http://localhost:8080/2022/04/17/e8643661-b80b-42c2-9b36-b0e9aced9813.jpg'),(2,13,'http://localhost:8080/2022/04/17/06f697c9-1847-433a-bcf4-ca2527ec55b2.jpg'),(3,20,'http://localhost:8080/2022/04/19/31906bea-8165-4898-bd4e-4a5a1773b1be.jpg'),(4,21,'http://localhost:8080/2022/04/20/e4359c54-2630-4dad-9c81-ccd47face325.jpg'),(5,22,'http://localhost:8080/2022/04/20/9a0cd0fb-5810-46c1-b347-4b19fae5c3b3.jpg'),(6,23,'http://localhost:8080/2022/04/21/0f12b8ff-b188-4633-954b-d25a2c75dc04.jpg');

/*Table structure for table `defect_update_record` */

DROP TABLE IF EXISTS `defect_update_record`;

CREATE TABLE `defect_update_record` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `defect_id` int(20) NOT NULL COMMENT '缺陷id',
  `update_time` char(30) DEFAULT NULL COMMENT '变更时间',
  `record_content` longtext COMMENT '变更内容',
  `update_person` char(20) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

/*Data for the table `defect_update_record` */

insert  into `defect_update_record`(`id`,`defect_id`,`update_time`,`record_content`,`update_person`) values (1,20,'1650383903914','测试新建缺陷11111999 变更为 测试新建缺陷111119993333\n70% 变更为 50%\n20% 变更为 30%\n',NULL),(2,20,'1650419817504','缺陷标题:/n描述:/n优先级:/n出现概率:/n50% 变更为 70%指派人:/n计划开始日期:/n计划完成日期:/n进度:/n30% 变更为 60%关联已有缺陷:/n缺陷状态:/n',NULL),(3,20,'1650419943029','缺陷标题:\n描述:\n优先级:\n较高 变更为 最高出现概率:\n70% 变更为 100%指派人:\n计划开始日期:\n计划完成日期:\n2022-04-20 变更为 2022-04-21进度:\n60% 变更为 90%关联已有缺陷:\n缺陷状态:\n',NULL),(4,21,'1650420389264','缺陷标题:\n描述:\n测试更新记录 变更为 测试更新记录test1优先级:\n较高 变更为 最高出现概率:\n10% 变更为 20%指派人:\n计划开始日期:\n填写了 2022-04-20计划完成日期:\n填写了 2022-04-21进度:\n0 变更为 10%关联已有缺陷:\n缺陷状态:\n',NULL),(5,21,'1650421018838','缺陷标题:\n\n描述:\n测试更新记录test1 变更为 测试更新记录test2\n优先级:\n\n出现概率:\n20% 变更为 30%\n指派人:\n\n计划开始日期:\n\n计划完成日期:\n\n进度:\n\n关联已有缺陷:\n\n缺陷状态:\n新建 变更为 进行中\n',NULL),(6,21,'1650421704347','缺陷标题:\n\n描述:\n\n优先级:\n\n出现概率:\n\n指派人:\n\n计划开始日期:\n\n计划完成日期:\n2022-04-21 变更为 2022-04-22\n进度:\n10% 变更为 20%\n关联已有缺陷:\n\n缺陷状态:\n\n',NULL),(7,21,'1650422060735','缺陷标题:\n\n描述:\n测试更新记录test2 变更为 测试更新记录test3\n优先级:\n\n出现概率:\n30% 变更为 70%\n指派人:\n\n计划开始日期:\n\n计划完成日期:\n\n进度:\n\n关联已有缺陷:\n\n缺陷状态:\n\n',NULL),(8,21,'1650422359092','缺陷标题:\n测试更新记录 变更为 测试更新记录\n描述:\n测试更新记录test3 变更为 测试更新记录test44\n优先级:\n最高 变更为 较高\n出现概率:\n\n指派人:\n\n计划开始日期:\n\n计划完成日期:\n\n进度:\n\n关联已有缺陷:\n\n缺陷状态:\n\n',NULL),(9,21,'1650422748767','缺陷标题:\n测试更新记录 变更为 测试更新记录\n描述:\n测试更新记录test44 变更为 测试更新记录test44\n优先级:\n较高 变更为 较高\n出现概率:\n\n指派人:\n\n计划开始日期:\n\n计划完成日期:\n\n进度:\n\n关联已有缺陷:\n\n缺陷状态:\n进行中 变更为 待反馈\n',NULL),(10,21,'1650422974535','缺陷标题:\n测试更新记录 变更为 测试更新记录\n描述:\n测试更新记录test44 变更为 测试更新记录test55\n\n出现概率:\n\n指派人:\n\n计划开始日期:\n\n计划完成日期:\n\n进度:\n\n关联已有缺陷:\n\n缺陷状态:\n待反馈 变更为 待反馈\n',NULL),(11,21,'1650422996900','缺陷标题:\n测试更新记录 变更为 测试更新记录\n描述:\n测试更新记录test55 变更为 测试更新记录test55\n优先级:\n较高 变更为 优先级:\n最高\n出现概率:\n\n指派人:\n\n计划开始日期:\n\n计划完成日期:\n\n进度:\n\n关联已有缺陷:\n\n缺陷状态:\n待反馈 变更为 待反馈\n',NULL),(12,21,'1650423344625','缺陷标题:\n测试更新记录 变更为 \n测试更新记录\n描述:\n测试更新记录test55 变更为 \n测试更新记录test66\n优先级:\n最高 变更为 \n优先级:\n普通\n出现概率:\n70% 变更为 \n出现概率:\n90%\n\n\n计划完成日期:\n2022-04-22 变更为 \n计划完成日期:\n2022-04-23\n进度:\n20% 变更为 \n进度:\n50%\n\n\n',NULL),(13,21,'1650424225131','缺陷标题:\n测试更新记录 变更为 测试更新记录\n描述:\n测试更新记录test66 变更为 测试更新记录test77\n\n出现概率:\n90% 变更为 出现概率:\n100%\n\n\n\n\n\n\n',NULL),(14,21,'1650424318384','缺陷标题:\n测试更新记录 变更为 测试更新记录\n描述:\n测试更新记录test77 变更为 测试更新记录test77\n\n\n\n\n计划完成日期:\n2022-04-23 变更为 计划完成日期:\n2022-04-24\n\n\n\n',NULL),(15,21,'1650424474069','\n缺陷标题:\n测试更新记录 变更为 测试更新记录\n描述:\n测试更新记录test77 变更为 测试更新记录test77\n计划完成日期:\n2022-04-24 变更为 计划完成日期:\n2022-04-25',NULL),(16,21,'1650424824212','\n描述:\n\n测试更新记录test77 变更为 测试更新记录test88\n优先级:\n\n普通 变更为 最高\n计划完成日期:\n\n2022-04-25 变更为 2022-04-23\n进度:\n\n50% 变更为 90%\n缺陷状态:\n\n待反馈 变更为 进行中',NULL),(17,21,'1650424997556','\n\n描述:\n测试更新记录test88 变更为 测试更新记录test99\n\n优先级:\n最高 变更为 较高\n\n计划开始日期:\n2022-04-20 变更为 2022-04-21\n\n进度:\n90% 变更为 100%\n\n缺陷状态:\n进行中 变更为 已解决',NULL),(18,21,'1650425506418','由userId更新于time\n\n计划完成日期:\n2022-04-23 变更为 2022-04-24',NULL),(19,21,'1650426422185','由userId更新于time\n\n描述:\n测试更新记录test99 变更为 测试更新记录test100由userId更新于time\n\n计划完成日期:\n2022-04-24 变更为 2022-04-26由userId更新于time\n\n进度:\n100% 变更为 90%',NULL),(20,21,'1650427631933','\n\n描述:\n由测试更新记录test1011 变更为 测试更新记录test101222\n\n计划完成日期:\n由2022-04-26 变更为 2022-04-25','xiaomin'),(21,21,'1650429705083','\n\n计划完成日期:\n由 2022-04-25 变更为 2022-04-26','xiaomin'),(22,21,'1650430956747','\n\n描述:\n由 测试更新记录test101222 变更为 测试更新记录test101222555','xiaomin'),(23,21,'1650432061672','\n\n进度:\n由 80% 变更为 90%','xiaomin'),(24,1,'1650435580157','\n\n描述:\n由 home路径错误啦啦啦 变更为 home路径错误啦啦啦哈哈哈','xiaomin'),(25,1,'1650436842677','\n\n计划完成日期:\n由 2022-03-31 变更为 2022-04-20\n\n进度:\n由 30% 变更为 90%\n\n缺陷状态:\n由 进行中 变更为 待反馈','xiaomin'),(26,16,'1650437429217','\n\n描述:\n由 测试创建时间3\r\n                             变更为 测试创建时间3333\r\n                            填写了 2022-04-20填写了 2022-04-21\n\n进度:\n由 0 变更为 10%','xiaomin'),(27,3,'1650437636280','\n\n描述:\n由 测试修改缺陷 变更为 测试修改缺陷1111','xiaomin'),(28,16,'1650437726979','\n\n描述:\n由 测试创建时间3333\r\n                             变更为 测试创建时间33334444\r\n                            ','xiaomin'),(29,16,'1650437756005','\n\n描述:\n由 测试创建时间33334444\r\n                             变更为 测试创建时间\r\n                            ','xiaomin'),(30,16,'1650437917410','\n\n描述:\n由 测试创建时间\r\n                             变更为 测试创建时间啊啊啊\r\n                            \n\n缺陷状态:\n由 新建 变更为 进行中','xiaomin'),(31,14,'1650437971504','\n\n描述:\n由 测试创建时间\r\n                             变更为 测试创建时间000\r\n                            填写了 2022-04-20填写了 2022-04-21','xiaomin'),(32,14,'1650438043812','\n\n描述:\n由 测试创建时间000\r\n                             变更为 测试描述是否有空行','xiaomin'),(33,14,'1650438062860','\n\n描述:\n由 测试描述是否有空行 变更为 测试描述是否有空行22','xiaomin'),(34,17,'1650438094967','填写了 2022-04-20填写了 2022-04-21','xiaomin'),(35,22,'1650438493775','\n\n描述:\n由 测试变更记录样式 变更为 测试变更记录样式22填写了 计划开始日期::\n2022-04-20\n填写了 计划完成日期::\n2022-04-21\n\n\n缺陷状态:\n由 新建 变更为 进行中','xiaomin'),(36,22,'1650438724434','\n\n进度:\n由 0 变更为 10%','xiaomin'),(37,16,'1650452256238','\n\n描述:\n由 测试创建时间啊啊啊\r\n                             变更为 测试创建时间啊啊啊啦啦啦\r\n                            \n\n计划完成日期:\n由 2022-04-21 变更为 2022-04-22','xiaomin'),(38,16,'1650452284679','\n\n描述:\n由 测试创建时间啊啊啊啦啦啦\r\n                             变更为 测试base类','xiaomin'),(39,16,'1650452299556','\n\n描述:\n由 测试base类 变更为 测试base类1','xiaomin'),(40,23,'1650526562702','\n\n描述:\n由 今日份测试 变更为 今日份测试啦啦啦\n\n填写了 计划开始日期:\n2022-04-21\n\n\n填写了 计划完成日期:\n2022-04-22\n\n\n进度:\n由 0 变更为 10%','xiaomin'),(41,23,'1650526581155','','xiaomin'),(42,3,'1650644116267','','xiaomin'),(43,9,'1650644642221','\n\n填写了 计划开始日期:\n2022-04-23\n\n\n填写了 计划完成日期:\n2022-04-24\n','xiaomin'),(44,14,'1650644662480','','xiaomin'),(45,16,'1650644681837','','xiaomin'),(46,16,'1650723409122','','xiaomin'),(47,1,'1650724613143','','xiaomin'),(48,1,'1650724631394','\n\n计划完成日期:\n由 2022-04-20 变更为 2022-04-23','xiaomin'),(49,17,'1650807725035','','xiaomin'),(50,7,'1650869540557','\n\n描述:\n由 测试是否能输入项目id11222333 变更为 测试是否能输入项目id22222\n\n计划完成日期:\n由 2022-04-20 变更为 2022-04-25','xiaomin'),(51,25,'1650893101673','\n\n描述:\n由 1. 上传文件未显示文件个数和文件名。\r\n2. 删除添加多余的文件。\r\n 变更为 1. 上传文件未显示文件个数和文件名；\r\n2. 删除添加多余的文件。\r\n\n\n填写了 计划开始日期:\n2022-04-25\n\n\n填写了 计划完成日期:\n2022-04-26\n','xiaomin'),(52,26,'1650894202381','\n\n描述:\n由                                 [操作步骤]\r\n                                [预期结果]\r\n                                [实际结果]\r\n                                实际结果:\r\n                             变更为 测试上传文件\n\n填写了 计划开始日期:\n2022-04-25\n\n\n填写了 计划完成日期:\n2022-04-26\n','xiaomin'),(53,1,'1650899694848','','xiaomin'),(54,26,'1650987774919','','xiaomin'),(55,26,'1650988017179','','xiaomin'),(56,26,'1650988095448','','xiaomin'),(57,26,'1650988250413','','xiaomin'),(58,26,'1650988718749','','xiaomin'),(59,26,'1650989355408','','xiaomin'),(60,25,'1650989469509','\n\n进度:\n由 0 变更为 100\n\n缺陷状态:\n由 新建 变更为 已解决','xiaomin'),(61,25,'1650989480397','\n\n进度:\n由 100 变更为 100%','xiaomin'),(62,26,'1650990509729','','xiaomin'),(63,1,'1650993727693','\n\n计划开始日期:\n由 2022-03-30 变更为 2022-04-27\n\n计划完成日期:\n由 2022-04-23 变更为 2022-04-27','xiaomin');

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `demand` */

insert  into `demand`(`demand_id`,`project_id`,`demand_name`,`demand_description`,`designated_person`,`demand_state`,`priority`,`progress`,`start_date`,`finish_date`,`creation_time`,`demand_record`,`update_time`,`demand_creator`,`demand_document`) values (1,2,'测试是否显示列表','测试是否显示列表','xiaomin',NULL,NULL,'0',NULL,NULL,'2022-04-19 15:35:26',NULL,'2022-04-19 15:35:26','xiaomin',NULL),(2,1,'测试新建需求','','xiaoguan','新建','较高','10%','2022-04-25','2022-04-26','2022-04-19 15:45:36',NULL,'2022-04-28 01:15:09','xiaomin',NULL),(3,1,'测试新建需求','','xiaoguan','新建','普通','10%','2022-04-19','2022-04-25','2022-04-19 15:35:26',NULL,'2022-04-19 21:22:05','xiaomin',NULL),(5,3,'测试新建需求22','测试修改需求555666lallalal','xiaoguan','进行中','最高','40%','2022-04-21','2022-04-25','2022-04-19 15:45:36',NULL,'2022-04-25 15:29:37','xiaomin',NULL),(6,3,'测试新建需求3','测试新建需求3333                           ','xiaoguan','进行中','较高','35%','2022-04-19','2022-04-19','2022-04-19 16:05:32',NULL,'2022-04-19 16:06:08','xiaomin',NULL),(7,1,'sda','                            ','xiaoguan','新建','普通','0','','','2022-04-19 21:19:28',NULL,'2022-04-19 21:21:54','xiaomin',NULL),(8,1,'sda','1111','xiaoguan','新建','普通','0','2022-04-28','2022-04-29','2022-04-19 21:22:15',NULL,'2022-04-28 01:06:54','xiaomin',NULL),(9,34,'文件更新/删除','新建/更新 需求/缺陷时 需要对文件进行删除和更新.','xiaoguan','进行中','普通','90%','2022-04-25','2022-04-27','2022-04-25 20:20:12',NULL,'2022-04-28 01:17:37','xiaomin',NULL),(10,34,'活动界面','需要新增活动界面显示当前项目所有缺陷/需求的变更记录。\r\n\r\n','xiaoguan','已解决','普通','100%','2022-04-28','2022-04-28','2022-04-25 20:22:57',NULL,'2022-04-28 01:16:56','xiaomin',NULL),(11,34,'个人信息界面 优化','个人信息界面 优化','xiaoguan',NULL,'普通','0',NULL,NULL,'2022-04-25 20:26:18',NULL,'2022-04-25 20:26:18','xiaomin',NULL),(12,34,'我的工作台 列出所有需求和缺陷','1. 包含被指派的和报告的。\r\n2. 显示所有数量。\r\n','xiaoguan',NULL,'普通','0',NULL,NULL,'2022-04-25 20:29:03',NULL,'2022-04-25 20:29:03','xiaomin',NULL),(13,34,'单点登录功能','当前登录session仅记录唯一用户，需要支持多用户登录','xiaomin','新建','普通','0','','','2022-04-25 21:26:35',NULL,'2022-04-27 00:57:55','xiaomin',NULL),(14,34,'修改/删除/新建/登录 弹出提示框','修改/删除/新建/登录 弹出提示框','xiaoguan','新建','普通','0',NULL,NULL,'2022-04-25 21:30:02',NULL,'2022-04-25 21:30:02','xiaomin',NULL);

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

/*Table structure for table `demand_update_record` */

DROP TABLE IF EXISTS `demand_update_record`;

CREATE TABLE `demand_update_record` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `demand_id` int(20) NOT NULL COMMENT '需求id',
  `update_time` char(30) DEFAULT NULL COMMENT '更改时间',
  `record_content` longtext COMMENT '更改内容',
  `update_person` char(20) DEFAULT NULL COMMENT '更改者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `demand_update_record` */

insert  into `demand_update_record`(`id`,`demand_id`,`update_time`,`record_content`,`update_person`) values (1,5,'1650869011042','\n\n描述:\n由 测试修改需求333                            变更为 测试修改需求555\n\n计划完成日期:\n由 2022-04-20 变更为 2022-04-25',NULL),(2,5,'1650869476222','\n\n描述:\n由 测试修改需求555 变更为 测试修改需求555666\n\n优先级:\n由 普通 变更为 较高\n\n进度:\n由 20% 变更为 30%\n\n计划开始日期:\n由 2022-04-19 变更为 2022-04-20','xiaomin'),(3,5,'1650870590176','\n\n描述:\n由 测试修改需求555666 变更为 测试修改需求555666lallalal\n\n优先级:\n由 较高 变更为 最高','xiaomin'),(4,5,'1650871029139','\n\n进度:\n由 30% 变更为 40%\n\n计划开始日期:\n由 2022-04-20 变更为 2022-04-21','xiaomin'),(5,5,'1650871490564','','xiaomin'),(6,5,'1650871777338','','xiaomin'),(7,2,'1650888843893','\n\n计划开始日期:\n由  变更为 2022-04-25\n\n计划完成日期:\n由  变更为 2022-04-26','xiaomin'),(8,9,'1650889241381','\n\n指派人:\n由 xiaomin 变更为 xiaoguan\n\n填写了 状态:\n新建\n\n\n填写了 计划开始日期:\n2022-04-25\n\n\n填写了 计划完成日期:\n2022-04-27\n','xiaomin'),(9,2,'1650991774955','','xiaomin'),(10,13,'1650992275250','\n\n填写了 状态:\n新建\n\n\n填写了 计划开始日期:\n\n\n\n填写了 计划完成日期:\n\n','xiaomin'),(11,8,'1650992312442','\n\n填写了 状态:\n新建\n\n\n填写了 计划开始日期:\n\n\n\n填写了 计划完成日期:\n\n','xiaomin');

/*Table structure for table `file_upload` */

DROP TABLE IF EXISTS `file_upload`;

CREATE TABLE `file_upload` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `assoc_id` int(11) NOT NULL COMMENT '缺陷或需求的id',
  `file_path` char(100) NOT NULL COMMENT '文件路径',
  `file_name` char(200) DEFAULT NULL COMMENT '文件名',
  `is_defect` tinyint(1) NOT NULL COMMENT '缺陷/需求 flag',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `file_upload` */

insert  into `file_upload`(`id`,`assoc_id`,`file_path`,`file_name`,`is_defect`) values (1,3,'http','test.txt',1),(4,3,'http','test(1).txt',1),(5,3,'http','test(2).txt',1),(7,3,'http2','hhh.jpg',1),(10,26,'http://localhost:8080/2022/04/27/759333d9-d0f5-4d38-85c7-d0aafb63a28f.py','ping.py',1),(11,26,'http://localhost:8080/2022/04/27/5c6f54ca-09ce-42b6-9de2-d165bc0d75b1.py','practice.py',1),(12,26,'http://localhost:8080/2022/04/27/2db6d5bf-20d4-4342-8c65-d82fcfc0f535.sh','startup.sh',1),(13,2,'http://localhost:8080/2022/04/27/c2a505a8-87f3-4535-a101-d1229ca5f558.py','ping.py',0),(14,13,'http://localhost:8080/2022/04/27/d75e6933-b0d4-4502-9726-36a35c3d1ff6.py','ping.py',0),(15,8,'http://localhost:8080/2022/04/27/dc86fbeb-50ba-40a0-b871-360f1f89e4ec.py','ping.py',0),(16,1,'http://localhost:8080/2022/04/28/d7385a3c-bd7a-4933-9cc0-771f2c0167a1.PNG','缺陷08.PNG',1),(17,1,'http://localhost:8080/2022/04/28/db836062-b3da-40df-8f5a-9907a9d31ac2.PNG','缺陷07.PNG',1),(18,27,'http://localhost:8080/2022/04/28/c229145f-d5c0-4b13-8ea0-a2cd13ec2af4.PNG','缺陷08.PNG',1),(19,27,'http://localhost:8080/2022/04/28/b48c0647-41e8-4dc2-9034-d45a4200dca4.PNG','缺陷09.PNG',1),(20,27,'http://localhost:8080/2022/04/28/724763ae-d117-4525-83c2-9a3b3aa6128b.jpg','缺陷10.jpg',1),(21,1,'http://localhost:8080/2022/04/28/c8e954fc-7a28-4e8b-883a-a19b8e226d79.PNG','缺陷08(1).PNG',1),(22,2,'http://localhost:8080/2022/04/28/a2638192-01fe-42b1-bdf8-3f2d95cddf64.PNG','缺陷09.PNG',0),(23,2,'http://localhost:8080/2022/04/28/17462c25-5f26-489e-aab5-5cbaf2782873.jpg','缺陷10.jpg',0),(24,8,'http://localhost:8080/2022/04/28/49a080f8-5a50-4f71-bd70-bdb1b3c96a13.PNG','缺陷09.PNG',0),(25,8,'http://localhost:8080/2022/04/28/fd0affc3-caca-4443-a124-234d78b9f3a2.jpg','缺陷10.jpg',0),(26,8,'http://localhost:8080/2022/04/28/f063795c-4401-4cce-8d74-8a111dbf74e2.PNG','缺陷09(1).PNG',0),(27,8,'http://localhost:8080/2022/04/28/badc8e24-aa83-4bb0-8a66-7158b512753a.jpg','缺陷10(1).jpg',0),(28,8,'http://localhost:8080/2022/04/28/33f1a87f-6879-4163-a2e7-504eb8daf806.PNG','缺陷09(2).PNG',0),(29,8,'http://localhost:8080/2022/04/28/8f22fb29-890e-4eee-94b3-9341a47c7ed9.jpg','缺陷10(2).jpg',0);

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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `project` */

insert  into `project`(`project_id`,`project_name`,`creator`,`creation_date`,`project_description`,`project_member`) values (1,'project1','xiaomin','2022-03-25','xiaomin的第一个项目','xiaomin'),(2,'project2','xiaoguan','2022-03-25','','xiaoguan'),(3,'project3','xiaomin','2022-03-25','xiaomin的第二个项目','xiaomin'),(4,'project4','xiaomin','2022-03-28','xiaomin的第三个项目','xiaomin'),(5,'project5','xiaomin','2022-03-28','xiaomin的第四个项目','xiaomin'),(6,'project6','xiaoguan','2022-03-28','xiaoguan的第二个项目','xiaoguan'),(7,'lalal','xiaomin',NULL,'测试sql语句','xiaomin'),(8,'测试新建项目2','xiaomin',NULL,'测试新建项目2','xiaomin'),(9,'测试修改项目id','xiaomin',NULL,'测试修改项目id','xiaomin'),(10,'测试user_and_project表是否能插入数据','xiaomin',NULL,'测试user_and_project表是否能插入数据','xiaomin'),(11,'测试user_and_project表是否能插入数据2','xiaomin',NULL,'测试user_and_project表是否能插入数据2','xiaomin'),(12,'测试user_and_project表是否能插入数据2','xiaomin',NULL,'测试user_and_project表是否能插入数据2','xiaomin'),(13,'测试user_and_project表是否能插入数据2','xiaomin',NULL,'project_id','xiaomin'),(14,'111','xiaomin',NULL,'111','xiaomin'),(15,'aaaa','xiaomin',NULL,'aaaa','xiaomin'),(16,'bbb','xiaomin',NULL,'bbb','xiaomin'),(17,'ccc','xiaomin',NULL,'ccc','xiaomin'),(18,'ddd','xiaomin',NULL,'ddd','xiaomin'),(19,'测试能否获取时间22','xiaomin','2022-04-15','测试能否获取时间22','xiaomin'),(21,'测试创建时间1','xiaomin','2022-04-19 h:m:s','测试创建时间1','xiaomin'),(22,'测试创建时间2','xiaomin','2022-04-19 h:m:s','测试创建时间2','xiaomin'),(23,'测试新建项目','xiaomin','2022-04-20 22:14:21','sfdsvdfsdfs','xiaomin;xiaoguan;'),(24,'fdfsd','xiaomin','2022-04-20 22:19:05','dd','fsfsdfsd;dfgfdg;'),(25,'测试修改项目sada','xiaomin','2022-04-20 22:24:19','sddasda','sdas;'),(26,'测试添加成员','xiaomin','2022-04-20 22:59:33','测试添加成员','cs;xiaoguan;'),(27,'测试显示项目成员','xiaomin','2022-04-21 11:59:03','测试显示项目成员','xiaomin;cs;sasa;'),(28,'测试显示项目成员','xiaomin','2022-04-21 11:59:43','测试显示项目成员','xiaomin;cs;'),(29,'测试多个项目成员','xiaomin','2022-04-21 12:24:51','测试多个项目成员','xiaomin;xiaoguan;cs;kkk;'),(30,'新增成员','xiaomin','2022-04-24 22:46:22','新增成员','cs;xiaomin;xiaoguan;aaa;'),(31,'新增成员','xiaomin','2022-04-24 22:47:46','新增成员','cs;xiaomin;xiaoguan;'),(32,'crshi','xiaomin','2022-04-24 23:56:32','crshi','xiaomin;'),(33,'gdf','xiaomin','2022-04-25 00:07:05','fdfd','xiaoguan;'),(34,'待完成的需求','xiaomin','2022-04-25 20:18:02','待完成的需求',';');

/*Table structure for table `project_version` */

DROP TABLE IF EXISTS `project_version`;

CREATE TABLE `project_version` (
  `id` int(10) NOT NULL COMMENT '版本id',
  `project_id` int(10) DEFAULT NULL COMMENT '项目id',
  `version` char(20) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `project_version` */

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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `update_record` */

insert  into `update_record`(`id`,`assoc_id`,`update_time`,`record_content`,`update_person`,`is_defect`,`project_id`,`assoc_title`) values (1,26,'1650989480397','','xiaomin',1,1,'测试活动'),(2,1,'1651074762366','','xiaomin',1,1,'home路径错误2'),(3,1,'1651074774766','','xiaomin',1,1,'home路径错误2'),(4,1,'1651074780211','','xiaomin',1,1,'home路径错误2'),(5,1,'1651074794447','','xiaomin',1,1,'home路径错误2'),(6,1,'1651075210356','','xiaomin',1,1,'home路径错误2'),(7,1,'1651075459793','','xiaomin',1,1,'home路径错误2'),(8,1,'1651075600366','','xiaomin',1,1,'home路径错误2'),(9,1,'1651075617546','\n\n缺陷状态:\n由 待反馈 变更为 新建','xiaomin',1,1,'home路径错误2'),(10,1,'1651075624374','','xiaomin',1,1,'home路径错误2'),(11,1,'1651075990261','\n\n缺陷类型:\n由 功能问题 变更为 接口问题','xiaomin',1,1,'home路径错误2'),(12,1,'1651076003185','','xiaomin',1,1,'home路径错误2'),(13,1,'1651076161053','','xiaomin',1,1,'home路径错误2'),(14,1,'1651076785033','\n\n填写了 文件\n缺陷08(1).PNG\n','xiaomin',1,1,'home路径错误2'),(15,2,'1651077135068','','xiaomin',0,1,'测试新建需求'),(16,8,'1651077187524','\n\n计划开始日期:\n由  变更为 2022-04-28\n\n计划完成日期:\n由  变更为 2022-04-29','xiaomin',0,1,'sda'),(17,8,'1651077268388','','xiaomin',0,1,'sda'),(18,8,'1651077436767','\n\n填写了 文件\n缺陷09(2).PNG,缺陷10(2).jpg\n','xiaomin',0,1,'sda'),(19,2,'1651078204070','\n\n填写了 文件\n\n','xiaomin',0,1,'测试新建需求'),(20,8,'1651078220122','\n\n描述:\n由                              变更为 1111\n\n填写了 文件\n\n','xiaomin',0,1,'sda'),(21,16,'1651078528579','\n\n填写了 文件\n\n','xiaomin',1,1,'测试创建时间3'),(22,2,'1651078566780','\n\n填写了 文件\n\n','xiaomin',0,1,'测试新建需求'),(23,2,'1651079075582','\n\n删除了 描述:\n\n','xiaomin',0,1,'测试新建需求'),(24,2,'1651079124996','\n\n删除了 描述:\n\n','xiaomin',0,1,'测试新建需求'),(25,2,'1651079192014','','xiaomin',0,1,'测试新建需求'),(26,8,'1651079214881','','xiaomin',0,1,'sda'),(27,16,'1651079248418','','xiaomin',1,1,'测试创建时间3'),(28,2,'1651079418079','','xiaomin',0,1,'测试新建需求'),(29,10,'1651079816797','\n\n填写了 状态:\n已解决\n\n\n进度:\n由 0 变更为 100%\n\n填写了 计划开始日期:\n2022-04-28\n\n\n填写了 计划完成日期:\n2022-04-28\n','xiaomin',0,34,'活动界面'),(30,9,'1651079857404','\n\n状态:\n由 新建 变更为 进行中\n\n进度:\n由 0 变更为 90%','xiaomin',0,34,'文件更新/删除');

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`nickname`,`password`,`user_role`,`head_img`,`company`,`user_position`,`employee_number`,`email`) values (2,'xiaomin',NULL,'0000','项目经理',NULL,NULL,'项目经理','66666','2352322148@qq.com'),(3,'xiaoguan',NULL,'0000','开发',NULL,NULL,'开发工程师',NULL,'2352322148@qq.com'),(4,'cs',NULL,'0000','测试',NULL,NULL,'测试工程师',NULL,'2352322148@qq.com'),(5,'lucy','露西','0000','项目经理',NULL,NULL,'项目经理',NULL,'2352322148@qq.com'),(6,'kkk','tom','0000','测试',NULL,NULL,'测试工程师',NULL,'2352322148@qq.com'),(10,'lalala','lalala','0000','项目经理',NULL,NULL,'项目经理',NULL,'2352322148@qq.com'),(11,'admin','admin','0000','管理员',NULL,NULL,'管理员','0001','2352322148@qq.com'),(13,'zzz','zzz','0000','项目经理',NULL,NULL,NULL,NULL,'2352322148@qq.com'),(14,'yczhou','周某人','0000','开发',NULL,NULL,NULL,NULL,'1057433699@qq.com');

/*Table structure for table `user_and_project` */

DROP TABLE IF EXISTS `user_and_project`;

CREATE TABLE `user_and_project` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户与项目关系id',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `project_id` int(10) NOT NULL COMMENT '项目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `user_and_project` */

insert  into `user_and_project`(`id`,`user_id`,`project_id`) values (1,2,1),(2,2,3),(3,3,2),(7,2,15),(8,2,16),(9,2,17),(10,2,18),(11,2,19),(12,2,20),(13,2,21),(14,2,22),(15,2,23),(16,2,24),(17,2,25),(18,2,26),(19,4,26),(20,3,26),(21,2,27),(22,4,27),(23,2,28),(24,4,28),(25,2,29),(26,3,29),(27,4,29),(28,6,29),(29,2,30),(30,4,30),(31,3,30),(32,2,31),(33,4,31),(34,3,31),(35,2,33),(36,3,33),(37,2,34);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
