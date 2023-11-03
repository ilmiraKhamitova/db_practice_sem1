alter table comment
alter column publication_date set not null,
add constraint max_caomment_publication_date check(publication_date < current_timestamp),
alter column comment_text set not null,
alter column post_id set not null,
alter column user_id set not null
