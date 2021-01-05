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
        try (InputStream i = new FileInputStream(file)) {
            StringBuilder output = new StringBuilder();
            int data;
            byte[] dataBuffer = new byte[1024];
            while ((data = i.read(dataBuffer, 0, 1024)) != -1) {
                output.append((char) data);
            }
            return output.toString();
        }
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        try (InputStream i = new FileInputStream(file)) {
            StringBuilder output = new StringBuilder();
            byte[] dataBuffer = new byte[1024];
            int data;
            while ((data = i.read(dataBuffer, 0, 1024)) > 0) {
                if (data < 0x80) {
                    output.append((char) data);
                }
            }
            i.close();
            return output.toString();
        }
    }

    public synchronized void saveContent(String content) throws IOException {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        }
    }
}
