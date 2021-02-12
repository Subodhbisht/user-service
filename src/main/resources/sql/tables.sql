CREATE TABLE `users` (
  `user_Id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_Name` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `modified_by` bigint DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `record_count` bigint DEFAULT NULL,
  PRIMARY KEY (`user_Id`)
)

CREATE TABLE `Role` (
  `role_Id` int NOT NULL AUTO_INCREMENT,
  `roles` varchar(45) NOT NULL,
  `enabled` varchar(1) NOT NULL,
  `record_Count` int DEFAULT NULL,
  `created_By` int NOT NULL,
  `created_On` datetime NOT NULL,
  `modified_By` int DEFAULT NULL,
  `modified_On` datetime DEFAULT NULL,
  PRIMARY KEY (`role_Id`)
)

CREATE TABLE `UserRole` (
  `userRoleId` int NOT NULL AUTO_INCREMENT,
  `fk_user_Id` int NOT NULL,
  `fk_role_Id` int NOT NULL,
  `enabled` varchar(1) DEFAULT NULL,
  `record_count` int DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `modified_by` int DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  PRIMARY KEY (`userRoleId`)
)