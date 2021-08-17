-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fooddb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `fooddb` ;

-- -----------------------------------------------------
-- Schema fooddb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fooddb` DEFAULT CHARACTER SET utf8 ;
USE `fooddb` ;

-- -----------------------------------------------------
-- Table `admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admin` ;

CREATE TABLE IF NOT EXISTS `admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `service_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `service_location` ;

CREATE TABLE IF NOT EXISTS `service_location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `location_name` VARCHAR(45) NULL,
  `location_phone` VARCHAR(45) NULL,
  `street_address` VARCHAR(500) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` INT NULL,
  `rating` INT NULL,
  `food` TINYINT NULL,
  `laundry` TINYINT NULL,
  `shower` TINYINT NULL,
  `shelter` TINYINT NULL,
  `counseling` TINYINT NULL,
  `medical` TINYINT NULL,
  `bilingual` TINYINT NULL,
  `hours` VARCHAR(45) NULL,
  `admin_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_service_location_admin1_idx` (`admin_id` ASC),
  CONSTRAINT `fk_service_location_admin1`
    FOREIGN KEY (`admin_id`)
    REFERENCES `admin` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donor` ;

CREATE TABLE IF NOT EXISTS `donor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `street_address` VARCHAR(500) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `zip` INT NOT NULL,
  `admin_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_donor_admin1_idx` (`admin_id` ASC),
  CONSTRAINT `fk_donor_admin1`
    FOREIGN KEY (`admin_id`)
    REFERENCES `admin` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipient` ;

CREATE TABLE IF NOT EXISTS `recipient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `street_address` VARCHAR(500) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `zip` INT NOT NULL,
  `admin_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_recipient_admin1_idx` (`admin_id` ASC),
  CONSTRAINT `fk_recipient_admin1`
    FOREIGN KEY (`admin_id`)
    REFERENCES `admin` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comments` ;

CREATE TABLE IF NOT EXISTS `comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `comment` VARCHAR(45) NOT NULL,
  `private_comment` TINYINT NOT NULL,
  `service_location_id` INT NOT NULL,
  `recipient_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comments_service_location1_idx` (`service_location_id` ASC),
  INDEX `fk_comments_recipient1_idx` (`recipient_id` ASC),
  CONSTRAINT `fk_comments_service_location1`
    FOREIGN KEY (`service_location_id`)
    REFERENCES `service_location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_recipient1`
    FOREIGN KEY (`recipient_id`)
    REFERENCES `recipient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donor_has_service_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donor_has_service_location` ;

CREATE TABLE IF NOT EXISTS `donor_has_service_location` (
  `donor_id` INT NOT NULL,
  `service_location_id` INT NOT NULL,
  PRIMARY KEY (`donor_id`, `service_location_id`),
  INDEX `fk_donor_has_service_location_service_location1_idx` (`service_location_id` ASC),
  INDEX `fk_donor_has_service_location_donor_idx` (`donor_id` ASC),
  CONSTRAINT `fk_donor_has_service_location_donor`
    FOREIGN KEY (`donor_id`)
    REFERENCES `donor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_donor_has_service_location_service_location1`
    FOREIGN KEY (`service_location_id`)
    REFERENCES `service_location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `service_location_has_recipient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `service_location_has_recipient` ;

CREATE TABLE IF NOT EXISTS `service_location_has_recipient` (
  `service_location_id` INT NOT NULL,
  `recipient_id` INT NOT NULL,
  PRIMARY KEY (`service_location_id`, `recipient_id`),
  INDEX `fk_service_location_has_recipient_recipient1_idx` (`recipient_id` ASC),
  INDEX `fk_service_location_has_recipient_service_location1_idx` (`service_location_id` ASC),
  CONSTRAINT `fk_service_location_has_recipient_service_location1`
    FOREIGN KEY (`service_location_id`)
    REFERENCES `service_location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_location_has_recipient_recipient1`
    FOREIGN KEY (`recipient_id`)
    REFERENCES `recipient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(200) NULL,
  `enabled` TINYINT NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS fooduser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'fooduser'@'localhost' IDENTIFIED BY 'fooduser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'fooduser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `service_location`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `service_location` (`id`, `location_name`, `location_phone`, `street_address`, `city`, `state`, `zip`, `rating`, `food`, `laundry`, `shower`, `shelter`, `counseling`, `medical`, `bilingual`, `hours`, `admin_id`) VALUES (1, 'Denver', '867-5309', '100 Baker Street', 'Denver', 'CO', 12345, 4, true, true, true, true, true, true, true, '0800-1700', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`) VALUES (1, 'hello', 'goodbye', true, NULL);

COMMIT;

