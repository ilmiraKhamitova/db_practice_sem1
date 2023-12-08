create or replace view v_test_virtual as
select u.user_id, rating, v.id as vacancy_id
from usr as u inner join vacancies as v
on u.user_id = v.user_id;