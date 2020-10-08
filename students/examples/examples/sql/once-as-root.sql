drop database if exists mydatabase;
create database mydatabase;
use mydatabase;
DROP TABLE IF EXISTS COMMENTS;
CREATE TABLE `comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `MYUSER` varchar(30) NOT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `URL` varchar(100) NOT NULL,
  `DATUM` datetime NOT NULL,
  `SUMMARY` varchar(40) NOT NULL,
  `COMMENTS` varchar(400) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;
INSERT INTO COMMENTS values (default, 'tnsilver', 'tnsilver@gmail.com','http://www.interbit.co.il', now(), 'User','My first comment' );
INSERT INTO COMMENTS values (default, 'admin', 'adminr@interbit.co.il','http://www.interbit.co.il', now(), 'Administrator','important comment' );
Drop User 'myuser'@'localhost';
Drop User 'myuser'@'%';
Create User 'myuser'@'localhost' Identified BY 'mysecret';
Create User 'myuser'@'%' Identified BY 'mysecret';
Grant All on mydatabase.* to 'myuser'@'localhost';
Grant All on mydatabase.* to 'myuser'@'%';
commit;
