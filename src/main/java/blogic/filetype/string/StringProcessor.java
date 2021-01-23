package blogic.filetype.string;

import blogic.filetype.executor.Executable;
import blogic.filetype.string.fileTypeConverter.IFileTypeConverter;
import util.io.FileHelper;
import blogic.model.Person;

import java.util.List;

import java.io.IOException;

import static util.Constants.View.*;

public class StringProcessor implements Executable {
    private final FileHelper fileHelper = new FileHelper();
    private final IFileTypeConverter converter;

    public StringProcessor(IFileTypeConverter converter) {
        this.converter = converter;
    }


    @Override
    public void create(String fileName, List<Person> persons) throws IOException {
        String personsStr = converter.getStrFromPersons(persons);
        fileHelper.writeToFile(personsStr);
    }

    @Override
    public List<Person> read(String fileName) throws IOException{
        String content = fileHelper.getFile();
        return converter.getPersonsFromString(content);
    }

    @Override
    public void update(long id, String valueToBeUpdated, String valueToChange) throws IOException {
        List<Person> person = read(filename);
        String content = converter.getStrFromPersons(person);
        personList = converter.updateDataInPerson(id, valueToBeUpdated, valueToChange, content);
    }

    @Override
    public void delete(long id) {
        try {
            personList = read(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String content = converter.getStrFromPersons(personList);
        personList = converter.removePersonsFromList(id, content);
    }
}


// связь конвертера с файлхелпером. Обработка стринговых типов данных
