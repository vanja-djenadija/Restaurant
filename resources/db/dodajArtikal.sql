CREATE DEFINER=`root`@`localhost` PROCEDURE `dodajArtikal`(in naziv VARCHAR(50), in cijena DECIMAL(4,2), in opis VARCHAR(200), out idArtikal INTEGER)
BEGIN
	DECLARE meniId INTEGER;
	DECLARE artikalId INTEGER;
	
	INSERT INTO artikal(Naziv, Cijena, Opis) VALUES (naziv, cijena, opis);
	SET idArtikal = last_insert_id();  
    
	SELECT IdMeni INTO meniId FROM meni WHERE CURDATE() >= meni.DatumOd AND CURDATE() <= meni.DatumDo;
	SELECT IdArtikal INTO artikalId FROM artikal a WHERE naziv=a.Naziv;
	  
	INSERT INTO artikal_ima_meni(ARTIKAL_IdArtikal, MENI_IdMeni) VALUES (artikalId, meniId);
END