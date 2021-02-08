package util;

import blogic.model.Person;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        Long idFst = o1.getId();
        Long idSec = o2.getId();
        return idFst.compareTo(idSec);
    }
}
