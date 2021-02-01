package util.update;

import blogic.model.Person;

import java.util.Iterator;
import java.util.List;

import static util.Constants.View.*;

public class Update {
    public void updating(long id, String[] newValue, List<Person> personList) {
        Iterator<Person> iterator = personList.iterator();
        int personIndex = 0;
        Person person = new Person();
        Person item;
        while (iterator.hasNext()) {
            item = iterator.next();
            if (item.getId() == id) {
                personIndex = personList.indexOf(item);
                person = refactorPerson(item, newValue);
                break;
            }
        }
        if (!person.equals(new Person())) {
            personList.set(personIndex, person);
        }
    }

    private Person refactorPerson(Person newPerson, String[] newValue) {
        String[] updatingTypeValue = new String[]{"Id", "Fname", "Lname", "Age", "City"};
        for (int count = 0; count < 5; count++) {
            if (!newValue[count].equals("")) {
                switch (updatingTypeValue[count]) {
                    case ID:
                        newPerson.setId(Long.parseLong(newValue[count]));
                        break;
                    case FIRST_NAME:
                        newPerson.setFName(newValue[count]);
                        break;
                    case LAST_NAME:
                        newPerson.setLName(newValue[count]);
                        break;
                    case AGE:
                        newPerson.setAge(Byte.parseByte(newValue[count]));
                        break;
                    case CITY:
                        newPerson.setCity(newValue[count]);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }
        return newPerson;
    }
}
