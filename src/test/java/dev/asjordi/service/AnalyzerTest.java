package dev.asjordi.service;

import dev.asjordi.analytics.*;
import dev.asjordi.model.PostalCode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzerTest {

    private static Analyzer analyzer;

    @BeforeAll
    static void setUp() {
        analyzer = Analyzer.getInstance();
        assertNotNull(analyzer);
        assertNotNull(analyzer.getAllData());
        assertFalse(analyzer.getAllData().isEmpty());
    }

    @Test
    void testGetInstance() {
        Analyzer instance1 = Analyzer.getInstance();
        Analyzer instance2 = Analyzer.getInstance();

        assertNotNull(instance1);
        assertNotNull(instance2);
        assertSame(instance1, instance2);
    }

    @Test
    void testFind() {
        Specification<PostalCode> stateSpec = new StateSpecification("Jalisco");

        List<PostalCode> results = analyzer.find(stateSpec);

        assertNotNull(results);
        assertFalse(results.isEmpty());

        for (PostalCode pc : results) {
            assertTrue(pc.getNombreEntidad().equalsIgnoreCase("Jalisco"));
        }
    }

    @Test
    void testFindFirst() {
        Specification<PostalCode> postalCodeSpec = new PostalCodeSpecification("44100");

        Optional<PostalCode> result = analyzer.findFirst(postalCodeSpec);

        assertTrue(result.isPresent());
        assertEquals("44100", result.get().getCpAsentamiento());
    }

    @Test
    void testGetDistinctWithSpecification() {
        Specification<PostalCode> stateSpec = new StateSpecification("Jalisco");

        List<String> municipalities = analyzer.getDistinct(stateSpec, PostalCode::getNombreMunicipio);

        assertNotNull(municipalities);
        assertFalse(municipalities.isEmpty());

        assertTrue(isSorted(municipalities));
    }

    @Test
    void testGetDistinctWithMapper() {
        List<String> states = analyzer.getDistinct(PostalCode::getNombreEntidad);

        assertNotNull(states);
        assertFalse(states.isEmpty());

        assertTrue(isSorted(states));

        assertTrue(states.contains("Jalisco"));
        assertTrue(states.contains("Aguascalientes"));
    }

    @Test
    void testGetTotalCount() {
        long count = analyzer.getTotalCount();

        assertTrue(count > 0);
        assertEquals(analyzer.getAllData().size(), count);
    }

    @Test
    void testGetCount() {
        Specification<PostalCode> stateSpec = new StateSpecification("Jalisco");

        long count = analyzer.getCount(stateSpec);

        assertTrue(count > 0);
        assertEquals(analyzer.find(stateSpec).size(), count);
    }

    @Test
    void testGetCountDistinctWithSpecification() {
        Specification<PostalCode> stateSpec = new StateSpecification("Jalisco");
        Function<PostalCode, String> mapper = PostalCode::getNombreMunicipio;

        long count = analyzer.getCountDistinct(stateSpec, mapper);

        assertTrue(count > 0);
        assertEquals(analyzer.getDistinct(stateSpec, mapper).size(), count);
    }

    @Test
    void testGetCountDistinct() {
        Function<PostalCode, String> mapper = PostalCode::getNombreEntidad;

        long count = analyzer.getCountDistinct(mapper);

        assertTrue(count > 0);
        assertEquals(analyzer.getDistinct(mapper).size(), count);
    }

    @Test
    void testGetAllData() {
        List<PostalCode> allData = analyzer.getAllData();

        assertNotNull(allData);
        assertFalse(allData.isEmpty());
    }

    @Test
    void testCombiningSpecifications() {
        Specification<PostalCode> stateSpec = new StateSpecification("Jalisco");
        Specification<PostalCode> citySpec = new CitySpecification("Guadalajara");
        Specification<PostalCode> combined = new AndSpecification<>(stateSpec, citySpec);

        List<PostalCode> results = analyzer.find(combined);

        assertNotNull(results);
        assertFalse(results.isEmpty());

        for (PostalCode pc : results) {
            assertTrue(pc.getNombreEntidad().equalsIgnoreCase("Jalisco"));
            assertTrue(pc.getNombreCiudad().equalsIgnoreCase("Guadalajara"));
        }
    }

    private boolean isSorted(List<String> list) {
        if (list.size() <= 1) return true;

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) return false;
        }

        return true;
    }
}
