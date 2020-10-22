package srp.report;

import org.junit.Test;
import srp.Employee;
import srp.report.formats.XMLTemplate;
import srp.report.formatter.DefaultSalatyFormatter;
import srp.store.MemStorage;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class XMLReportEngineTest {

    @Test
    public void whenXmlReportSuccess() {
        MemStorage store = new MemStorage();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new XMLReportEngine(store, new DefaultSalatyFormatter(), new XMLTemplate());
        StringBuilder expect = new StringBuilder()
            .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><element>")
            .append(String.format("<name>%s</name>", worker.getName()))
            .append(String.format("<hired>%s</hired>", worker.getHired()))
            .append(String.format("<fired>%s</fired>", worker.getFired()))
            .append(String.format("<salary>%s</salary>", worker.getSalary()))
            .append("</element></root>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}