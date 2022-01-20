CREATE TABLE project_type (
    proj_type_id SERIAL PRIMARY KEY,
    software_complex VARCHAR(150),
    is_mvp BOOLEAN,
    system VARCHAR(150),
    proj_model_id INT,
    CONSTRAINT fk_proj_model
        FOREIGN KEY (proj_model_id)
            REFERENCES project_model (proj_mod_id)
);