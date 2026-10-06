-- Dzienne cele użytkownika (kcal i makroskładniki).
-- Istniejący użytkownicy dostają domyślne cele; nowi dostają je automatycznie przy rejestracji (User.java).
BEGIN;
ALTER TABLE users
    ADD COLUMN IF NOT EXISTS goal_kcal    double precision,
    ADD COLUMN IF NOT EXISTS goal_protein double precision,
    ADD COLUMN IF NOT EXISTS goal_fat     double precision,
    ADD COLUMN IF NOT EXISTS goal_carbs   double precision;
UPDATE users SET goal_kcal    = 2500 WHERE goal_kcal    IS NULL;
UPDATE users SET goal_protein = 160  WHERE goal_protein IS NULL;
UPDATE users SET goal_fat     = 80   WHERE goal_fat     IS NULL;
UPDATE users SET goal_carbs   = 280  WHERE goal_carbs   IS NULL;
COMMIT;
