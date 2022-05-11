SET SQL_SAFE_UPDATES = 0;
#SHOW VARIABLES LIKE "sql_safe_updates";

DELETE FROM `oocinema`.`cinema_info`;
DELETE FROM `oocinema`.`film`;
DELETE FROM `oocinema`.`sala`;
DELETE FROM `oocinema`.`ora`;
DELETE FROM `oocinema`.`acquirente`;
DELETE FROM `oocinema`.`posto`;
DELETE FROM `oocinema`.`prenotazione`;
DELETE FROM `oocinema`.`proiezione`;


INSERT INTO `oocinema`.`cinema_info`
(`nome`,
`telefono`,
`user`,
`email`,
`password`,
`indirizzo`)
VALUES
('OOCINEMA - PAVIA',
'0382 - 000000',
'admin',
'oocinema.project@gmail.com',
'admin',
'Via Ferrata 5 - 27100 - Italia');

INSERT INTO `oocinema`.`ora`
(`ora`)
VALUES
('18:00');
INSERT INTO `oocinema`.`ora`
(`ora`)
VALUES
('19:30');
INSERT INTO `oocinema`.`ora`
(`ora`)
VALUES
('21
:00');

INSERT INTO `oocinema`.`sala`
(`id`,
`n_righe`,
`n_col`)
VALUES
(1,
15,
20);

INSERT INTO `oocinema`.`sala`
(`id`,
`n_righe`,
`n_col`)
VALUES
(2,
15,
20);

INSERT INTO `oocinema`.`sala`
(`id`,
`n_righe`,
`n_col`)
VALUES
(3,
10,
25);

SET SQL_SAFE_UPDATES = 1;

