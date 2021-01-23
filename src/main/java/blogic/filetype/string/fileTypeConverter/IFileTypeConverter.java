package blogic.filetype.string.fileTypeConverter;

import blogic.model.Person;

import java.util.Iterator;
import java.util.List;

import static util.Constants.View.*;


public interface IFileTypeConverter {

    String getStrFromPersons(List<Person> persons);

    public List<Person> getPersonsFromString(String strPersons);

    default List<Person> updateDataInPerson(long id, String fieldToBeUpdated, String valueToUpdate, String strPersons) {
        List<Person> persons = getPersonsFromString(strPersons);
        Iterator<Person> iterator = persons.iterator();
        Person reqPerson;
        while (iterator.hasNext()) {
            Person item = iterator.next();
            if (item.getId() == id) {
                reqPerson = item;
                switch (fieldToBeUpdated) {
                    case ID:
                        reqPerson.setId(Long.parseLong(valueToUpdate));
                    case FIRST_NAME:
                        reqPerson.setFName(valueToUpdate);
                    case LAST_NAME:
                        reqPerson.setLName(valueToUpdate);
                    case AGE:
                        reqPerson.setAge(Integer.parseInt(valueToUpdate));
                    case CITY:
                        reqPerson.setCity(valueToUpdate);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }
        return persons;
    }

    default List<Person> removePersonsFromList(long id, String strPersons) {
        personList.removeIf(item -> item.getId() == id);
        return personList;
    }
}
