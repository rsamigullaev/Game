import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    private static final File games = new File("C:\\Games");

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        FileInfo src = createDirectory(games, "src");
        FileInfo res = createDirectory(games, "res");
        FileInfo savegames = createDirectory(games, "savegames");
        FileInfo temp = createDirectory(games, "temp");
        FileInfo main = createDirectory(src.getFile(), "main");
        FileInfo test = createDirectory(src.getFile(), "test");
        sb.append(src.getMessage()).append("\n");
        sb.append(res.getMessage()).append("\n");
        sb.append(savegames.getMessage()).append("\n");
        sb.append(temp.getMessage()).append("\n");
        sb.append(main.getMessage()).append("\n");
        sb.append(test.getMessage()).append("\n");

        FileInfo mainJava = createFile(main.getFile(), "Main.java");
        FileInfo utilsJava = createFile(main.getFile(), "Utils.java");
        sb.append(mainJava.getMessage()).append("\n");
        sb.append(utilsJava.getMessage()).append("\n");

        FileInfo drawables = createDirectory(res.getFile(), "drawables");
        FileInfo vectors = createDirectory(res.getFile(), "vectors");
        FileInfo icons = createDirectory(res.getFile(), "icons");
        sb.append(drawables.getMessage()).append("\n");
        sb.append(vectors.getMessage()).append("\n");
        sb.append(icons.getMessage()).append("\n");

        FileInfo tempTxt = createFile(temp.getFile(), "temp.txt");
        sb.append(tempTxt.getMessage()).append("\n");

        try {
            Files.writeString(tempTxt.getFile().toPath(), sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FileInfo createDirectory(File parent, String directoryName) {
        File dir = new File(parent, directoryName);
        if (dir.exists()) return new FileInfo(dir, (directoryName + " " + "каталог уже существует"));

        String msg;
        if (dir.mkdir()) msg = (directoryName + " " + "каталог создан");
        else msg = "Не создано";

        return new FileInfo(dir, msg);
    }

    public static FileInfo createFile(File parent, String fileName) {
        File dir = new File(parent, fileName);
        try {
            if (dir.createNewFile()) return new FileInfo(dir, (fileName + " файл создан"));
            else return new FileInfo(dir, (fileName + " файл уже существует"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return new FileInfo(null, "Не удалось создать файл " + fileName);
        }
    }
}
