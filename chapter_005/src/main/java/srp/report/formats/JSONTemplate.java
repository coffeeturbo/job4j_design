package srp.report.formats;

import srp.Employee;

import java.util.List;

public class JSONTemplate extends Template {
    private static final String ELEMENT_TEMPLATE = "{"
        + "\"name\":\"${name}\","
        + "\"hired\":\"${hired}\","
        + "\"fired\":\"${fired}\","
        + "\"salary\":\"${salary}\""
        + "}";


    public String produce(List<Employee> employees) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (Employee employee: employees) {
            builder.append(produceElement(ELEMENT_TEMPLATE, createTemplateVars(employee)));
        }
        builder.append("]");
        return builder.toString();
    }
}
