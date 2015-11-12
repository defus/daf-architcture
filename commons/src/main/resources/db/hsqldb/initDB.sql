DROP TABLE professions IF EXISTS;
DROP TABLE type_professions IF EXISTS;
DROP TABLE regions IF EXISTS;

CREATE TABLE type_professions (
  id         			INTEGER IDENTITY PRIMARY KEY,
  libelle 				VARCHAR(30),
  description  			VARCHAR(100)
);

CREATE TABLE professions (
  id         			INTEGER IDENTITY PRIMARY KEY,
  libelle 				VARCHAR(30),
  description  			VARCHAR(1000),
  date_creation			DATE,
  salaire_min 			DECIMAL(10,2) NOT NULL,
  type_profession_id	INTEGER
);

CREATE INDEX ix_type_profession ON professions (type_profession_id);

ALTER TABLE professions ADD CONSTRAINT fk_professions_type_professions FOREIGN KEY (type_profession_id) REFERENCES type_professions(id);

CREATE TABLE regions (
  id         			INTEGER IDENTITY PRIMARY KEY,
  libelle 				VARCHAR(200)
);

CREATE TABLE departements (
  id         			INTEGER IDENTITY PRIMARY KEY,
  libelle 				VARCHAR(200),
  region_id				INTEGER
);

CREATE INDEX ix_regions ON departements (region_id);

ALTER TABLE departements ADD CONSTRAINT fk_departements_regions FOREIGN KEY (region_id) REFERENCES regions(id);
