CREATE DATABASE HardwareHamlet;

CREATE TABLE medals(
    medal_id int not null PRIMARY KEY AUTO_INCREMENT,
    name varchar(100) not null,
    icon varchar(100) not null,
    amount_likes int not null
);

CREATE TABLE titles(
    title_id int not null PRIMARY KEY AUTO_INCREMENT,
    name varchar(100) not null,
    rep_amount int not null,
	color varchar(50) not null
);

CREATE TABLE user_type(
    usertype_id int not null PRIMARY KEY AUTO_INCREMENT,
    name varchar(100) not null
);

CREATE TABLE component_type(
    component_type_id int not null PRIMARY KEY AUTO_INCREMENT,
    name varchar(100) not null
);

CREATE TABLE build_type(
    buildtype_id int not null PRIMARY KEY AUTO_INCREMENT,
    name varchar(100) not null
);

CREATE TABLE users(
    user_id int not null PRIMARY KEY AUTO_INCREMENT,
    title_id int not null,
    usertype_id int not null,
    medal_id int not null,
    email varchar(100) not null,
    username varchar(100) not null,
    password varchar(100) not null,
    description varchar(500) not null,
    reputation int not null,
	active boolean not null,
	validation_code varchar(12) not null,
    FOREIGN KEY (title_id) REFERENCES titles(title_id),
    FOREIGN KEY (usertype_id) REFERENCES user_type(usertype_id),
    FOREIGN KEY (medal_id) REFERENCES medals(medal_id)
);

CREATE TABLE components(
    component_id int not null PRIMARY KEY AUTO_INCREMENT,
    component_type_id int not null,
    user_id int not null,
    brand varchar(100) not null,
    name varchar(150) not null,
    description varchar(2000) not null,
    price double not null,
    flg_available boolean not null,
	icon_url varchar(255) not null,
    FOREIGN KEY (component_type_id) REFERENCES component_type(component_type_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE builds(
    build_id int not null PRIMARY KEY AUTO_INCREMENT,
    user_id int not null,
    build_type_id int not null,
    name varchar(100) not null,
    description varchar(500) not null,
    cpu_description varchar(500) not null,
    gpu_description varchar(500) not null,
    ram_description varchar(500) not null,
	price int not null,
    likes int not null,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (buildtype_id) REFERENCES build_type(buildtype_id)
);

CREATE TABLE build_components(
    build_id int not null,
    component_id int not null,
    PRIMARY KEY(build_id, component_id),
    FOREIGN KEY(build_id) REFERENCES builds(build_id),
    FOREIGN KEY(component_id) REFERENCES components(component_id)
);

CREATE TABLE comments(
    comment_id int not null PRIMARY KEY AUTO_INCREMENT,
    build_id int not null,
    content varchar(500) not null,
    FOREIGN KEY (build_id) REFERENCES builds(build_id)
);