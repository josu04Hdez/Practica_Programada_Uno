CREATE DATABASE IF NOT EXISTS practica_web
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_general_ci;

USE practica_web;

CREATE TABLE IF NOT EXISTS curso (
  id_curso     INT AUTO_INCREMENT PRIMARY KEY,
  nombre       VARCHAR(100) NOT NULL,
  descripcion  VARCHAR(255) NOT NULL,
  creditos     INT NOT NULL,
  estado       TINYINT(1) NOT NULL DEFAULT 1,
  imagen       VARCHAR(255) NULL
);

CREATE TABLE IF NOT EXISTS estudiante (
  id_estudiante INT AUTO_INCREMENT PRIMARY KEY,
  nombre        VARCHAR(100) NOT NULL,
  correo        VARCHAR(120) NOT NULL,
  edad          INT NOT NULL,
  id_curso      INT NOT NULL,
  CONSTRAINT fk_estudiante_curso
    FOREIGN KEY (id_curso) REFERENCES curso(id_curso)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

CREATE USER IF NOT EXISTS 'usuario_web'@'localhost' IDENTIFIED BY 'la_Clave';
GRANT ALL PRIVILEGES ON practica_web.* TO 'usuario_web'@'localhost';
FLUSH PRIVILEGES;