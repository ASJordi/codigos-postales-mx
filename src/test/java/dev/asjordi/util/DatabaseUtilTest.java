package dev.asjordi.util;

import dev.asjordi.model.PostalCode;
import dev.asjordi.persistence.db.PostalCodeRepository;
import dev.asjordi.persistence.file.PostalCodeDataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class DatabaseUtilTest {

    // Mock para el repositorio de códigos postales
    @Mock
    private PostalCodeRepository mockRepository;

    // Mock para el cargador de datos
    @Mock
    private PostalCodeDataLoader mockDataLoader;

    // La clase a probar
    private DatabaseUtil databaseUtil;

    // Lista de prueba con códigos postales
    private List<PostalCode> testPostalCodes;

    @BeforeEach
    void setUp() {
        // Inicializar mocks
        MockitoAnnotations.openMocks(this);

        // Crear datos de prueba
        testPostalCodes = createTestPostalCodes();

        // Configurar comportamiento del cargador de datos mock
        when(mockDataLoader.getAll()).thenReturn(testPostalCodes);

        // Crear una subclase de DatabaseUtil para inyectar los mocks
        databaseUtil = new DatabaseUtilForTest(mockRepository, mockDataLoader);
    }

    @Test
    void saveToDatabaseAllData_ShouldAddAllDataToDatabaseInBatch() {
        // Ejecutar el método a probar
        databaseUtil.saveToDatabaseAllData();

        // Verificar que se llamó al método addBatch con la lista correcta de códigos postales
        verify(mockRepository, times(1)).addBatch(testPostalCodes);
    }

    @Test
    void constructor_ShouldLoadDataWhenInitialized() {
        // Este test verifica que al crear una instancia de DatabaseUtil, se cargan los datos

        // Crear una nueva instancia para este test específico
        new DatabaseUtilForTest(mockRepository, mockDataLoader);

        // Verificar que el método getAll del DataLoader fue llamado durante la inicialización
        verify(mockDataLoader, times(2)).getAll(); // una vez en setUp y otra en este test
    }

    // Clase interna para pruebas que nos permite inyectar los mocks
    private class DatabaseUtilForTest extends DatabaseUtil {
        public DatabaseUtilForTest(PostalCodeRepository repo, PostalCodeDataLoader dataLoader) {
            super();
            // Inyectar los mocks usando reflection
            try {
                java.lang.reflect.Field repoField = DatabaseUtil.class.getDeclaredField("repo");
                repoField.setAccessible(true);
                repoField.set(this, repo);

                java.lang.reflect.Field dataField = DatabaseUtil.class.getDeclaredField("postalCodeList");
                dataField.setAccessible(true);
                dataField.set(this, dataLoader.getAll());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Error setting up test", e);
            }
        }
    }

    // Método helper para crear datos de prueba
    private List<PostalCode> createTestPostalCodes() {
        List<PostalCode> testData = new ArrayList<>();

        // Crear algunos datos de prueba
        PostalCode cp1 = new PostalCode();
        cp1.setId(1);
        cp1.setCpAsentamiento("44100");
        cp1.setNombreAsentamiento("Centro");
        cp1.setNombreEntidad("Jalisco");
        cp1.setNombreMunicipio("Guadalajara");

        PostalCode cp2 = new PostalCode();
        cp2.setId(2);
        cp2.setCpAsentamiento("06000");
        cp2.setNombreAsentamiento("Centro");
        cp2.setNombreEntidad("Ciudad de México");
        cp2.setNombreMunicipio("Cuauhtémoc");

        testData.add(cp1);
        testData.add(cp2);

        return testData;
    }
}
