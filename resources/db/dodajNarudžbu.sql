CREATE DEFINER=`root`@`localhost` PROCEDURE `dodajNarudžbu`(in idSto INTEGER, in zaposleniId INTEGER, out narudžbaId INTEGER)
BEGIN
	INSERT INTO narudžba(DatumVrijeme, STO_IdSto, STATUS_IdStatus, ZAPOSLENI_IdZaposleni, ZAPOSLENI_OSOBA_IdOsobe) 
	VALUES (sysdate(), idSto, 1, zaposleniId, zaposleniId);
    SELECT last_insert_id() INTO narudžbaId;
END