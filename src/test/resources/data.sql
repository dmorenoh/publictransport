insert into LINE (id, name) select * from CSVREAD ('classpath:lines.csv')
insert into STOP (id, x, y) select * from CSVREAD ('classpath:stops.csv')
insert into SCHEDULE (line_id, stop_id, time) select * from CSVREAD ('classpath:times.csv')