alter table techsupportmessage 
add foreign key (employee_id) references employee (id) on delete cascade on update cascade,
add foreign key (user_id) references usr (user_id) on delete cascade on update cascade;

