CREATE DEFINER=`root`@`localhost` PROCEDURE `artikliTrenutnogMenija`(in naziv VARCHAR(50))
BEGIN
	DECLARE meniId INTEGER;
	SELECT IdMeni INTO meniId FROM meni WHERE CURDATE() >= meni.DatumOd AND CURDATE() <= meni.DatumDo;
	SELECT * FROM artikal a INNER JOIN artikal_ima_meni aim ON a.IdArtikal = aim.ARTIKAL_IdArtikal WHERE aim.MENI_IdMeni = meniId AND a.Naziv LIKE naziv;
END