CREATE DATABASE IF NOT EXISTS rental;

USE rental;

CREATE TABLE IF NOT EXISTS brands (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

INSERT INTO brands (name)
VALUES ('Ford'),
('Chevrolet'),
('Toyota'),
('Honda');

CREATE TABLE IF NOT EXISTS models (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  brand_id INT NOT NULL,
  FOREIGN KEY (brand_id) REFERENCES brands(id)
);

INSERT INTO models (name, brand_id)
VALUES ('Fusion', 1),
('Focus', 1),
('Camaro', 2),
('Corolla', 3),
('Civic', 4);

CREATE TABLE IF NOT EXISTS cars (
  id INT AUTO_INCREMENT PRIMARY KEY,
  model_id INT NOT NULL,
  available BOOLEAN NOT NULL,
  FOREIGN KEY (model_id) REFERENCES models(id)
);

INSERT INTO cars (model_id, available)
VALUES (1, true),
(1, false),
(2, true),
(3, false),
(4, true),
(5, true),
(5, false);

