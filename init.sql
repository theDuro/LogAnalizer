CREATE TABLE log_structure (
    id SERIAL PRIMARY KEY,
    log_class  VARCHAR(200),
);

CREATE TABLE logs (
    id SERIAL PRIMARY KEY,
    log VARCHAR(1000) NOT NULL,
    log_date DATE,
    log_class  VARCHAR(200),
    log_structure_id INT REFERENCES log_structure(id),
);