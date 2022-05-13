/*
SQLyog Ultimate v11.27 (32 bit)
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `defect` */

insert  into `defect`(`defect_id`,`defect_name`,`defect_description`,`priority`,`probability`,`project_version`,`defect_creator`,`designated_person`,`defect_module`,`defect_type`,`start_date`,`finish_date`,`progress`,`associated_defects`,`defect_document`,`project_id`,`defect_state`,`creation_time`,`defect_record`,`update_time`) values (2,'项目中无该成员时，仍可指派缺陷给该成员','[操作步骤]\r\n1. 点击项目。\r\n2. 点击导航栏缺陷。\r\n3. 添加缺陷信息和指派非当前项目成员。\r\n[预期结果] \r\n创建失败，当前项目无该成员。\r\n[实际结果]\r\n缺陷新建成功。','普通','100%',NULL,'cs','xiaoguan',NULL,'功能问题','2022-04-15','2022-05-13','100%',NULL,NULL,48,'已解决','2022-05-13 00:08:47',NULL,'2022-05-13 00:20:19'),(3,'上传文件未显示文件个数和文件名','[操作步骤]\r\n1. 点击项目。\r\n2. 点击导航栏缺陷。\r\n3. 上传多个文件。\r\n[预期结果] \r\n显示上传文件个数。\r\n[实际结果]\r\n显示第一个上传的文件名称。','普通','100%',NULL,'cs','xiaoguan',NULL,'功能问题','2022-04-22','2022-04-29','100%',NULL,NULL,48,'已解决','2022-05-13 00:13:31',NULL,'2022-05-13 00:20:25'),(4,'更新项目信息时加入项目成员，概述界面项目成员无改变','[操作步骤]\r\n1. 点击项目。\r\n2. 点击编辑。\r\n3. 添加成员。\r\n4. 保存编辑。\r\n[预期结果] \r\n概述界面显示新增成员名称。\r\n[实际结果]\r\n概述界面成员无变化。','普通','100%',NULL,'cs','xiaoguan',NULL,'功能问题','2022-04-26','2022-04-26','100%',NULL,NULL,48,'已解决','2022-05-13 00:15:34',NULL,'2022-05-13 00:20:32'),(5,'个人信息界面编辑头像失败','[操作步骤]\r\n1. 点击头像。\r\n2. 点击编辑我的资料。\r\n3. 点击修改头像。\r\n4. 点击保存。\r\n[预期结果] \r\n个人信息界面和导航栏头像更新。\r\n[实际结果]\r\n个人信息界面和导航栏头像无变化。','普通','100%',NULL,'cs','xiaomin',NULL,'功能问题','2022-05-05','2022-05-05','100%',NULL,NULL,48,'已解决','2022-05-13 00:16:23',NULL,'2022-05-13 00:20:38'),(7,'新建缺陷失败','[操作步骤]\r\n1. 点击项目。\r\n2. 点击导航栏缺陷。\r\n3. 添加缺陷信息。\r\n[预期结果] \r\n创建成功。\r\n[实际结果]\r\n创建失败。','普通','10%',NULL,'cs','xiaoguan',NULL,'功能问题','2022-05-07','2022-05-07','10%',NULL,NULL,48,'进行中','2022-05-13 00:20:10',NULL,'2022-05-13 00:37:00'),(9,'测试新建缺陷','1111','普通','10%',NULL,'xiaomin','xiaoguan',NULL,'功能问题','2022-05-13','2022-05-13','10%',NULL,NULL,55,'进行中','2022-05-13 18:04:13',NULL,'2022-05-13 18:05:44');

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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `file_upload` */

