-- Mange-til-mange-eksempel forelesning tirsdag 22. mars 2021.

-- MERK!!! DROP SCHEMA ... CASCADE sletter alt !!!
DROP SCHEMA IF EXISTS obligatorisk CASCADE;

CREATE SCHEMA obligatorisk;
SET search_path TO obligatorisk;

CREATE TABLE Ansatt
(
  Id         SERIAL,
  Brukernavn VARCHAR(10) NOT NULL,
  Fornavn    VARCHAR(30),
  Etternavn  VARCHAR(30),
  Ansettelses_Dato DATE NOT NULL DEFAULT CURRENT_DATE, 
  Stilling VARCHAR(30),
  maaneds_lonn DECIMAL(10,2),
  avdeling INTEGER,
  CONSTRAINT Ansatt_PK PRIMARY KEY (Id)
  
);
CREATE TABLE Avdeling
(
  Id        SERIAL,
  Navn      VARCHAR(30),
  Sjef		INTEGER NOT NULL,
  CONSTRAINT Avdeling_PK PRIMARY KEY (Id),
  CONSTRAINT Ansatt_FK FOREIGN KEY (Sjef) REFERENCES Ansatt(Id) 
 
);
ALTER TABLE Ansatt
ADD CONSTRAINT Avdeling_FK FOREIGN KEY (Avdeling) REFERENCES Avdeling(Id) ; 


CREATE TABLE Prosjekt
(
  Id        SERIAL,
  Navn      VARCHAR(30),
  Beskrivelse VARCHAR(50),
  CONSTRAINT Prosjekt_PK PRIMARY KEY (Id)
);


-- Mange-til-mange m√• l√∏ses med en koblingstabell, slik:
CREATE TABLE Prosjektdeltagelse
(
  Prosjektdeltagelse_id SERIAL,
  Ansatt_Id INTEGER,
  Prosjekt_Id INTEGER,
  Timer_Jobbet INTEGER,
  Rolle VARCHAR(30),
  CONSTRAINT Prosjektdeltagelse_PK PRIMARY KEY (Ansatt_Id, Prosjekt_Id),
  CONSTRAINT AnsattProsjekt_Unik UNIQUE (Ansatt_Id, Prosjekt_Id),
  CONSTRAINT Ansatt_FK FOREIGN KEY (Ansatt_Id) REFERENCES Ansatt(Id) ,
  CONSTRAINT Prosjekt_FK FOREIGN KEY (Prosjekt_Id) REFERENCES Prosjekt(Id)   
);


INSERT INTO
  Ansatt(Brukernavn, Fornavn, Etternavn, Stilling, maaneds_lonn)
VALUES
  ('arn', 'Arne', 'Arnesen', 'IT Sjef', 30432.32),
  ('bri', 'Brit', 'Britsen', 'IT konsulent', 21060.32),
  ('car', 'Carl', 'Carlsen', 'IT konsulent', 21060.32),
  ('don', 'Donald', 'Duck', 'HR Sjef', 28185.54),
  ('sig', 'Sigur', 'Petter', 'Eigar', 95185.65);
  
INSERT INTO
	Avdeling(Navn, Sjef)
VALUES
	('IT',  1),
	('HR', 4),
	('Ledelsen',  5);
INSERT INTO
  Prosjekt(Navn, Beskrivelse)
VALUES
  ('ServerUtvidingsProsjekt', 'Planlegge utvidelse av serverrommet'),
  ('Synergiprosjektet', 'Finne ut mÂter Â auke trivselen til ansatte');

INSERT INTO
  Prosjektdeltagelse(Ansatt_Id, Prosjekt_Id, Timer_Jobbet, Rolle)
VALUES
  (1, 1, 13, 'Prosjektleder'),
  (2, 1, 12, 'Prosjektassistent'),
  (3, 1, 6, 'Prosjektassistent'),
  (3, 2, 2, 'IT hjelpekonsulent'),
  (4, 2, 7, 'Prosjektleder');
 
UPDATE  Ansatt
SET Avdeling=1
WHERE Id=1;

UPDATE  Ansatt
SET Avdeling=1
WHERE Id=2;

UPDATE  Ansatt
SET Avdeling=1
WHERE Id=3;

UPDATE  Ansatt
SET Avdeling=2
WHERE Id=4;

UPDATE  Ansatt
SET Avdeling=3
WHERE Id=5;

ALTER TABLE ansatt
ALTER COLUMN Avdeling SET NOT NULL;

 
  