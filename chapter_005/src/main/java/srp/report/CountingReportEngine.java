package srp.report;

import srp.Employee;
import srp.report.formatter.SalaryFormatter;
import srp.store.Store;

import java.util.function.Predicate;

public class CountingReportEngine implements Report {
    private Store store;
    private SalaryFormatter salaryFormatter;

    public CountingReportEngine(Store store, SalaryFormatter salaryFormatter) {
        this.store = store;
        this.salaryFormatter = salaryFormatter;
    }

    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
            .append(System.lineSeparator());

        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                .append(employee.getHired()).append(";")
                .append(employee.getFired()).append(";")
                .append(salaryFormatter.getFormated(employee.getSalary())).append(";")
                .append(System.lineSeparator());
        }
        return text.toString();
    }
}
