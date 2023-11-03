alter table walkingarea
alter column latitude set not null,
add constraint latitude_value check(latitude between 0 and 180),
alter column longitude set not null,
add constraint longitude_value check(longitude between 0 and 180),
alter column city set not null
