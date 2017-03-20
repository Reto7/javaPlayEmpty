create sequence aktien_seq start 1;

create table aktie_t (
                      id integer default nextval('aktien_seq') PRIMARY KEY
                    , name text
                    , kurs int)

insert into aktie_t(name,kurs) values('NESN',15);
insert into aktie_t(name,kurs) values('GOOG',17);

select *
from aktie_t
;