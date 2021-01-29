package blogic.filetype.string.fileTypeConverter;

import blogic.filetype.binary.BinaryProcessor;
import blogic.model.Person;

import java.util.List;

import static util.Constants.Config.*;


public interface IFileTypeConverter {

    String getStrFromPersons(List<Person> persons);

    List<Person> getPersonsFromString(String strPersons);

    default List<Person> updateDataInPerson(long id, String[] updatingTypeValue, String[] newValue) {
        BinaryProcessor.updating(id, updatingTypeValue, newValue);
        return personList;
    }

    default List<Person> removePersonsFromList(long id) {
        personList.removeIf(item -> item.getId() == id);
        return personList;
    }
}