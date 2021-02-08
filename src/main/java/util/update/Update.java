package util.update;

import blogic.model.Person;
import java.util.Iterator;
import java.util.List;

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

                person = new Person(
                        Long.parseLong(newValue[0]),
                        newValue[1],
                        newValue[2],
                        Byte.parseByte(newValue[3]),
                        newValue[4]
                );
                break;
            }
        }
        if (!person.equals(new Person())) {
            personList.set(personIndex, person);
        }
    }
}