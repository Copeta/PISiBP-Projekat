--liquibase formatted sql
--changeset Mirko:create-tables spitStatement:true andDelimiter:;

CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    active BIT,
    password VARCHAR(255),
    roles VARCHAR(255),
    user_name VARCHAR(255)
);