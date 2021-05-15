package di;

import org.springframework.stereotype.Component;

@Component
public class ConsoleInput {

    public String askStr(String question) {
        return "Console input: " + question;
    }
}
