package srp.report.formats;

import srp.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Template {
    public abstract String produce(List<Employee> employees);

    protected String produceElement(String template, Map<String, String> args) {
        for (String pattern : args.keySet()) {

            if (!template.contains(pattern)) {
                throw new IllegalArgumentException();
            }
            template = template.replace(pattern, args.get(pattern));
        }
        return template;
    }

    protected Map<String, String> createTemplateVars(Employee employee) {
        Map<String, String> vars = new HashMap<>(4);
        vars.put("${fired}", String.valueOf(employee.getFired()));
        vars.put("${hired}", String.valueOf(employee.getHired()));
        vars.put("${name}", String.valueOf(employee.getName()));
        vars.put("${salary}", String.valueOf(employee.getSalary()));

        return vars;
    }
}
