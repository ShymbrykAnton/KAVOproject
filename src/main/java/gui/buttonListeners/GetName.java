package gui.buttonListeners;

import blogic.filetype.binary.BinaryProcessor;
import blogic.filetype.executor.Executable;
import blogic.filetype.executor.factory.FileTypeFactory;

import static util.Constants.Messages.FILE_FORMAT;

public class GetName {
    private String fileName = "";
    private Executable executable = new BinaryProcessor();
    private final FileTypeFactory fileTypeFactory = new FileTypeFactory();

    public String constructFileName(String input, String format) {
        this.fileName = String.format(FILE_FORMAT, input, format);
        return fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public Executable constructExecutable(String format) {
        this.executable = fileTypeFactory.getInstance(format);
        return executable;
    }

    public Executable getExecutable() {
        return executable;
    }
}

