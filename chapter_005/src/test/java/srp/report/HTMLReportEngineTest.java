package srp.report;

import org.junit.Test;
import srp.Employee;
import srp.report.formats.HTMLTemplate;
import srp.report.formatter.DefaultSalatyFormatter;
import srp.store.MemStorage;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HTMLReportEngineTest {
    @Test
    public void whenHTMLReportSuccess() {
        MemStorage store = new MemStorage();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new HTMLReportEngine(store, new DefaultSalatyFormatter(), new HTMLTemplate());
        StringBuilder expect = new StringBuilder()
            .append("<!doctype html>"
                + "<head>"
                + "  <meta charset=\"utf-8\">"
                + "  <title>Job4j</title>"
                + "  </head>"
                + "<body>")
            .append("<table>"
                + "<thead>"
                + "  <tr>"
                + "    <th>Name</th>"
                + "    <th>Hired</th>"
                + "    <th>Fired</th>"
                + "    <th>Salary</th>"
                + "  </tr>"
                + "</thead>"
                + "<tbody>")
            .append("<tr>")
            .append(String.format("<td>%s</td>", worker.getName()))
            .append(String.format("<td>%s</td>", worker.getHired()))
            .append(String.format("<td>%s</td>", worker.getFired()))
            .append(String.format("<td>%s</td>", worker.getSalary()))
            .append("</tr>")
            .append("</tbody>"
            + "</table>"
            + "</body>"
            + "</html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}