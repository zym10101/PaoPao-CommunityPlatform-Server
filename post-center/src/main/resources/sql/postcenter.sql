CREATE DATABASE `postcenter` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION = 'N' */;
use postcenter;


CREATE TABLE `post`
(
    `post_id`         bigint       NOT NULL AUTO_INCREMENT,
    `is_public`       boolean      NOT NULL,
    `tag_list`         NOT NULL,
    `email`           varchar(100) NOT NULL,
    `create_time`     date         NOT NULL,
    `last_login_time` date DEFAULT NULL,
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;