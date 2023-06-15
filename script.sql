
USE sda;


CREATE TABLE Employee (
    emp_id VARCHAR(60) PRIMARY KEY,
    emp_name VARCHAR(60) NOT NULL,
    password_ VARCHAR(60) NOT NULL,
    cnic_id VARCHAR(60) NOT NULL,
    phone VARCHAR(60),
    withdraw BOOLEAN NOT NULL,
    deposit BOOLEAN NOT NULL,
    creates BOOLEAN NOT NULL,
    deletes BOOLEAN NOT NULL
);


CREATE TABLE Loan (
    Id INT PRIMARY KEY,
    amount DOUBLE NOT NULL,
    paid DOUBLE NOT NULL,
    interest INT NOT NULL,
    duration Double Not null,
    acc_id VARCHAR(60) not null
);

CREATE TABLE Loanreq (
    Id INT PRIMARY KEY,
    amount DOUBLE NOT NULL,
    repay DOUBLE NOT NULL,
    interest DOUBLE  NOT NULL,
    duration Double Not null,
    acc_id VARCHAR(60) Not null
);

CREATE TABLE Accounts (
    acc_id VARCHAR(60) PRIMARY KEY,
    balance DOUBLE NOT NULL,
    PIN INT NOT NULL,
    cnic VARCHAR(60) Not null,
    status BOOLEAN,
    lastactive Date not null,
    name VARCHAR(60) not null
);
select * from accounts;
select * from accounts;

CREATE TABLE Customer (
    cnic VARCHAR(60) PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    acc_id VARCHAR(60) NOT NULL,
    Phone VARCHAR(60),
    password VARCHAR(60),
    FOREIGN KEY (acc_id) REFERENCES Accounts(acc_id)  ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Transaction (
    T_id  int PRIMARY KEY auto_increment,
    acc_id varchar(60) NOT NULL,
    amount int Not null,
    type_ VARCHAR(60) NOT NULL,
    dates DATE NOT NULL
);


create table counts(
id int Primary key auto_increment, 
emp_count int not null,
acc_count int not null,
loan_count int not null,
loanr_count int not null

);

select * from CUSTOMER;
insert into counts values(0,0,0,0,0);