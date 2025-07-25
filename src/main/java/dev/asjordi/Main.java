package dev.asjordi;

import dev.asjordi.service.Analyzer;

public class Main {

    public static void main( String[] args ) {
        var analyzer = Analyzer.getInstance();

        var allPostalCodes = analyzer.getAllPostalCodes();
        var postalCode = analyzer.findByPostalCode("50000");
        var postalCodeByState = analyzer.findByState("Aguascalientes");
        var postalCodeByCity = analyzer.findByCity("Toluca de Lerdo");
        var totalStates = analyzer.getTotalStates();
        var totalCities = analyzer.getTotalCities();
        var totalPostalCodes = analyzer.getTotalPostalCodes();
    }

}
