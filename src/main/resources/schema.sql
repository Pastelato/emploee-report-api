CREATE TABLE employees
(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    department VARCHAR(100),
    salary NUMERIC(10,2),
    city VARCHAR(100),
    active BOOLEAN
);
