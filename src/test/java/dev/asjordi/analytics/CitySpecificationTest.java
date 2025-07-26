package dev.asjordi.analytics;

import dev.asjordi.model.PostalCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CitySpecificationTest {

    private CitySpecification guadalajaraSpec;
    private PostalCode guadalajaraPostalCode;
    private PostalCode monterreyPostalCode;

    @BeforeEach
    void setUp() {
        // Crear especificación para filtrar por la ciudad de Guadalajara
        guadalajaraSpec = new CitySpecification("Guadalajara");

        // Crear código postal de Guadalajara
        guadalajaraPostalCode = new PostalCode();
        guadalajaraPostalCode.setId(1);
        guadalajaraPostalCode.setCpAsentamiento("44100");
        guadalajaraPostalCode.setNombreAsentamiento("Centro");
        guadalajaraPostalCode.setNombreEntidad("Jalisco");
        guadalajaraPostalCode.setNombreMunicipio("Guadalajara");
        guadalajaraPostalCode.setNombreCiudad("Guadalajara");

        // Crear código postal de Monterrey
        monterreyPostalCode = new PostalCode();
        monterreyPostalCode.setId(2);
        monterreyPostalCode.setCpAsentamiento("64000");
        monterreyPostalCode.setNombreAsentamiento("Centro");
        monterreyPostalCode.setNombreEntidad("Nuevo León");
        monterreyPostalCode.setNombreMunicipio("Monterrey");
        monterreyPostalCode.setNombreCiudad("Monterrey");
    }

    @Test
    void shouldMatchPostalCodesFromSpecifiedCity() {
        // Verificar que la especificación coincide con el código postal de Guadalajara
        assertTrue(guadalajaraSpec.isSatisfiedBy(guadalajaraPostalCode),
                "La especificación debería coincidir con el código postal de Guadalajara");
    }

    @Test
    void shouldNotMatchPostalCodesFromOtherCities() {
        // Verificar que la especificación no coincide con el código postal de Monterrey
        assertFalse(guadalajaraSpec.isSatisfiedBy(monterreyPostalCode),
                "La especificación no debería coincidir con el código postal de Monterrey");
    }

    @Test
    void shouldBeInsensitiveToCase() {
        // Crear especificación con nombre en minúsculas
        CitySpecification lowerCaseSpec = new CitySpecification("guadalajara");

        // Verificar que la especificación coincide independientemente de mayúsculas/minúsculas
        assertTrue(lowerCaseSpec.isSatisfiedBy(guadalajaraPostalCode),
                "La especificación debería ser insensible a mayúsculas/minúsculas");

        // Crear código postal con el nombre de ciudad en mayúsculas
        PostalCode upperCaseCity = new PostalCode();
        upperCaseCity.setNombreCiudad("GUADALAJARA");

        // Verificar que la especificación original coincide con el nombre en mayúsculas
        assertTrue(guadalajaraSpec.isSatisfiedBy(upperCaseCity),
                "La especificación debería coincidir con el nombre en mayúsculas");
    }

    @Test
    void shouldCompareFullCityName() {
        // Crear especificación para una ciudad parcial
        CitySpecification partialSpec = new CitySpecification("Guadala");

        // Verificar que la especificación parcial no coincide
        assertFalse(partialSpec.isSatisfiedBy(guadalajaraPostalCode),
                "La especificación parcial no debería coincidir con el nombre completo");
    }

    @Test
    void shouldWorkWithEmptyCity() {
        // Crear especificación con cadena vacía
        CitySpecification emptySpec = new CitySpecification("");

        // Crear código postal con ciudad vacía
        PostalCode emptyCityCP = new PostalCode();
        emptyCityCP.setNombreCiudad("");

        // Verificar que la especificación vacía coincide con la ciudad vacía
        assertTrue(emptySpec.isSatisfiedBy(emptyCityCP),
                "La especificación con cadena vacía debería coincidir con ciudad vacía");

        // Verificar que la especificación vacía no coincide con ciudades no vacías
        assertFalse(emptySpec.isSatisfiedBy(guadalajaraPostalCode),
                "La especificación vacía no debería coincidir con ciudades no vacías");
    }

    @Test
    void shouldHandleAccentsAndSpecialCharacters() {
        // Crear código postal con ciudad que tiene acentos
        PostalCode accentedCityCP = new PostalCode();
        accentedCityCP.setNombreCiudad("León");

        // Crear especificación que incluye acentos
        CitySpecification accentedSpec = new CitySpecification("León");

        // Verificar que la especificación coincide con la ciudad acentuada
        assertTrue(accentedSpec.isSatisfiedBy(accentedCityCP),
                "La especificación debería manejar correctamente los acentos");

        // Verificar con ciudad sin acento y especificación con acento
        PostalCode nonAccentedCityCP = new PostalCode();
        nonAccentedCityCP.setNombreCiudad("Leon");

        assertFalse(accentedSpec.isSatisfiedBy(nonAccentedCityCP),
                "La especificación con acento no debería coincidir con ciudad sin acento");
    }

    @Test
    void shouldHandleCitiesWithMultipleWords() {
        // Crear código postal con ciudad de múltiples palabras
        PostalCode multiWordCityCP = new PostalCode();
        multiWordCityCP.setNombreCiudad("San Luis Potosí");

        // Crear especificación para ciudad con múltiples palabras
        CitySpecification multiWordSpec = new CitySpecification("San Luis Potosí");

        // Verificar que la especificación coincide con la ciudad de múltiples palabras
        assertTrue(multiWordSpec.isSatisfiedBy(multiWordCityCP),
                "La especificación debería manejar ciudades con múltiples palabras");

        // Verificar con una especificación parcial de la ciudad
        CitySpecification partialMultiWordSpec = new CitySpecification("San Luis");

        assertFalse(partialMultiWordSpec.isSatisfiedBy(multiWordCityCP),
                "La especificación parcial no debería coincidir con la ciudad completa");
    }

    @Test
    void shouldWorkWithNull() {
        // Crear código postal con ciudad null
        PostalCode nullCityCP = new PostalCode();
        nullCityCP.setNombreCiudad(null);

        // Verificar que la especificación no coincide con ciudad null y lanza excepción
        assertThrows(NullPointerException.class, () -> guadalajaraSpec.isSatisfiedBy(nullCityCP),
                "Se espera NullPointerException al comparar con una ciudad null");
    }
}
