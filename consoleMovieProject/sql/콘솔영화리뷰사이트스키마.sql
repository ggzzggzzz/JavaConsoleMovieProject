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
