package blogic.filetype.string;

import blogic.filetype.executor.Executable;
import blogic.filetype.string.fileTypeConverter.IFileTypeConverter;
import util.io.FileHelper;
import blogic.model.Person;
import java.util.List;

import java.io.IOException;

public class StringProcessor implements Executable {
    private final FileHelper fileHelper=new FileHelper();
    private final IFileTypeConverter converter;
    public StringProcessor(IFileTypeConverter converter){this.converter=converter;}

    @Override
    public void write(String fileName, List<Person> personList) throws IOException {
        fileHelper.saveToFile(converter.getStrFromPersons(personList), fileName);
    }

    @Override
    public List<Person> read(String fileName) throws IOException {
        return converter.getPersonsFromString(fileHelper.readFromFile(fileName));
    }}




    // связь конвертера с файлхелпером. Обработка стринговых типов данных
