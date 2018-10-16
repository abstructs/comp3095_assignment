DROP DATABASE IF EXISTS COMP3095;

CREATE DATABASE IF NOT EXISTS COMP3095;
USE COMP3095;
-- grant all priviledges on COMP3095.* to 'root'@'localhost' identified by 'admin'; 

GRANT ALL PRIVILEGES ON COMP3095.* TO 'root'@'localhost' WITH GRANT OPTION;

CREATE TABLE USERS 
( 
	id int(11) AUTO_INCREMENT PRIMARY KEY, 
	firstname varchar(255),
	lastname varchar(255),
	email varchar(255), 
	role varchar(20),
	created timestamp default current_timestamp,
	password varchar(20)	
);

INSERT INTO `USERS` (`firstname`, `lastname`, `email`, `role`, `password`) VALUES (NULL, NULL, 'admin@domain.ca', NULL, 'P@ssword1');