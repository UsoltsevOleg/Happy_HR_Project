CREATE TABLE team (
    team_id SERIAL PRIMARY KEY,
    dev_method VARCHAR(500),
    is_product_dev BOOLEAN,
    has_testers BOOLEAN,
    has_technical_writers BOOLEAN,
    analysts_num INT,
    devs_num INT,
    team_ready BOOLEAN,
    people_in_team_num INT
);