insert  into `file_upload`(`id`,`assoc_id`,`file_path`,`file_name`,`is_defect`) values (3,24,'http://localhost:80/2022/05/12/f18d6c33-e678-4f19-b2eb-f672d303c9af.jpg','缺陷10.jpg',0),(4,25,'http://localhost:80/2022/05/12/5790e843-99ec-4d7f-b3d7-dd1814dbd8c7.PNG','缺陷03.PNG',0),(5,27,'http://localhost:80/2022/05/12/f91854be-e005-469a-a942-24897f7e207a.PNG','缺陷08.PNG',0),(6,28,'http://localhost:80/2022/05/12/817ad2ef-c5a3-4878-a438-b939228d7b42.PNG','完善无活动时的ui.PNG',0),(7,7,'http://8.134.8.197:80/2022/05/13/c648d20c-b561-43e1-9c8c-7f4aea1054bb.txt','新建文本文档.txt',1),(8,8,'http://8.134.8.197:80/2022/05/13/9d6a4a46-3d7d-4bb7-9bba-228bb3cf47f0.txt','排序.txt',1),(9,9,'http://8.134.8.197:80/2022/05/13/b53d0099-cf9b-4951-813c-8c5a9790db93.txt','排序.txt',1);

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
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

/*Data for the table `project` */

insert  into `project`(`project_id`,`project_name`,`creator`,`creation_date`,`project_description`,`project_member`) values (46,'UI界面测试','xiaomin','2022-05-12 17:07:04','UI界面测试','xiaomin;cs;xiaoguan'),(47,'音频测试','xiaomin','2022-05-12 17:07:58','音频测试','xiaomin;cs;xiaoguan'),(48,'缺陷跟踪管理系统（v1.0）','xiaomin','2022-05-12 17:15:28','提高软件开发过程中对缺陷进行跟踪和管理的效率，并实时查看和监督缺陷的系统。提升软件质量与推动软件迭代开发。\r\n','xiaomin;xiaoguan;cs'),(49,'动漫网站','xiaomin','2022-05-12 23:06:17','动漫网站:\r\n二次元动漫展示','xiaomin;cs;xiaoguan'),(50,'快音直播','xiaomin','2022-05-13 00:40:41','互联网的发展如火如荼，催生了很多新兴职业以及从业大军，直播行业也在此趋势下得到快速发展。截至2021年6月，我国网络直播用户规模达6.38亿，与2020年同期相比增长47.2%，占网民整体的63.1%。随着互联网技术的深入发展，网络直播生态链备受关注，网络直播用户规模持续上升，直播行业拥有巨大的市场发展空间。所以成立该项目开发快音直播APP。\r\n','xiaomin;xiaoguan;cs'),(51,'携橙旅行','xiaomin','2022-05-13 00:51:11','近年来，随着人民群众的生活水平不断提高，都市人追求回归自然，返朴归真的意识愈来愈浓。我乡党委，政府审时度势，实施“旅游兴乡”战略。随着以农业休闲为核心的旅游产业的成功运作，部分乡村旅游的知名度越来越高，引起各界人士的高度关注。成立该项目开发旅游网站，介绍风景特色，预定机票，导游等等。','xiaomin;xiaoguan;cs'),(52,'饱了么','xiaomin','2022-05-13 01:29:30','在经济发展迅速，大学生生活水平普遍提高的今天，作为大学生日常生活中最重要的就餐问题得到了广泛关注。传统就餐地点食堂因为等餐时间过长已经不能满足大部分同学的需求。开发当前项目用于给同学们送外卖，填写送餐地点，支付外卖费用等等。','xiaomin;xiaoguan;cs'),(53,'答答','xiaomin','2022-05-13 02:01:51','《报告》显示，网约车司机拥有较强的工作主动性及驱动力，超过3成网约车司机每月休假天数在4天以下，但80%以上的司机对工作时间表示满意。多项调研数据显示，司机在平台抽成比例设置方面的满意度最低，70%的网约车司机表示平台费所占比例太多，降低抽成比例成为司机们最为强烈的诉求。包干模式得到了62.5%的网约车司机认可，并希望网约车平台在增强司机归属感方面能有更多举措，30%的网约车司机将归属感作为其选择网约车公司的一项主要因素。成立该项目提供网约车服务。','xiaomin;xiaoguan;cs'),(55,'测试新建项目','xiaomin','2022-05-13 18:03:55','测试新建项目','xiaomin;cs;xiaoguan');

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `update_record` */

