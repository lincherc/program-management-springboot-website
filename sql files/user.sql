drop database if exists pmp;
create database pmp;
use pmp;

drop table if exists `user`;
create table `user` (
`user_id` int not null AUTO_INCREMENT,     
`user_name` varchar(255) default null,
`avatar` varchar(255) default null,     
`email` varchar(255) not null,     
`password` varchar(255) default null,
`reset_token` varchar(255) default null,
`enable` boolean not null default true,
`create_date` date,
`modified_date` date,
primary key(`user_id`),
unique key UK_EMAIL(`email`) )
ENGINE=InnoDB
AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

insert into user(user_id,user_name,email,password) values 
(1,"joshua","joshua@dataapplab.com","abcdefg"),
(2,"peize","peize@dataapplab.com","123456"),
(3,"shen","shen@dataapplab.com","67890");
