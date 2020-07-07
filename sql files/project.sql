drop table if exists `project`;
create table `project`(
	`project_id` int not null auto_increment,
    `project_name` varchar(255) default null,
    `project_introduction` varchar(255) default null,
    `is_passed` int not null default 0, 
    `file_url` varchar(255) default null,
    primary key(`project_id`)
) engine=InnoDB auto_increment=5 default charset=utf8mb4;

insert into `project`
(`project_id`,`project_name`,`project_introduction`)
values
(1,'shoppingwebsite','a gratewebsite for shopping sjfjd and wen dkjfd dsafdsfdsafasdf'),
(2,'algotithm','new tec for java jvmsdf sadfdsfddsfafccdssdfadsf dsadfa fdfasdfsdfdsfcx'),
(3,'operatingsystem','a idea to develop a operating system ddfdsfdffdsafadsfasdf dsafdsf sdfsdafsdaf'),
(4,'chattingroom','dating for university students  dating dsfsdafsadfsadfdsfsa sadfsdafsdafadsf asdfdsaf');

drop table if exists `user_project`;
create table `user_project`(
	`id` int not null auto_increment,
    `user_id` int not null,
    `project_id` int not null,
    primary key(`id`),
    key `user_id` (`user_id`),
    key `project_id` (`project_id`),
    constraint `user_project_fk_2` foreign key (`project_id`) references `project` (`project_id`) on delete cascade on update cascade,
	constraint `user_project_fk_1` foreign key (`user_id`) references `user` (`user_id`) on delete cascade on update cascade
    )engine=InnoDB auto_increment=3 default charset=utf8mb4;
    
    insert into `user_project`
    (`id`,`user_id`,`project_id`)
    values
	(1,2,1),
    (2,3,1);
    
    
    
    