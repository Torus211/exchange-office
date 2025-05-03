CREATE TABLE currencies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(3) NOT NULL UNIQUE,
    currency DECIMAL(10, 2) NOT NULL
);

CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    position VARCHAR(100)
);

CREATE TABLE exchange_operations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    currency BIGINT NOT NULL,
    currency_id BIGINT NOT NULL,
    amount DOUBLE NOT NULL,
    rate DOUBLE NOT NULL,
    exchanged_amount DOUBLE NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(id),
    FOREIGN KEY (currency) REFERENCES currencies(id)
   );

