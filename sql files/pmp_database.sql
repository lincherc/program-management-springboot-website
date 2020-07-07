drop database if exists pmp;
create database pmp default charset=utf8mb4;
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
unique key UK_EMAIL(`email`)
)engine=InnoDB auto_increment=4;

insert into `user`(`user_id`,`user_name`,`avatar`,`email`,`password`) values 
(1,"joshua","admin","joshua@shu.com","1234"),
(2,"peize","审核员","peize@shu.com","1234"),
(3,"roster","项目负责人","roster@shu.com","1234");

drop table if exists `role`;
create table `role`(
	`role_id` int not null auto_increment,
    `name` varchar(255) default null,
    `description` varchar(255) default null,
    primary key(`role_id`)
)engine=InnoDB auto_increment=5;

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
    )engine=InnoDB auto_increment=6;
    
insert into `user_role`
    (`id`,`user_id`,`role_id`)
values
    (1,1,3),
	(2,1,2),
	(3,1,1),
	(4,2,1),
    (5,3,2);
    
create table `image`(
	`image_id` int not null auto_increment,
    `image_url` varchar(255) not null,
    `image_content` varchar(255) default null,
	primary key(`image_id`)
)engine=InnoDB auto_increment=1;

drop table if exists `project`;
create table `project`(
	`project_id` int not null auto_increment,
    `project_name` varchar(255) default null,
    `project_introduction` varchar(500) default null,
    `is_passed` int not null default 0, 
    `file_url` varchar(255) default null,
    primary key(`project_id`)
) engine=InnoDB auto_increment=5;

insert into `project`
(`project_id`,`project_name`,`project_introduction`)
values
(1,'闲云潭影','习惯城市中学习生活，只有在逢年过节之余回到乡下。 这件水中丞，从选材到制作，总共花了两天时间。细细想来，能够有时间在乡下慢慢做手工，幸运而又幸福。抛光，上蜡，做成之后，也有一种成就感。 半壁山房待明月，一盏清茗酬知音。做完手工当天晚上，邀儿时好友于书房中品茶谈书，感慨往事。兴起，拿出自制的水中丞，滴水研墨，写上几幅蹩脚的毛笔字，突然感觉到由衷的快乐。 大千世界中花花绿绿，还不及小小书房中一份从容，一份宁静。 弱水三千，我愿取这一瓢饮。'),
(2,'好好学学','贪官污吏的产生，根本原因在于封建社会的专制统治以及阶级对立。以酷刑整治贪腐之风，固可收一时之效，却无法从根本上杜绝腐败现象，这是历史的必然规律。严法酷刑不能防止腐败，只有斩断利益关系、消灭阶级关系对立才能彻底消灭腐败，这也是“重典治国”的明法思想给现代法律体系一些警示和教训。'),
(3,'明法思想与明朝兴衰','朱元璋理所应当地认为酷刑能够震慑官民，维系朝纲，但却忽视了其危害性。酷刑在短期时间确实有极大的正面作用，然而，重典的最初作用力越强，其受用波及越广，影响便越深；停滞症状维持时间越长久，反弹危害便越大。'),
(4,'阶级下的人性','艾丝黛拉是一个极具讽刺意味的角色。她破坏了人们对于浪漫爱情的观念，狄更斯借艾斯黛拉这一形象表达了对深陷阶级社会的人们的辛辣批判。通常狄更斯对待这类角色是毫不留情的，他给她安排了具有讽刺意味的两个强烈反差：虽然艾丝黛拉是匹普遇见的第一个上层社会的理想女子，实际上艾丝黛拉的真实出身比匹普还要低下；上流社会的生活并没有拯救艾丝黛拉，相反，她两次成为收养她的上流社会的牺牲品。');

create table `image_project`(
    `id` int not null auto_increment,
    `image_id` int not null,
    `project_id` int not null,
    primary key(`id`),
    key `image_id`(`image_id`),
    key `project_id`(`project_id`),
	constraint `image_project_fk_2` foreign key (`project_id`) references `project` (`project_id`) on delete cascade on update cascade,
	constraint `image_project_fk_1` foreign key (`image_id`) references `image` (`image_id`) on delete cascade on update cascade
)engine=InnoDB auto_increment=1;

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
)engine=InnoDB auto_increment=5;

insert into `user_project`
	(`id`,`user_id`,`project_id`)
values
	(1,1,1),
    (2,1,2),
    (3,1,3),
    (4,1,4);