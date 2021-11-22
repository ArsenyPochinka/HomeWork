package server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LocalHistory {
    private final File file;
    private final List<String> stringsList;

    public LocalHistory() {
        file = new File("src/main/txt/recordingLocalHistory.txt");
        stringsList = new ArrayList<>();
    }

    synchronized void doBufferedInputStream() {
            try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file))) {
                int buff=0;
                StringBuffer stringbuff = new StringBuffer();
                while (buff != -1) {
                    while ((buff = bin.read()) != (int) '\n') {
                        if(buff == -1) {
                            break;
                        }
                        stringbuff.append((char) buff);
                    }
                    stringsList.add(stringbuff.toString());
                    stringbuff.delete(0,stringbuff.length());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    synchronized void doBufferedOutputStream(String message) {
        try (BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(file, true))) {
            bout.write(message.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getStringsList() {
        return stringsList;
    }
}
