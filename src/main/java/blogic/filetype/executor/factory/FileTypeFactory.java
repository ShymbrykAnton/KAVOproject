package blogic.filetype.executor.factory;

import blogic.filetype.binary.BinaryProcessor;
import blogic.filetype.executor.Executable;
import blogic.filetype.string.StringProcessor;
import blogic.filetype.string.fileTypeConverter.impl.CsvConverter;
import blogic.filetype.string.fileTypeConverter.impl.JsonConverter;
import blogic.filetype.string.fileTypeConverter.impl.XmlConverter;
import blogic.filetype.string.fileTypeConverter.impl.YamlConverter;

import static gui.buttonListeners.ChooseDataSourceButtonListener.*;
import static util.Constants.DataSource.*;

public class FileTypeFactory {
    private final Executable binaryExecutable = new BinaryProcessor();
    private final Executable jsonExecutable = new StringProcessor(new JsonConverter());
    private final Executable xmlExecutable = new StringProcessor(new XmlConverter());
    private final Executable ymlExecutable = new StringProcessor(new YamlConverter());
    private final Executable csvExecutable = new StringProcessor(new CsvConverter());

    public Executable getInstance() {
        Executable instance;
        switch (format) {
            case YAML_TYPE:
                instance = ymlExecutable;
                break;
            case CSV_TYPE:
                instance = csvExecutable;
                break;
            case XML_TYPE:
                instance = xmlExecutable;
                break;
            case JSON_TYPE:
                instance = jsonExecutable;
                break;
            case BINARY_TYPE:
                instance = binaryExecutable;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return instance;
    }

}