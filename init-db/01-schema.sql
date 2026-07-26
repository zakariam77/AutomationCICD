create table IF NOT EXISTS testingdata
(
    id int primary key auto_increment,
    username varchar(50),
    userpass varchar(50)
);
insert into testingdata (username, userpass)
values  ("username1", "username1pass"),
        ("username2", "username2pass");