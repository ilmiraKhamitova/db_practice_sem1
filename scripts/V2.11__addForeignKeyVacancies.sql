alter table vacancies 
add foreign key (pet_id) references pet(pet_id) on delete cascade on update cascade,
add foreign key (user_id) references usr(user_id) on delete cascade on update cascade,
add foreign key (place_id) references walkingarea(id) on delete set null on update cascade;

