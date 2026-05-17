CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       registerDate DATETIME NULL,
                       accountStatus VARCHAR(20) DEFAULT 'ACTIVE' CHECK ( accountStatus IN ('ACTIVE','BANNED','DELETED')) NULL,
                       accountLevel INT DEFAULT 1
);