package dev.asjordi.analytics;

import dev.asjordi.database.repository.PostalCodeRepository;
import dev.asjordi.model.PostalCode;
import dev.asjordi.processor.PostalCodeDataLoader;
import java.util.List;

public class DatabaseUtil {

    private final PostalCodeRepository repo = new PostalCodeRepository();
    private final List<PostalCode> postalCodeList;

    public DatabaseUtil() {
        postalCodeList = new PostalCodeDataLoader().getPostalCodeList();
    }

    public void saveToDatabaseAllData() {
        repo.addBatch(postalCodeList);
    }

}
