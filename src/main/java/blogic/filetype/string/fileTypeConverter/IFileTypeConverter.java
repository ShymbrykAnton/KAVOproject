package blogic.filetype.string.fileTypeConverter;

import blogic.model.Person;

import java.util.Iterator;
import java.util.List;

public interface IFileTypeConverter {

    String getStrFromPersons(List<Person> persons);

    public List<Person> getPersonsFromString(String strPersons);

    default List<Person> updateDataInPerson(long id, String[] fieldToBeUpdated, String valueToUpdate, String strPersons) {

        List<Person> persons = getPersonsFromString(strPersons);

        Iterator<Person> iterator = persons.iterator();

        boolean hasID = false;

        Person reqPerson;

        while (iterator.hasNext()) {
            Person item = iterator.next();
            if (item.getId() == id) {
                while (iterator.hasNext()) {
                    Person iterPerson = iterator.next();
                    if (iterPerson.getId() == id) {
                        hasID = true;
                        iterPerson.setFName(fieldToBeUpdated[0]);
                        iterPerson.setLName(fieldToBeUpdated[1]);
                        iterPerson.setAge(Integer.parseInt(fieldToBeUpdated[2]));
                        iterPerson.setCity(fieldToBeUpdated[3]);
                        break;
                    }
                }
            } else if (!hasID) {
                throw new IllegalArgumentException();
            }
        }
        return persons;
    }

    default List<Person> removePersonsFromList(long id, String strPersons) {
        List<Person> persons = getPersonsFromString(strPersons);
        Iterator<Person> iterator = persons.iterator();
        boolean hasID = false;
        while (iterator.hasNext()) {
            Person item = iterator.next();
            if (item.getId() == id) {
                iterator.remove();
                hasID = true;
                break;
            }
        }
        if (!hasID) {
            throw new IllegalArgumentException();
        }
        return persons;
    }
}
