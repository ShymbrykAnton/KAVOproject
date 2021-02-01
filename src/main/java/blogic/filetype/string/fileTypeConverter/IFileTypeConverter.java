package blogic.filetype.string.fileTypeConverter;

import blogic.filetype.binary.BinaryProcessor;
import blogic.model.Person;

import java.util.List;


public interface IFileTypeConverter {
    BinaryProcessor binaryProcessor = new BinaryProcessor();

    String getStrFromPersons(List<Person> persons);

    List<Person> getPersonsFromString(String strPersons);

    default List<Person> updateDataInPerson(long id, String[] newValue, List<Person> personList) {
        binaryProcessor.update(id, newValue, personList);
        return personList;
    }

    default List<Person> removePersonsFromList(long id, List<Person> personList) {
        personList.removeIf(item -> item.getId() == id);
        return personList;
    }
}