

CREATE DATABASE IF NOT EXISTS `forum`;
USE `forum`;



DROP TABLE IF EXISTS `user_role` ;
CREATE TABLE `user_role`(
                            `user_id` INTEGER  ,
                            `role_id` INTEGER
);


DROP TABLE IF EXISTS `user_privilege` ;
CREATE TABLE `user_privilege`(
                                 `user_id` INTEGER   ,
                                 `privilege_id` INTEGER
);


DROP TABLE IF EXISTS `role_privilege` ;
CREATE TABLE `role_privilege`(
                                 `role_id` INTEGER   ,
                                 `privilege_id` INTEGER
);



DROP TABLE IF EXISTS `role` ;
CREATE TABLE `role`(
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL
);


DROP TABLE IF EXISTS `privilege` ;
CREATE TABLE `privilege`(
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL
);



DROP TABLE IF EXISTS `topic_response` ;
CREATE TABLE `topic_response`(
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `text` TEXT NOT NULL,
    `user_id` INTEGER NOT NULL,
    `timestamp` BIGINT NOT NULL,
    `topic_id` INTEGER NOT NULL
);

DROP TABLE IF EXISTS `topic` ;
CREATE TABLE `topic`(
                        `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
                        `title` VARCHAR(20) NOT NULL,
                        `text` TEXT NOT NULL,
                        `user_id` INTEGER NOT NULL,
                        `timestamp` BIGINT NOT NULL
);


DROP TABLE IF EXISTS `user` ;
CREATE TABLE `user`(
                       `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
                       `user_name` VARCHAR(20) NOT NULL ,
                       `password` TEXT NOT NULL ,
                       `first_name` VARCHAR(20) NOT NULL ,
                       `last_name` VARCHAR(20) NOT NULL
);



ALTER TABLE user_role ADD CONSTRAINT FK_user_user_role FOREIGN KEY (user_id) REFERENCES user (id);
ALTER TABLE user_role ADD CONSTRAINT FK_role_user_role FOREIGN KEY (role_id) REFERENCES role (id);


ALTER TABLE user_privilege ADD CONSTRAINT FK_user_user_privilege FOREIGN KEY (user_id) REFERENCES user (id);
ALTER TABLE user_privilege ADD CONSTRAINT FK_privilege_user_privilege FOREIGN KEY (privilege_id) REFERENCES privilege (id);


ALTER TABLE role_privilege ADD CONSTRAINT FK_role_role_privilege FOREIGN KEY (role_id) REFERENCES role (id);
ALTER TABLE role_privilege ADD CONSTRAINT FK_privilege_role_privilege FOREIGN KEY (privilege_id) REFERENCES privilege (id);


ALTER TABLE topic ADD CONSTRAINT FK_user_topic FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE topic_response ADD CONSTRAINT FK_user_topic_response FOREIGN KEY (user_id) REFERENCES user (id);
ALTER TABLE topic_response ADD CONSTRAINT FK_topic_topic_response FOREIGN KEY (topic_id) REFERENCES topic (id);




INSERT INTO user(user_name,password,first_name,last_name) VALUES ('admin','admin','admin','admin');
INSERT INTO user(user_name,password,first_name,last_name) VALUES ('user','user','user','user');
INSERT INTO user(user_name,password,first_name,last_name) VALUES ('visitor','visitor','visitor','visitor');


INSERT INTO privilege(name) VALUES ('deleteTopic');
INSERT INTO privilege(name) VALUES ('writeTopic');
INSERT INTO privilege(name) VALUES ('deleteResponse');
INSERT INTO privilege(name) VALUES ('writeResponse');


INSERT INTO role(name) VALUES ('Admin');
INSERT INTO role(name) VALUES ('User');
INSERT INTO role(name) VALUES ('Visitor');


INSERT INTO role_privilege(role_id, privilege_id) VALUES (1,1);
INSERT INTO role_privilege(role_id, privilege_id) VALUES (1,2);
INSERT INTO role_privilege(role_id, privilege_id) VALUES (1,3);
INSERT INTO role_privilege(role_id, privilege_id) VALUES (1,4);

INSERT INTO role_privilege(role_id, privilege_id) VALUES (2,2);
INSERT INTO role_privilege(role_id, privilege_id) VALUES (2,3);
INSERT INTO role_privilege(role_id, privilege_id) VALUES (2,4);

INSERT INTO role_privilege(role_id, privilege_id) VALUES (3,4);


INSERT INTO user_role(user_id, role_id) VALUES (1,1);
INSERT INTO user_role(user_id, role_id) VALUES (2,2);
INSERT INTO user_role(user_id, role_id) VALUES (3,3);