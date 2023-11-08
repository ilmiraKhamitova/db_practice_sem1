alter table vacancies
alter column description set not null,
add constraint description check (description <> ''),
alter column pet_id set not null,
alter column user_id set not null,
alter column publication_date set not null,
add constraint salary check (salary is null or (salary is not null and salary>0));









        
