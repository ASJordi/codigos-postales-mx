package dev.asjordi.analytics;

public interface Specification<T> {

    boolean isSatisfiedBy(T t);

}
