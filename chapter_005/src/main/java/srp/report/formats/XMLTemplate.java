package srp.report.formats;

import srp.Employee;

import java.util.List;

public class XMLTemplate extends Template {
    private static final String ELEMENT_TEMPLATE = "<element>"
        + "<name>${name}</name>"
        + "<hired>${hired}</hired>"
        + "<fired>${fired}</fired>"
        + "<salary>${salary}</salary>"
        + "</element>";

    public String produce(List<Employee> employees) {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>");
        for (Employee employee: employees) {
            builder.append(produceElement(ELEMENT_TEMPLATE, createTemplateVars(employee)));
        }
        builder.append("</root>");
        return builder.toString();
    }
}
