CREATE TABLE members (
    member_id VARCHAR2(50) PRIMARY KEY,  -- 아이디 (PK)
    password VARCHAR2(100) NOT NULL,     -- 비밀번호
    nickname VARCHAR2(100) NOT NULL,     -- 닉네임
    is_admin CHAR(1) DEFAULT 'N',         -- 관리자 여부 ('Y', 'N')
    created_at DATE DEFAULT SYSDATE       -- 가입일
);
CREATE TABLE movies (
    movie_id NUMBER PRIMARY KEY,
    title VARCHAR2(200) NOT NULL,
    genre VARCHAR2(100),
    director VARCHAR2(100),
    running_time NUMBER,
    release_date DATE,
    synopsis VARCHAR2(2000),          -- ✅ 줄거리 추가
    created_at DATE DEFAULT SYSDATE
);

CREATE TABLE reviews (
    review_id NUMBER PRIMARY KEY,         -- 리뷰 고유번호 (PK)
    member_id VARCHAR2(50),                -- 작성자 (FK)
    movie_id NUMBER,                       -- 영화 ID (FK)
    rating NUMBER(2,1) NOT NULL,           -- 평점 (0.0 ~ 10.0)
    content VARCHAR2(1000),                -- 리뷰 내용
    like_count NUMBER DEFAULT 0,           -- 좋아요 수
    created_at DATE DEFAULT SYSDATE,       -- 작성일
    CONSTRAINT fk_review_member FOREIGN KEY (member_id) REFERENCES members(member_id),
    CONSTRAINT fk_review_movie FOREIGN KEY (movie_id) REFERENCES movies(movie_id)
);
CREATE TABLE wishlists (
    wishlist_id NUMBER PRIMARY KEY,          -- 찜 고유번호
    member_id VARCHAR2(50),                   -- 회원 ID (FK)
    movie_id NUMBER,                          -- 영화 ID (FK)
    created_at DATE DEFAULT SYSDATE,           -- 찜 추가일
    CONSTRAINT fk_wishlist_member FOREIGN KEY (member_id) REFERENCES members(member_id),
    CONSTRAINT fk_wishlist_movie FOREIGN KEY (movie_id) REFERENCES movies(movie_id)
);

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
