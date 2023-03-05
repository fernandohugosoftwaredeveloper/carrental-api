CREATE DATABASE rental;

USE rental;

CREATE TABLE brands (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE models (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  brand_id INT NOT NULL,
  FOREIGN KEY (brand_id) REFERENCES brands(id)
);

CREATE TABLE cars (
  id INT AUTO_INCREMENT PRIMARY KEY,
  model_id INT NOT NULL,
  available BOOLEAN NOT NULL,
  FOREIGN KEY (model_id) REFERENCES models(id)
);

INSERT INTO brands (name)
VALUES ('Ford'),
('Chevrolet'),
('Toyota'),
('Honda');
GO

INSERT INTO models (name, brand_id)
VALUES ('Fusion', 1),
('Focus', 1),
('Camaro', 2),
('Corolla', 3),
('Civic', 4);
GO

INSERT INTO cars (model_id, available)
VALUES (1, true),
(1, false),
(2, true),
(3, false),
(4, true),
(5, true),
(5, false);

GO
