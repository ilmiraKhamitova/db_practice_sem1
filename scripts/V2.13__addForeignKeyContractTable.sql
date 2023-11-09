alter table contract
add foreign key (employer_id) references usr (user_id) on delete cascade on update cascade,
add foreign key (employee_id) references usr (user_id) on delete cascade on update cascade;