package dev.asjordi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostalCode {
    private String cpAsentamiento;
    private String nombreAsentamiento;
    private String tipoAsentamiento;
    private String nombreMunicipio;
    private String nombreEntidad;
    private String nombreCiudad;
    private String cpAdministracionPostalAsentamiento;
    private String claveEntidad;
    private String cpAdministracionPostalAsentamiento2;
    private String claveTipoAsentamiento;
    private String claveMunicipio;
    private String idUnicoAsentamiento;
    private String zonaUbicacionAsentamiento;
    private String claveCiudad;
}
