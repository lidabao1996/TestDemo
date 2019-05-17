package tree;

import java.io.File;

public class Deletefile {
    public static void main(String[] args) {
        String folder = "/Users/lf/my/test/";
        deleteFile(new File(folder));
    }


    public static void deleteFile(File file) {

        if (file==null|| !file.exists()){
            System.out.println("文件不存在？file.getName() = " + file.getName());
            return;
        }

        if (file.isFile()) {
            file.delete();
        }else {
            File[] files = file.listFiles();
            for(int i=0;files != null && i<files.length;i++){
                deleteFile(files[i]);
            }
            file.delete();
        }


        System.out.println("删除file.getName() = " + file.getName());

    }

}
