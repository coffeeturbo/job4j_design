package srp.report.formatter;

public class DefaultSalatyFormatter implements SalaryFormatter {
    @Override
    public String getFormated(double salary) {
        return String.valueOf(salary);
    }
}
