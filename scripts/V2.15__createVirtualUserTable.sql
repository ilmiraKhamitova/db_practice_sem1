create or replace view v_users_virtual as
select * from usr
where role = 'выгульщик' and rating >= 80
order by rating desc;

