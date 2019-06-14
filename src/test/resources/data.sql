delete from SCHEDULE
delete from delay
delete from line
delete from stop

insert into LINE (id, name) select * from CSVREAD ('classpath:lines.csv')
insert into STOP (id, x, y) select * from CSVREAD ('classpath:stops.csv')
insert into SCHEDULE (line_id, stop_id, time) select * from CSVREAD ('classpath:times.csv')
insert into DELAY (line_name, delay) select * from CSVREAD ('classpath:delays.csv')