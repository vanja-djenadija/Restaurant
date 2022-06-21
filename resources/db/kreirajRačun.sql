CREATE DEFINER=`root`@`localhost` PROCEDURE `kreirajRačun`(in idNarudžba INTEGER)
BEGIN
	DECLARE cijenaRačuna DECIMAL(6,2) DEFAULT 0;
	DECLARE idZaposleni INTEGER;
    DECLARE računId INTEGER;
    
	DROP TABLE IF EXISTS temp;
    CREATE TEMPORARY TABLE temp(IdNarudžba INTEGER, IdArtikal INTEGER, Količina INTEGER, Cijena DECIMAL(4,2));
    INSERT INTO temp SELECT NARUDŽBA_IdNarudžba, IdArtikal, Količina, Cijena 
    FROM narudžba_ima_artikal nia, artikal a 
    WHERE nia.ARTIKAL_IdArtikal = a.IdArtikal 
    AND nia.NARUDŽBA_IdNarudžba = idNarudžba;
    
	-- računanje ukupne cijene računa
    SELECT SUM(Količina*Cijena) INTO cijenaRačuna FROM temp;

    -- kreiranje računa
    SELECT ZAPOSLENI_IdZaposleni INTO idZaposleni FROM narudžba n WHERE n.IdNarudžba=idNarudžba;
    INSERT INTO račun(UkupnaCijena, Datum, ZAPOSLENI_IdZaposleni, ZAPOSLENI_OSOBA_IdOsobe) 
	VALUES (cijenaRačuna, curdate(), idZaposleni, idZaposleni);
    SET računId = last_insert_id();  
    
    -- dodavanje stavki računa
    INSERT INTO stavka(RAČUN_IdRačun, ARTIKAL_IdArtikal, Cijena, Količina)
    SELECT računId, IdArtikal, Cijena, Količina FROM temp;
    
    -- promjena statusa narudžbe u 3=Završena, i IdRačuna 
    UPDATE narudžba n SET STATUS_IdStatus=3, RAČUN_IdRačun=računId WHERE n.IdNarudžba=idNarudžba;
    
    -- smanjiti količinu pića ukoliko je artikal piće
    UPDATE piće p, temp SET p.TrenutnaKoličina=p.TrenutnaKoličina-temp.Količina WHERE p.ARTIKAL_IdArtikal=temp.IdArtikal;
END

