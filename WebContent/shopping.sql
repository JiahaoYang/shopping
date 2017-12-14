create database `shopping1`;
use `shopping1`;

create table classify
(
       gid   int primary key auto_increment,
       gname varchar(50) not null
);

create table commodity
(
    commodity_number int primary key auto_increment,
    commodity_name   varchar(50) not null,
    commodity_made   varchar(50),
    commodity_price  double not null,
    commodity_balance double not null,
    commodity_pic    double not null,
    commodity_id int not null references classify(gid)
);	

create table orderForm 
(
     id int primary key auto_increment,
     username varchar(50) not null,
     commodity_name varchar(50) not null,
     commodity_price double not null,
     orderDate DATE  NOT NULL,
     sum  double 
);

create table vip
(
       username varchar(50) primary key,
       userpass varchar(50) not null,
       phone    varchar(20),
       address  varchar(50),
       realname varchar(50)
);