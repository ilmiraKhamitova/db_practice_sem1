alter table pet
add foreign key (owner_id) references usr (user_id) on delete cascade on update cascade