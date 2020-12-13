-- # CREATE DATABASE IF NOT EXISTS applianceservice;
-- #
-- # ALTER DATABASE applianceservice
-- # DEFAULT CHARACTER SET utf8
-- # DEFAULT COLLATE utf8_general_ci;
-- #
-- # CREATE USER 'myuser'@'%' IDENTIFIED BY 'myroot';
-- # GRANT ALL PRIVILEGES ON applianceservice.* TO 'myuser'@'%' WITH GRANT OPTION;

-- CREATE USER 'myuserroot'@'%' IDENTIFIED BY 'mypasswordroot';
-- GRANT ALL PRIVILEGES ON applianceservice.* TO 'myuserroot'@'%' WITH GRANT OPTION;

CREATE TABLE IF NOT EXISTS types (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS customers (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  address VARCHAR(255),
  city VARCHAR(80),
  telephone VARCHAR(20),
  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS appliances (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  purchase_date DATE,
  type_id INT(4) UNSIGNED NOT NULL,
  customer_id INT(4) UNSIGNED NOT NULL,
  INDEX(name),
  FOREIGN KEY (customer_id) REFERENCES customers(id),
  FOREIGN KEY (type_id) REFERENCES types(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS drops (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  appliance_id INT(4) UNSIGNED NOT NULL,
  drop_date DATE,
  description VARCHAR(255),
  FOREIGN KEY (appliance_id) REFERENCES appliances(id)
) engine=InnoDB;


