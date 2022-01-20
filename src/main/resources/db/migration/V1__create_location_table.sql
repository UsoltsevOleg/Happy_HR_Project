CREATE TABLE location (
    location_id SERIAL PRIMARY KEY,
    address VARCHAR(1024),
    office BOOLEAN,
    outsource BOOLEAN
);