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
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
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
  UNIQUE INDEX `recipient_id_UNIQUE` (`recipient_id` ASC),
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
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `phone`, `image`) VALUES (2, 'a', 'a', 'a', 'a', 'a', 'admin', true, 'a', 'a');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `phone`, `image`) VALUES (3, 'Paul', 'Smith', 'MufflerMan', 'ThisIsMyPassword@29', 'MyEmail@yahoo.com', 'recipient', true, '712-2534', '\'images/tomato.jpeg\'');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `phone`, `image`) VALUES (4, 'Beth', 'Hunter', 'Kitten132', 'YodelOnMountain000', 'MrCar@gmail.com', 'recipient', true, '987-9078', '\'images/justme.jpeg\'');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `phone`, `image`) VALUES (5, 'Cyndi', 'Slattery', 'Sunshine999', 'MySweet16', 'BigTimePrez@yahoo.com', 'donor', true, '836-7823', '\'images/lastyearphoto.jpeg\'');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `phone`, `image`) VALUES (6, 'Mike', 'Pitts', 'GumbyClay76', 'TVOnTheRadio100', 'DontTredOnMe@gmail.com', 'recipient', true, '816-8032', '\'images/2020.jpeg\'');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `phone`, `image`) VALUES (7, 'Ronald', 'Albers', 'MeatballYumYum23', 'NamedAfterPresidents1', 'Wanderer@yahoo.com', 'donor', true, '623-6719', '\'images/vacation.jpeg\'');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `phone`, `image`) VALUES (8, 'Robert', 'Paulson', 'FightClubSoap11', 'JustLetGoCrash', 'SenorFrog@gmail.com', 'recipient', true, '510-3529', '\'images/summer69.jpeg\'');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `phone`, `image`) VALUES (9, 'Karen', 'Kennedy', 'QueenOfYelling911', 'HighMaintenanceLife20', '8Ballah@gmail.com', 'recipient', true, '954-7819', '\'images/colorfulphoto.jpeg\'');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `role`, `enabled`, `phone`, `image`) VALUES (10, 'Becky', 'White', 'RoseGardenGirl', 'TheUndertaker911', 'RowdyCivility@yahoo.com', 'donor', true, '761-0926', '\'images/walkingbeach.jpeg\'');

COMMIT;


-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `address` (`id`, `street_address`, `city`, `state`, `zip`) VALUES (1, '100 Baker Street', 'Denver', 'CO', '12345');
INSERT INTO `address` (`id`, `street_address`, `city`, `state`, `zip`) VALUES (2, '13730 West 85th Drive', 'Arvada', 'CO', '80005');
INSERT INTO `address` (`id`, `street_address`, `city`, `state`, `zip`) VALUES (3, '9740 Grant Street', 'Thornton', 'CO', '80229');
INSERT INTO `address` (`id`, `street_address`, `city`, `state`, `zip`) VALUES (4, '7400 East Orchard Road', 'Greenwood Village', 'CO', '80111');
INSERT INTO `address` (`id`, `street_address`, `city`, `state`, `zip`) VALUES (5, '401 Center Drive', 'Superior', 'CO', '80027');
INSERT INTO `address` (`id`, `street_address`, `city`, `state`, `zip`) VALUES (6, '1700 Dogwood Street', 'Louisville', 'CO', '80027');
INSERT INTO `address` (`id`, `street_address`, `city`, `state`, `zip`) VALUES (7, '6620 Wadsworth Blvd', 'Arvada', 'CO', '80003');
INSERT INTO `address` (`id`, `street_address`, `city`, `state`, `zip`) VALUES (8, '14618 Deleware Street', 'Westminster', 'CO', '80023');
INSERT INTO `address` (`id`, `street_address`, `city`, `state`, `zip`) VALUES (9, '10180 East Colfax Avenue', 'Aurora', 'CO', '80010');
INSERT INTO `address` (`id`, `street_address`, `city`, `state`, `zip`) VALUES (10, '66 Sheridan Boulevard', 'Denver', 'CO', '80226');

COMMIT;


