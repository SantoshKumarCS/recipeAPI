CREATE TABLE `food_point`.`recipe` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `created_date` TIMESTAMP NOT NULL,
  `dish_type` BOOLEAN NOT NULL,
  `portions` INTEGER UNSIGNED NOT NULL,
  `ingredients` VARCHAR(45) NOT NULL,
  `description` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`id`)
)