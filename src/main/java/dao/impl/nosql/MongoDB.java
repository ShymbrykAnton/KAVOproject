package dao.impl.nosql;

import blogic.model.Person;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import dao.IDatabaseController;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class MongoDB implements IDatabaseController {

    private MongoClient getMongoClient() {
        return new MongoClient("localhost", 27017);
    }

    private MongoCollection<Document> getCollection() {
        return getMongoClient()
                .getDatabase("mongodb")
                .getCollection("persons");
    }

    @Override
    public void addToDatabase(Person person) {
        Document document = new Document();

        document.put("id", person.getId());
        document.put("fname", person.getFName());
        document.put("lname", person.getLName());
        document.put("age", person.getAge());
        document.put("city", person.getCity());

        getCollection().insertOne(document);

        getMongoClient().close();
    }

    @Override
    public List<Person> readFromDatabase() {
        MongoCursor<Document> documentFindIterable = getCollection().find().iterator();

        List<Person> personList = new ArrayList<>();

        Person person;
        while (documentFindIterable.hasNext()) {
            person = new Person();

            Document document = documentFindIterable.next();

            person.setId(document.getLong("id"));
            person.setFName(document.getString("fname"));
            person.setLName(document.getString("lname"));
            person.setAge((byte) document.getInteger("age", 0));
            person.setCity(document.getString("city"));

            personList.add(person);
        }
        getMongoClient().close();

        return personList;
    }

    @Override
    public void updateDataInPerson(long id, String[] newValue) {
        Bson filter = eq("id", id);

        Bson updateOperation = and(
                set("fname", newValue[1]),
                set("lname", newValue[2]),
                set("age", Integer.parseInt(newValue[3])),
                set("city", newValue[4]));

        getCollection().updateOne(filter, updateOperation);

        getMongoClient().close();
    }

    @Override
    public void removePersonsFromList(long id) {
        Bson filter = eq("id", id);
        getCollection().deleteOne(filter);
        getMongoClient().close();
    }
}
