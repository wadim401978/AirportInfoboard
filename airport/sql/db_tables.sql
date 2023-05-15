DROP TABLE IF EXISTS `arrivals_i18n`;
DROP TABLE IF EXISTS `departures_i18n`;

DROP TABLE IF EXISTS `arrivals`;
DROP TABLE IF EXISTS `departures`;

DROP TABLE IF EXISTS `flights`;
DROP TABLE IF EXISTS `airports_i18n`;
DROP TABLE IF EXISTS `airports`;

DROP TABLE IF EXISTS `airlines_i18n`;
DROP TABLE IF EXISTS `airlines`;
DROP TABLE IF EXISTS `languages`;
DROP TABLE IF EXISTS `textAnnouncments`;

CREATE TABLE `languages` ( 
    `id` INT(11) NOT NULL AUTO_INCREMENT, 
    `name` VARCHAR(50) NOT NULL, 
    `tag` VARCHAR(2) NOT NULL UNIQUE, 
	`active` BOOLEAN NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB; 


CREATE TABLE `airports` ( 
    `id` INT(11) NOT NULL AUTO_INCREMENT, 
    `IATA` VARCHAR(3) NOT NULL, 
    `ICAO` VARCHAR(4) NOT NULL UNIQUE, 
    PRIMARY KEY (`id`)
) ENGINE = InnoDB; 

CREATE TABLE `airports_i18n` ( 
    `id_airports` INT(11) NOT NULL, 
    `id_languages` INT(11) NOT NULL, 
    `name` VARCHAR(40) NOT NULL,
    CONSTRAINT `pk_AirportNameID` PRIMARY KEY (`id_airports`, `id_languages`),
	FOREIGN KEY (`id_airports`)
		REFERENCES `airports`(`id`)
		ON DELETE CASCADE,
	FOREIGN KEY (`id_languages`)
		REFERENCES `languages`(`id`)
		ON DELETE RESTRICT
) ENGINE = InnoDB; 

CREATE TABLE `airlines` ( 
    `id` INT(11) NOT NULL AUTO_INCREMENT, 
    `IATA` VARCHAR(2) NOT NULL, 
    `ICAO` VARCHAR(3) NOT NULL UNIQUE, 
    `path` VARCHAR(150),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB; 

CREATE TABLE `airlines_i18n` ( 
    `id_airlines` INT(11) NOT NULL, 
    `id_languages` INT(11) NOT NULL, 
    `name` VARCHAR(40) NOT NULL,
	`ordering` INT(11) NOT NULL DEFAULT '0',	
    PRIMARY KEY (`id_airlines`, `id_languages`),
	FOREIGN KEY (`id_airlines`)
		REFERENCES `airlines`(`id`)
		ON DELETE CASCADE,
	FOREIGN KEY (`id_languages`)
		REFERENCES `languages`(`id`)
		ON DELETE RESTRICT
) ENGINE = InnoDB; 

CREATE TABLE `flights` ( 
    `id` INT(11) NOT NULL AUTO_INCREMENT, 
    `aviacompany_flight_number` VARCHAR(4) NOT NULL, 
    `id_airports` INT(11) NOT NULL, 
    `id_airlines` INT(11) NOT NULL, 
    `type` ENUM('arrival', 'departure'),
	CONSTRAINT `flight_number` UNIQUE (`id_airlines`, `aviacompany_flight_number`),
    PRIMARY KEY (`id`),
	FOREIGN KEY (`id_airports`)
		REFERENCES `airports`(`id`)
		ON DELETE RESTRICT,
	FOREIGN KEY (`id_airlines`)
		REFERENCES `airlines`(`id`)
		ON DELETE RESTRICT
) ENGINE = InnoDB; 

CREATE TABLE `arrivals` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`id_flights` INT(11) NOT NULL,
	`scheduledDate` DATETIME NOT NULL,
    `ArrivalStatus` ENUM('1', '2', '3', '4'),
	`statusTime` DATETIME,
	CONSTRAINT `flight_date` UNIQUE (`id_flights`, `scheduledDate`),
	PRIMARY KEY(`id`),
	FOREIGN KEY(`id_flights`)
		REFERENCES `flights`(`id`)
		ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE `departures` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`id_flights` INT(11) NOT NULL,
	`scheduledDate` DATETIME NOT NULL,
    `DepartureStatus` ENUM('1', '2', '3', '4', '5'),
	`statusTime` DATETIME,
	CONSTRAINT `flight_date` UNIQUE (`id_flights`, `scheduledDate`),
	PRIMARY KEY(`id`),
	FOREIGN KEY(`id_flights`)
		REFERENCES `flights`(`id`)
		ON DELETE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE `textAnnouncments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `html` TEXT NOT NULL,
  `active` TINYINT NULL,
  PRIMARY KEY (`id`))  ENGINE = InnoDB;
