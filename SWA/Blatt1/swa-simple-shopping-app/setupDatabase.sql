CONNECT 'jdbc:derby:simpleshoppingapp;create=true';

DROP TABLE produktinformation;

DROP TABLE ware;

CREATE TABLE ware(
    warennummer BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR(64),
    preis DOUBLE,
    beschreibung LONG VARCHAR
);

INSERT INTO ware VALUES(
    123,
    'TestWare',
    1.0,
    'TestBeschreibung'
);

CREATE TABLE produktinformation(
    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    bezeichnung VARCHAR(64),
    information BLOB,
    warennummer BIGINT CONSTRAINT produktinformation_foreign_key
    REFERENCES ware ON DELETE CASCADE ON UPDATE RESTRICT
);

INSERT INTO produktinformation (bezeichnung,warennummer) VALUES(
    'Testbezeichnung',
    123
);

exit;