-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE test_table (test_id INT, test_column VARCHAR, PRIMARY KEY (test_id))


-- changeset liquibase:2
INSERT into test_table values (1, 'value1')



