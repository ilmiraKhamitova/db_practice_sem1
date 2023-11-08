alter table review
add foreign key (user_id) references usr (user_id) on delete cascade on update cascade, 
add foreign key (worker_id) references usr (user_id)