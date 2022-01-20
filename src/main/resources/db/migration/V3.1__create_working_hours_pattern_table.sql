CREATE TABLE working_hours_pattern (
    whp_id SERIAL PRIMARY KEY,
    whp_from TIME,
    whp_till TIME,
    overtime VARCHAR(150),
    possible_work_schedule_id INT,
    CONSTRAINT fk_possible_schedule_whp
        FOREIGN KEY (possible_work_schedule_id)
            REFERENCES possible_work_schedule (pws_id)
);