package blogic.filetype.executor.factory;
import blogic.filetype.binary.BinaryProcessor;
import blogic.filetype.executor.Executable;
import blogic.filetype.string.StringProcessor;
import blogic.filetype.string.fileTypeConverter.impl.CsvConverter;
import blogic.filetype.string.fileTypeConverter.impl.JsonConverter;
import blogic.filetype.string.fileTypeConverter.impl.XmlConverter;
import blogic.filetype.string.fileTypeConverter.impl.YamlConverter;

import javax.xml.XMLConstants;
import util.io.FileHelper;
import util.Constants.Format;


public class fileTypeFactory {
   // private final Executable binaryExecutable = new BinaryProcessor();
    private final Executable jsonExecutable = new StringProcessor(new JsonConverter());
    private final Executable xmlExecutable = new StringProcessor(new XmlConverter());
    private final Executable ymlExecutable = new StringProcessor(new YamlConverter());
    private final Executable csvExecutable = new StringProcessor(new CsvConverter());

    public Executable getInstance(String format) {
        Executable instance;
        switch (format.toLowerCase()) {
            case Format.YAML:
                instance = ymlExecutable;
                break;
            case Format.CSV:
                instance = csvExecutable;
                break;
            case Format.XML:
                instance = xmlExecutable;
                break;
            case Format.JSON:
                instance = jsonExecutable;
                break;
//            case Format.BINARY:
//                instance = binaryExecutable;
//                break;
            default:
                throw new IllegalArgumentException("Данная программа не работает введенным форматом.\n");
        }
        return instance;
    }
}
    // сюда будет приходить тип файла и отдаватся тип конвертера который мы используем в данный момент. При этом думаю было бы
    // неплохо во вью делать вывод названия файла с которым мы в данный момент работаем    

