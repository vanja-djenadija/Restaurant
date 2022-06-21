CREATE DEFINER=`root`@`localhost` PROCEDURE `provjeraStanjaPića`(in idArtikal INTEGER, in količina INTEGER, out dodajArtikal BOOLEAN)
BEGIN
	DECLARE količinaBaza INTEGER;
    DECLARE jeJelo BOOLEAN;

	SELECT count(*)>0 INTO jeJelo FROM jelo j WHERE j.ARTIKAL_IdArtikal=idArtikal;
    
    IF jeJelo THEN
		SELECT TRUE INTO dodajArtikal;
    ELSE
		SELECT TrenutnaKoličina INTO količinaBaza FROM piće p WHERE p.ARTIKAL_IdArtikal=idArtikal;
		IF količinaBaza > količina THEN
			SELECT TRUE INTO dodajArtikal;
		ELSE
			SELECT FALSE INTO dodajArtikal;
		END IF;
    END IF;

END