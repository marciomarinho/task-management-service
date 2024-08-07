create sequence task_seq start with 1 increment by 50;
create sequence user_seq start with 1 increment by 50

create table task (
    id bigint not null,
    description varchar(255),
    title varchar(255),
    user_id integer unique,
    primary key (id)
);

create table task_user (
    id integer not null,
    name varchar(255),
    primary key (id)
);

alter table if exists task
    add constraint FK_USER_TASK
    foreign key (user_id)
    references task_user;