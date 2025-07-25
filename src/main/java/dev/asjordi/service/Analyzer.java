package dev.asjordi.service;

import dev.asjordi.analytics.Specification;
import dev.asjordi.model.PostalCode;
import dev.asjordi.persistence.file.PostalCodeDataLoader;
import dev.asjordi.persistence.repository.IDataReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Analyzer {

    private List<PostalCode> postalCodeList;
    private static final Analyzer INSTANCE = new Analyzer();
    private IDataReader<PostalCode> reader;

    private Analyzer() {
        this.reader = new PostalCodeDataLoader();
        init();
    }

    private void init() {
        this.postalCodeList = reader.getAll();
    }

    public static Analyzer getInstance() {
        return INSTANCE;
    }

    /**
     * Finds a list of items matching the specification.
     * @param spec The specification to filter by.
     * @return A list of matching items.
     */
    public List<PostalCode> find(Specification<PostalCode> spec) {
        return postalCodeList.stream()
                .filter(spec::isSatisfiedBy)
                .collect(Collectors.toList());
    }

    /**
     * Finds the first item matching the specification.
     * @param spec The specification to filter by.
     * @return An Optional containing the first matching item, or empty if not found.
     */
    public Optional<PostalCode> findFirst(Specification<PostalCode> spec) {
        return postalCodeList.stream()
                .filter(spec::isSatisfiedBy)
                .findFirst();
    }

    /**
     * Gets a list of distinct string values from the data set based on a specification and a mapper.
     * @param spec The specification to filter by.
     * @param mapper The function to map the PostalCode object to a String.
     * @return A list of distinct string values.
     */
    public List<String> getDistinct(Specification<PostalCode> spec, Function<PostalCode, String> mapper) {
        return postalCodeList.stream()
                .filter(spec::isSatisfiedBy)
                .map(mapper)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Gets a list of all distinct string values from the data set based on a mapper.
     * @param mapper The function to map the PostalCode object to a String.
     * @return A list of all distinct string values.
     */
    public List<String> getDistinct(Function<PostalCode, String> mapper) {
        return postalCodeList.stream()
                .map(mapper)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Gets the total count of items in the data set.
     * @return The total count.
     */
    public long getTotalCount() {
        return postalCodeList.size();
    }

    /**
     * Gets the count of items matching a specification.
     * @param spec The specification to filter by.
     * @return The count of matching items.
     */
    public long getCount(Specification<PostalCode> spec) {
        return postalCodeList.stream()
                .filter(spec::isSatisfiedBy)
                .count();
    }

    /**
     * Gets the count of distinct values based on a specification and a mapper.
     * @param spec The specification to filter by.
     * @param mapper The function to map the PostalCode object to a String.
     * @return The count of distinct values.
     */
    public long getCountDistinct(Specification<PostalCode> spec, Function<PostalCode, String> mapper) {
        return postalCodeList.stream()
                .filter(spec::isSatisfiedBy)
                .map(mapper)
                .distinct()
                .count();
    }

    /**
     * Gets the count of all distinct values based on a mapper.
     * @param mapper The function to map the PostalCode object to a String.
     * @return The count of all distinct values.
     */
    public long getCountDistinct(Function<PostalCode, String> mapper) {
        return postalCodeList.stream()
                .map(mapper)
                .distinct()
                .count();
    }

    /**
     * Returns the complete, unfiltered list of postal codes.
     * @return The raw list of postal codes.
     */
    public List<PostalCode> getAllData() {
        return postalCodeList;
    }

}
