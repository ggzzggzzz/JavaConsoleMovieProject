


클래스(Class)와 객체(Object)

상속(Inheritance)

다형성(Polymorphism)

캡슐화(Encapsulation)

추상 클래스(Abstract Class)

인터페이스(Interface)

예외 처리(Exception Handling)

컬렉션 프레임워크(Collection Framework)

입출력(I/O)

기본적인 API 활용

프로젝트 목표

자바의 핵심 문법을 코드를 작성하며 자연스럽게 익히기

객체지향 설계 원칙을 이해하고 적용해보기

코드 작성 → 수정 → 리팩토링 과정을 통해 개발 감각 키우기

간단한 프로그램 구조를 스스로 설계하고 구현하는 연습

# Java 콘솔 영화 관리 및 리뷰 프로젝트

## 📑 프로젝트 소개
이 프로젝트는 자바(Java)의 기본 문법을 실습하고 이해하기 위해 제작되었습니다.
주요 학습 대상은 객체지향 프로그래밍(OOP) 개념과 자바의 핵심 기능들입니다.


## 🎬 주요 기능
- **사용자 인증**
  - 회원가입 및 로그인/로그아웃 기능
  - 사용자 계정 정보(닉네임, 비밀번호) 수정 및 회원 탈퇴

- **영화 정보**
  - 전체 영화 목록 조회 및 상세 정보 확인
  - 제목 기반 영화 검색
  - 영화별 평균 평점 및 리뷰 목록 제공

- **리뷰 관리**
  - 영화에 대한 리뷰 작성, 수정, 삭제
  - 다른 사용자의 리뷰에 '좋아요' 추가 및 취소 (토글)
  - 내가 작성한 리뷰 모아보기

- **찜 목록 (Wishlist)**
  - 원하는 영화를 찜 목록에 추가 및 삭제
  - 나의 찜 목록 조회

- **관리자 기능**
  - 신규 영화 등록 및 기존 영화 삭제
  - 전체 회원 목록 조회

## ⚙️ 시작 가이드

### 사전 요구사항
- **Java Development Kit (JDK)** 8 이상
- **Oracle Database** 11g XE 이상
- **Oracle JDBC 드라이버** (ojdbc.jar)

### 1. 데이터베이스 설정
아래 명세에 따라 Oracle DB에 테이블과 시퀀스를 생성해야 합니다.
(SQL 스크립트를 프로젝트에 포함하여 사용자가 쉽게 실행할 수 있도록 하는 것을 권장합니다.)

<details>
<summary><b>테이블 및 시퀀스 생성 SQL 보기</b></summary>

```sql
-- 회원 테이블
CREATE TABLE members (
    member_id VARCHAR2(50) PRIMARY KEY,          -- 아이디 (PK)
    password VARCHAR2(100) NOT NULL,             -- 비밀번호
    nickname VARCHAR2(100) NOT NULL,             -- 닉네임
    is_admin CHAR(1) DEFAULT 'N',                -- 관리자 여부 ('Y', 'N')
    created_at DATE DEFAULT SYSDATE              -- 가입일
);

-- 영화 테이블
CREATE TABLE movies (
    movie_id NUMBER PRIMARY KEY,                 -- 영화 ID
    title VARCHAR2(200) NOT NULL,                -- 제목
    genre VARCHAR2(100),                         -- 장르
    director VARCHAR2(100),                      -- 감독
    running_time NUMBER,                         -- 상영시간
    release_date DATE,                           -- 개봉일
    synopsis VARCHAR2(2000),                     -- 줄거리
    created_at DATE DEFAULT SYSDATE              -- 등록일
);

-- 리뷰 테이블
CREATE TABLE reviews (
    review_id NUMBER PRIMARY KEY,                -- 리뷰 고유번호
    member_id VARCHAR2(50),                      -- 작성자 (FK)
    movie_id NUMBER,                             -- 영화 ID (FK)
    rating NUMBER(3,1) NOT NULL,                 -- 평점 (0.0 ~ 10.0)
    content VARCHAR2(1000),                      -- 리뷰 내용
    like_count NUMBER DEFAULT 0,                 -- 좋아요 수
    created_at DATE DEFAULT SYSDATE,             -- 작성일
    CONSTRAINT fk_review_member FOREIGN KEY (member_id) 
        REFERENCES members(member_id) ON DELETE CASCADE,
    CONSTRAINT fk_review_movie FOREIGN KEY (movie_id) 
        REFERENCES movies(movie_id) ON DELETE CASCADE,
    CONSTRAINT chk_rating CHECK (rating BETWEEN 0 AND 10) -- 평점 유효성 검사
);

-- 찜 테이블
CREATE TABLE wishlists (
    wishlist_id NUMBER PRIMARY KEY,              -- 찜 고유번호
    member_id VARCHAR2(50),                      -- 회원 ID (FK)
    movie_id NUMBER,                             -- 영화 ID (FK)
    created_at DATE DEFAULT SYSDATE,             -- 찜 등록일
    CONSTRAINT fk_wishlist_member FOREIGN KEY (member_id) 
        REFERENCES members(member_id) ON DELETE CASCADE,
    CONSTRAINT fk_wishlist_movie FOREIGN KEY (movie_id) 
        REFERENCES movies(movie_id) ON DELETE CASCADE,
    CONSTRAINT uq_wishlist UNIQUE (member_id, movie_id) -- 중복 방지
);

-- 좋아요 테이블
CREATE TABLE review_likes (
    review_id NUMBER,
    member_id VARCHAR2(50),
    created_at DATE DEFAULT SYSDATE,
    PRIMARY KEY (review_id, member_id),
    FOREIGN KEY (review_id) REFERENCES reviews(review_id) ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES members(member_id) ON DELETE CASCADE
);

-- 시퀀스 생성
CREATE SEQUENCE seq_wishlist_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE seq_review_id
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

```
</details>

### 2. 소스코드 컴파일
`src` 디렉토리의 모든 Java 파일을 컴파일하여 `bin` 디렉토리에 클래스 파일을 생성합니다.

```bash
# bin 디렉토리가 없다면 생성
mkdir bin

# 컴파일 실행
javac -d bin src/**/*.java
```

### 3. 애플리케이션 실행
컴파일된 클래스 파일과 Oracle JDBC 드라이버를 클래스패스에 추가하여 메인 클래스를 실행합니다.

```bash
# ojdbc.jar 파일의 실제 경로로 수정해야 합니다.
java -cp "bin;C:\path\to\ojdbc.jar" main.ConsoleMovieMain
```

## 🛠️ 기술 스택
| 구분 | 기술 |
|------|------|
| **Language** | Java |
| **Database** | Oracle DB |
| **Connectivity**| JDBC |
| **Environment** | Console / Terminal |

## 🏛️ 아키텍처 및 디렉토리 구조
이 프로젝트는 데이터 접근, 비즈니스 로직, 사용자 인터페이스를 분리하는 **3-Tier 계층형 아키텍처**를 따릅니다.

- **main**: 프로그램 시작점
- **ui**: 사용자 입력/출력 처리 (콘솔 UI)
- **service**: UI와 DAO를 연결하며, 비즈니스 로직을 처리합니다.
- **dao**: DB 접근 및 SQL 실행
- **vo**: 테이블과 매핑되는 객체 (Value Object)
- **util**: 공통 유틸리티 클래스 (DB 연결, 자원 해제 등)
