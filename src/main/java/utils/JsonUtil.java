package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class JsonUtil {

    public static List<HashMap<String, String>> getDataJson(String filePath) throws IOException {
        String jsonOrigin = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> data =  objectMapper.readValue(jsonOrigin, new TypeReference<List<HashMap<String, String>>>(){
        });
        return data;
    }
}
