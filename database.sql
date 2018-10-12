DELETE DATABASE IF EXISTS COMP3095;

CREATE DATABASE IF NOT EXISTS COMP3095;
USE COMP3095;
grant all on COMP3095.* to 'admin'@'localhost' identified by 'admin'; 

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

INSERT INTO `USERS` (`firstname`, `lastname`, `email`, `role`, `password`) VALUES
(NULL, NULL, `admin@domain.ca`, NULL, `P@ssword1`);
