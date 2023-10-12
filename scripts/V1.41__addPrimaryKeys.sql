alter table post add column id bigint primaty key;
alter table comment add column id bigint primary key;
alter table walkingArea add column id bigint primary key;

create sequence posts_seq;
alter table post alter column id set default nextval('posts_seq');

create sequence comments_seq;
alter table comment column id set default nextval('comments_seq');

create sequence walking_area_seq;
alter table walkingArea column id set default nextval('walking_area_seq');
