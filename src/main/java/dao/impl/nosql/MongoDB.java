package dao.impl.nosql;

import blogic.model.Person;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import dao.IDatabaseController;
import org.bson.Document;
import org.bson.conversions.Bson;
import util.Constants;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class MongoDB implements IDatabaseController {

    private MongoClient getMongoClient() {
        return new MongoClient(Constants.MongoDB.HOST, Constants.MongoDB.PORT);
    }

    private MongoCollection<Document> getCollection() {
        return getMongoClient()
                .getDatabase(Constants.MongoDB.NAME_DB)
                .getCollection(Constants.MongoDB.COLLECTION_NAME);
    }

    @Override
    public void addToDatabase(Person person) {
        Document document = new Document();

        document.put(Constants.MongoDB.ID_FIELD, person.getId());
        document.put(Constants.MongoDB.FIRST_NAME_FIELD, person.getFName());
        document.put(Constants.MongoDB.LAST_NAME_FIELD, person.getLName());
        document.put(Constants.MongoDB.AGE_FIELD, person.getAge());
        document.put(Constants.MongoDB.CITY_FIELD, person.getCity());

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

            person.setId(document.getLong(Constants.MongoDB.ID_FIELD));
            person.setFName(document.getString(Constants.MongoDB.FIRST_NAME_FIELD));
            person.setLName(document.getString(Constants.MongoDB.LAST_NAME_FIELD));
            person.setAge((byte) document.getInteger(Constants.MongoDB.AGE_FIELD, Constants.MongoDB.DEFAULT_VALUE));
            person.setCity(document.getString(Constants.MongoDB.CITY_FIELD));

            personList.add(person);
        }
        getMongoClient().close();

        return personList;
    }

    @Override
    public void updateDataInPerson(long id, String[] newValue) {
        Bson filter = eq(Constants.MongoDB.ID_FIELD, id);

        Bson updateOperation = and(
                set(Constants.MongoDB.FIRST_NAME_FIELD, newValue[1]),
                set(Constants.MongoDB.LAST_NAME_FIELD, newValue[2]),
                set(Constants.MongoDB.AGE_FIELD, Integer.parseInt(newValue[3])),
                set(Constants.MongoDB.CITY_FIELD, newValue[4]));

        getCollection().updateOne(filter, updateOperation);

        getMongoClient().close();
    }

    @Override
    public void removePersonsFromList(long id) {
        Bson filter = eq(Constants.MongoDB.ID_FIELD, id);
        getCollection().deleteOne(filter);
        getMongoClient().close();
    }
}
