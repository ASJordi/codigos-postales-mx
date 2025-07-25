package dev.asjordi.analytics;

import dev.asjordi.model.PostalCode;

public class StateSpecification implements Specification<PostalCode> {

    private final String state;

    public StateSpecification(String state) {
        this.state = state;
    }

    @Override
    public boolean isSatisfiedBy(PostalCode item) {
        return item.getNombreEntidad().equalsIgnoreCase(state);
    }

}
