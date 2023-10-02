DROP TABLE IF EXISTS article;

CREATE TABLE IF NOT EXISTS article (
    id BIGINT AUTO_INCREAMENT PRIMARY KEY,
    title VARCHAR(255),
    body VARCHAR(2000),
    author_id BIGINT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

INSERT INTO article(title, body, author_id, created_at, updated_at)
values ('title 1', 'body 1', 1234, current_timestamp(), current_timestamp());