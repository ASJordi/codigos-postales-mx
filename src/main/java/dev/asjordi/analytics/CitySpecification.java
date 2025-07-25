package dev.asjordi.analytics;

import dev.asjordi.model.PostalCode;

public class CitySpecification implements Specification<PostalCode> {

    private final String city;

    public CitySpecification(String city) {
        this.city = city;
    }

    @Override
    public boolean isSatisfiedBy(PostalCode item) {
        return item.getNombreCiudad().equalsIgnoreCase(city);
    }
}
