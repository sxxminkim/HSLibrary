#### Table: book
| Column Name | Data Type | Nullable | Comment |
|-------------|----------|----------|---------|
| bookID      | varchar(45) | NOT NULL |         |
| bookName    | varchar(45) | NOT NULL |         |
| bookWriter  | varchar(45) | NOT NULL |         |
| bookGenre   | varchar(45) | NOT NULL | 도서 장르 |
| bookCompany | varchar(45) | DEFAULT NULL | 출판사 |
| bookISBN    | varchar(45) | NOT NULL | 국제 도서 등록 번호 |
| bookYear    | varchar(45) | DEFAULT NULL | 출간 연도 |
| bookEdition | varchar(45) | NOT NULL | 책 출간 판, 1판,2판,3판 |
| bookVolume  | varchar(45) | DEFAULT NULL | 책 " "권, 시리즈 계열 1권,2권,3권 등등 |
| bookIssue   | varchar(10) | DEFAULT NULL | 잡지 호 |
| bookSummary | varchar(255) | DEFAULT NULL |         |
| bookRegister | date       | NOT NULL | 도서 도서관 등록 날짜 |
| bookType    | varchar(45) | DEFAULT NULL | 전자책/실제 발간 책 형태 |

#### Table: client_Information
| Column Name | Data Type | Nullable | Comment |
|-------------|----------|----------|---------|
| clientNUM   | varchar(45) | NOT NULL | 회원 번호 |
| clientName  | varchar(45) | NOT NULL | 회원 이름 |
| clientID    | varchar(14) | NOT NULL | 회원 주민번호 |
| clientPhone | varchar(20) | NOT NULL | 회원 전화번호 |
| clientAddr  | varchar(255) | DEFAULT NULL |         |
| clientEmail | varchar(45) | NOT NULL | 회원 Emai |
| clientRegister | date   | DEFAULT NULL |         |

#### Table: staff_Information
| Column Name | Data Type | Nullable | Comment |
|-------------|----------|----------|---------|
| staffNUM    | varchar(45) | NOT NULL | 직원 번호 |
| staffPW     | varchar(45) | NOT NULL | 로그인용 비밀번호 |
| staffName   | varchar(45) | DEFAULT NULL |         |
| staffID     | varchar(14) | NOT NULL | 직원 주민번호 |
| staffAddr   | varchar(255) | NOT NULL |         |
| staffPhone  | varchar(20) | NOT NULL |         |
| staffDepartment | varchar(45) | DEFAULT NULL |         |

#### Table: video
| Column Name | Data Type | Nullable | Comment |
|-------------|----------|----------|---------|
| videoID     | varchar(45) | NOT NULL | '등록 번호' |
| videoName   | varchar(45) | NOT NULL | '영상 이름' |
| videoDirector | varchar(45) | NOT NULL | '감독' |
| videoCompany | varchar(45) | DEFAULT NULL | '개봉 회사' |
| videoRelease | varchar(45) | NOT NULL | '개봉 연도' |
| videoGenre   | varchar(45) | NOT NULL | '장르' |
| videoSequel  | varchar(45) | DEFAULT NULL | '속편, 시리즈, 재개봉' |
| videoRegister | date     | DEFAULT NULL |         |

#### Table: videoRoom
| Column Name | Data Type | Nullable | Comment |
|-------------|----------|----------|---------|
| vid_roomNum | varchar(30) | NOT NULL | 영상룸 번호 |
| vid_roomName | varchar(45) | NOT NULL | 영상룸 이름 |

#### Table: bookRental
| Column Name | Data Type | Nullable |
|-------------|----------|----------|
| bookRentalNUM | varchar(45) | NOT NULL |
| bookID      | varchar(45) | NOT NULL |
| clientNUM   | varchar(45) | NOT NULL |
| bookRental_start | date | NOT NULL |
| bookRental_end | date   | NOT NULL |

#### Table: videoRental
| Column Name | Data Type | Nullable |
|-------------|----------|----------|
| videoRentalNUM | varchar(45) | NOT NULL |
| videoID     | varchar(45) | NOT NULL |
| clientNUM   | varchar(45) | NOT NULL |
| videoRental_start | date | NOT NULL |
| videoRental_end | date   | NOT NULL |

#### Table: videoRoomRental
| Column Name | Data Type | Nullable |
|-------------|----------|----------|
| videoRoomRentelNUM | varchar(45) | NOT NULL |
| clientNUM   | varchar(45) | NOT NULL |
| vid_roomNUM | varchar(30) | NOT NULL |
| videoRoomRental_start | date | NOT NULL |
| videoRoomRental_end | date   | NOT NULL |
