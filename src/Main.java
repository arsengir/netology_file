import java.io.*;

public class Main {
    private static final StringBuilder LOG = new StringBuilder();
    private static final String PATH = "C:\\Games";

    public static void main(String[] args) {

        createDir("src");
        createDir("res");
        createDir("savegames");
        createDir("temp");
        createDir("src\\main");
        createDir("src\\test");
        createFile("src\\main\\Main.java");
        createFile("src\\main\\Utils.java");
        createDir("res\\drawables");
        createDir("res\\vectors");
        createDir("res\\icons");

        File temp = new File(PATH, "temp\\temp.txt");
        try (FileWriter fw = new FileWriter(temp);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(LOG.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void createDir(String nameDir){
        File dir = new File(PATH, nameDir);
        if (dir.exists()){
            LOG.append(String.format("Директория %s существует \n", nameDir));
        } else if (dir.mkdir()) {
            LOG.append(String.format("Директория %s создана \n", nameDir));
        }
    }

    private static void createFile(String nameFile){
        File dir = new File(PATH, nameFile);
        if (dir.exists()){
            LOG.append(String.format("Файл %s существует \n", nameFile));
        } else {
            try {
                if (dir.createNewFile()) {
                    LOG.append(String.format("Файл %s создан \n", nameFile));
                }
            } catch (IOException e) {
                LOG.append(String.format("Ошибка создания файла %s: %s \n", nameFile, e.getMessage()));
            }
        }
    }


}
