package srp.report;

import org.junit.Test;
import srp.Employee;
import srp.report.formatter.DefaultSalatyFormatter;
import srp.store.MemStorage;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProgrammerReportEngineTest {
    @Test
    public void whenOldGenerated() {
        MemStorage store = new MemStorage();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ProgrammerReportEngine(store, new DefaultSalatyFormatter());
        StringBuilder expect = new StringBuilder()
            .append("<!doctype html><head>  <meta charset=\"utf-8\">  <title>Job4j</title>  </head><body><table><thead>  <tr>    <th>Name</th>    <th>Hired</th>    <th>Fired</th>    <th>Salary</th>  </tr></thead><tbody>"
                + "<tr>"
                + "<td>" + worker.getName() + "</td>")
                .append("<td>" + worker.getHired() + "</td>"
                + "<td>" + worker.getFired() + "</td>"
                + "<td>" + worker.getSalary() + "</td>"
                + "</tr>"
                + "</tbody></table></body></html>");

        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}