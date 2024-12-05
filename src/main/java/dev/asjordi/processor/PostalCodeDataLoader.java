package dev.asjordi.processor;

import dev.asjordi.model.PostalCode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class PostalCodeDataLoader {

    private final Path FOLDER_PATH = Paths.get("src/main/resources/data");
    private final List<PostalCode> postalCodeList = new ArrayList<>();

    public PostalCodeDataLoader() {
        loadPostalCodeData();
    }

    private void loadPostalCodeData() {
        var files = getFilesFromFolder();

        for (Path path : files) {
            try (Reader r = new FileReader(path.toFile());
                 BufferedReader br = new BufferedReader(r)) {
                String line;

                while ((line = br.readLine()) != null) {
                    String[] data = line.split("\\|");
                    PostalCode cp = new PostalCode(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10], data[11], data[12], data[13]);
                    postalCodeList.add(cp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private List<Path> getFilesFromFolder() {
        List<Path> pathList = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(FOLDER_PATH)) {
            for (Path path : stream) {
                if (Files.isRegularFile(path) && path.toString().endsWith(".csv")) pathList.add(path);
            }
        }
        catch (IOException | DirectoryIteratorException e) {
            e.printStackTrace();
        }

        return pathList;
    }

    public List<PostalCode> getPostalCodeList() {
        return postalCodeList;
    }

}
