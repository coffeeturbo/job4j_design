package templates;

import java.util.Map;

public class Template implements Generator {
    @Override
    public String produce(String template, Map<String, String> args) {
        for (String pattern : args.keySet()) {

            if (!template.contains(pattern)) {
                throw new IllegalArgumentException();
            }
            template = template.replace(pattern, args.get(pattern));
        }
        return template;
    }
}
