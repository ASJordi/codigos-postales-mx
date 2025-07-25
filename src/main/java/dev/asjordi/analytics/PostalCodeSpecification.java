package dev.asjordi.analytics;

import dev.asjordi.model.PostalCode;

public class PostalCodeSpecification implements Specification<PostalCode> {

    private final String postalCode;

    public PostalCodeSpecification(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean isSatisfiedBy(PostalCode item) {
        return item.getCpAsentamiento().equalsIgnoreCase(postalCode);
    }
}
