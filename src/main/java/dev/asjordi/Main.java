package dev.asjordi;

import dev.asjordi.analytics.AndSpecification;
import dev.asjordi.analytics.CitySpecification;
import dev.asjordi.analytics.PostalCodeSpecification;
import dev.asjordi.analytics.StateSpecification;
import dev.asjordi.model.PostalCode;
import dev.asjordi.service.Analyzer;

public class Main {

    public static void main( String[] args ) {

        var analyzer = Analyzer.getInstance();

        var postalCode = analyzer.findFirst(new PostalCodeSpecification("50000"));
        var postalCodeByState = analyzer.find(new StateSpecification("Aguascalientes"));
        var postalCodeByCity = analyzer.find(new CitySpecification("Toluca de Lerdo"));

        var postalCodeByStateAndCity = analyzer.find(
                new AndSpecification<>(new StateSpecification("México"), new CitySpecification("Toluca de Lerdo"))
        );

        var totalStates = analyzer.getCountDistinct(PostalCode::getNombreEntidad);
        var totalCities = analyzer.getCountDistinct(PostalCode::getNombreCiudad);
        var totalPostalCodes = analyzer.getCountDistinct(PostalCode::getCpAsentamiento);

        System.out.println("Postal Code 51906: " + postalCode.orElse(null));
        System.out.println("Postal Codes in Aguascalientes: " + postalCodeByState.size());
        System.out.println("Postal Codes in Toluca de Lerdo: " + postalCodeByCity.size());
        System.out.println("Postal Codes in Mexico, Toluca de Lerdo: " + postalCodeByStateAndCity.size());
        System.out.println("Total distinct states: " + totalStates);
        System.out.println("Total distinct cities: " + totalCities);
        System.out.println("Total distinct postal codes: " + totalPostalCodes);

    }

}
