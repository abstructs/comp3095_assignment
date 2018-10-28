DROP DATABASE IF EXISTS COMP3095;

CREATE DATABASE IF NOT EXISTS COMP3095;
USE COMP3095;
-- grant all priviledges on COMP3095.* to 'root'@'localhost' identified by 'admin'; 

-- GRANT ALL PRIVILEGES ON COMP3095.* TO 'root'@'localhost' WITH GRANT OPTION;

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'P@ssword1';

-- ALTER USER 'admin'@'localhost' IDENTIFIED BY 'P@ssword1';

GRANT ALL PRIVILEGES ON COMP3095.* TO 'admin'@'localhost' WITH GRANT OPTION;

SHOW GRANTS FOR 'admin'@'localhost';

CREATE TABLE USERS 
( 
	id int(11) AUTO_INCREMENT PRIMARY KEY, 
	firstname varchar(255),
	lastname varchar(255),
	address varchar(255),
	email varchar(255), 
	role varchar(20),
	created timestamp default current_timestamp,
	password char(64)	
);

INSERT INTO `USERS` (`firstname`, `lastname`, `address`, `email`, `role`, `password`) VALUES (NULL, NULL, NULL, 'admin@domain.ca', NULL, 'P@ssword1');
