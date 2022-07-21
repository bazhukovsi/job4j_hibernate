CREATE TABLE IF NOT EXISTS candidate
(
    id SERIAL PRIMARY KEY,
    name TEXT,
    experience DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS vacancy
(
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE IF NOT EXISTS bank_vacancies
(
    id SERIAL PRIMARY KEY,
    category TEXT
);