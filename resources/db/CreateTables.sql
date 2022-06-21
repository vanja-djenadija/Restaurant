-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema restoran
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema restoran
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `restoran` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `restoran` ;

-- -----------------------------------------------------
-- Table `restoran`.`MJESTO_STANOVANJA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`MJESTO_STANOVANJA` (
  `BrojPošte` INT NOT NULL,
  `Naziv` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`BrojPošte`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`OSOBA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`OSOBA` (
  `IdOsobe` INT NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(45) NOT NULL,
  `Prezime` VARCHAR(45) NOT NULL,
  `KorisničkoIme` VARCHAR(50) NOT NULL,
  `Lozinka` VARCHAR(256) NOT NULL,
  `BrojTelefona` INT NOT NULL,
  `MJESTO_STANOVANJA_BrojPošte` INT NOT NULL,
  PRIMARY KEY (`IdOsobe`),
  UNIQUE INDEX `idOsobe_UNIQUE` (`IdOsobe` ASC) VISIBLE,
  INDEX `fk_OSOBA_MJESTO_STANOVANJA1_idx` (`MJESTO_STANOVANJA_BrojPošte` ASC) VISIBLE,
  UNIQUE INDEX `KorisničkoIme_UNIQUE` (`KorisničkoIme` ASC) VISIBLE,
  CONSTRAINT `fk_OSOBA_MJESTO_STANOVANJA1`
    FOREIGN KEY (`MJESTO_STANOVANJA_BrojPošte`)
    REFERENCES `restoran`.`MJESTO_STANOVANJA` (`BrojPošte`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`STO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`STO` (
  `IdSto` INT NOT NULL AUTO_INCREMENT,
  `BrojMjesta` INT NOT NULL,
  `Zauzet` TINYINT NOT NULL,
  PRIMARY KEY (`IdSto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`ZAPOSLENI`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`ZAPOSLENI` (
  `IdZaposleni` INT NOT NULL AUTO_INCREMENT,
  `Plata` INT NOT NULL,
  `OSOBA_IdOsobe` INT NOT NULL,
  `Uloga` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`IdZaposleni`, `OSOBA_IdOsobe`),
  INDEX `fk_ZAPOSLENI_OSOBA1_idx` (`OSOBA_IdOsobe` ASC) VISIBLE,
  CONSTRAINT `fk_ZAPOSLENI_OSOBA1`
    FOREIGN KEY (`OSOBA_IdOsobe`)
    REFERENCES `restoran`.`OSOBA` (`IdOsobe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`RAČUN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`RAČUN` (
  `IdRačun` INT NOT NULL AUTO_INCREMENT,
  `UkupnaCijena` DECIMAL(6,2) NOT NULL,
  `Datum` DATE NOT NULL,
  `ZAPOSLENI_IdZaposleni` INT NOT NULL,
  `ZAPOSLENI_OSOBA_IdOsobe` INT NOT NULL,
  PRIMARY KEY (`IdRačun`),
  INDEX `fk_RAČUN_ZAPOSLENI1_idx` (`ZAPOSLENI_IdZaposleni` ASC, `ZAPOSLENI_OSOBA_IdOsobe` ASC) VISIBLE,
  CONSTRAINT `fk_RAČUN_ZAPOSLENI1`
    FOREIGN KEY (`ZAPOSLENI_IdZaposleni` , `ZAPOSLENI_OSOBA_IdOsobe`)
    REFERENCES `restoran`.`ZAPOSLENI` (`IdZaposleni` , `OSOBA_IdOsobe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`ARTIKAL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`ARTIKAL` (
  `IdArtikal` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  `Cijena` DECIMAL(4,2) NOT NULL,
  `Opis` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`IdArtikal`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`STAVKA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`STAVKA` (
  `RAČUN_IdRačun` INT NOT NULL,
  `ARTIKAL_IdArtikal` INT NOT NULL,
  `Cijena` DECIMAL(4,2) NOT NULL,
  `Količina` INT NOT NULL,
  PRIMARY KEY (`RAČUN_IdRačun`, `ARTIKAL_IdArtikal`),
  INDEX `fk_STAVKA_ARTIKAL1_idx` (`ARTIKAL_IdArtikal` ASC) VISIBLE,
  CONSTRAINT `fk_STAVKA_RAČUN1`
    FOREIGN KEY (`RAČUN_IdRačun`)
    REFERENCES `restoran`.`RAČUN` (`IdRačun`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_STAVKA_ARTIKAL1`
    FOREIGN KEY (`ARTIKAL_IdArtikal`)
    REFERENCES `restoran`.`ARTIKAL` (`IdArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`STATUS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`STATUS` (
  `IdStatus` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`IdStatus`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`NARUDŽBA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`NARUDŽBA` (
  `IdNarudžba` INT NOT NULL AUTO_INCREMENT,
  `DatumVrijeme` DATETIME NOT NULL,
  `STO_IdSto` INT NOT NULL,
  `STATUS_IdStatus` INT NOT NULL,
  `RAČUN_IdRačun` INT NOT NULL,
  `ZAPOSLENI_IdZaposleni` INT NOT NULL,
  `ZAPOSLENI_OSOBA_IdOsobe` INT NOT NULL,
  PRIMARY KEY (`IdNarudžba`),
  INDEX `fk_NARUDŽBA_STO1_idx` (`STO_IdSto` ASC) VISIBLE,
  INDEX `fk_NARUDŽBA_STATUS1_idx` (`STATUS_IdStatus` ASC) VISIBLE,
  INDEX `fk_NARUDŽBA_RAČUN1_idx` (`RAČUN_IdRačun` ASC) VISIBLE,
  INDEX `fk_NARUDŽBA_ZAPOSLENI1_idx` (`ZAPOSLENI_IdZaposleni` ASC, `ZAPOSLENI_OSOBA_IdOsobe` ASC) VISIBLE,
  CONSTRAINT `fk_NARUDŽBA_STO1`
    FOREIGN KEY (`STO_IdSto`)
    REFERENCES `restoran`.`STO` (`IdSto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NARUDŽBA_STATUS1`
    FOREIGN KEY (`STATUS_IdStatus`)
    REFERENCES `restoran`.`STATUS` (`IdStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NARUDŽBA_RAČUN1`
    FOREIGN KEY (`RAČUN_IdRačun`)
    REFERENCES `restoran`.`RAČUN` (`IdRačun`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NARUDŽBA_ZAPOSLENI1`
    FOREIGN KEY (`ZAPOSLENI_IdZaposleni` , `ZAPOSLENI_OSOBA_IdOsobe`)
    REFERENCES `restoran`.`ZAPOSLENI` (`IdZaposleni` , `OSOBA_IdOsobe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`RESTORAN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`RESTORAN` (
  `IdRestoran` INT NOT NULL,
  `Naziv` VARCHAR(50) NOT NULL,
  `Adresa` VARCHAR(50) NOT NULL,
  `Telefon` VARCHAR(50) NOT NULL,
  `Email` VARCHAR(320) NOT NULL,
  `RadnoVrijeme` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`IdRestoran`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`SASTOJAK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`SASTOJAK` (
  `IdSastojak` INT NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(50) NOT NULL,
  `Mjerna jedinica` VARCHAR(20) NOT NULL,
  `BrojKalorijaPoJedinici` INT NOT NULL,
  `Količina` INT NOT NULL,
  PRIMARY KEY (`IdSastojak`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`MENI`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`MENI` (
  `IdMeni` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  `DatumOd` DATE NOT NULL,
  `DatumDo` DATE NOT NULL,
  PRIMARY KEY (`IdMeni`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`REZERVACIJA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`REZERVACIJA` (
  `IdRezervacija` INT NOT NULL AUTO_INCREMENT,
  `Opis` VARCHAR(100) NOT NULL,
  `DatumVrijeme` DATETIME NOT NULL,
  `OSOBA_IdOsobe` INT NOT NULL,
  PRIMARY KEY (`IdRezervacija`),
  INDEX `fk_REZERVACIJA_OSOBA1_idx` (`OSOBA_IdOsobe` ASC) VISIBLE,
  CONSTRAINT `fk_REZERVACIJA_OSOBA1`
    FOREIGN KEY (`OSOBA_IdOsobe`)
    REFERENCES `restoran`.`OSOBA` (`IdOsobe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`VRSTA_JELA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`VRSTA_JELA` (
  `IdVrstaJela` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`IdVrstaJela`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`VRSTA_PIĆA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`VRSTA_PIĆA` (
  `IdVrstaPića` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`IdVrstaPića`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`JELO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`JELO` (
  `Recept` VARCHAR(1000) NOT NULL,
  `VeličinaPorcije` VARCHAR(50) NOT NULL,
  `VRSTA_JELA_IdVrstaJela` INT NOT NULL,
  `ARTIKAL_IdArtikal` INT NOT NULL,
  PRIMARY KEY (`ARTIKAL_IdArtikal`),
  INDEX `fk_JELO_VRSTA_JELA_idx` (`VRSTA_JELA_IdVrstaJela` ASC) VISIBLE,
  INDEX `fk_JELO_ARTIKAL1_idx` (`ARTIKAL_IdArtikal` ASC) VISIBLE,
  CONSTRAINT `fk_JELO_VRSTA_JELA`
    FOREIGN KEY (`VRSTA_JELA_IdVrstaJela`)
    REFERENCES `restoran`.`VRSTA_JELA` (`IdVrstaJela`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_JELO_ARTIKAL1`
    FOREIGN KEY (`ARTIKAL_IdArtikal`)
    REFERENCES `restoran`.`ARTIKAL` (`IdArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`PROIZVOĐAČ`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`PROIZVOĐAČ` (
  `IdProizvođač` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NULL,
  PRIMARY KEY (`IdProizvođač`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`PIĆE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`PIĆE` (
  `ARTIKAL_IdArtikal` INT NOT NULL,
  `TrenutnaKoličina` INT NOT NULL,
  `PROIZVOĐAČ_IdProizvođač` INT NOT NULL,
  `VRSTA_PIĆA_IdVrstaPića` INT NOT NULL,
  PRIMARY KEY (`ARTIKAL_IdArtikal`),
  INDEX `fk_PIĆE_ARTIKAL1_idx` (`ARTIKAL_IdArtikal` ASC) VISIBLE,
  INDEX `fk_PIĆE_PROIZVOĐAČ1_idx` (`PROIZVOĐAČ_IdProizvođač` ASC) VISIBLE,
  INDEX `fk_PIĆE_VRSTA_PIĆA1_idx` (`VRSTA_PIĆA_IdVrstaPića` ASC) VISIBLE,
  CONSTRAINT `fk_PIĆE_ARTIKAL1`
    FOREIGN KEY (`ARTIKAL_IdArtikal`)
    REFERENCES `restoran`.`ARTIKAL` (`IdArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PIĆE_PROIZVOĐAČ1`
    FOREIGN KEY (`PROIZVOĐAČ_IdProizvođač`)
    REFERENCES `restoran`.`PROIZVOĐAČ` (`IdProizvođač`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PIĆE_VRSTA_PIĆA1`
    FOREIGN KEY (`VRSTA_PIĆA_IdVrstaPića`)
    REFERENCES `restoran`.`VRSTA_PIĆA` (`IdVrstaPića`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`JELO_ima_SASTOJAK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`JELO_ima_SASTOJAK` (
  `SASTOJAK_IdSastojak` INT NOT NULL,
  `ARTIKAL_IdArtikal` INT NOT NULL,
  `Količina` INT NOT NULL,
  PRIMARY KEY (`SASTOJAK_IdSastojak`, `ARTIKAL_IdArtikal`),
  INDEX `fk_IMA_JELO1_idx` (`ARTIKAL_IdArtikal` ASC) VISIBLE,
  CONSTRAINT `fk_table1_SASTOJAK1`
    FOREIGN KEY (`SASTOJAK_IdSastojak`)
    REFERENCES `restoran`.`SASTOJAK` (`IdSastojak`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_IMA_JELO1`
    FOREIGN KEY (`ARTIKAL_IdArtikal`)
    REFERENCES `restoran`.`JELO` (`ARTIKAL_IdArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`STO_ima_REZERVACIJU`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`STO_ima_REZERVACIJU` (
  `STO_IdSto` INT NOT NULL,
  `REZERVACIJA_IdRezervacija` INT NOT NULL,
  PRIMARY KEY (`STO_IdSto`, `REZERVACIJA_IdRezervacija`),
  INDEX `fk_STO_has_REZERVACIJA_REZERVACIJA1_idx` (`REZERVACIJA_IdRezervacija` ASC) VISIBLE,
  INDEX `fk_STO_has_REZERVACIJA_STO1_idx` (`STO_IdSto` ASC) VISIBLE,
  CONSTRAINT `fk_STO_has_REZERVACIJA_STO1`
    FOREIGN KEY (`STO_IdSto`)
    REFERENCES `restoran`.`STO` (`IdSto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_STO_has_REZERVACIJA_REZERVACIJA1`
    FOREIGN KEY (`REZERVACIJA_IdRezervacija`)
    REFERENCES `restoran`.`REZERVACIJA` (`IdRezervacija`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`NARUDŽBA_ima_ARTIKAL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`NARUDŽBA_ima_ARTIKAL` (
  `NARUDŽBA_IdNarudžba` INT NOT NULL,
  `ARTIKAL_IdArtikal` INT NOT NULL,
  `Količina` INT NOT NULL,
  PRIMARY KEY (`NARUDŽBA_IdNarudžba`, `ARTIKAL_IdArtikal`),
  INDEX `fk_NARUDŽBA_has_ARTIKAL_ARTIKAL1_idx` (`ARTIKAL_IdArtikal` ASC) VISIBLE,
  INDEX `fk_NARUDŽBA_has_ARTIKAL_NARUDŽBA1_idx` (`NARUDŽBA_IdNarudžba` ASC) VISIBLE,
  CONSTRAINT `fk_NARUDŽBA_has_ARTIKAL_NARUDŽBA1`
    FOREIGN KEY (`NARUDŽBA_IdNarudžba`)
    REFERENCES `restoran`.`NARUDŽBA` (`IdNarudžba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NARUDŽBA_has_ARTIKAL_ARTIKAL1`
    FOREIGN KEY (`ARTIKAL_IdArtikal`)
    REFERENCES `restoran`.`ARTIKAL` (`IdArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restoran`.`ARTIKAL_ima_MENI`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restoran`.`ARTIKAL_ima_MENI` (
  `ARTIKAL_IdArtikal` INT NOT NULL,
  `MENI_IdMeni` INT NOT NULL,
  PRIMARY KEY (`ARTIKAL_IdArtikal`, `MENI_IdMeni`),
  INDEX `fk_ARTIKAL_has_MENI_MENI1_idx` (`MENI_IdMeni` ASC) VISIBLE,
  INDEX `fk_ARTIKAL_has_MENI_ARTIKAL1_idx` (`ARTIKAL_IdArtikal` ASC) VISIBLE,
  CONSTRAINT `fk_ARTIKAL_has_MENI_ARTIKAL1`
    FOREIGN KEY (`ARTIKAL_IdArtikal`)
    REFERENCES `restoran`.`ARTIKAL` (`IdArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ARTIKAL_has_MENI_MENI1`
    FOREIGN KEY (`MENI_IdMeni`)
    REFERENCES `restoran`.`MENI` (`IdMeni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
