CREATE DEFINER=`root`@`localhost` PROCEDURE `dodajJelo`(in artikalId INTEGER, in recept VARCHAR(1000), in porcija VARCHAR(50), in vrstaJela VARCHAR(50))
BEGIN
	DECLARE vrstaJelaId INTEGER;
	SELECT IdVrstaJela INTO vrstaJelaId FROM vrsta_jela vj WHERE vrstaJela=vj.Naziv;
	
	INSERT INTO jelo(ARTIKAL_IdArtikal, Recept, VeličinaPorcije, VRSTA_JELA_IdVrstaJela) VALUES (artikalId, recept, porcija, vrstaJelaId);
END