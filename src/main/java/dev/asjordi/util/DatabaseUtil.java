package dev.asjordi.util;

import dev.asjordi.model.PostalCode;
import dev.asjordi.persistence.db.PostalCodeRepository;
import dev.asjordi.persistence.file.PostalCodeDataLoader;

import java.util.List;

public class DatabaseUtil {

    private final PostalCodeRepository repo = new PostalCodeRepository();
    private final List<PostalCode> postalCodeList;

    public DatabaseUtil() {
        postalCodeList = new PostalCodeDataLoader().getAll();
    }

    public void saveToDatabaseAllData() {
        repo.addBatch(postalCodeList);
    }

}
