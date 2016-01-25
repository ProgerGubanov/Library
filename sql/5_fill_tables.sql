/*Data for the table `library`.`BookStatus` */
INSERT INTO `library`.`BookStatus`(`BookStatusName`) 
VALUES ('В библиотеке'),('В читальном зале'),('Выдана на абонемент');


/*Data for the table `library`.`UserRole` */
INSERT INTO `library`.`UserRole` (`RoleName`) 
VALUES ('Библиотекарь'),('Читатель');


/*Data for the table `library`.`User` */
INSERT INTO `library`.`User` (`Surname`, `Name`, `Patronymic`, `Subscription`, `Address`, `PhoneHome`, `PhoneMobile`, `Email`, `IdUserRole`, `Login`, `Password`) 
VALUES
('Иванова','Валентина','Сергеевна',NULL,NULL,'123456',NULL,NULL,2,'librarian','35fa1bcb6fbfa7aa343aa7f253507176'), /* MD5 password hash "librarian" */
('Семенов','Дмитрий','Владимирович','123456','ул.Ленина, д.32, кв.48','564223','+375291234567','semenov@tut.by',3,'reader1','6e6fba5122623ea1ec36af3336cdb70c'), /* MD5 password hash "reader1" */
('Петров','Вадим','Олегович','34634','пр-т.Фрунзе, д.24, кв.18',NULL,'+375295123486',NULL,3,'reader2','273a531832ab8a9e3c2d62cea8230e41'), /* MD5 password hash "reader2" */
('Сидоров','Александр','Петрович','4646642','ул.Правды, д.17, кв. 3',NULL,'+375294584126',NULL,3,'reader3','a997b62d1638d3af0f4b47a2280c6375'); /* MD5 password hash "reader3" */


/*Data for the table `library`.`Card` */
INSERT INTO `library`.`Card` (`Author`, `Title`, `Isbn`, `YearPublication`)
VALUES 
('Вадим Дунаев','Сценарии для Web-сайта PHP и JavaScript','978-5-9775-0112-5',2008),
('Жилинский А.А.','Самоучитель Microsoft SQL Server 2008','978-5-9775-0217-7',2009),
('Корняков В.Н.','Программирование документов и приложений MS Office в Delphi','5-94157-458-4',2005),
('Тернстрем Т.','Microsoft SQL Server 2008. Разработка баз данных. Учебный курс Microsoft','978-5-7502-0394-9',2010),
('Борри Х.','Firebird: руководство разработчика баз данных','5-94157-609-9',2006);


/*Data for the table `library`.`Book` */
INSERT INTO `library`.`Book` (`InventoryNumber`, `IdBookStatus`, `IdCard`)
VALUES
('АБ2346343',1,1),
('ВГ5465884',1,2),
('ДЕ5874831',3,3),
('ВД46436',1,4),
('ИК54688568',2,5),
('АБ2346344',1,1),
('ВГ5465885',1,2),
('ДЕ5874832',1,3),
('ВД46435',1,4),
('ИК54688569',1,5);


/*Data for the table `library`.`Request` */
INSERT INTO `library`.`Request` (`IdCard`, `IdUser`, `DateRequest`, `IsReadingRoom`)
VALUES
(1,5,'2015-12-08 12:41:02', 0);


/*Data for the table `library`.`Order` */
INSERT INTO `library`.`Order` (`IdBook`, `IdUser`, `IdLibrarian`, `DatePlannedReturn`, `DateActualReturn`, `DateIssue`, `IsReadingRoom`) 
VALUES
(3,3,2,'2016-01-07',NULL,'2015-12-08 10:24:21',0),
(5,4,2,'2015-12-08','2015-12-08 11:37:02','2015-12-08 10:36:22',1);

