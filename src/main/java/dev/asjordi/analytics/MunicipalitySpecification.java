package dev.asjordi.analytics;

import dev.asjordi.model.PostalCode;

public class MunicipalitySpecification implements Specification<PostalCode> {

    private final String municipality;

    public MunicipalitySpecification(String municipality) {
        this.municipality = municipality;
    }

    @Override
    public boolean isSatisfiedBy(PostalCode item) {
        return item.getNombreMunicipio().equalsIgnoreCase(municipality);
    }
}
