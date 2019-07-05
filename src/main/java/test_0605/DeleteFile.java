package test_0605;

import java.io.File;

public class DeleteFile {
    public static void main(String[] args) {

    }


    public void deleteFile(File file) {
        if (file == null || file.exists()) {
            return;
        }

        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            for (int i = 0; files != null && i < files.length; i++) {
                deleteFile(files[i]);
            }

            file.delete();
        }


    }
}
