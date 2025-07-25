package dev.asjordi.service;

import dev.asjordi.model.PostalCode;
import dev.asjordi.persistence.file.PostalCodeDataLoader;
import dev.asjordi.persistence.repository.IDataReader;
import java.util.*;
import java.util.stream.Collectors;

public class Analyzer {

    private List<PostalCode> postalCodeList;
    private static final Analyzer INSTANCE = new Analyzer();
    private IDataReader<PostalCode> reader;

    private Analyzer() {
        this.reader = new PostalCodeDataLoader();
//         this.reader = new PostalCodeRepository();
        init();
    }

    private void init() {
        this.postalCodeList = reader.getAll();
    }

    public static Analyzer getInstance() {
        return INSTANCE;
    }

    public Optional<PostalCode> findByPostalCode(String postalCode) {
        return postalCodeList.stream()
                .filter(p -> p.getCpAsentamiento().equalsIgnoreCase(postalCode))
                .findFirst();
    }

    public List<PostalCode> findByState(String state) {
        return postalCodeList.stream()
                .filter(p -> p.getNombreEntidad().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    public List<PostalCode> findByCity(String city) {
        return postalCodeList.stream()
                .filter(p -> p.getNombreCiudad().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<PostalCode> findByCity(String state, String city) {
        return postalCodeList.stream()
                .filter(p -> p.getNombreEntidad().equalsIgnoreCase(state))
                .filter(p -> p.getNombreCiudad().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<PostalCode> findByMunicipality(String municipality) {
        return postalCodeList.stream()
                .filter(p -> p.getNombreMunicipio().equalsIgnoreCase(municipality))
                .collect(Collectors.toList());
    }

    public List<PostalCode> findByMunicipality(String state, String municipality) {
        return postalCodeList.stream()
                .filter(p -> p.getNombreEntidad().equalsIgnoreCase(state))
                .filter(p -> p.getNombreMunicipio().equalsIgnoreCase(municipality))
                .collect(Collectors.toList());
    }

    public int getCountPostalCodesFromState(String state) {
        return postalCodeList.stream()
                .filter(p -> p.getNombreEntidad().equalsIgnoreCase(state))
                .map(PostalCode::getCpAsentamiento)
                .distinct()
                .collect(Collectors.toList())
                .size();
    }

    public int getCountPostalCodesFromCity(String city) {
        return postalCodeList.stream()
                .filter(p -> p.getNombreCiudad().equalsIgnoreCase(city))
                .map(PostalCode::getCpAsentamiento)
                .distinct()
                .collect(Collectors.toList())
                .size();
    }

    public int getCountPostalCodesFromMunicipality(String state, String municipality) {
        return postalCodeList.stream()
                .filter(p -> p.getNombreEntidad().equalsIgnoreCase(state))
                .filter(p -> p.getNombreMunicipio().equalsIgnoreCase(municipality))
                .map(PostalCode::getCpAsentamiento)
                .distinct()
                .collect(Collectors.toList())
                .size();
    }

    public List<String> getAllStates() {
        return postalCodeList.stream()
                .map(PostalCode::getNombreEntidad)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getAllCities() {
        return postalCodeList.stream()
                .map(PostalCode::getNombreCiudad)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getAllMunicipalities() {
        return postalCodeList.stream()
                .map(PostalCode::getNombreMunicipio)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getAllCitiesFromState(String state) {
        return postalCodeList.stream()
                .filter(p -> p.getNombreEntidad().equalsIgnoreCase(state))
                .map(PostalCode::getNombreCiudad)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getAllMunicipalityFromState(String state) {
        return postalCodeList.stream()
                .filter(p -> p.getNombreEntidad().equalsIgnoreCase(state))
                .map(PostalCode::getNombreMunicipio)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getAllMunicipalitiesFromCity(String city) {
        return postalCodeList.stream()
                .filter(p -> p.getNombreCiudad().equalsIgnoreCase(city))
                .map(PostalCode::getNombreMunicipio)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getAllPostalCodesFromState(String state) {
        return postalCodeList.stream()
                .filter(p -> p.getNombreEntidad().equalsIgnoreCase(state))
                .map(PostalCode::getCpAsentamiento)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getAllPostalCodesFromCity(String city) {
        return postalCodeList.stream()
                .filter(p -> p.getNombreCiudad().equalsIgnoreCase(city))
                .map(PostalCode::getCpAsentamiento)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getAllPostalCodesFromMunicipality(String state, String municipality) {
        return postalCodeList.stream()
                .filter(p -> p.getNombreEntidad().equalsIgnoreCase(state))
                .filter(p -> p.getNombreMunicipio().equalsIgnoreCase(municipality))
                .map(PostalCode::getCpAsentamiento)
                .distinct()
                .collect(Collectors.toList());
    }

    public long getTotalPostalCodes() {
        return postalCodeList.stream()
                .map(PostalCode::getCpAsentamiento)
                .distinct()
                .count();
    }

    public int getTotalStates() {
        return getAllStates().size();
    }

    public int getTotalCities() {
        return getAllCities().size();
    }

    public int getTotalMunicipalities() {
        return getAllMunicipalities().size();
    }

    public List<String> getAllPostalCodes() {
        return postalCodeList.stream()
                .map(PostalCode::getCpAsentamiento)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<PostalCode> getAllRawData() {
        return postalCodeList;
    }

}
