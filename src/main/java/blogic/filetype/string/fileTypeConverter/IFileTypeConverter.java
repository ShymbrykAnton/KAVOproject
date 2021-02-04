package blogic.filetype.string.fileTypeConverter;

import blogic.model.Person;
import util.update.Update;

import java.util.List;


public interface IFileTypeConverter {


    String getStrFromPersons(List<Person> persons);

    List<Person> getPersonsFromString(String strPersons);

    default List<Person> updateDataInPerson(long id, String[] newValue, List<Person> personList) {
        Update update = new Update();
        update.updating(id, newValue, personList);
        return personList;
    }

    default List<Person> removePersonsFromList(long id, List<Person> personList) {
        personList.removeIf(item -> item.getId() == id);
        return personList;
    }
}