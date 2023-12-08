create or replace view v_resume_virtual as
select id, u.name, u.age, u.rating, u.contacts, description, salary 
from resume as r inner join usr as u
on r.user_id = u.user_id 
where place_id = 5 and salary  <= 300;