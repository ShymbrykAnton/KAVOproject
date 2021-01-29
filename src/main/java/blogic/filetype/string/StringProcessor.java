package blogic.filetype.string;

import blogic.filetype.executor.Executable;
import blogic.filetype.string.fileTypeConverter.IFileTypeConverter;
import util.io.FileHelper;
import blogic.model.Person;

import java.util.List;


import static util.Constants.Config.*;

public class StringProcessor implements Executable {
    private final FileHelper fileHelper = new FileHelper();
    private final IFileTypeConverter converter;

    public StringProcessor(IFileTypeConverter converter) {
        this.converter = converter;
    }


    @Override
    public void create(String fileName, List<Person> persons) {
        fileHelper.writeToFile(converter.getStrFromPersons(persons));
    }

    @Override
    public List<Person> read(String fileName) {
        return converter.getPersonsFromString(fileHelper.getFile());
    }

    @Override
    public void update(long id, String [] updatingTypeValue, String[] newValue) {
        personList = converter.updateDataInPerson(id, updatingTypeValue, newValue);
    }

    @Override
    public void delete(long id) {
        personList = converter.removePersonsFromList(id);
    }
}