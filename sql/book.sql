charset utf8;
USE springmvcDB;

CREATE TABLE IF NOT EXISTS book (
    b_bookId VARCHAR(10) NOT NULL,
    b_name VARCHAR(30),
    b_unitPrice INTEGER,
    b_author VARCHAR(50),
    b_description TEXT,
    b_publisher VARCHAR(20),
    b_category VARCHAR(20),
    b_unitsInStock LONG,
    b_releaseDate VARCHAR(20),
    b_condition VARCHAR(20),
    b_fileName VARCHAR(20),
    PRIMARY KEY (b_bookId)
) DEFAULT CHARSET=utf8;

DELETE FROM book;

INSERT INTO  book VALUES ('ISBN1234', 'C#교과서', 30000, '박용준', 'C# 교과서』는 생애 첫 프로그래밍 언어로 C#을 시작하는 독자를 대상으로 한다. 특히 응용 프로그래머를 위한 C# 입문서로, C#을 사용하여 게임(유니티), 웹, 모바일, IoT 등을 개발할 때 필요한 C# 기초 문법을 익히고 기본기를 탄탄하게 다지는 것이 목적이다.', '길벗','IT전문서',1000,'2020/05/29','','ISBN1234.png');
INSERT INTO  book VALUES ('ISBN1235', 'Node.js 교과서', 36000, '조현영', '이 책은 프런트부터 서버, 데이터베이스, 배포까지 아우르는 광범위한 내용을 다룬다. 군더더기 없는 직관적인 설명으로 기본 개념을 확실히 이해하고, 노드의 기능과 생태계를 사용해보면서 실제로 동작하는 서버를 만들어보자. 웹 서버, 웹 API 서버, 노드 서비스 테스트, SNS 서비스, 실시간 GIF 채팅방, 경매 시스템, 커맨드라인 인터페이스까지 경험할 수 있으며, 예제와 코드는 최신 문법을 사용해 실무에 바로 참고하거나 적용할 수 있게 했다.', '길벗','IT전문서',1000,'2020/07/25','','ISBN1235.png');
INSERT INTO  book VALUES ('ISBN1236', '어도비 XD CC 2020', 25000, '김두한', '사용자가 단 한 권으로, 쉽고 빠르게 어도비 XD를 배울 수 있도록 구성한 책이다. 어도비 XD는 모바일 환경과 다양한 콘텐츠의 발달로 UI/UX 디자인을 쉽고 빠르게 구현하고자 만들어진 프로그램으로, 〈어도비 XD CC 2020 무작정 따라하기〉는 UI/.UX 디자인 작업 전 미리 알아두어야 할 이론을 익히고, 기본 기능과 예제를 무작정 따라하여 기초를 탄탄히 쌓을 수 있으며, 실무 예제를 따라하여 어도비 XD를 제대로 활용할 수 있도록 도와준다.', '길벗','IT활용서',1000,'2019/05/29','','ISBN1236.png');