drop table if exists `role`;
create table `role`(
	`role_id` int not null auto_increment,
    `name` varchar(255) default null,
    `description` varchar(255) default null,
    primary key(`role_id`)
) engine=InnoDB auto_increment=4 default charset=utf8mb4;

insert into `role`
(`role_id`,`name`,`description`)
values
(1,'ROLE_CHECKER','authorization for checker'),
(2,'ROLE_PETITIONER','authorization for petitioner'),
(3,'ROLE_ADMIN','platform admin'),
(4,'ROLE_ROOT','root for developer');

drop table if exists `user_role`;
create table `user_role`(
	`id` int not null auto_increment,
    `user_id` int default null,
    `role_id` int default null,
    primary key(`id`),
    key `user_id` (`user_id`),
    key `role_id` (`role_id`),
    constraint `user_role_fk_2` foreign key (`role_id`) references `role` (`role_id`) on delete cascade on update cascade,
	constraint `user_role_fk_1` foreign key (`user_id`) references `user` (`user_id`) on delete cascade on update cascade
    )engine=InnoDB auto_increment=6 default charset=utf8mb4;
    
    insert into `user_role`
    (`id`,`user_id`,`role_id`)
    values
    (1,1,3),
	(2,1,2),
	(3,1,1),
	(4,2,1);
    
    
    
    