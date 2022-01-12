DROP DATABASE IF EXISTS HospitalManagementSystem;
CREATE DATABASE IF NOT EXISTS HospitalManagementSystem;
SHOW DATABASES ;
USE HospitalManagementSystem;

#-----------------------------------------------------
DROP TABLE IF EXISTS Doctor;
CREATE TABLE IF NOT EXISTS Doctor(
	doctorId VARCHAR(15),
	doctorName VARCHAR(45) NOT NULL DEFAULT 'Unknown',
	doctorContactNo VARCHAR(15),
	workingDate VARCHAR(15),
	workigTime VARCHAR(15),
	doctorEmail VARCHAR(15),
	roomNo VARCHAR(15),
	category VARCHAR(15),
	CONSTRAINT PRIMARY KEY (doctorId)

);
SHOW TABLES ;
DESCRIBE Doctor;

#----------------------------------------------------

DROP TABLE IF EXISTS Receptionist;
CREATE TABLE IF NOT EXISTS Receptionist(
	recepId VARCHAR(15),
	patientNo VARCHAR(15),
	recepContactNo VARCHAR(15),
	CONSTRAINT PRIMARY KEY (recepId)

);
SHOW TABLES;
DESCRIBE Receptionist;

#--------------------------------------------------------

DROP TABLE IF EXISTS Medicine;
CREATE TABLE IF NOT EXISTS Medicine(
	productCode VARCHAR(15),
	productName VARCHAR(15),
	unitPrice DOUBLE DEFAULT 0.00,
	qtyOnHand INT DEFAULT 0,
    CONSTRAINT PRIMARY KEY (productCode)

);
SHOW TABLES;
DESCRIBE Medicine;
#--------------------------------------------------------------

DROP TABLE IF EXISTS MedicalRef;
CREATE TABLE IF NOT EXISTS MedicalRef(
	refId VARCHAR(15),
	refName VARCHAR(45) NOT NULL DEFAULT 'Unknown',
	orderDate DATE,
	orderId VARCHAR(15),
	productCode VARCHAR(15)


);
SHOW TABLES;
DESCRIBE MedicalRef;


#----------------------------------------------------------------

DROP TABLE IF EXISTS Pharmacist;
CREATE TABLE IF NOT EXISTS Pharmacist(
	pharmId VARCHAR(15),
	pharmName VARCHAR(45) NOT NULL DEFAULT 'Unknown',
	drugName VARCHAR(45),
	refId VARCHAR(45),
	CONSTRAINT PRIMARY KEY (pharmId)


);
SHOW TABLES;
DESCRIBE Pharmacist;
#-------------------------------------------------------------------

DROP TABLE IF EXISTS `Order`;
CREATE TABLE IF NOT EXISTS `Order`(
   orderId VARCHAR(15),
   pharmId VARCHAR(15),
   orderDate DATE,
   time VARCHAR(15),
   cost DOUBLE,
   CONSTRAINT PRIMARY KEY (orderId),
   CONSTRAINT FOREIGN KEY (pharmId) REFERENCES Pharmacist(pharmId) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES ;
DESCRIBE `Order`;


#-------------------------------------------------------------------

DROP TABLE IF EXISTS MedicalDetail;
CREATE TABLE IF NOT EXISTS MedicalDetail(
	orderId VARCHAR(15),
	productCode VARCHAR(15),
	qty INT,
	price DOUBLE DEFAULT 0.00



);
SHOW TABLES;
DESCRIBE MedicalDetail;




#------------------------------------------------------------------------
DROP TABLE IF EXISTS Ward;
CREATE TABLE IF NOT EXISTS Ward(
    patientId VARCHAR(45),
	bedNo VARCHAR(45),
	admitDate VARCHAR(45),
	availabilityOfRooms VARCHAR(45)


);
SHOW TABLES;
DESCRIBE Ward;

#-------------------------------------------------------------
DROP TABLE IF EXISTS Nurse;
CREATE TABLE IF NOT EXISTS Nurse(
	nurseId VARCHAR(15),
	nurseName VARCHAR(45) NOT NULL DEFAULT 'Unknown',
	roomNo VARCHAR(15),
	wardNo VARCHAR(15),
	CONSTRAINT PRIMARY KEY (nurseId)

);
SHOW TABLES;
DESCRIBE Nurse;


#-------------------------------------------------------------

DROP TABLE IF EXISTS LabDetail;
CREATE TABLE IF NOT EXISTS LabDetail(
	patientId VARCHAR(15),
	bloodTypes VARCHAR(15),
	testings VARCHAR(45),
	doctorName VARCHAR(15)

);
SHOW TABLES;
DESCRIBE LabDetail;

#---------------------------------------------------------------------

DROP TABLE IF EXISTS Patient;
CREATE TABLE IF NOT EXISTS Patient(
	patientNo VARCHAR(15),
	roomNo VARCHAR(15),
	doctorName VARCHAR(45),
	patientName VARCHAR(45) NOT NULL DEFAULT 'Unknown',
	age VARCHAR(15),
	address VARCHAR(15),
	birthday VARCHAR(15),
	category VARCHAR(15),
	gender VARCHAR(15),
	wardNo VARCHAR(15),
	CONSTRAINT PRIMARY KEY (patientNo)
);
SHOW TABLES;
DESCRIBE Patient;

#-------------------------------------------------------------------

DROP TABLE IF EXISTS Payment;
CREATE TABLE IF NOT EXISTS Payment(
    billNo VARCHAR(15),
    doctorName VARCHAR(15),
    category VARCHAR(45),
    roomCharges DOUBLE DEFAULT 0.00,
    doctorCharge DOUBLE DEFAULT 0.00,
    testings DOUBLE DEFAULT 0.00,
    CONSTRAINT PRIMARY KEY (billNo)
);
SHOW TABLES;
DESCRIBE Payment;

#----------------------------------------------------------------------------

DROP TABLE IF EXISTS `Appointment`;
CREATE TABLE IF NOT EXISTS `Appointment`(
   appointmentNo VARCHAR(15),
   patientNo VARCHAR(15),
   appointmentDate DATE,
   time VARCHAR(15),
   cost DOUBLE ,
    CONSTRAINT PRIMARY KEY (appointmentNo),
   CONSTRAINT FOREIGN KEY (patientNo) REFERENCES Patient(patientNo) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES ;
DESCRIBE `Appointment`;

#-------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS `Appointment Detail`;
CREATE TABLE IF NOT EXISTS `Appointment Detail`(
    billNo VARCHAR(15),
    appointmentNo VARCHAR(15),
    roomCharges DOUBLE DEFAULT 0.00,
    doctorCharge DOUBLE DEFAULT 0.00,
    testings DOUBLE DEFAULT 0.00


);
SHOW TABLES ;
DESCRIBE `Appointment Detail`;



