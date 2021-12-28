package c22;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Document {

    private boolean changed = false;

    private List<String> content = new ArrayList<>();

    private FileWriter writer;

    private static AutoSaveThread autoSaveThread;

    public Document(String documentPath, String docName) throws IOException {
        this.writer = new FileWriter(new File(documentPath, docName), true);
    }

    public static Document create(String docPath, String docName) throws IOException {
        Document document = new Document(docPath, docName);
        autoSaveThread = new AutoSaveThread(document);
        autoSaveThread.start();
        return document;
    }

    public void edit(String content) {
        synchronized (this) {
            this.content.add(content);
            this.changed = true;
        }
    }

    public void close() throws IOException {
        autoSaveThread.interrupt();
        writer.close();
    }

    public void save() throws IOException {
        synchronized (this) {
            // balking, 有人干了，就不再重复干.
            if (!changed) {
                return;
            }
            System.out.println(Thread.currentThread() + "exe the save action");
            for (String cacheLine : content) {
                this.writer.write(cacheLine);
                this.writer.write("\r\n");
            }
            this.writer.flush();
            this.changed = false;
            this.content.clear();
        }
    }
}
