package es.wipop.client.fixture;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// TODO borrar si no se usa
public final class ObjectMapperFixture {

   public static final ObjectMapper objectMapper = new ObjectMapper();

   public static <T> T readValue(String fileName, Class<T> clazz) {
      try {
         final var fileStr = Files.readString(Path.of("./src/test/resources/%s".formatted(fileName)));
         return objectMapper.readValue(fileStr, clazz);
      } catch (IOException e) {
         throw new RuntimeException(e.getMessage(), e);
      }
   }
}
