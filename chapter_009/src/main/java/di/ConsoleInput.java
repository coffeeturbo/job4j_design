package di;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class ConsoleInput {

    public String askStr(String question) {
        return "Console input: " + question;
    }
}
