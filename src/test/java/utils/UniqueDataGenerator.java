package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UniqueDataGenerator {
	
    public static String getUniqueNumber() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return "UNQ-" + now.format(formatter);
    }
}
