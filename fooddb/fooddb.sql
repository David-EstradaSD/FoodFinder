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
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  `role` VARCHAR(100) NULL,
  `enabled` TINYINT NULL,
  `phone` VARCHAR(45) NULL,
  `image` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street_address` VARCHAR(500) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(10) NULL,
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
  `hours` VARCHAR(45) NULL,
  `user_id` INT NULL,
  `address_id` INT NOT NULL,
  `description` TEXT NULL,
  `create_date` DATETIME NULL,
  `image_url` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_service_location_admin1_idx` (`user_id` ASC),
  INDEX `fk_service_location_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_service_location_admin1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_location_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donor` ;

CREATE TABLE IF NOT EXISTS `donor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_donor_admin1_idx` (`user_id` ASC),
  INDEX `fk_donor_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_donor_admin1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_donor_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipient` ;

CREATE TABLE IF NOT EXISTS `recipient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_recipient_admin1_idx` (`user_id` ASC),
  INDEX `fk_recipient_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_recipient_admin1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipient_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` VARCHAR(500) NOT NULL,
  `private_comment` TINYINT NOT NULL,
  `service_location_id` INT NOT NULL,
  `recipient_id` INT NOT NULL,
  `create_date` DATETIME NULL,
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
-- Table `service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `service` ;

CREATE TABLE IF NOT EXISTS `service` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(500) NULL,
  `service_name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `service_location_has_service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `service_location_has_service` ;

CREATE TABLE IF NOT EXISTS `service_location_has_service` (
  `service_location_id` INT NOT NULL,
  `service_id` INT NOT NULL,
  PRIMARY KEY (`service_location_id`, `service_id`),
  INDEX `fk_service_location_has_service_service1_idx` (`service_id` ASC),
  INDEX `fk_service_location_has_service_service_location1_idx` (`service_location_id` ASC),
  CONSTRAINT `fk_service_location_has_service_service_location1`
    FOREIGN KEY (`service_location_id`)
    REFERENCES `service_location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_location_has_service_service1`
    FOREIGN KEY (`service_id`)
    REFERENCES `service` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rating` ;

CREATE TABLE IF NOT EXISTS `rating` (
  `recipient_id` INT NOT NULL,
  `service_location_id` INT NOT NULL,
  `rating` INT NULL,
  PRIMARY KEY (`recipient_id`, `service_location_id`),
  INDEX `fk_rating_service_location1_idx` (`service_location_id` ASC),
  CONSTRAINT `fk_rating_recipient1`
    FOREIGN KEY (`recipient_id`)
    REFERENCES `recipient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rating_service_location1`
    FOREIGN KEY (`service_location_id`)
    REFERENCES `service_location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `phone`, `image`) VALUES (1, 'Mary', 'Moore', 'username', '$2a$10$sYPe08XBasp4gh9wBoe.yeCHhICbXzg/yT3KthXUxcRL8fMFABXva', 'mary.morre@yahoo.com', 'admin', true, '867-5309', '\'images/mary.jpeg\'');

COMMIT;


-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `address` (`id`, `street_address`, `city`, `state`, `zip`) VALUES (1, '100 Baker Street', 'Denver', 'CO', '12345');

COMMIT;


-- -----------------------------------------------------
-- Data for table `service_location`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `service_location` (`id`, `location_name`, `location_phone`, `hours`, `user_id`, `address_id`, `description`, `create_date`, `image_url`) VALUES (1, 'Denver', '867-5309', '0800-1700', 1, 1, 'First Baptist Church', '2021-01-20 12:00:00', 'https://www.spam.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `donor`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `donor` (`id`, `category`, `user_id`, `address_id`) VALUES (1, 'food', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `recipient`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `recipient` (`id`, `user_id`, `address_id`) VALUES (1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `comment` (`id`, `comment`, `private_comment`, `service_location_id`, `recipient_id`, `create_date`) VALUES (1, 'Great service', false, 1, 1, '2021-01-20 12:00:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `donor_has_service_location`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `donor_has_service_location` (`donor_id`, `service_location_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `service_location_has_recipient`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `service_location_has_recipient` (`service_location_id`, `recipient_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `service`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `service` (`id`, `description`, `service_name`) VALUES (1, 'hot', 'shower');

COMMIT;


-- -----------------------------------------------------
-- Data for table `service_location_has_service`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `service_location_has_service` (`service_location_id`, `service_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `rating` (`recipient_id`, `service_location_id`, `rating`) VALUES (1, 1, 5);

COMMIT;

