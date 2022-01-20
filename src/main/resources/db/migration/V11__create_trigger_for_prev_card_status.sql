CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE FUNCTION set_prev_status()
    RETURNS TRIGGER AS
$func$
BEGIN
    NEW.prev_card_status = NEW.card_status;
    RETURN NEW;
END;
$func$
    LANGUAGE 'plpgsql';

CREATE TRIGGER users_hash_password
    BEFORE INSERT ON project_card
    FOR EACH ROW EXECUTE PROCEDURE set_prev_status();