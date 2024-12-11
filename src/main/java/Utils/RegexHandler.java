package Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHandler {

    public static List<String> extractCarReg(String text) {
        List<String> registrations = new ArrayList<>();
        Pattern pattern = Pattern.compile("[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            registrations.add(matcher.group().replace(" ", ""));
        }
        return registrations;
    }
}
