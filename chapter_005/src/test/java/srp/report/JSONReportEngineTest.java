package srp.report;

import org.junit.Test;
import srp.Employee;
import srp.report.formats.JSONTemplate;
import srp.report.formatter.DefaultSalatyFormatter;
import srp.store.MemStorage;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class JSONReportEngineTest {
    @Test
    public void whenJsonReportSuccess() {
        MemStorage store = new MemStorage();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new JSONReportEngine(store, new DefaultSalatyFormatter(), new JSONTemplate());
        StringBuilder expect = new StringBuilder()
            .append("[{")
            .append(String.format("\"name\":\"%s\",", worker.getName()))
            .append(String.format("\"hired\":\"%s\",", worker.getHired()))
            .append(String.format("\"fired\":\"%s\",", worker.getFired()))
            .append(String.format("\"salary\":\"%s\"", worker.getSalary()))
            .append("}]");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}