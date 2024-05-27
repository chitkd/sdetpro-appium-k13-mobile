package test_data;

import com.google.gson.Gson;
import test_data.models.LoginCred;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class DataObjectBuilder {
    public static <T> T buildDataObject(String relativeFilePath, Class<T> dataType){
        T returnedData;
        // Read the JSON content
        String absolutFilePath = System.getProperty("user.dir").concat(relativeFilePath);
        try(
                Reader reader = Files.newBufferedReader(Paths.get(absolutFilePath))
        ){
            Gson gson = new Gson();
            returnedData = gson.fromJson(reader, dataType);

        } catch (NoSuchFileException noSuchFieldException){
            throw new RuntimeException("[ERR] Could not find the file: ".concat(absolutFilePath));
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        // Convert into type T
        return returnedData;
    }

    public static void main(String[] args) {
        String invalidPath = "src/test/java/test_data/authen/LoginCredData.json";
        String validPath = "/src/test/java/test_data/authen/LoginCredData.json";

        LoginCred[] loginCreds = DataObjectBuilder.buildDataObject(validPath, LoginCred[].class);
        //LoginCred loginCreds = DataObjectBuilder.buildDataObject(invalidPath, LoginCred.class);
        System.out.println(Arrays.toString(loginCreds));
    }
}
