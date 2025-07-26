package dev.asjordi.analytics;

import dev.asjordi.model.PostalCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostalCodeSpecificationTest {

    private PostalCodeSpecification postalCodeSpec;
    private PostalCode postalCode44100;
    private PostalCode postalCode06000;

    @BeforeEach
    void setUp() {
        // Crear especificación para filtrar por el código postal 44100
        postalCodeSpec = new PostalCodeSpecification("44100");

        // Crear un código postal con CP 44100
        postalCode44100 = new PostalCode();
        postalCode44100.setId(1);
        postalCode44100.setCpAsentamiento("44100");
        postalCode44100.setNombreAsentamiento("Centro");
        postalCode44100.setNombreEntidad("Jalisco");
        postalCode44100.setNombreMunicipio("Guadalajara");

        // Crear un código postal con CP 06000
        postalCode06000 = new PostalCode();
        postalCode06000.setId(2);
        postalCode06000.setCpAsentamiento("06000");
        postalCode06000.setNombreAsentamiento("Centro");
        postalCode06000.setNombreEntidad("Ciudad de México");
        postalCode06000.setNombreMunicipio("Cuauhtémoc");
    }

    @Test
    void shouldMatchPostalCodesWithSpecifiedCode() {
        // Verificar que la especificación coincide con el código postal correcto
        assertTrue(postalCodeSpec.isSatisfiedBy(postalCode44100),
                "La especificación debería coincidir con el CP 44100");
    }

    @Test
    void shouldNotMatchPostalCodesWithDifferentCode() {
        // Verificar que la especificación no coincide con otros códigos postales
        assertFalse(postalCodeSpec.isSatisfiedBy(postalCode06000),
                "La especificación no debería coincidir con el CP 06000");
    }

    @Test
    void shouldBeInsensitiveToCase() {
        // Los códigos postales suelen ser numéricos, pero probamos con una variante alfanumérica

        // Crear un código postal con letras en mayúsculas
        PostalCode alphanumericPostalCode = new PostalCode();
        alphanumericPostalCode.setCpAsentamiento("ABC123");

        // Especificación en minúsculas
        PostalCodeSpecification lowerCaseSpec = new PostalCodeSpecification("abc123");

        // Verificar que la comparación es insensible a mayúsculas/minúsculas
        assertTrue(lowerCaseSpec.isSatisfiedBy(alphanumericPostalCode),
                "La especificación debería ser insensible a mayúsculas/minúsculas");
    }

    @Test
    void shouldRequireExactMatch() {
        // Crear especificación para un código postal parcial
        PostalCodeSpecification partialSpec = new PostalCodeSpecification("441");

        // Verificar que la especificación parcial no coincide con el código completo
        assertFalse(partialSpec.isSatisfiedBy(postalCode44100),
                "La especificación parcial no debería coincidir con el código completo");
    }

    @Test
    void shouldWorkWithLeadingZeros() {
        // Crear un código postal con ceros a la izquierda
        PostalCode leadingZerosCP = new PostalCode();
        leadingZerosCP.setCpAsentamiento("06000");

        // Especificación sin ceros a la izquierda
        PostalCodeSpecification noLeadingZerosSpec = new PostalCodeSpecification("6000");

        // Verificar que no coincide, ya que los ceros iniciales son significativos
        assertFalse(noLeadingZerosSpec.isSatisfiedBy(leadingZerosCP),
                "La especificación sin ceros iniciales no debería coincidir");

        // Especificación correcta con ceros a la izquierda
        PostalCodeSpecification correctSpec = new PostalCodeSpecification("06000");

        // Verificar que coincide cuando la especificación incluye los ceros iniciales
        assertTrue(correctSpec.isSatisfiedBy(leadingZerosCP),
                "La especificación con ceros iniciales debería coincidir");
    }

    @Test
    void shouldHandleWhitespace() {
        // Crear un código postal con espacios
        PostalCode cpWithSpace = new PostalCode();
        cpWithSpace.setCpAsentamiento(" 44100 ");

        // Verificar que la especificación original no coincide debido a los espacios
        assertFalse(postalCodeSpec.isSatisfiedBy(cpWithSpace),
                "La especificación no debería coincidir con código postal que contiene espacios");

        // Crear especificación que incluye los espacios
        PostalCodeSpecification specWithSpaces = new PostalCodeSpecification(" 44100 ");

        // Verificar que la especificación con espacios coincide exactamente
        assertTrue(specWithSpaces.isSatisfiedBy(cpWithSpace),
                "La especificación con espacios debería coincidir");
    }

    @Test
    void shouldWorkWithEmptyPostalCode() {
        // Crear especificación con cadena vacía
        PostalCodeSpecification emptySpec = new PostalCodeSpecification("");

        // Crear código postal vacío
        PostalCode emptyCP = new PostalCode();
        emptyCP.setCpAsentamiento("");

        // Verificar que la especificación vacía coincide con el código postal vacío
        assertTrue(emptySpec.isSatisfiedBy(emptyCP),
                "La especificación vacía debería coincidir con un código postal vacío");

        // Verificar que la especificación vacía no coincide con códigos postales no vacíos
        assertFalse(emptySpec.isSatisfiedBy(postalCode44100),
                "La especificación vacía no debería coincidir con códigos postales no vacíos");
    }

    @Test
    void shouldWorkWithNull() {
        // Crear código postal con CP null
        PostalCode nullCP = new PostalCode();
        nullCP.setCpAsentamiento(null);

        // Verificar que la especificación no coincide con CP null y lanza excepción
        assertThrows(NullPointerException.class, () -> postalCodeSpec.isSatisfiedBy(nullCP),
                "Se espera NullPointerException al comparar con un CP null");
    }
}
