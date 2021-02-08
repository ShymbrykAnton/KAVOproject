package dao.impl.nosql;

import blogic.model.Person;
import dao.IDatabaseController;
import redis.clients.jedis.Jedis;
import util.Constants;

import java.util.ArrayList;
import java.util.List;

public class Redis implements IDatabaseController {

    private final Jedis jedis = new Jedis(Constants.Redis.JEDIS);

    @Override
    public void addToDatabase(Person person) {
        jedis.lpush(
                Constants.Redis.KEY,
                person.getId() + Constants.Redis.SEPARATOR
                        + person.getFName() + Constants.Redis.SEPARATOR
                        + person.getLName() + Constants.Redis.SEPARATOR
                        + person.getAge() + Constants.Redis.SEPARATOR
                        + person.getCity()
        );
        jedis.close();
    }

    @Override
    public List<Person> readFromDatabase() {
        List<Person> personList = new ArrayList<>();

        Person person;
        for (int count = 0; jedis.lindex(Constants.Redis.KEY, count) != null; count++) {

            String read = jedis.lindex(Constants.Redis.KEY, count);
            String[] arrayRead = read.split(Constants.Redis.REGEX);

            person = new Person(
                    Integer.parseInt(arrayRead[0]),
                    arrayRead[1],
                    arrayRead[2],
                    Byte.parseByte(arrayRead[3]),
                    arrayRead[4]);
            personList.add(person);
        }
        jedis.close();
        return personList;
    }

    @Override
    public void updateDataInPerson(long id, String[] newValue) {
        for (int count = 0; jedis.lindex(Constants.Redis.KEY, count) != null; count++) {

            String read = jedis.lindex(Constants.Redis.KEY, count);
            String[] arrayRead = read.split(Constants.Redis.REGEX);

            if (Integer.parseInt(arrayRead[0]) == id) {
                jedis.lset(
                        Constants.Redis.KEY,
                        count,
                        newValue[0] + Constants.Redis.SEPARATOR
                                + newValue[1] + Constants.Redis.SEPARATOR
                                + newValue[2] + Constants.Redis.SEPARATOR
                                + newValue[3] + Constants.Redis.SEPARATOR
                                + newValue[4]
                );
            }
        }
        jedis.close();
    }

    @Override
    public void removePersonsFromList(long id) {

        for (int count = 0; jedis.lindex(Constants.Redis.KEY, count) != null; count++) {

            String read = jedis.lindex(Constants.Redis.KEY, count);
            String[] arrayRead = read.split(Constants.Redis.REGEX);

            if (Integer.parseInt(arrayRead[0]) == id) {
                jedis.lrem(Constants.Redis.KEY, count, read);
            }
        }
        jedis.close();
    }
}


