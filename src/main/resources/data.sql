insert into member(memail, mpassword, mname) values
('qwe1','1234','유재석'),
('qwe2','1234','강호동'),
('qwe3','1234','신동엽'),
('qwe4','1234','하하'),
('qwe5','1234','서장훈');

insert into board(bcontent,mno) values
('게시물내용1',1),
('게시물내용2',2),
('게시물내용3',3),
('게시물내용4',4),
('게시물내용5',5),
('게시물내용6',1);

insert into reply(rcontent,bno, mno) values
('댓글내용1',1,1),
('댓글내용2',1,1),
('댓글내용3',2,2),
('댓글내용4',2,1),
('댓글내용5',3,4),
('댓글내용6',1,5);

insert into gallery(gname, bno) values
('1.jpg',1),
('2.jpg',1),
('3.jpg',2),
('4.jpg',3),
('5.jpg',4),
('6.jpg',5),
('7.jpg',6);