CREATE DATABASE flourstorage;
USE flourstorage;

CREATE TABLE User (
userid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(150) NOT NULL,
password VARCHAR(50) NOT NULL
);

CREATE TABLE Flour (
flourid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
flour VARCHAR(45) NOT NULL,
user_userid INT NOT NULL,
FOREIGN KEY (user_userid) REFERENCES User(userid)
);
