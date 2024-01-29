USE `employee_directory`;

DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `members`;

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
                           `user_id` varchar(50) NOT NULL,
                           `pw` char(68) NOT NULL,
                           `active` tinyint NOT NULL,
                           PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `members`
--

INSERT INTO `members`
VALUES
    ('eren','{bcrypt}$2a$12$rtrJfW4J6rZVywfScPaOGeC5Qy20A6CSzb0G2ZPtC0NauNVMK8/M.',1),
    ('mary','{noop}mary',1),
    ('linus','{noop}linus',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `roles` (
                         `user_id` varchar(50) NOT NULL,
                         `role` varchar(50) NOT NULL,
                         UNIQUE KEY `authorities5_idx_1` (`user_id`,`role`),
                         CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `roles`
--

INSERT INTO `roles`
VALUES
    ('eren','ROLE_EMPLOYEE'),
    ('mary','ROLE_EMPLOYEE'),
    ('mary','ROLE_MANAGER'),
    ('linus','ROLE_EMPLOYEE'),
    ('linus','ROLE_MANAGER'),
    ('linus','ROLE_ADMIN');
