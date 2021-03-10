import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    private static final String ZIP_FILE = "C:\\Games\\savegames\\zip.zip";
    private static final String DIR_NAME = "C:\\Games\\savegames";

    public static void main(String[] args) {
        openZip(ZIP_FILE, DIR_NAME);

        GameProgress gameProgress = openProgress(DIR_NAME+"\\save2.dat");
        System.out.println(gameProgress);
    }

    private static void openZip(String filename, String dirname){
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(filename))){
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null){
                name = entry.getName();
                File fileOut = new File(dirname, name);
                try (FileOutputStream fout = new FileOutputStream(fileOut)) {
                    for (int i = zin.read(); i != -1; i = zin.read()) {
                        fout.write(i);
                    }
                    fout.flush();
                }
                zin.closeEntry();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static GameProgress openProgress(String filename) {
        GameProgress gameProgress = null;

        try (FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis)){
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return gameProgress;
    }
}
