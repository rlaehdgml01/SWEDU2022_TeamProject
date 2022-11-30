DROP TABLE board.board;

CREATE TABLE IF NOT EXISTS `board`.board
(
  id     INT       NOT NULL AUTO_INCREMENT COMMENT 'PK',
  title   VARCHAR(100) NOT NULL COMMENT '제목',
  content   VARCHAR(20)  NOT NULL COMMENT '내용',
  PRIMARY KEY (id)
) COMMENT '게시판';