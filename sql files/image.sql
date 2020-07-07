create table image(
	image_id int not null auto_increment,
    image_url varchar(255) not null,
    image_content varchar(255) default null,
	primary key(image_id)
    )engine=InnoDB auto_increment=1 default charset=utf8mb4;
    
    create table image_project(
    id int not null auto_increment,
    image_id int not null,
    project_id int not null,
    primary key(id),
    key image_id(image_id),
    key project_id(project_id),
	constraint `image_project_fk_2` foreign key (`project_id`) references `project` (`project_id`) on delete cascade on update cascade,
	constraint `image_project_fk_1` foreign key (`image_id`) references `image` (`image_id`) on delete cascade on update cascade
    )engine=InnoDB auto_increment=1 default charset=utf8mb4;
