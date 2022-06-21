CREATE DEFINER=`root`@`localhost` PROCEDURE `obrišiNarudžbu`(in idNarudžba INTEGER)
BEGIN
	DELETE FROM narudžba_ima_artikal nia WHERE nia.NARUDŽBA_IdNarudžba=idNarudžba;
    DELETE FROM narudžba n WHERE n.IdNarudžba=idNarudžba;
END