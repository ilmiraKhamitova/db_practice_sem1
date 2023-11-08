alter table techsupportmessage
add constraint message_length check (text <> '');
