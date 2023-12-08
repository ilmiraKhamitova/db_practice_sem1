create or replace view v_vacancies as
select id, description, duration, v.pet_id, user_id, place_id, publication_date, begin_date, salary, species
from vacancies as v
inner join pet as p on 
v.pet_id = p.pet_id
where begin_date > now() and position('овчарка' in species)>0;
