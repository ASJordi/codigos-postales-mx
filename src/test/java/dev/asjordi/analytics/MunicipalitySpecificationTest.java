package dev.asjordi.analytics;

import dev.asjordi.model.PostalCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MunicipalitySpecificationTest {

    private MunicipalitySpecification guadalajaraSpec;
    private PostalCode guadalajaraPostalCode;
    private PostalCode cuauhtemocPostalCode;

    @BeforeEach
    void setUp() {
        // Crear especificación para filtrar por el municipio de Guadalajara
        guadalajaraSpec = new MunicipalitySpecification("Guadalajara");

        // Crear código postal de Guadalajara
        guadalajaraPostalCode = new PostalCode();
        guadalajaraPostalCode.setId(1);
        guadalajaraPostalCode.setCpAsentamiento("44100");
        guadalajaraPostalCode.setNombreAsentamiento("Centro");
        guadalajaraPostalCode.setNombreEntidad("Jalisco");
        guadalajaraPostalCode.setNombreMunicipio("Guadalajara");

        // Crear código postal de Cuauhtémoc
        cuauhtemocPostalCode = new PostalCode();
        cuauhtemocPostalCode.setId(2);
        cuauhtemocPostalCode.setCpAsentamiento("06000");
        cuauhtemocPostalCode.setNombreAsentamiento("Centro");
        cuauhtemocPostalCode.setNombreEntidad("Ciudad de México");
        cuauhtemocPostalCode.setNombreMunicipio("Cuauhtémoc");
    }

    @Test
    void shouldMatchPostalCodesFromSpecifiedMunicipality() {
        // Verificar que la especificación coincide con el código postal de Guadalajara
        assertTrue(guadalajaraSpec.isSatisfiedBy(guadalajaraPostalCode),
                "La especificación debería coincidir con el código postal de Guadalajara");
    }

    @Test
    void shouldNotMatchPostalCodesFromOtherMunicipalities() {
        // Verificar que la especificación no coincide con el código postal de Cuauhtémoc
        assertFalse(guadalajaraSpec.isSatisfiedBy(cuauhtemocPostalCode),
                "La especificación no debería coincidir con el código postal de Cuauhtémoc");
    }

    @Test
    void shouldBeInsensitiveToCase() {
        // Crear especificación con nombre en minúsculas
        MunicipalitySpecification lowerCaseSpec = new MunicipalitySpecification("guadalajara");

        // Verificar que la especificación coincide independientemente de mayúsculas/minúsculas
        assertTrue(lowerCaseSpec.isSatisfiedBy(guadalajaraPostalCode),
                "La especificación debería ser insensible a mayúsculas/minúsculas");

        // Crear código postal con el nombre de municipio en mayúsculas
        PostalCode upperCaseMunicipality = new PostalCode();
        upperCaseMunicipality.setNombreMunicipio("GUADALAJARA");

        // Verificar que la especificación original coincide con el nombre en mayúsculas
        assertTrue(guadalajaraSpec.isSatisfiedBy(upperCaseMunicipality),
                "La especificación debería coincidir con el nombre en mayúsculas");
    }

    @Test
    void shouldCompareFullMunicipalityName() {
        // Crear especificación para un municipio parcial
        MunicipalitySpecification partialSpec = new MunicipalitySpecification("Guadala");

        // Verificar que la especificación parcial no coincide
        assertFalse(partialSpec.isSatisfiedBy(guadalajaraPostalCode),
                "La especificación parcial no debería coincidir con el nombre completo");
    }

    @Test
    void shouldWorkWithEmptyMunicipality() {
        // Crear especificación con cadena vacía
        MunicipalitySpecification emptySpec = new MunicipalitySpecification("");

        // Verificar que la especificación vacía no coincide con ningún municipio
        assertFalse(emptySpec.isSatisfiedBy(guadalajaraPostalCode),
                "La especificación con cadena vacía no debería coincidir");
        assertFalse(emptySpec.isSatisfiedBy(cuauhtemocPostalCode),
                "La especificación con cadena vacía no debería coincidir");
    }

    @Test
    void shouldHandleSpecialCharacters() {
        // Crear especificación con acentos y caracteres especiales
        MunicipalitySpecification specialCharsSpec = new MunicipalitySpecification("Cuauhtémoc");

        // Verificar que la especificación maneja correctamente los caracteres especiales
        assertTrue(specialCharsSpec.isSatisfiedBy(cuauhtemocPostalCode),
                "La especificación debería manejar correctamente los acentos y caracteres especiales");
    }

    @Test
    void shouldWorkWithNull() {
        // Crear código postal con municipio null
        PostalCode nullMunicipalityPostalCode = new PostalCode();
        nullMunicipalityPostalCode.setNombreMunicipio(null);

        // Verificar que la especificación no coincide con municipio null y no lanza excepciones
        assertThrows(NullPointerException.class, () -> guadalajaraSpec.isSatisfiedBy(nullMunicipalityPostalCode),
                "Se espera NullPointerException al comparar con un municipio null");
    }
}
