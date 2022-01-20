CREATE TABLE project_card (
    proj_card_id SERIAL PRIMARY KEY,
    project_name VARCHAR(500),
    proj_client_name VARCHAR(500),
    project_stage VARCHAR(150),
    functional_direction VARCHAR(1024),
    subject_area VARCHAR(1024),
    project_description TEXT,
    objectives TEXT,
    technologies VARCHAR [],
    stakeholders TEXT,
    completion_date DATE,
    people_launch_date DATE,
    has_gost BOOLEAN,
    card_status VARCHAR(150),
    prev_card_status VARCHAR(150),
    project_type_id INT,
    team_id INT,
    location_id INT,
    author_id INT,
    whp_id INT,
    CONSTRAINT fk_proj_type_proj_card
        FOREIGN KEY (project_type_id)
            REFERENCES project_type (proj_type_id),
    CONSTRAINT fk_team_proj_card
        FOREIGN KEY (team_id)
            REFERENCES team (team_id),
    CONSTRAINT fk_location_proj_card
        FOREIGN KEY (location_id)
            REFERENCES location (location_id),
    CONSTRAINT fk_author_proj_card
        FOREIGN KEY (author_id)
            REFERENCES users (user_id),
    CONSTRAINT fk_whp_proj_card
        FOREIGN KEY (whp_id)
            REFERENCES working_hours_pattern (whp_id)
);