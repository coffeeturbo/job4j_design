package srp.report;

import srp.Employee;
import srp.report.formats.Template;
import srp.report.formatter.SalaryFormatter;
import srp.store.Store;

import java.util.function.Predicate;

public class HTMLReportEngine implements Report {
    private Store store;
    private SalaryFormatter salaryFormatter;
    private Template template;

    public HTMLReportEngine(Store store, SalaryFormatter salaryFormatter, Template template) {
        this.store = store;
        this.salaryFormatter = salaryFormatter;
        this.template = template;
    }

    public String generate(Predicate<Employee> filter) {
        return template.produce(store.findBy(filter));
    }
}
