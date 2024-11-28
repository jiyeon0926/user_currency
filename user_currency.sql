-- 유저 테이블
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '유저 고유 식별자',        
    email VARCHAR(50) UNIQUE NOT NULL COMMENT '이메일',
    name VARCHAR(6) NOT NULL COMMENT '이름',  
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자' 
);

-- 통화 테이블
CREATE TABLE currency (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '통화 고유 식별자',
    exchange_rate DECIMAL(10, 4) NOT NULL COMMENT '환율', 
    currency_name VARCHAR(10) NOT NULL COMMENT '통화 이름', 
    symbol VARCHAR(5) NOT NULL COMMENT '표시',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자'
);

-- 환전 요청 테이블
CREATE TABLE exchange_requests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '환전 요청 고유 식별자',  
    user_id BIGINT NOT NULL COMMENT '유저 고유 식별자',              
    currency_id BIGINT NOT NULL COMMENT '통화 고유 식별자',           
    amount_in_krw DECIMAL(15, 2) NOT NULL COMMENT '환전 전 금액(원화 기준)', 
    amount_after_exchange DECIMAL(15, 2) NOT NULL COMMENT '환전 후 금액', 
    status VARCHAR(8) DEFAULT 'normal' COMMENT '상태',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (currency_id) REFERENCES currency(id) ON DELETE CASCADE
);
