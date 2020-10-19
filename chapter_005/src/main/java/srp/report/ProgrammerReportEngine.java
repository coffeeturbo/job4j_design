package srp.report;

import srp.Employee;
import srp.store.Store;

import java.util.function.Predicate;

public class ProgrammerReportEngine implements Report {
    private Store store;

    public ProgrammerReportEngine(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<!doctype html>"
            + "<head>"
            + "  <meta charset=\"utf-8\">"
            + "  <title>Job4j</title>"
            + "  </head>"
            + "<body>");

        text.append("<table>"
            + "<thead>"
            + "  <tr>"
            + "    <th>Name</th>"
            + "    <th>Hired</th>"
            + "    <th>Fired</th>"
            + "    <th>Salary</th>"
            + "  </tr>"
            + "</thead>"
            + "<tbody>");

        for (Employee employee : store.findBy(filter)) {
            text.append("<tr>");
            text.append("<td>" + employee.getName() + "</td>")
                .append("<td>" + employee.getHired() + "</td>")
                .append("<td>" + employee.getFired() + "</td>")
                .append("<td>" + employee.getSalary() + "</td>");
            text.append("</tr>");
        }

        text.append("</tbody>"
            + "</table>"
            + "</body>"
            + "</html>");

        return text.toString();
    }
}
