BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "brojilo" (
	"sifra"	INTEGER,
	"stanje"	INTEGER,
	"hod"	TEXT,
	"vlasnik"	INTEGER,
	"popisano" INTEGER,
	PRIMARY KEY("sifra"),
	FOREIGN KEY("vlasnik") REFERENCES "korisnik"("id")
);
CREATE TABLE IF NOT EXISTS "racun" (
	"id"	INTEGER,
	"novac_za_uplatu"	TEXT,
	"mjesec"	TEXT,
	"godina"    INTEGER,
	"brojilo"	INTEGER,
	"placen"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("brojilo") REFERENCES "brojilo"("sifra")
);
CREATE TABLE IF NOT EXISTS "korisnik" (
	"id"	INTEGER,
	"ime"	TEXT,
	"prezime"	TEXT,
	"korisnicko_ime" TEXT,
	"lozinka" TEXT,
	"uloga"	TEXT,
	PRIMARY KEY("id")
);

INSERT INTO "korisnik" VALUES (1,'Meho', 'Mehić', 'mmehic', 'meho', 'POTROSAC');
INSERT INTO "korisnik" VALUES (2,'Suljo', 'Suljić', 'ssuljic', 'suljo', 'POTROSAC');
INSERT INTO "korisnik" VALUES (3,'Hana', 'Mehić', 'hmehic', 'hana', 'POTROSAC');
INSERT INTO "korisnik" VALUES (4,'Selma', 'Selmić', 'sselmic', 'selma', 'POPISIVAC');
INSERT INTO "korisnik" VALUES (5,'Sarajevo', 'Gas', 'admin', 'password', 'ADMIN');

INSERT INTO "brojilo" VALUES (11111, 1200, '062', 1, 0);
INSERT INTO "brojilo" VALUES (22222, 200, '064', 2, 1);
INSERT INTO "brojilo" VALUES (33333, 800, '061', 3, 0);
INSERT INTO "brojilo" VALUES (44444, 200, '063', 1, 1);

INSERT INTO "racun" VALUES (1, '120.6', 'Januar', 2022, 11111, 0);
INSERT INTO "racun" VALUES (2, '95', 'Novembar', 2021, 22222, 1);
INSERT INTO "racun" VALUES (3, '100', 'Decembar', 2021, 22222, 0);
INSERT INTO "racun" VALUES (4, '130', 'Januar', 2022, 22222, 0);

INSERT INTO "racun" VALUES (5, 120, 'Januar', 2022, 33333, 1);
INSERT INTO "racun" VALUES (6, 115, 'Januar', 2022, 44444, 1);
COMMIT;
