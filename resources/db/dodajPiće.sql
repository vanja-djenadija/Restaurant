CREATE DEFINER=`root`@`localhost` PROCEDURE `dodajPiće`(in artikalId INTEGER, in količina INTEGER, in proizvođač VARCHAR(50), in vrstaPića VARCHAR(50))
BEGIN
	DECLARE vrstaPićaId INTEGER;
	DECLARE proizvođačId INTEGER;
	
	SELECT IdVrstaPića INTO vrstaPićaId FROM vrsta_pića vp WHERE vrstaPića=vp.Naziv;
	SELECT IdProizvođač INTO proizvođačId FROM proizvođač p WHERE proizvođač=p.Naziv;
	
	INSERT INTO piće(ARTIKAL_IdArtikal, TrenutnaKoličina, PROIZVOĐAČ_IdProizvođač, VRSTA_PIĆA_IdVrstaPića) VALUES (artikalId, količina, proizvođačId, vrstaPićaId);
END