-- -----------------------------------------------------
-- Data for table `service_location`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `service_location` (`id`, `location_name`, `location_phone`, `hours`, `user_id`, `address_id`, `description`, `create_date`, `image_url`) VALUES (1, 'Denver', '867-5309', '0800-1700', 1, 1, 'First Baptist Church', '2021-01-18 11:38:00', 'https://www.spam.com');
INSERT INTO `service_location` (`id`, `location_name`, `location_phone`, `hours`, `user_id`, `address_id`, `description`, `create_date`, `image_url`) VALUES (2, 'La Casa Bonita', '243-1765', '1000-2200', 2, 2, 'Mexican Restaurant', '2021-02-20 09:45:00', 'https://www.lcb.com');
INSERT INTO `service_location` (`id`, `location_name`, `location_phone`, `hours`, `user_id`, `address_id`, `description`, `create_date`, `image_url`) VALUES (3, 'Old School Pizza', '243-8734', '1100-2300', 3, 3, 'Funky Pizza Staple', '2021-02-11 02:56:00', 'https://www.oldschool.com');
INSERT INTO `service_location` (`id`, `location_name`, `location_phone`, `hours`, `user_id`, `address_id`, `description`, `create_date`, `image_url`) VALUES (4, 'EZ Laundry', '243-8987', '0000-0000', 4, 4, '24 Hour Laundry', '2021-03-13 06:22:00', 'https://www.ezlaundry.com');
INSERT INTO `service_location` (`id`, `location_name`, `location_phone`, `hours`, `user_id`, `address_id`, `description`, `create_date`, `image_url`) VALUES (5, 'Medical Support Center', '343-7755', '0000-0000', 5, 5, 'Medical Clinic', '2021-03-23 10:12:00', 'https://www.msc.com');
INSERT INTO `service_location` (`id`, `location_name`, `location_phone`, `hours`, `user_id`, `address_id`, `description`, `create_date`, `image_url`) VALUES (6, 'Lady of Hope Shelter', '243-9836', '0000-0000', 6, 6, 'Open Bay Shelter', '2021-04-20 00:05:00', 'https://www.ladyofhope.com');
INSERT INTO `service_location` (`id`, `location_name`, `location_phone`, `hours`, `user_id`, `address_id`, `description`, `create_date`, `image_url`) VALUES (7, 'Bennies BBQ ', '243-8911', '1500-2200', 7, 7, 'Iconic BBQ ', '2021-04-15 12:28:00', 'https://www.benniesBBQ.com');
INSERT INTO `service_location` (`id`, `location_name`, `location_phone`, `hours`, `user_id`, `address_id`, `description`, `create_date`, `image_url`) VALUES (8, 'Los Pollos Hermanos', '243-7776', '1100-2300', 8, 8, 'Original Albuquerqe Recipe', '2021-05-09 07:23:00', 'https://www.heizenburgblue.com');
INSERT INTO `service_location` (`id`, `location_name`, `location_phone`, `hours`, `user_id`, `address_id`, `description`, `create_date`, `image_url`) VALUES (9, 'Employment Center', '334-8855', '0900-1800', 9, 9, 'Temporary Employment Center', '2021-06-17 11:30:00', 'https://www.employmentcenter.com');
INSERT INTO `service_location` (`id`, `location_name`, `location_phone`, `hours`, `user_id`, `address_id`, `description`, `create_date`, `image_url`) VALUES (10, 'Language Learning Center', '354-9224', '0900-2200', 10, 10, 'Language School', '2021-08-05 09:32:00', 'https://www.learnwithus.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `donor`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `donor` (`id`, `category`, `user_id`, `address_id`) VALUES (1, 'food', 1, 1);
INSERT INTO `donor` (`id`, `category`, `user_id`, `address_id`) VALUES (2, 'food', 2, 2);
INSERT INTO `donor` (`id`, `category`, `user_id`, `address_id`) VALUES (3, 'food', 3, 3);
INSERT INTO `donor` (`id`, `category`, `user_id`, `address_id`) VALUES (4, 'laundry', 4, 4);
INSERT INTO `donor` (`id`, `category`, `user_id`, `address_id`) VALUES (5, 'counseling', 5, 5);
INSERT INTO `donor` (`id`, `category`, `user_id`, `address_id`) VALUES (6, 'medical', 6, 6);
INSERT INTO `donor` (`id`, `category`, `user_id`, `address_id`) VALUES (7, 'counseling', 7, 7);
INSERT INTO `donor` (`id`, `category`, `user_id`, `address_id`) VALUES (8, 'food', 8, 8);
INSERT INTO `donor` (`id`, `category`, `user_id`, `address_id`) VALUES (9, 'shelter', 9, 9);
INSERT INTO `donor` (`id`, `category`, `user_id`, `address_id`) VALUES (10, 'showers', 10, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `recipient`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `recipient` (`id`, `user_id`, `address_id`) VALUES (1, 1, 1);
INSERT INTO `recipient` (`id`, `user_id`, `address_id`) VALUES (2, 2, 2);
INSERT INTO `recipient` (`id`, `user_id`, `address_id`) VALUES (3, 3, 3);
INSERT INTO `recipient` (`id`, `user_id`, `address_id`) VALUES (4, 4, 4);
INSERT INTO `recipient` (`id`, `user_id`, `address_id`) VALUES (5, 5, 5);
INSERT INTO `recipient` (`id`, `user_id`, `address_id`) VALUES (6, 6, 6);
INSERT INTO `recipient` (`id`, `user_id`, `address_id`) VALUES (7, 7, 7);
INSERT INTO `recipient` (`id`, `user_id`, `address_id`) VALUES (8, 8, 8);
INSERT INTO `recipient` (`id`, `user_id`, `address_id`) VALUES (9, 9, 9);
INSERT INTO `recipient` (`id`, `user_id`, `address_id`) VALUES (10, 10, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `comment` (`id`, `comment`, `private_comment`, `service_location_id`, `recipient_id`, `create_date`) VALUES (1, 'Great service', false, 1, 1, '2021-01-20 12:00:00');
INSERT INTO `comment` (`id`, `comment`, `private_comment`, `service_location_id`, `recipient_id`, `create_date`) VALUES (2, 'Very nice people, great food!', false, 2, 2, '2021-02-18 11:38:00');
INSERT INTO `comment` (`id`, `comment`, `private_comment`, `service_location_id`, `recipient_id`, `create_date`) VALUES (3, 'Comfortable chairs, tasty food!', false, 3, 3, '2021-02-12 02:21:00');
INSERT INTO `comment` (`id`, `comment`, `private_comment`, `service_location_id`, `recipient_id`, `create_date`) VALUES (4, 'Excellent portions', false, 4, 4, '2021-02-02 10:54:00');
INSERT INTO `comment` (`id`, `comment`, `private_comment`, `service_location_id`, `recipient_id`, `create_date`) VALUES (5, 'Clean showers and a hot meal', false, 5, 5, '2021-03-05 05:15:00');
INSERT INTO `comment` (`id`, `comment`, `private_comment`, `service_location_id`, `recipient_id`, `create_date`) VALUES (6, 'Was grateful for laundry', false, 6, 6, '2021-03-26 18:01:00');
INSERT INTO `comment` (`id`, `comment`, `private_comment`, `service_location_id`, `recipient_id`, `create_date`) VALUES (7, 'Nice refuge, felt safe and welcome', false, 7, 7, '2021-04-15 23:12:00');
INSERT INTO `comment` (`id`, `comment`, `private_comment`, `service_location_id`, `recipient_id`, `create_date`) VALUES (8, 'Understanding counselors and good food', false, 8, 8, '2021-04-18 09:17:00');
INSERT INTO `comment` (`id`, `comment`, `private_comment`, `service_location_id`, `recipient_id`, `create_date`) VALUES (9, 'Felt like family', false, 9, 9, '2021-05-27 11:52:00');
INSERT INTO `comment` (`id`, `comment`, `private_comment`, `service_location_id`, `recipient_id`, `create_date`) VALUES (10, 'Grateful for a bed and good sleep', false, 10, 10, '2021-07-12 15:37:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `donor_has_service_location`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `donor_has_service_location` (`donor_id`, `service_location_id`) VALUES (1, 1);
INSERT INTO `donor_has_service_location` (`donor_id`, `service_location_id`) VALUES (2, 2);
INSERT INTO `donor_has_service_location` (`donor_id`, `service_location_id`) VALUES (3, 3);
INSERT INTO `donor_has_service_location` (`donor_id`, `service_location_id`) VALUES (4, 4);
INSERT INTO `donor_has_service_location` (`donor_id`, `service_location_id`) VALUES (5, 5);
INSERT INTO `donor_has_service_location` (`donor_id`, `service_location_id`) VALUES (6, 6);
INSERT INTO `donor_has_service_location` (`donor_id`, `service_location_id`) VALUES (7, 7);
INSERT INTO `donor_has_service_location` (`donor_id`, `service_location_id`) VALUES (8, 8);
INSERT INTO `donor_has_service_location` (`donor_id`, `service_location_id`) VALUES (9, 9);
INSERT INTO `donor_has_service_location` (`donor_id`, `service_location_id`) VALUES (10, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `service_location_has_recipient`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `service_location_has_recipient` (`service_location_id`, `recipient_id`) VALUES (1, 1);
INSERT INTO `service_location_has_recipient` (`service_location_id`, `recipient_id`) VALUES (2, 2);
INSERT INTO `service_location_has_recipient` (`service_location_id`, `recipient_id`) VALUES (3, 3);
INSERT INTO `service_location_has_recipient` (`service_location_id`, `recipient_id`) VALUES (4, 4);
INSERT INTO `service_location_has_recipient` (`service_location_id`, `recipient_id`) VALUES (5, 5);
INSERT INTO `service_location_has_recipient` (`service_location_id`, `recipient_id`) VALUES (6, 6);
INSERT INTO `service_location_has_recipient` (`service_location_id`, `recipient_id`) VALUES (7, 7);
INSERT INTO `service_location_has_recipient` (`service_location_id`, `recipient_id`) VALUES (8, 8);
INSERT INTO `service_location_has_recipient` (`service_location_id`, `recipient_id`) VALUES (9, 9);
INSERT INTO `service_location_has_recipient` (`service_location_id`, `recipient_id`) VALUES (10, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `service`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `service` (`id`, `description`, `service_name`) VALUES (1, 'Individual', 'Showers');
INSERT INTO `service` (`id`, `description`, `service_name`) VALUES (2, 'No cost and detergent provided', 'Laundry');
INSERT INTO `service` (`id`, `description`, `service_name`) VALUES (3, 'Employment Assistance', 'Counseling');
INSERT INTO `service` (`id`, `description`, `service_name`) VALUES (4, 'Addiction', 'Counseling');
INSERT INTO `service` (`id`, `description`, `service_name`) VALUES (5, 'Immunization', 'Medical ');
INSERT INTO `service` (`id`, `description`, `service_name`) VALUES (6, 'HIV Testing', 'Medical');
INSERT INTO `service` (`id`, `description`, `service_name`) VALUES (7, 'Individual Room', 'Shelter');
INSERT INTO `service` (`id`, `description`, `service_name`) VALUES (8, 'Bunk / Open Bay', 'Shelter');
INSERT INTO `service` (`id`, `description`, `service_name`) VALUES (9, 'Spanish', 'Multilingual');
INSERT INTO `service` (`id`, `description`, `service_name`) VALUES (10, 'Pashto', 'Multilingual');

COMMIT;


-- -----------------------------------------------------
-- Data for table `service_location_has_service`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `service_location_has_service` (`service_location_id`, `service_id`) VALUES (1, 1);
INSERT INTO `service_location_has_service` (`service_location_id`, `service_id`) VALUES (2, 2);
INSERT INTO `service_location_has_service` (`service_location_id`, `service_id`) VALUES (3, 3);
INSERT INTO `service_location_has_service` (`service_location_id`, `service_id`) VALUES (4, 4);
INSERT INTO `service_location_has_service` (`service_location_id`, `service_id`) VALUES (5, 5);
INSERT INTO `service_location_has_service` (`service_location_id`, `service_id`) VALUES (6, 6);
INSERT INTO `service_location_has_service` (`service_location_id`, `service_id`) VALUES (7, 7);
INSERT INTO `service_location_has_service` (`service_location_id`, `service_id`) VALUES (8, 8);
INSERT INTO `service_location_has_service` (`service_location_id`, `service_id`) VALUES (9, 9);
INSERT INTO `service_location_has_service` (`service_location_id`, `service_id`) VALUES (10, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `fooddb`;
INSERT INTO `rating` (`recipient_id`, `service_location_id`, `rating`) VALUES (1, 1, 5);
INSERT INTO `rating` (`recipient_id`, `service_location_id`, `rating`) VALUES (2, 2, 5);
INSERT INTO `rating` (`recipient_id`, `service_location_id`, `rating`) VALUES (3, 3, 4);
INSERT INTO `rating` (`recipient_id`, `service_location_id`, `rating`) VALUES (4, 4, 5);
INSERT INTO `rating` (`recipient_id`, `service_location_id`, `rating`) VALUES (5, 5, 5);
INSERT INTO `rating` (`recipient_id`, `service_location_id`, `rating`) VALUES (6, 6, 5);
INSERT INTO `rating` (`recipient_id`, `service_location_id`, `rating`) VALUES (7, 7, 4);
INSERT INTO `rating` (`recipient_id`, `service_location_id`, `rating`) VALUES (8, 8, 5);
INSERT INTO `rating` (`recipient_id`, `service_location_id`, `rating`) VALUES (9, 9, 5);
INSERT INTO `rating` (`recipient_id`, `service_location_id`, `rating`) VALUES (10, 10, 4);

COMMIT;

