--
-- Table structure for table `member`
--
DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `activated` bit(1) DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `profileImage` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `authority`
--
DROP TABLE IF EXISTS `authority`;

CREATE TABLE `authority` (
  `authority_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`authority_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `member_authority`
--
DROP TABLE IF EXISTS `member_authority`;

CREATE TABLE `member_authority` (
  `member_id` bigint NOT NULL,
  `authority_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`member_id`,`authority_name`),
  KEY `FKasnmjar8jr5gaxvd7966p19ir` (`authority_name`),
  CONSTRAINT `FK8uf5ff5jr0nuvbj4yfp5ob9sq` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKasnmjar8jr5gaxvd7966p19ir` FOREIGN KEY (`authority_name`) REFERENCES `authority` (`authority_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


--
-- Table structure for table `task`
--
DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `task_id` bigint NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  KEY `FKtisaouhsp1pjc613txc886xfh` (`member_id`),
  CONSTRAINT `FKtisaouhsp1pjc613txc886xfh` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `task_time_log`
--
DROP TABLE IF EXISTS `task_time_log`;

CREATE TABLE `task_time_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `endTime` datetime DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `task_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6avfvyj8w9tnyww5p0ychh96o` (`task_id`),
  CONSTRAINT `FK6avfvyj8w9tnyww5p0ychh96o` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `allocation_time`
--
DROP TABLE IF EXISTS `allocation_time`;

CREATE TABLE `allocation_time` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `category` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hour` bigint DEFAULT NULL,
  `task_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8axjfr22pso85lm6bs2r9c2yl` (`task_id`),
  CONSTRAINT `FK8axjfr22pso85lm6bs2r9c2yl` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


--
-- Table structure for table `hashtag`
--
DROP TABLE IF EXISTS `hashtag`;

CREATE TABLE `hashtag` (
  `hashtag_id` bigint NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`hashtag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `tasktag`
--
DROP TABLE IF EXISTS `tasktag`;

CREATE TABLE `tasktag` (
  `tasktag_id` bigint NOT NULL AUTO_INCREMENT,
  `hashtag_id` bigint DEFAULT NULL,
  `task_id` bigint DEFAULT NULL,
  PRIMARY KEY (`tasktag_id`),
  KEY `FKb2fytwrbbnkv6yprb1guyhc7` (`hashtag_id`),
  KEY `FKpi0afj83kadl9u7yh04qopuxy` (`task_id`),
  CONSTRAINT `FKb2fytwrbbnkv6yprb1guyhc7` FOREIGN KEY (`hashtag_id`) REFERENCES `hashtag` (`hashtag_id`),
  CONSTRAINT `FKpi0afj83kadl9u7yh04qopuxy` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `chatroom`
--
DROP TABLE IF EXISTS `chatroom`;

CREATE TABLE `chatroom` (
  `room_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


--
-- Table structure for table `chatmessage`
--
DROP TABLE IF EXISTS `chatmessage`;

CREATE TABLE `chatmessage` (
  `message_id` bigint NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `room_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `FKkxv7m090gqp20qpnq3cg1ibaw` (`room_id`),
  KEY `FKh9m5a2qqy5f8k2b82exh5dswf` (`member_id`),
  CONSTRAINT `FKh9m5a2qqy5f8k2b82exh5dswf` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKkxv7m090gqp20qpnq3cg1ibaw` FOREIGN KEY (`room_id`) REFERENCES `chatroom` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
