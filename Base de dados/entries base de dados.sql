insert into medals (name,icon,amount_likes)
values
('','',0),
('Begginer', '', 5),
('Intermediate','', 50),
('Semi-pro','', 500),
('Pro Builder','',2500),
('Build God All Mighty','',5000);

insert into titles (name,rep_amount, color)
values
('Member',0,''),
('Researcher',150,'#00ff00'),
('Scavenger',500,'#0000ff'),
('Moderator',2500,'#cc0099'),
('Partner',5000,'#dcd045'),
('Administrator',9999,'#09f4e1');

insert into user_type (name)
values
('common'),
('partner'),
('admin');


insert into build_type (name)
values
('Budget'),
('Gaming'),
('Gaming EXPERT'),
('Designer/Editer'),
('Workstation'),
('Farming');

insert into component_type (name)
values
('Processador'),
('Placa gráfica'),
('Motherboard'),
('Ventoinha'),
('Cooler CPU'),
('Fonte de alimentação'),
('Disco SSD'),
('Disco HDD'),
('Disco SSHD'),
('Memória RAM'),
('Caixa');

insert into users (title_id,usertype_id,medal_id,email,username,password,description,reputation)
values
(6,3,1,'hardwarehamlet.mail@gmail.com', 'hardwarehamlet','hardwarehamlet.arm123','Administrators of Hardware Hamlet', 9999);