create sequence task_seq start with 1 increment by 1;
create sequence user_seq start with 1 increment by 1;

create table task (
    id bigint not null,
    title varchar(255),
    description varchar(255),
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

-- insert into task_user(id, name) values
--     (nextval('user_seq'), 'Marcio Marinho'),
--     (nextval('user_seq'), 'Peter Pan'),
--     (nextval('user_seq'), 'Elvis Presley');
--
-- insert into task(id, title, description, user_id) values
--     (nextval('task_seq'), 'A simple task', 'Something really simple to accomplish', (select id from task_user where name='Marcio Marinho')),
--     (nextval('task_seq'), 'Another task', 'This one is still not too hard', (select id from task_user where name='Peter Pan')),
--     (nextval('task_seq'), 'Hardcore task', 'Here you will feel pain and despair', (select id from task_user where name='Elvis Presley'));