DROP DATABASE IF EXISTS SuperMarketSystem;
CREATE DATABASE IF NOT EXISTS SuperMarketSystem;
SHOW DATABASES ;
USE SuperMarketSystem;
#-------------------
DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
   id VARCHAR(6),
   title VARCHAR(5),
   name VARCHAR(30) NOT NULL DEFAULT 'Unknown',
   address VARCHAR(30),
   city VARCHAR(30),
   province VARCHAR(30),
   postalCode VARCHAR(30),
   CONSTRAINT PRIMARY KEY (id)
);
SHOW TABLES ;
DESCRIBE Customer;
#---------------------
DROP TABLE IF EXISTS `Order`;
CREATE TABLE IF NOT EXISTS `Order`(
   orderId VARCHAR(6),
   cId VARCHAR(6),
   orderDate DATE,
   time VARCHAR(15),
   cost DOUBLE ,
   CONSTRAINT PRIMARY KEY (orderId),
   CONSTRAINT FOREIGN KEY (cId) REFERENCES Customer(id) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES ;
DESCRIBE `Order`;
#-----------------------
DROP TABLE IF EXISTS Item;
CREATE TABLE IF NOT EXISTS Item(
   itemCode VARCHAR(6),
   description VARCHAR(50),
   packSize VARCHAR(20),
   qtyOnHand INT DEFAULT 0,
   unitPrice DOUBLE DEFAULT 0.00,
   CONSTRAINT PRIMARY KEY (itemCode)
);
SHOW TABLES ;
DESCRIBE Item;
#------------------------
DROP TABLE IF EXISTS `Order Detail`;
CREATE TABLE IF NOT EXISTS `Order Detail`(
   detailCode VARCHAR(6),
   detailOrderId VARCHAR(6),
   qty INT(11),
   price DOUBLE,
   discount DOUBLE,
  CONSTRAINT PRIMARY KEY (detailCode, detailOrderId),
 CONSTRAINT FOREIGN KEY (detailOrderId) REFERENCES `Order`(orderId) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES ;
DESCRIBE `Order Detail`;
