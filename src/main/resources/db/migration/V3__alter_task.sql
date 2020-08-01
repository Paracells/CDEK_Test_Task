alter table task
    add column number serial;

update task set number=2847125 where id=0;
update task set number=0 where id=1;
update task set number=67264 where id=2;
