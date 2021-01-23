package blogic.filetype.executor.factory;
import blogic.filetype.binary.BinaryProcessor;
import blogic.filetype.executor.Executable;
import blogic.filetype.string.StringProcessor;
import blogic.filetype.string.fileTypeConverter.impl.CsvConverter;
import blogic.filetype.string.fileTypeConverter.impl.JsonConverter;
import blogic.filetype.string.fileTypeConverter.impl.XmlConverter;
import blogic.filetype.string.fileTypeConverter.impl.YamlConverter;

import java.util.Locale;

import static util.Constants.View.*;
import static gui.buttonListeners.ChooseDataSourceButtonListener.*;


public class FileTypeFactory {
    private final static char POINT = '.';
    private final Executable binaryExecutable = new BinaryProcessor();
    private final Executable jsonExecutable = new StringProcessor(new JsonConverter());
    private final Executable xmlExecutable = new StringProcessor(new XmlConverter());
    private final Executable ymlExecutable = new StringProcessor(new YamlConverter());
    private final Executable csvExecutable = new StringProcessor(new CsvConverter());

    public Executable getInstance() {
        Executable instance;
        switch (format) {
            case "yaml":
                instance = ymlExecutable;
                break;
            case "csv":
                instance = csvExecutable;
                break;
            case "xml":
                instance = xmlExecutable;
                break;
            case "json":
                instance = jsonExecutable;
                break;
            case "bin":
                instance = binaryExecutable;
                break;
            default:
                throw new IllegalArgumentException("Данная программа не работает введенным форматом.\n");
        }
        return instance;
    }

}
    // сюда будет приходить тип файла и отдаватся тип конвертера который мы используем в данный момент. При этом думаю было бы
    // неплохо во вью делать вывод названия файла с которым мы в данный момент работаем

