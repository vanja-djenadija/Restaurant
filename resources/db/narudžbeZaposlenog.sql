CREATE DEFINER=`root`@`localhost` PROCEDURE `narudžbeZaposlenog`(in idZaposleni INTEGER)
BEGIN
	SELECT * FROM narudžba n 
    INNER JOIN status s  ON n.STATUS_IdStatus=s.IdStatus
    WHERE n.ZAPOSLENI_IdZaposleni=idZaposleni;
END