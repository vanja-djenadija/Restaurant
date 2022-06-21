CREATE DEFINER=`root`@`localhost` PROCEDURE `prijava`(in korisnickoIme VARCHAR(50), in lozinka VARCHAR(50), out prijava boolean, out idZaposleni INTEGER)
BEGIN
	SELECT count(*)>0, IdOsobe INTO prijava, idZaposleni FROM osoba o
	INNER JOIN zaposleni z ON z.OSOBA_IdOsobe=o.IdOsobe 
	WHERE o.KorisničkoIme=korisnickoIme AND o.Lozinka=lozinka;
END