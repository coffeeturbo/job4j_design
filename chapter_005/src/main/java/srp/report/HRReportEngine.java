package srp.report;

import srp.Employee;
import srp.report.formatter.SalaryFormatter;
import srp.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HRReportEngine implements Report {

    private Store store;
    private SalaryFormatter salaryFormatter;

    public HRReportEngine(Store store, SalaryFormatter salaryFormatter) {
        this.store = store;
        this.salaryFormatter = salaryFormatter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
            .append(System.lineSeparator());

        List<Employee> employees = store.findBy(filter);
        employees.sort(Comparator.reverseOrder());

        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                .append(salaryFormatter.getFormated(employee.getSalary())).append(";")
                .append(System.lineSeparator());
        }
        return text.toString();
    }
}
