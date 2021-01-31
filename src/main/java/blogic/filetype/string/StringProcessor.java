package blogic.filetype.string;

import blogic.filetype.executor.Executable;
import blogic.filetype.string.fileTypeConverter.IFileTypeConverter;
import util.io.FileHelper;
import blogic.model.Person;

import java.util.List;

public class StringProcessor implements Executable {
    private final FileHelper fileHelper = new FileHelper();
    private final IFileTypeConverter converter;

    public StringProcessor(IFileTypeConverter converter) {
        this.converter = converter;
    }


    @Override
    public void create(String fileName, List<Person> persons) {
        fileHelper.writeToFile(converter.getStrFromPersons(persons), fileName);
    }

    @Override
    public List<Person> read(String fileName) {
        return converter.getPersonsFromString(fileHelper.getFile(fileName));
    }

    @Override
    public void update(long id,  String[] newValue, List<Person> personList) {
        personList = converter.updateDataInPerson(id, newValue, personList);
    }

    @Override
    public void delete(long id, List<Person> personList) {
        personList = converter.removePersonsFromList(id, personList);
    }
}