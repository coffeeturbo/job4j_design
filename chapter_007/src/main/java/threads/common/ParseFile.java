package threads.common;

import java.io.*;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        return getContent(file, new SimpleAppendStrategy());
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        return getContent(file, new AppendWithoutUnicode());
    }

    public synchronized void saveContent(String content) throws IOException {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        }
    }

    private String getContent(File file, AppendStrategy strategy) throws IOException {
        try (InputStream i = new FileInputStream(file)) {
            StringBuilder output = new StringBuilder();
            int data;
            byte[] dataBuffer = new byte[1024];
            while ((data = i.read(dataBuffer, 0, 1024)) != -1) {
                strategy.execute(output, data);
            }
            return output.toString();
        }
    }

    private abstract class AppendStrategy {
        abstract void execute(StringBuilder output, int data);
    }

    private class SimpleAppendStrategy extends AppendStrategy {
        @Override
        public void execute(StringBuilder output, int data) {
            output.append((char) data);
        }
    }

    private class AppendWithoutUnicode extends AppendStrategy {
        @Override
        void execute(StringBuilder output, int data) {
            if (data < 0x80) {
                output.append((char) data);
            }
        }
    }
}
