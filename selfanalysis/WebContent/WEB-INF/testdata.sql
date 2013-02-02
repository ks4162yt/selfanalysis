DROP TABLE IF EXISTS nameId;
DROP TABLE IF EXISTS personalInfo;

CREATE TABLE nameId
(
name VARCHAR(20) not null unique,
id int not null unique
);

CREATE TABLE personalInfo
(
id int not null,
name VARCHAR(20) not null,
appearance int not null,
chara int not null,
age int not null,
income int not null,
status int not null,
total int not null
);

INSERT INTO nameId (name,id) VALUES('tsutomu',1);
INSERT INTO personalInfo (id,name,appearance,chara,age,income,status,total) VALUES (1,'AIKO',9,7,4,5,5,8);
INSERT INTO personalInfo (id,name,appearance,chara,age,income,status,total) VALUES (1,'KEIKO',7,8,9,9,10,9);
INSERT INTO personalInfo (id,name,appearance,chara,age,income,status,total) VALUES (1,'YOSHIKO',10,6,6,6,9,10);
INSERT INTO personalInfo (id,name,appearance,chara,age,income,status,total) VALUES (1,'HANAKO',3,6,5,9,9,5);
INSERT INTO personalInfo (id,name,appearance,chara,age,income,status,total) VALUES (1,'NAOKO',6,5,5,3,4,5);
INSERT INTO personalInfo (id,name,appearance,chara,age,income,status,total) VALUES (1,'NORIKO',5,5,7,10,6,6);



