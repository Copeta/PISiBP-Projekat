--liquibase formatted sql
--changeset Mirko:create-tables spitStatement:true andDelimiter:;

INSERT INTO users VALUES (
1,1,'$2a$10$Whcr6HD8FI6/TsaH0nqyMuF8nEYkw987FBkcraT3wLBPSJlnE5/te','ROLE_ADMIN','user'
);