import java.io.File;

public class FileInfo {
    private File file;
    private String message;

    public FileInfo(File file, String message) {
        this.file = file;
        this.message = message;
    }

    public File getFile() {
        return file;
    }

    public String getMessage() {
        return message;
    }
}

