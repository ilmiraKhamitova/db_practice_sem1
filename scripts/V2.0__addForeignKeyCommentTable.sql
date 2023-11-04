alter table comment
add foreign key (post_id) references post (id) on delete cascade on update cascade, add foreign key (user_id) references usr (user_id) on delete cascade on update cascade

