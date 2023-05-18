DROP TABLE IF EXISTS `Notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Notice` (
`NoticeNum` int NOT NULL AUTO_INCREMENT COMMENT '공지사항 번호',
`NoticeTitle` varchar(45) NOT NULL COMMENT '공지사항 제목',
`NoticeDate` date NOT NULL,
`NoticeAuthor` varchar(45) NOT NULL COMMENT '부서, 이름 (사무실 전화번호) 순',
`NoticeMain` varchar(255) NOT NULL COMMENT '본문 ',
`NoticeLink` varchar(255) DEFAULT NULL COMMENT '링크할 주소란',
PRIMARY KEY (`NoticeNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='공지사항 페이지';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Notice`
--

LOCK TABLES `Notice` WRITE;
/*!40000 ALTER TABLE `Notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `Notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
`bookRegister` date NOT NULL COMMENT '도서 도서관 등록 날짜',
`bookType` varchar(45) DEFAULT NULL COMMENT '전자책/실제 발간 책 형태',
PRIMARY KEY (`bookID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--


--
-- Table structure for table `bookRental`
--

DROP TABLE IF EXISTS `bookRental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookRental`
--

LOCK TABLES `bookRental` WRITE;
/*!40000 ALTER TABLE `bookRental` DISABLE KEYS */;
INSERT INTO `bookRental` VALUES ('br0001','123','member1','2023-05-09','2023-05-23');
/*!40000 ALTER TABLE `bookRental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_Information`
--

DROP TABLE IF EXISTS `client_Information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_Information`
--

LOCK TABLES `client_Information` WRITE;
/*!40000 ALTER TABLE `client_Information` DISABLE KEYS */;
INSERT INTO `client_Information` VALUES ('member1','김회원','000330-4567891','010-3333-0000','서울시 성북구','member@mail.com','2023-04-26'),('member2','이원회','010110-3456789','010-1111-1101','서울시 도봉구','hsl123@mail.com','2023-05-09');
/*!40000 ALTER TABLE `client_Information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_Information`
--

DROP TABLE IF EXISTS `staff_Information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_Information`
--

LOCK TABLES `staff_Information` WRITE;
/*!40000 ALTER TABLE `staff_Information` DISABLE KEYS */;
INSERT INTO `staff_Information` VALUES ('1','2','3','4','5','6','7'),('admin111','admin111','정토익','010101-1234789','부산시 헤헤구','010-9999-8888','청소팀'),('stark1569','hslib456','박건달','451236-5467456','서울 강남구 성탄길 45','01156424651','운영팀');
/*!40000 ALTER TABLE `staff_Information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES ('111','222','333','444','555','666','777','2023-04-25'),('123','123','123','123','123','123','123','2023-04-26'),('videoid','우주영상','영화감독','배급사','개봉연도','장르','1','2023-04-26');
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videoRental`
--

DROP TABLE IF EXISTS `videoRental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videoRental`
--

LOCK TABLES `videoRental` WRITE;
/*!40000 ALTER TABLE `videoRental` DISABLE KEYS */;
INSERT INTO `videoRental` VALUES ('vr0001','videoid','member1','2023-05-09','2023-05-16');
/*!40000 ALTER TABLE `videoRental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videoRoom`
--

DROP TABLE IF EXISTS `videoRoom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videoRoom` (
`vid_roomNum` varchar(30) NOT NULL COMMENT '영상룸 번호',
`vid_roomName` varchar(45) NOT NULL COMMENT '영상룸 이름',
PRIMARY KEY (`vid_roomNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videoRoom`
--

LOCK TABLES `videoRoom` WRITE;
/*!40000 ALTER TABLE `videoRoom` DISABLE KEYS */;
/*!40000 ALTER TABLE `videoRoom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videoRoomRental`
--

DROP TABLE IF EXISTS `videoRoomRental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videoRoomRental`
--

LOCK TABLES `videoRoomRental` WRITE;
/*!40000 ALTER TABLE `videoRoomRental` DISABLE KEYS */;
/*!40000 ALTER TABLE `videoRoomRental` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!50606 SET GLOBAL INNODB_STATS_AUTO_RECALC=@OLD_INNODB_STATS_AUTO_RECALC */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;