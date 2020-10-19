package srp.report;

import srp.Employee;
import srp.store.Store;

import java.util.function.Predicate;

public class CountingReportEngine implements Report {
    private Store store;

    public CountingReportEngine(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
            .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                .append(employee.getHired()).append(";")
                .append(employee.getFired()).append(";")
                .append((int) employee.getSalary()).append(";")
                .append(System.lineSeparator());
        }
        return text.toString();
    }
}
