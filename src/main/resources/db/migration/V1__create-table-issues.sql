create table issues (
    id bigint not null auto_increment,
    title varchar(100) not null,
    message text,
    created_at date,
    status varchar(100),
    primary key(id)
);