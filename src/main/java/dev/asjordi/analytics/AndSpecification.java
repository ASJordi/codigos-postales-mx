package dev.asjordi.analytics;

public class AndSpecification<T> implements Specification<T> {

    private final Specification<T> first;
    private final Specification<T> second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfiedBy(T item) {
        return first.isSatisfiedBy(item) && second.isSatisfiedBy(item);
    }
}
