CREATE DATABASE IF NOT EXISTS postal_codes_mx;
USE postal_codes_mx;

CREATE TABLE codes(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cpAsentamiento VARCHAR(255),
    nombreAsentamiento VARCHAR(255),
    tipoAsentamiento VARCHAR(255),
    nombreMunicipio VARCHAR(255),
    nombreEntidad VARCHAR(255),
    nombreCiudad VARCHAR(255),
    cpAdministracionPostalAsentamiento VARCHAR(255),
    claveEntidad VARCHAR(255),
    cpAdministracionPostalAsentamiento2 VARCHAR(255),
    claveTipoAsentamiento VARCHAR(255),
    claveMunicipio VARCHAR(255),
    idUnicoAsentamiento VARCHAR(255),
    zonaUbicacionAsentamiento VARCHAR(255),
    claveCiudad VARCHAR(255)
);

