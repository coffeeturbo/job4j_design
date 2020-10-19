package srp.report;

import org.junit.Test;
import srp.Employee;
import srp.store.MemStorage;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CountingReportEngineTest {
    @Test
    public void whenOldGenerated() {
        MemStorage store = new MemStorage();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new CountingReportEngine(store);
        StringBuilder expect = new StringBuilder()
            .append("Name; Hired; Fired; Salary;")
            .append(System.lineSeparator())
            .append(worker.getName()).append(";")
            .append(worker.getHired()).append(";")
            .append(worker.getFired()).append(";")
            .append((int) worker.getSalary()).append(";")
            .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}