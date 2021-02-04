package dao.impl.nosql;

import blogic.model.Person;
import dao.IDatabaseController;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class Redis implements IDatabaseController {

   private final Jedis jedis = new Jedis("localhost");


    @Override
    public void addToDatabase(Person person) {
        jedis.lpush("persons", person.getId() + " " + person.getFName() + " " + person.getLName()
                + " " + person.getAge() + " " + person.getCity());
        jedis.close();
    }

    @Override
    public List<Person> readFromDatabase() {
        List<Person> personList = new ArrayList<>();
        Person person;
        for (int count = 0; jedis.lindex("persons", count) != null; count++) {
            String read = jedis.lindex("persons", count);
            String[] arrayRead = read.split("\\s");
            person = new Person(Integer.parseInt(arrayRead[0]), arrayRead[1], arrayRead[2],
                    Byte.parseByte(arrayRead[3]), arrayRead[4]);
            personList.add(person);
        }
        jedis.close();
        return personList;
    }

    @Override
    public void updateDataInPerson(long id, String[] newValue) {
        for (int count = 0; jedis.lindex("persons", count) != null; count++) {
            String read = jedis.lindex("persons", count);
            String[] arrayRead = read.split("\\s");
//            read = read.substring(0, String.valueOf(newValue[0]).length()).trim();
            if (Integer.parseInt(arrayRead[0]) == id) {
                jedis.lset("persons", count, newValue[0]+" "+newValue[1]+" "
                        +newValue[2]+" "+newValue[3]+" "+newValue[4]);
            }
        }
        jedis.close();
    }

    @Override
    public void removePersonsFromList(long id) {
        for (int count = 0; jedis.lindex("persons", count) != null; count++) {
            String read = jedis.lindex("persons", count);
            String[] arrayRead = read.split("\\s");
            if (Integer.parseInt(arrayRead[0]) == id) {
                jedis.lrem("persons", count, read);
            }
        }
        jedis.close();
    }
}


