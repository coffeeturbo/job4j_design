package srp.report;

import org.junit.Test;
import srp.Employee;
import srp.store.MemStorage;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HRReportEngineTest {
    @Test
    public void whenOldGenerated() {
        MemStorage store = new MemStorage();
        Calendar now = Calendar.getInstance();
        Employee worker2 = new Employee("Ivan", now, now, 100);
        Employee worker = new Employee("Dean", now, now, 200);
        store.add(worker);
        store.add(worker2);
        Report engine = new HRReportEngine(store);
        StringBuilder expect = new StringBuilder()
            .append("Name; Salary;")
            .append(System.lineSeparator())
            .append(worker.getName()).append(";")
            .append(worker.getSalary()).append(";")
            .append(System.lineSeparator())
            .append(worker2.getName()).append(";")
            .append(worker2.getSalary()).append(";")
            .append(System.lineSeparator());

        assertThat(engine.generate(em -> true), is(expect.toString()));

        System.out.println(engine.generate(em -> true));
    }
}