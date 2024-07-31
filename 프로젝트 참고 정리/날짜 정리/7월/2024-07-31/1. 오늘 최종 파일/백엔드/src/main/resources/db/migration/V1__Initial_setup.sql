-- V1__Create_product_tables.sql

-- 테이블 생성
CREATE TABLE product_main_types (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE product_sub_types (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(255) NOT NULL UNIQUE,
    product_main_type_id BIGINT NOT NULL,
    FOREIGN KEY (product_main_type_id) REFERENCES product_main_types(id)
);

-- 메인 타입 데이터 삽입
INSERT INTO product_main_types (id, type_name) VALUES 
(1, '상의'),
(2, '하의'),
(3, '아우터'),
(4, '신발'),
(5, '액세서리');

-- 서브 타입 데이터 삽입
INSERT INTO product_sub_types (id, type_name, product_main_type_id) VALUES 
-- 상의
(1, '후드티셔츠', 1),
(2, '반소매티셔츠', 1),
(3, '긴소매티셔츠', 1),
(4, '니트/스웨터', 1),
(5, '카라티셔츠', 1),
(6, '맨투맨', 1),
(7, '셔츠/블라우스', 1),
(8, '스포츠웨어', 1),

-- 하의
(9, '코튼팬츠', 2),
(10, '데님팬츠', 2),
(11, '조거팬츠', 2),
(12, '슈트/슬랙스', 2),
(13, '점프슈트', 2),
(14, '레깅스', 2),
(15, '치마', 2),
(16, '원피스', 2),
(17, '스포츠웨어', 2),

-- 아우터
(18, '무스탕', 3),
(19, '슈트', 3),
(20, '아노락재킷', 3),
(21, '숏패딩', 3),
(22, '롱패딩', 3),
(23, '가디건', 3),
(24, '코트', 3),

-- 신발
(25, '구두', 4),
(26, '크록스', 4),
(27, '런닝화', 4),
(28, '슬리퍼', 4),
(29, '단화', 4),
(30, '부츠', 4),
(31, '스포츠신발', 4),

-- 액세서리
(32, '모자', 5),
(33, '가방', 5),
(34, '반지/팔찌', 5),
(35, '목걸이', 5),
(36, '귀걸이', 5),
(37, '선글라스/안경', 5),
(38, '시계', 5);