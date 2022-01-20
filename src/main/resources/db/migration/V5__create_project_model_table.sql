CREATE TABLE project_model (
    proj_mod_id SERIAL PRIMARY KEY,
    model_type VARCHAR(150),
    interviewer_name VARCHAR(300),
    num_of_interviews INT,
    cv_to VARCHAR(300)
);