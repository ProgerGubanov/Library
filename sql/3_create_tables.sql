-- -----------------------------------------------------
-- Table `library`.`BookStatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`books_status`;

CREATE TABLE `library`.`BookStatus` (
  `IdBookStatus` TINYINT(1) NOT NULL AUTO_INCREMENT COMMENT 'уникальный код записи',
  `BookStatusName` VARCHAR(50) NOT NULL COMMENT 'наименование статуса',
  PRIMARY KEY (`IdBookStatus`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COMMENT = 'статус книги';


-- -----------------------------------------------------
-- Table `library`.`Card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`Card`;


CREATE TABLE `library`.`Card` (
  `IdCard` INT NOT NULL AUTO_INCREMENT COMMENT 'уникальный код книги',
  `Author` VARCHAR(100) NOT NULL COMMENT 'автор(ы)',
  `Title` VARCHAR(100) NOT NULL COMMENT 'название',
  `Isbn` VARCHAR(25) NULL COMMENT 'международный стандартный книжный номер',
  `YearPublication` INT(4) NULL COMMENT 'дата публикации',
  PRIMARY KEY (`IdCard`),
  INDEX `TitleIdx` (`Title` ASC),
  INDEX `AuthorIdx` (`Author` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COMMENT = 'книги';


-- -----------------------------------------------------
-- Table `library`.`Book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`Book`;

CREATE TABLE `library`.`Book` (
  `IdBook` INT NOT NULL AUTO_INCREMENT COMMENT 'уникальный код книги',
  `InventoryNumber` VARCHAR(25) NOT NULL COMMENT 'инвентарный номер',
  `IdBookStatus` TINYINT(1) NOT NULL COMMENT 'код статуса книги (выдана на абонемент, в читальном зале, в библиотеке)',
  `IdCard` INT NULL,
  PRIMARY KEY (`IdBook`),
  INDEX `FkIdBookStatusIdx` (`IdBookStatus` ASC),
  INDEX `FkIdCardIdx` (`IdCard` ASC),
  CONSTRAINT `FkIdBookStatus`
    FOREIGN KEY (`IdBookStatus`)
    REFERENCES `library`.`BookStatus` (`IdBookStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FkIdCard`
    FOREIGN KEY (`IdCard`)
    REFERENCES `library`.`Card` (`IdCard`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COMMENT = 'книги';


-- -----------------------------------------------------
-- Table `library`.`UserRole`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`UserRole`;

CREATE TABLE `library`.`UserRole` (
  `IdUserRole` TINYINT(1) NOT NULL AUTO_INCREMENT COMMENT 'уникальный код',
  `RoleName` VARCHAR(50) NOT NULL COMMENT 'роль пользователя',
  PRIMARY KEY (`IdUserRole`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COMMENT = 'роли пользователей';


-- -----------------------------------------------------
-- Table `library`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`User`;

CREATE TABLE `library`.`User` (
  `IdUser` INT NOT NULL AUTO_INCREMENT COMMENT 'код читателя',
  `Surname` VARCHAR(30) NOT NULL COMMENT 'фамилия',
  `Name` VARCHAR(30) NOT NULL COMMENT 'имя',
  `Patronymic` VARCHAR(30) NOT NULL COMMENT 'отчество',
  `Subscription` VARCHAR(20) NULL COMMENT 'номер абонемента (читательского билета)',
  `Address` VARCHAR(100) NULL COMMENT 'адрес',
  `PhoneHome` VARCHAR(20) NULL COMMENT 'телефон домашний',
  `PhoneMobile` VARCHAR(20) NULL COMMENT 'телефон мобильный',
  `Email` VARCHAR(50) NULL COMMENT 'e-mail',
  `IdUserRole` TINYINT(1) NOT NULL COMMENT 'статус пользователя',
  `Login` VARCHAR(50) NOT NULL COMMENT 'Логин',
  `Password` CHAR(32) NOT NULL COMMENT 'MD5 хэш пароля',
  PRIMARY KEY (`IdUser`),
  INDEX `FkIdUserRoleIdx` (`IdUserRole` ASC),
  CONSTRAINT `FkIdUserRole`
    FOREIGN KEY (`IdUserRole`)
    REFERENCES `library`.`UserRole` (`IdUserRole`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COMMENT = 'пользователи';


-- -----------------------------------------------------
-- Table `library`.`Request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`Request`;

CREATE TABLE `library`.`Request` (
  `IdRequest` INT NOT NULL AUTO_INCREMENT COMMENT 'уникальный код',
  `IdCard` INT NULL COMMENT 'код книги',
  `IdUser` INT NOT NULL COMMENT 'код читателя',
  `DateRequest` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'дата оформления заказа',
  `IsReadingRoom` TINYINT(1) NULL COMMENT 'признак заказа книги в читальный зал',
  PRIMARY KEY (`IdRequest`),
  INDEX `FkIdUserRIdx` (`IdUser` ASC),
  INDEX `FkIdCardIdx` (`IdCard` ASC),
  CONSTRAINT `FkIdCardR`
    FOREIGN KEY (`IdCard`)
    REFERENCES `library`.`Card` (`IdCard`),
  CONSTRAINT `FkIdUserR`
    FOREIGN KEY (`IdUser`)
    REFERENCES `library`.`User` (`IdUser`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COMMENT = 'заявки';


-- -----------------------------------------------------
-- Table `library`.`Order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`Order`;

CREATE TABLE `library`.`Order` (
  `IdOrder` INT NOT NULL AUTO_INCREMENT COMMENT 'уникальный код',
  `IdBook` INT NULL COMMENT 'код книги',
  `IdUser` INT NULL COMMENT 'код читателя',
  `IdLibrarian` INT NULL COMMENT 'код библиотекаря',
  `DatePlannedReturn` DATE NULL COMMENT 'планируемая дата возврата книги',
  `DateActualReturn` TIMESTAMP NULL COMMENT 'реальная дата возврата книги',
  `DateIssue` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'дата выдачи книги',
  `IsReadingRoom` TINYINT(1) NULL COMMENT 'признак заказа книги в читальный зал',
  PRIMARY KEY (`IdOrder`),
  INDEX `FkIdBookIdx` (`IdBook` ASC),
  INDEX `FkIdUserIdx` (`IdUser` ASC),
  INDEX `FkIdLibrarianIdx` (`IdLibrarian` ASC),
  CONSTRAINT `FkIdBook`
    FOREIGN KEY (`IdBook`)
    REFERENCES `library`.`Book` (`IdBook`),
  CONSTRAINT `FkIdUser`
    FOREIGN KEY (`IdUser`)
    REFERENCES `library`.`User` (`IdUser`),
  CONSTRAINT `FkIdLibrarian`
    FOREIGN KEY (`IdLibrarian`)
    REFERENCES `library`.`User` (`IdUser`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COMMENT = 'заказы';