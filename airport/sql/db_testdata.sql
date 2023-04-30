

INSERT INTO `languages` (`name`, `tag`, `active`) VALUES ('русский', 'ru', true);
INSERT INTO `languages` (`name`, `tag`, `active`) VALUES ('english-английский', 'en', true);
INSERT INTO `languages` (`name`, `tag`, `active`) VALUES ('беларуская-белорусский', 'be', false);




INSERT INTO `airports` (`IATA`, `ICAO`) VALUES
    ('VTB', 'UMII'),('HRG', 'HEGN'),
    ('SSH', 'HESH');
	
INSERT INTO `airports_i18n` (`id_airports`, `id_languages`, `name`) VALUES
    ((SELECT `id` FROM `airports` WHERE IATA='VTB'), (SELECT `id` FROM `languages` WHERE tag='ru'), 'Витебск-Восточный');

INSERT INTO `airports_i18n` (`id_airports`, `id_languages`, `name`) VALUES
    ((SELECT `id` FROM `airports` WHERE IATA='VTB'), (SELECT `id` FROM `languages` WHERE tag='en'), 'Vitebsk-Vostochny');

INSERT INTO `airports_i18n` (`id_airports`, `id_languages`, `name`) VALUES
    ((SELECT `id` FROM `airports` WHERE IATA='HRG'), (SELECT `id` FROM `languages` WHERE tag='ru'), 'Хургада');

INSERT INTO `airports_i18n` (`id_airports`, `id_languages`, `name`) VALUES
    ((SELECT `id` FROM `airports` WHERE IATA='HRG'), (SELECT `id` FROM `languages` WHERE tag='en'), 'Hurghada');
	
INSERT INTO `airports_i18n` (`id_airports`, `id_languages`, `name`) VALUES
    ((SELECT `id` FROM `airports` WHERE IATA='SSH'), (SELECT `id` FROM `languages` WHERE tag='en'), 'Sharm el-Sheikh');

INSERT INTO `airports_i18n` (`id_airports`, `id_languages`, `name`) VALUES
    ((SELECT `id` FROM `airports` WHERE IATA='SSH'), (SELECT `id` FROM `languages` WHERE tag='ru'), 'Шарм-эль-Шейх');




INSERT INTO `airlines` (`IATA`, `ICAO`) VALUES
    ('B2', 'BRU'), ('SU', 'AFL');
	
INSERT INTO `airlines_i18n` (`id_airlines`, `id_languages`, `name`) VALUES
    ((SELECT `id` FROM `airlines` WHERE IATA='B2'), (SELECT `id` FROM `languages` WHERE tag='ru'), 'Белавиа');

INSERT INTO `airlines_i18n` (`id_airlines`, `id_languages`, `name`) VALUES
    ((SELECT `id` FROM `airlines` WHERE IATA='B2'), (SELECT `id` FROM `languages` WHERE tag='en'), 'Belavia');

INSERT INTO `airlines_i18n` (`id_airlines`, `id_languages`, `name`) VALUES
    ((SELECT `id` FROM `airlines` WHERE IATA='SU'), (SELECT `id` FROM `languages` WHERE tag='ru'), 'Аэрофлот');

INSERT INTO `airlines_i18n` (`id_airlines`, `id_languages`, `name`) VALUES
    ((SELECT `id` FROM `airlines` WHERE IATA='SU'), (SELECT `id` FROM `languages` WHERE tag='en'), 'Aeroflot');



INSERT INTO `flights` (`aviacompany_flight_number`, `id_airports`, `id_airlines`, `type`) VALUES
    (8218, (SELECT `id` FROM `airports` WHERE IATA='HRG'), (SELECT `id` FROM `airlines` WHERE IATA='B2'), 'arrival'), (8219, (SELECT `id` FROM `airports` WHERE IATA='HRG'),(SELECT `id` FROM `airlines` WHERE IATA='B2'), 'departure');

INSERT INTO `flights` (`aviacompany_flight_number`, `id_airports`, `id_airlines`, `type`) VALUES
    (8208, (SELECT `id` FROM `airports` WHERE IATA='SSH'), (SELECT `id` FROM `airlines` WHERE IATA='B2'), 'arrival'), (8209, (SELECT `id` FROM `airports` WHERE IATA='SSH'),(SELECT `id` FROM `airlines` WHERE IATA='B2'), 'departure');


INSERT INTO `arrivals` (`id_flights`, `scheduledDate`,  `ArrivalStatus`, `statusTime`) 
	VALUES ((SELECT `id` FROM `flights` 
	WHERE `aviacompany_flight_number`='8218' AND `id_airlines` = (SELECT `id` FROM `airlines` WHERE IATA='B2')), '2023-08-23 10:40:00', '2', '2023-08-23 11:40:00');
INSERT INTO `arrivals` (`id_flights`, `scheduledDate`, `ArrivalStatus`, `statusTime`) 
	VALUES ((SELECT `id` FROM `flights` 
	WHERE `aviacompany_flight_number`='8218' AND `id_airlines` = (SELECT `id` FROM `airlines` WHERE IATA='B2')), '2023-09-13 10:40:00', '1', '2023-09-13 10:40:00');
INSERT INTO `arrivals` (`id_flights`, `scheduledDate`,  `ArrivalStatus`) 
	VALUES ((SELECT `id` FROM `flights` 
	WHERE `aviacompany_flight_number`='8208' AND `id_airlines` = (SELECT `id` FROM `airlines` WHERE IATA='B2')), '2023-09-13 12:40:00', '4');
INSERT INTO `arrivals` (`id_flights`, `scheduledDate`, `ArrivalStatus`, `statusTime`) 
	VALUES ((SELECT `id` FROM `flights` 
	WHERE `aviacompany_flight_number`='8208' AND `id_airlines` = (SELECT `id` FROM `airlines` WHERE IATA='B2')), '2023-08-23 12:40:00', '1', '2023-08-23 12:50:00');


INSERT INTO `departures` (`id_flights`, `scheduledDate`, `DepartureStatus`, `statusTime`) 
	VALUES ((SELECT `id` FROM `flights` 
	WHERE `aviacompany_flight_number`='8219' AND `id_airlines` = (SELECT `id` FROM `airlines` WHERE IATA='B2')), '2023-08-23 13:40:00', '4', '2023-08-23 14:40:00');
INSERT INTO `departures` (`id_flights`, `scheduledDate`, `DepartureStatus`, `statusTime`) 
	VALUES ((SELECT `id` FROM `flights` 
	WHERE `aviacompany_flight_number`='8219' AND `id_airlines` = (SELECT `id` FROM `airlines` WHERE IATA='B2')), '2023-09-13 13:40:00', '1', '2023-09-13 13:40:00');
INSERT INTO `departures` (`id_flights`, `scheduledDate`, `DepartureStatus`) 
	VALUES ((SELECT `id` FROM `flights` 
	WHERE `aviacompany_flight_number`='8209' AND `id_airlines` = (SELECT `id` FROM `airlines` WHERE IATA='B2')), '2023-09-13 15:40:00', '5');
INSERT INTO `departures` (`id_flights`, `scheduledDate`, `DepartureStatus`, `statusTime`) 
	VALUES ((SELECT `id` FROM `flights` 
	WHERE `aviacompany_flight_number`='8209' AND `id_airlines` = (SELECT `id` FROM `airlines` WHERE IATA='B2')), '2023-08-23 15:40:00', '2', '2023-08-23 15:50:00');
    
    
    