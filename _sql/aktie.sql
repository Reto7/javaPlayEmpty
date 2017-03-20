create sequence aktien_seq start 1;

create table Aktie (
                      id integer default nextval('aktien_seq') PRIMARY KEY
                    , name text
                    , kurs int)
;

insert into Aktie(name,kurs) values('NESN',15);
insert into Aktie(name,kurs) values('GOOG',17);

select *
from Aktie
;