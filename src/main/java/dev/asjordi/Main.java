package dev.asjordi;

import dev.asjordi.analytics.AndSpecification;
import dev.asjordi.analytics.CitySpecification;
import dev.asjordi.analytics.PostalCodeSpecification;
import dev.asjordi.analytics.StateSpecification;
import dev.asjordi.model.PostalCode;
import dev.asjordi.service.Analyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main( String[] args ) {

        logger.atInfo().log("Starting Postal Code Analyzer...");
        var analyzer = Analyzer.getInstance();

        var postalCode = analyzer.findFirst(new PostalCodeSpecification("50000"));
        logger.atDebug().log("Postal Code Found: " + postalCode);
        var postalCodeByState = analyzer.find(new StateSpecification("Aguascalientes"));
        logger.atDebug().log("Postal Codes Found by State: " + postalCodeByState);
        var postalCodeByCity = analyzer.find(new CitySpecification("Toluca de Lerdo"));
        logger.atDebug().log("Postal Codes Found by City: " + postalCodeByCity);

        var postalCodeByStateAndCity = analyzer.find(
                new AndSpecification<>(new StateSpecification("México"), new CitySpecification("Toluca de Lerdo"))
        );
        logger.atDebug().log("Postal Codes Found by State and City: " + postalCodeByStateAndCity);

        var totalStates = analyzer.getCountDistinct(PostalCode::getNombreEntidad);
        logger.atDebug().log("Total States: " + totalStates);
        var totalCities = analyzer.getCountDistinct(PostalCode::getNombreCiudad);
        logger.atDebug().log("Total Cities: " + totalCities);
        var totalPostalCodes = analyzer.getCountDistinct(PostalCode::getCpAsentamiento);
        logger.atDebug().log("Total Postal Codes: " + totalPostalCodes);

    }

}
