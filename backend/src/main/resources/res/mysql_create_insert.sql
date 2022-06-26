use ssafyweb;

-- notice: 공지사항
create table notice (
nid int not null auto_increment primary key,
username varchar(30),
title varchar(50),
content varchar(200),
hit int default 0,
regtime datetime default now()
);

insert into notice(username, title, content) values ("admin", "공지 제목 테스트", "내용 테스트");
insert into notice(username, title, content) values ("admin", "공지 제목 테스트", "내용 테스트");
insert into notice(username, title, content) values ("admin", "공지 제목 테스트", "내용 테스트");
insert into notice(username, title, content) values ("admin", "공지 제목 테스트", "내용 테스트");
insert into notice(username, title, content) values ("admin", "공지 제목 테스트", "내용 테스트");
insert into notice(username, title, content) values ("admin", "공지 제목 테스트", "내용 테스트");
insert into notice(username, title, content) values ("admin", "공지 제목 테스트", "내용 테스트");


-- qna: Q&A 게시판
create table qna(
qid int not null auto_increment primary key,
username varchar(30),
title varchar(50),
content varchar(200),
hit int default 0,
regtime datetime default now());

insert into qna(username, title, content) values ("heejin", "qna 제목 테스트", "내용 테스트");
insert into qna(username, title, content) values ("heejin", "qna 제목 테스트", "내용 테스트");
insert into qna(username, title, content) values ("heejin", "qna 제목 테스트", "내용 테스트");
insert into qna(username, title, content) values ("heejin", "qna 제목 테스트", "내용 테스트");
insert into qna(username, title, content) values ("heejin", "qna 제목 테스트", "내용 테스트");
insert into qna(username, title, content) values ("heejin", "qna 제목 테스트", "내용 테스트");
insert into qna(username, title, content) values ("heejin", "qna 제목 테스트", "내용 테스트");


-- qna_reply : Q&A 게시판 댓글
create table qna_reply(
rid int not null auto_increment primary key,
qid int,
username varchar(30),
content varchar(200),
regtime datetime default now(),
foreign key(qid)
references qna(qid));

insert into qna_reply(qid, username, content) values(1, "heejin" ,"hi");
insert into qna_reply(qid, username, content) values(1, "heejin" ,"hi 댓글 테스트");
insert into qna_reply(qid, username, content) values(3, "heejin" ,"hi");
insert into qna_reply(qid, username, content) values(8, "heejin" ,"hi");
insert into qna_reply(qid, username, content) values(8, "heejin" ,"hi");

-- user : 사용자
create table user(
kid varchar(20) not null primary key,
username varchar(30),
nickname varchar(30),
email varchar(50),
address varchar(100),
regdate datetime default now());

insert into user(kid, username, nickname, email, address) 
values("1", "heejin1", "이희진1", "bobae1998@gmail.com", "경기도 성남시");
insert into user(kid, username, nickname, email, address) 
values("2", "heejin2", "이희진2", "bobae1998@gmail.com", "경기도 성남시");
insert into user(kid, username, nickname, email, address) 
values("3", "heejin2", "이희진2", "bobae1998@gmail.com", "경기도 성남시");
insert into user(kid, username, nickname, email, address) 
values("4", "heejin2", "이희진2", "bobae1998@gmail.com", "경기도 성남시");



-- 개인 테스트용
delete from user where kid="2250600465";
select * from user;
select * from notice;
select content from notice where nid=16;
select * from user where kid="1";
delete from user where kid=1;
select * from qna;
select * from qna_reply;
delete from qna_reply where rid=7;

delete from qna where qid="16";
alter table qna_reply
add constraint qna_reply_fk
foreign key(qid)
references qna(qid)
on delete cascade;

    