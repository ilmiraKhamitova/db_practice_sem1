alter table advertisers
alter column company set not null,
add constraint company check (company<>''),
alter column description set not null,
add constraint description check (description<>''),
alter column publication_date set not null,
alter column contract_price set not null;

