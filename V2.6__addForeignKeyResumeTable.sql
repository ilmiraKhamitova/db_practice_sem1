alter table resume 
add foreign key (user_id) references usr (user_id) on delete cascade on update cascade,
add foreign key (place_id) references walkingarea (id) on update cascade on delete set null
