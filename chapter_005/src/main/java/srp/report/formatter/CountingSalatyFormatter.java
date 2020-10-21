package srp.report.formatter;

public class CountingSalatyFormatter implements SalaryFormatter {
    @Override
    public String getFormated(double salary) {
        return String.valueOf((int) salary);
    }
}
