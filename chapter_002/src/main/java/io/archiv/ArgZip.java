package io.archiv;

public class ArgZip {
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        return (args[0] != null && !args[0].isEmpty())
            && (args[1] != null && !args[1].isEmpty())
            && (args[2] != null && !args[2].isEmpty());
    }

    public String directory() {
        return getValueBySeparator("=", args[0]);
    }

    public String exclude() {
        return getValueBySeparator("=", args[1]);
    }

    public String output() {
        return getValueBySeparator("=", args[2]);
    }

    private String getValueBySeparator(String separator, String arg) {
        return arg.contains(separator) ? arg.split(separator)[1] : arg;
    }
}
