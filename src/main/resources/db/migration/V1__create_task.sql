drop table if exists task cascade;
create table task
(
    id          bigserial not null,
    date        date,
    time        time,
    is_complete boolean   not null,
    primary key (id)
)
