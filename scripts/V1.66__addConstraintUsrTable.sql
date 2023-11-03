alter table usr 
add constraint role check (role in ('хозяин', 'выгульщик')),
alter column name set not null,
add constraint age check (age >= 0),
alter column city set not null,
add constraint balance check (balance >= 0),
add constraint rating check (rating >= 0),
add constraint contacts check (length(contacts) = 18);