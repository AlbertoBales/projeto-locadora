-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema locadora
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema locadora
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `locadora` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `locadora` ;

-- -----------------------------------------------------
-- Table `locadora`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `locadora`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NULL DEFAULT NULL,
  `telefone` VARCHAR(50) NULL DEFAULT NULL,
  `email` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `locadora`.`filme`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `locadora`.`filme` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `locadora`.`locacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `locadora`.`locacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cliente_id` INT NULL DEFAULT NULL,
  `filme_id` INT NULL DEFAULT NULL,
  `data_emprestimo` DATE NULL DEFAULT NULL,
  `data_devolucao` DATE NULL DEFAULT NULL,
  `obs` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `cliente_id` (`cliente_id` ASC) VISIBLE,
  INDEX `filme_id` (`filme_id` ASC) VISIBLE,
  CONSTRAINT `locacao_ibfk_1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `locadora`.`cliente` (`id`),
  CONSTRAINT `locacao_ibfk_2`
    FOREIGN KEY (`filme_id`)
    REFERENCES `locadora`.`filme` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
