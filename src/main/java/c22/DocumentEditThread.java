package c22;

import java.io.IOException;
import java.util.Scanner;

public class DocumentEditThread extends Thread {


    private final String docPath;

    private final String docName;

    private final Scanner scanner = new Scanner(System.in);

    public DocumentEditThread(String docPath, String docName) {
        super("docEditThread");
        this.docPath = docPath;
        this.docName = docName;
    }

    @Override
    public void run() {
        int times = 0;
        try {
            Document document = Document.create(docPath, docName);
            while (true) {
                String text = scanner.next();
                if ("quit".equals(text)) {
                    document.close();
                    break;
                }
                document.edit(text);
                if (times == 5) {
                    document.save();
                    times = 0;
                    times++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
