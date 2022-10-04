import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        File dir1 = new File("D://Games/temp");
        if (dir1.mkdir()) ;
        fileCreat("D://Games/temp", "temp.txt");
        writeFile(dir1);
        dirCreat("D://Games", "src");
        dirCreat("D://Games", "res");
        dirCreat("D://Games", "savegames");
        dirCreat("D://Games", "temp");
        dirCreat("D://Games/src", "main");
        dirCreat("D://Games/src", "test");
        dirCreat("D://Games/res", "drawables");
        dirCreat("D://Games/res", "vectors");
        dirCreat("D://Games/res", "icons");
        fileCreat("D://Games/src/main", "Main.java");
        fileCreat("D://Games/src/main", "Utils.java");


        GameProgress save1 = new GameProgress(85, 120, 12, 25);
        GameProgress save2 = new GameProgress(50, 70, 23, 43);
        GameProgress save3 = new GameProgress(96, 99, 38, 57);

        ArrayList<String> list = new ArrayList<String>();

        saveGame("D://Games/savegames/save1.dat", save1, list);
        saveGame("D://Games/savegames/save2.dat", save2, list);
        saveGame("D://Games/savegames/save3.dat", save3, list);



        zipFiles("D://Games/savegames/zip.zip", list);

    }

    public static void saveGame(String fileName, GameProgress save, ArrayList list) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save);
            list.add(fileName);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void zipFiles(String fileName, ArrayList<String> list) {
            try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(fileName))) {
                for (int i = 0; i == list.size(); i++) {
                    FileInputStream fis = new FileInputStream(list.get(i));
                    ZipEntry entry = new ZipEntry(list.get(i));
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    public static void fileCreat(String dirParth, String fileName) {
        File file = new File(dirParth, fileName);
        try {
            if (file.createNewFile())
                writeFile(file);
        } catch (IOException ex) {
            System.out.println("Ошибка при добавлении файла " + file.getName());
        }

    }

    public static void dirCreat(String dirParth, String dirParth2) {
        File file = new File(dirParth, dirParth2);
        if (file.mkdir()) {
            writeFile(file);
        }
        ;
    }

    public static void writeFile(File file) {
        Date date = new Date();
        String text = date + " файл " + file.getName() + " был добавлен в папку " + file.getParent();
        String text2 = date + " папка " + file.getName() + " была добавлена в папку " + file.getParent();
        try (FileWriter writer = new FileWriter("D://Games/temp/temp.txt", true)) {
            if (file.isDirectory()) {
                writer.write(text2);
                writer.append('\n');
                writer.flush();
            } else {
                writer.write(text);
                writer.append('\n');
                writer.flush();
            }

        } catch (IOException ex) {
            System.out.println("Ошибка при записи файла");
        }
    }


}
