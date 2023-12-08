create or replace view v_review as
select * from review
where publication_date > now();