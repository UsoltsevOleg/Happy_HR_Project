CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE FUNCTION hash_password()
RETURNS TRIGGER AS
    $func$
        BEGIN
            NEW.password = digest(NEW.password, 'sha512');
            RETURN NEW;
        END;
    $func$
LANGUAGE 'plpgsql';

CREATE TRIGGER users_hash_password
BEFORE INSERT ON users
FOR EACH ROW EXECUTE PROCEDURE hash_password();