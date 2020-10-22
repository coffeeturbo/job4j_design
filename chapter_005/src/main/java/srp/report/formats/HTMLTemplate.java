package srp.report.formats;

import srp.Employee;

import java.util.List;

public class HTMLTemplate extends Template {
    private static final String ELEMENT_TEMPLATE = "<tr>"
        + "<td>${name}</td>"
        + "<td>${hired}</td>"
        + "<td>${fired}</td>"
        + "<td>${salary}</td>"
        + "</tr>";


    public String produce(List<Employee> employees) {
        StringBuilder builder = new StringBuilder();
        builder.append("<!doctype html>"
            + "<head>"
            + "  <meta charset=\"utf-8\">"
            + "  <title>Job4j</title>"
            + "  </head>"
            + "<body>");

        builder.append("<table>"
            + "<thead>"
            + "  <tr>"
            + "    <th>Name</th>"
            + "    <th>Hired</th>"
            + "    <th>Fired</th>"
            + "    <th>Salary</th>"
            + "  </tr>"
            + "</thead>"
            + "<tbody>");
        for (Employee employee: employees) {
            builder.append(produceElement(ELEMENT_TEMPLATE, createTemplateVars(employee)));
        }
        builder.append("</tbody>"
            + "</table>"
            + "</body>"
            + "</html>");
        return builder.toString();
    }
}
