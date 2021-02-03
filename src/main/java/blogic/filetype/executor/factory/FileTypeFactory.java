package blogic.filetype.executor.factory;

import blogic.filetype.binary.BinaryProcessor;
import blogic.filetype.executor.Executable;
import blogic.filetype.string.StringProcessor;
import blogic.filetype.string.fileTypeConverter.impl.CsvConverter;
import blogic.filetype.string.fileTypeConverter.impl.JsonConverter;
import blogic.filetype.string.fileTypeConverter.impl.XmlConverter;
import blogic.filetype.string.fileTypeConverter.impl.YamlConverter;
import dao.DbProcessor;
import dao.impl.nosql.Cassandra;
import dao.impl.sql.H2;
import dao.impl.sql.MySQL;
import dao.impl.sql.PostgreSQL;
import gui.buttonListeners.controller.ListenerController;

import static util.Constants.DataSource.*;

public class FileTypeFactory {

    public FileTypeFactory() {
    }
    private final Executable binaryExecutable = new BinaryProcessor();
    private final Executable jsonExecutable = new StringProcessor(new JsonConverter());
    private final Executable xmlExecutable = new StringProcessor(new XmlConverter());
    private final Executable ymlExecutable = new StringProcessor(new YamlConverter());
    private final Executable csvExecutable = new StringProcessor(new CsvConverter());
    private final Executable mySqlExecutable = new DbProcessor(new MySQL());
    private final Executable postgresSQL = new DbProcessor(new PostgreSQL());
    private final Executable h2 = new DbProcessor(new H2());
    private final Executable cassandra = new DbProcessor(new Cassandra());

    public Executable getInstance(String format) {
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
            case MY_SQL:
                instance = mySqlExecutable;
                break;
            case POSTGRE_SQL:
                instance = postgresSQL;
                break;
            case H2:
                instance = h2;
                break;
            case CASSANDRA:
                instance = cassandra;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return instance;
    }

}