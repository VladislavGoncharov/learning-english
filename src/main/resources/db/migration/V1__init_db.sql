drop table if exists english_russian_language cascade;
drop sequence if exists erl_seq;
create sequence erl_seq start 1 increment 1;
create table english_russian_language (id int8 not null, england_word varchar(255), russian_word varchar(255), primary key (id));
