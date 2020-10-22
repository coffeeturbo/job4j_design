package srp.report.formats;

import srp.Employee;

import java.util.List;

public class StringTemplate extends Template {
    private static final String ELEMENT_TEMPLATE =
        "${name};"
        + "${hired};"
        + "${fired};"
        + "${salary};";

    public String produce(List<Employee> employees) {
        StringBuilder builder = new StringBuilder();
        builder.append("Name; Hired; Fired; Salary;")
            .append(System.lineSeparator());
        for (Employee employee: employees) {
            builder.append(produceElement(ELEMENT_TEMPLATE, createTemplateVars(employee)));
        }
        builder.append(System.lineSeparator());
        return builder.toString();
    }
}
