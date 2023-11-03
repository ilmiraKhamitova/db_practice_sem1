alter table post 
alter column title set not null,
alter column publication_date set not null,
add constraint max_post_publication_date check(publication_date < current_timestamp)
