alter table employee 
alter column name set not null,
alter column position set not null,
alter column salary set not null,
add constraint min_salary check(salary>=16242);

