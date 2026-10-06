-- Makroskładniki jako liczby zmiennoprzecinkowe zamiast całkowitych.
-- Hibernate (ddl-auto=update) nie zmienia typów istniejących kolumn, więc trzeba to zrobić ręcznie.
-- Konwersja bigint -> double precision nie traci danych.
BEGIN;
ALTER TABLE products
    ALTER COLUMN caloric TYPE double precision,
    ALTER COLUMN protein TYPE double precision,
    ALTER COLUMN carbs   TYPE double precision,
    ALTER COLUMN fat     TYPE double precision;
ALTER TABLE recipes
    ALTER COLUMN caloric TYPE double precision,
    ALTER COLUMN protein TYPE double precision,
    ALTER COLUMN carbs   TYPE double precision,
    ALTER COLUMN fat     TYPE double precision;
COMMIT;