insert  into `update_record`(`id`,`assoc_id`,`update_time`,`record_content`,`update_person`,`is_defect`,`project_id`,`assoc_title`) values (3,2,'1652371747937','\n\n填写了 计划开始日期:\n2022-04-15\n\n\n填写了 计划完成日期:\n2022-05-13\n\n\n进度:\n由 0 变更为 100%','cs',1,48,'项目中无该成员时，仍可指派缺陷给该成员'),(4,2,'1652371757460','\n\n缺陷状态:\n由 新建 变更为 已解决','cs',1,48,'项目中无该成员时，仍可指派缺陷给该成员'),(5,3,'1652372033211','\n\n填写了 计划开始日期:\n2022-04-22\n\n\n填写了 计划完成日期:\n2022-04-29\n\n\n进度:\n由 0 变更为 100%','cs',1,48,'上传文件未显示文件个数和文件名'),(6,4,'1652372146917','\n\n填写了 计划开始日期:\n2022-04-26\n\n\n填写了 计划完成日期:\n2022-04-26\n\n\n进度:\n由 0 变更为 100','cs',1,48,'更新项目信息时加入项目成员，概述界面项目成员无改变'),(7,4,'1652372153932','\n\n进度:\n由 100 变更为 100%','cs',1,48,'更新项目信息时加入项目成员，概述界面项目成员无改变'),(8,5,'1652372265215','\n\n描述:\n由 个人信息界面 优化 变更为 [操作步骤]\r\n1. 点击头像\r\n2. 点击编辑我的资料\r\n3. 点击修改头像\r\n4. 点击保存\r\n[预期结果] \r\n个人信息界面和导航栏头像更新\r\n[实际结果]\r\n个人信息界面和导航栏头像无变化\n\n出现概率:\n由 10% 变更为 100%\n\n填写了 计划开始日期:\n2022-05-05\n\n\n填写了 计划完成日期:\n2022-05-05\n\n\n进度:\n由 0 变更为 100%\n\n缺陷状态:\n由 新建 变更为 已解决','cs',1,48,'个人信息界面编辑头像失败'),(9,2,'1652372419026','\n\n描述:\n由 [操作步骤]\r\n1. 点击项目\r\n2. 点击导航栏缺陷\r\n3. 添加缺陷信息和指派非当前项目成员。\r\n[预期结果] \r\n创建失败，当前项目无该成员。\r\n[实际结果]\r\n缺陷新建成功。 变更为 [操作步骤]\r\n1. 点击项目。\r\n2. 点击导航栏缺陷。\r\n3. 添加缺陷信息和指派非当前项目成员。\r\n[预期结果] \r\n创建失败，当前项目无该成员。\r\n[实际结果]\r\n缺陷新建成功。','cs',1,48,'项目中无该成员时，仍可指派缺陷给该成员'),(10,3,'1652372425379','\n\n描述:\n由 [操作步骤]\r\n1. 点击项目\r\n2. 点击导航栏缺陷\r\n3. 上传多个文件\r\n[预期结果] \r\n显示上传文件个数。\r\n[实际结果]\r\n显示第一个上传的文件名称。 变更为 [操作步骤]\r\n1. 点击项目。\r\n2. 点击导航栏缺陷。\r\n3. 上传多个文件。\r\n[预期结果] \r\n显示上传文件个数。\r\n[实际结果]\r\n显示第一个上传的文件名称。','cs',1,48,'上传文件未显示文件个数和文件名'),(11,4,'1652372432205','\n\n描述:\n由 [操作步骤]\r\n1. 点击项目\r\n2. 点击编辑\r\n3. 添加成员\r\n4. 保存编辑\r\n[预期结果] \r\n概述界面显示新增成员名称\r\n[实际结果]\r\n概述界面成员无变化 变更为 [操作步骤]\r\n1. 点击项目。\r\n2. 点击编辑。\r\n3. 添加成员。\r\n4. 保存编辑。\r\n[预期结果] \r\n概述界面显示新增成员名称。\r\n[实际结果]\r\n概述界面成员无变化。','cs',1,48,'更新项目信息时加入项目成员，概述界面项目成员无改变'),(12,5,'1652372438736','\n\n描述:\n由 [操作步骤]\r\n1. 点击头像\r\n2. 点击编辑我的资料\r\n3. 点击修改头像\r\n4. 点击保存\r\n[预期结果] \r\n个人信息界面和导航栏头像更新\r\n[实际结果]\r\n个人信息界面和导航栏头像无变化 变更为 [操作步骤]\r\n1. 点击头像。\r\n2. 点击编辑我的资料。\r\n3. 点击修改头像。\r\n4. 点击保存。\r\n[预期结果] \r\n个人信息界面和导航栏头像更新。\r\n[实际结果]\r\n个人信息界面和导航栏头像无变化。','cs',1,48,'个人信息界面编辑头像失败'),(13,7,'1652372451985','\n\n填写了 计划开始日期:\n2022-05-07\n\n\n填写了 计划完成日期:\n2022-05-07\n\n\n进度:\n由 0 变更为 10%','cs',1,48,'新建缺陷失败'),(14,7,'1652373420282','\n\n填写了 文件\n新建文本文档.txt\n','xiaomin',1,48,'新建缺陷失败'),(15,8,'1652433810671','\n\n填写了 计划开始日期:\n2022-05-13\n\n\n填写了 计划完成日期:\n2022-05-13\n\n\n进度:\n由 0 变更为 10%\n\n缺陷类型:\n由 功能问题 变更为 性能问题','xiaomin',1,54,'测试新建缺陷'),(16,9,'1652436272204','\n\n描述:\n由                                 [操作步骤][预期结果][实际结果]\r\n                             变更为 1111\n\n填写了 计划开始日期:\n2022-05-13\n\n\n填写了 计划完成日期:\n2022-05-13\n','xiaomin',1,55,'测试新建缺陷'),(17,9,'1652436306834','\n\n进度:\n由 0 变更为 10%\n\n缺陷状态:\n由 新建 变更为 进行中','xiaomin',1,55,'测试新建缺陷'),(18,9,'1652436344415','\n\n指派人:\n由 xiaomin 变更为 xiaoguan','xiaomin',1,55,'测试新建缺陷');

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`nickname`,`password`,`user_role`,`head_img`,`company`,`user_position`,`employee_number`,`email`) values (2,'xiaomin','小小小敏','0000','项目经理','../imgs/headimgs/headimg04.jpg',NULL,'项目经理','66666','2352322148@qq.com'),(3,'xiaoguan','小官','0000','开发','../imgs/headimgs/headimg02.jpg',NULL,'开发工程师',NULL,'2352322148@qq.com'),(4,'cs','测试酱','0000','测试','../imgs/headimgs/headimg03.jpg',NULL,'测试工程师',NULL,'2352322148@qq.com'),(11,'admin','admin','0000','管理员','../imgs/headimgs/headimg03.jpg',NULL,'管理员','0001','2352322148@qq.com'),(14,'yczhou','周某人','0000','开发','../imgs/headimgs/headimg07.jpg',NULL,'开发工程师',NULL,'1057433699@qq.com'),(23,'111','111','1111','管理员','../imgs/headimgs/headimg03.jpg',NULL,'管理员',NULL,'g611728@163.com');

/*Table structure for table `user_and_project` */

DROP TABLE IF EXISTS `user_and_project`;

CREATE TABLE `user_and_project` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户与项目关系id',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `project_id` int(10) NOT NULL COMMENT '项目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8;

/*Data for the table `user_and_project` */

insert  into `user_and_project`(`id`,`user_id`,`project_id`) values (74,2,46),(75,4,46),(76,3,46),(77,2,47),(78,4,47),(79,3,47),(85,2,49),(86,4,49),(87,3,49),(91,2,48),(92,3,48),(93,4,48),(96,2,50),(97,3,50),(98,4,50),(99,2,51),(100,3,51),(101,4,51),(102,2,52),(103,3,52),(104,4,52),(105,2,53),(106,3,53),(107,4,53),(111,2,54),(112,4,54),(113,3,54),(114,2,55),(115,4,55),(116,3,55);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
