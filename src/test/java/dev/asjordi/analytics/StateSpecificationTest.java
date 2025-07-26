package dev.asjordi.analytics;

import dev.asjordi.model.PostalCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateSpecificationTest {

    private StateSpecification stateSpecification;
    private PostalCode jaliscoPostalCode;
    private PostalCode cdmxPostalCode;

    @BeforeEach
    void setUp() {
        stateSpecification = new StateSpecification("Jalisco");

        jaliscoPostalCode = new PostalCode();
        jaliscoPostalCode.setId(1);
        jaliscoPostalCode.setCpAsentamiento("44100");
        jaliscoPostalCode.setNombreAsentamiento("Centro");
        jaliscoPostalCode.setNombreEntidad("Jalisco");
        jaliscoPostalCode.setNombreMunicipio("Guadalajara");

        // Crear código postal de Ciudad de México
        cdmxPostalCode = new PostalCode();
        cdmxPostalCode.setId(2);
        cdmxPostalCode.setCpAsentamiento("06000");
        cdmxPostalCode.setNombreAsentamiento("Centro");
        cdmxPostalCode.setNombreEntidad("Ciudad de México");
        cdmxPostalCode.setNombreMunicipio("Cuauhtémoc");
    }

    @Test
    void shouldMatchPostalCodesFromSpecifiedState() {
        assertTrue(stateSpecification.isSatisfiedBy(jaliscoPostalCode),
                "La especificación debería coincidir con el código postal de Jalisco");
    }

    @Test
    void shouldNotMatchPostalCodesFromOtherStates() {
        assertFalse(stateSpecification.isSatisfiedBy(cdmxPostalCode),
                "La especificación no debería coincidir con el código postal de Ciudad de México");
    }

    @Test
    void shouldBeInsensitiveToCase() {
        StateSpecification lowerCaseSpec = new StateSpecification("jalisco");

        assertTrue(lowerCaseSpec.isSatisfiedBy(jaliscoPostalCode),
                "La especificación debería ser insensible a mayúsculas/minúsculas");

        PostalCode upperCaseState = new PostalCode();
        upperCaseState.setNombreEntidad("JALISCO");

        assertTrue(stateSpecification.isSatisfiedBy(upperCaseState),
                "La especificación debería coincidir con el nombre en mayúsculas");
    }

    @Test
    void shouldCompareFullStateName() {
        StateSpecification partialSpec = new StateSpecification("Jalis");

        assertFalse(partialSpec.isSatisfiedBy(jaliscoPostalCode),
                "La especificación parcial no debería coincidir con el nombre completo");
    }

    @Test
    void shouldWorkWithEmptyState() {
        StateSpecification emptySpec = new StateSpecification("");

        assertFalse(emptySpec.isSatisfiedBy(jaliscoPostalCode),
                "La especificación con cadena vacía no debería coincidir");
        assertFalse(emptySpec.isSatisfiedBy(cdmxPostalCode),
                "La especificación con cadena vacía no debería coincidir");
    }

    @Test
    void shouldWorkWithNull() {
        PostalCode nullStatePostalCode = new PostalCode();
        nullStatePostalCode.setNombreEntidad(null);

        assertThrows(NullPointerException.class, () -> stateSpecification.isSatisfiedBy(nullStatePostalCode),
                "Se espera NullPointerException al comparar con un estado null");
    }
}
