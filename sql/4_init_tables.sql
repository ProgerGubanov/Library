INSERT INTO `library`.`UserRole` (`RoleName`) 
VALUES ('Администратор');

INSERT INTO `library`.`User` (`IdUserRole`, `Surname`, `Name`, `Patronymic`, `login`, `password`)
VALUES (1,'', '', '', 'admin','21232F297A57A5A743894A0E4A801FC3'); /* MD5 password hash "admin" */