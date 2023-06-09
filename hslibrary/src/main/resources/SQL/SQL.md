
create database hslibrary;
show databases;
use hslibrary;

###Table : Notice

CREATE TABLE `Notice` (
`NoticeNum` int NOT NULL AUTO_INCREMENT COMMENT '공지사항 번호',
`NoticeTitle` varchar(45) NOT NULL COMMENT '공지사항 제목',
`NoticeAuthor` varchar(45) NOT NULL COMMENT '부서, 이름 (사무실 전화번호) 순',
`NoticeMain` varchar(255) NOT NULL COMMENT '본문 ',
`NoticeDate` date NOT NULL,
PRIMARY KEY (`NoticeNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='공지사항 페이지';

###Table : book

CREATE TABLE `book` (
`bookID` varchar(45) NOT NULL,
`bookName` varchar(45) NOT NULL,
`bookWriter` varchar(45) NOT NULL,
`bookGenre` varchar(45) NOT NULL COMMENT '도서 장르',
`bookCompany` varchar(45) DEFAULT NULL COMMENT '출판사',
`bookISBN` varchar(45) NOT NULL COMMENT '국제 도서 등록 번호',
`bookYear` varchar(45) DEFAULT NULL COMMENT '출간 연도',
`bookEdition` varchar(45) NOT NULL COMMENT '책 출간 판, 1판,2판,3판',
`bookVolume` varchar(45) DEFAULT NULL COMMENT '책 " "권, 시리즈 계열 1권,2권,3권 등등',
`bookIssue` varchar(10) DEFAULT NULL COMMENT '잡지 호',
`bookSummary` varchar(255) DEFAULT NULL,
`bookType` int DEFAULT NULL COMMENT '전자책/실제 발간 책 형태',
`bookRegister` date NOT NULL COMMENT '도서 도서관 등록 날짜',
PRIMARY KEY (`bookID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

###Table:client_Information

CREATE TABLE `client_Information` (
`clientNUM` varchar(45) NOT NULL COMMENT '회원 번호',
`clientName` varchar(45) NOT NULL COMMENT '회원 이름',
`clientID` varchar(14) NOT NULL COMMENT '회원 주민번호',
`clientPhone` varchar(20) NOT NULL COMMENT '회원 전화번호',
`clientAddr` varchar(255) DEFAULT NULL,
`clientEmail` varchar(45) NOT NULL COMMENT '회원 Emai',
`clientRegister` date DEFAULT NULL,
PRIMARY KEY (`clientNUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


###Table : staff_Information

CREATE TABLE `staff_Information` (
`staffNUM` varchar(45) NOT NULL COMMENT '직원 번호',
`staffPW` varchar(45) NOT NULL COMMENT '로그인용 비밀번호',
`staffName` varchar(45) DEFAULT NULL,
`staffID` varchar(14) NOT NULL COMMENT '직원 주민번호',
`staffAddr` varchar(255) NOT NULL,
`staffPhone` varchar(20) NOT NULL,
`staffDepartment` varchar(45) DEFAULT NULL,
PRIMARY KEY (`staffNUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

###Table:video

CREATE TABLE `video` (
`videoID` varchar(45) NOT NULL COMMENT '''등록 번호''',
`videoName` varchar(45) NOT NULL COMMENT '''영상 이름''',
`videoDirector` varchar(45) NOT NULL COMMENT '''감독''',
`videoCompany` varchar(45) DEFAULT NULL COMMENT '''개봉 회사''',
`videoRelease` varchar(45) NOT NULL COMMENT '''개봉 연도''',
`videoGenre` varchar(45) NOT NULL COMMENT '''장르''',
`videoSequel` varchar(45) DEFAULT NULL COMMENT '''속편, 시리즈, 재개봉''\n',
`videoRegister` date DEFAULT NULL,
PRIMARY KEY (`videoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

###Table : videoRoom

CREATE TABLE `videoRoom` (
`vid_roomNum` varchar(30) NOT NULL COMMENT '영상룸 번호',
`vid_roomName` varchar(45) NOT NULL COMMENT '영상룸 이름',
PRIMARY KEY (`vid_roomNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

###Table : bookRental

CREATE TABLE `bookRental` (
`bookRentalNUM` varchar(45) NOT NULL,
`bookID` varchar(45) NOT NULL,
`clientNUM` varchar(45) NOT NULL,
`bookRental_start` date NOT NULL,
`bookRental_end` date NOT NULL,
PRIMARY KEY (`bookRentalNUM`),
KEY `fk_clientNUM_br` (`clientNUM`),
KEY `bookRental_ibfk_2` (`bookID`),
CONSTRAINT `bookRental_ibfk_1` FOREIGN KEY (`clientNUM`) REFERENCES `client_Information` (`clientNUM`),
CONSTRAINT `bookRental_ibfk_2` FOREIGN KEY (`bookID`) REFERENCES `book` (`bookID`),
CONSTRAINT `fk_clientNUM_br` FOREIGN KEY (`clientNUM`) REFERENCES `client_Information` (`clientNUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

###Table : videoRental

CREATE TABLE `videoRental` (
`videoRentalNUM` varchar(45) NOT NULL,
`videoID` varchar(45) NOT NULL,
`clientNUM` varchar(45) NOT NULL,
`videoRental_start` date NOT NULL,
`videoRental_end` date NOT NULL,
PRIMARY KEY (`videoRentalNUM`),
KEY `videoID` (`videoID`),
KEY `fk_clientNUM_vr` (`clientNUM`),
CONSTRAINT `fk_clientNUM_vr` FOREIGN KEY (`clientNUM`) REFERENCES `client_Information` (`clientNUM`),
CONSTRAINT `videoRental_ibfk_1` FOREIGN KEY (`videoID`) REFERENCES `video` (`videoID`),
CONSTRAINT `videoRental_ibfk_2` FOREIGN KEY (`clientNUM`) REFERENCES `client_Information` (`clientNUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

###Table : videoRoomRental

CREATE TABLE `videoRoomRental` (
`videoRoomRentelNUM` varchar(45) NOT NULL,
`clientNUM` varchar(45) NOT NULL,
`vid_roomNUM` varchar(30) NOT NULL,
`videoRoomRental_start` date NOT NULL,
`videoRoomRental_end` date NOT NULL,
PRIMARY KEY (`videoRoomRentelNUM`),
KEY `vid_roomNUM` (`vid_roomNUM`),
KEY `fk_clientNUM_vrR` (`clientNUM`),
CONSTRAINT `fk_clientNUM_vrR` FOREIGN KEY (`clientNUM`) REFERENCES `client_Information` (`clientNUM`),
CONSTRAINT `videoRoomRental_ibfk_1` FOREIGN KEY (`vid_roomNUM`) REFERENCES `videoRoom` (`vid_roomNum`),
CONSTRAINT `videoRoomRental_ibfk_2` FOREIGN KEY (`clientNUM`) REFERENCES `client_Information` (`clientNUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;