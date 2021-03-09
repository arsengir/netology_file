import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(5, 10, 4, 20.4);
        GameProgress gameProgress2 = new GameProgress(3, 5, 8, 40.4);
        GameProgress gameProgress3 = new GameProgress(15, 0, 15, 120.4);

        saveGame("C:\\Games\\savegames\\save1.dat", gameProgress1);
        saveGame("C:\\Games\\savegames\\save2.dat", gameProgress2);
        saveGame("C:\\Games\\savegames\\save3.dat", gameProgress3);

        ArrayList<String> fileList = new ArrayList<>();
        File dirSave = new File("C:\\Games\\savegames");
        for (File item : dirSave.listFiles(f -> f.getName().endsWith(".dat"))) {
            fileList.add(item.getAbsolutePath());
        }

        zipFiles("C:\\Games\\savegames\\zip.zip", fileList);
        for (File item : dirSave.listFiles(f -> !f.getName().endsWith(".zip"))) {
            item.delete();
        }
    }

    private static void saveGame(String fileName, GameProgress gameProgress){
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void zipFiles(String fileName, ArrayList<String> fileList){
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(fileName))){
            for (String item : fileList) {
                String nameFile = item.substring(item.lastIndexOf("\\") + 1);
                try(FileInputStream fis = new FileInputStream(item)) {
                    ZipEntry entry = new ZipEntry(nameFile);
                    zout.putNextEntry(entry);

                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
