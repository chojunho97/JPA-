# 프로젝트 구조 소개
Controller (BoardApiController): 게시판의 CRUD(Create, Read, Update, Delete) 연산을 위한 API를 제공합니다.

DTO (Data Transfer Object) (BoardRequestDto, BoardResponseDto): 데이터 전송 및 응답을 위한 객체입니다.

Entity (Board): 게시판의 핵심 정보를 나타내는 데이터 객체입니다.

Repository (BoardRepository): JPA Repository를 활용하여 데이터베이스와의 연동을 관리합니다.

Service (BoardService): 비즈니스 로직을 담당하는 서비스 계층입니다.

Config (DatabaseConfig): 데이터베이스 연결과 관련된 설정 정보입니다.

Exception: 사용자 정의 예외 및 예외 처리 핸들러를 포함합니다.

테이블

CREATE TABLE board (
id BIGINT(20) NOT NULL AUTO_INCREMENT,
title VARCHAR(100) NOT NULL,
content TEXT NOT NULL,
writer VARCHAR(20) NOT NULL,
hits INT(11) NOT NULL,
delete_yn ENUM('Y', 'N') NOT NULL DEFAULT 'N',
create_at DATETIME NOT NULL,
update_at DATETIME,
name ENUM('NEWS', 'EVENT', 'NOTICE') NOT NULL,
PRIMARY KEY (id)
);

# 구현 방법에 대한 설명
게시판의 기본 CRUD 연산은 BoardApiController에 구현되어 있습니다.
게시글 저장, 수정, 조회 및 삭제 기능을 제공합니다.
삭제된 게시글은 물리적으로 삭제되지 않고 deleteYn 플래그를 Y로 설정하여 삭제된 것처럼 처리됩니다.
사용자는 게시글의 작성자가 아닌 경우 수정 및 삭제 권한이 없습니다.

# 개발 환경
Framework: Spring Boot 2.7.15
ORM: JPA
빌드 도구: Gradle
기타: Lombok
Database: HikariCP를 사용한 관계형 데이터베이스(MariaDB)

# 빌드 및 실행방법

프로젝트 폴더로 이동하여 빌드

java -jar build/libs/chojunho-project-0.0.1-SNAPSHOT.jar 실행 후

웹 브라우저 또는 API 테스팅 도구를 사용하여 API호출
ex)http://localhost:8080/api/boards


-