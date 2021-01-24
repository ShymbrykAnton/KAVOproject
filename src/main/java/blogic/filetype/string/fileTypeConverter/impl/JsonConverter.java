package blogic.filetype.string.fileTypeConverter.impl;

import blogic.filetype.string.fileTypeConverter.IFileTypeConverter;
import blogic.model.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonConverter implements IFileTypeConverter {

    private final Gson json;

    public JsonConverter() {
        json = new Gson();
    }

    @Override
    public String getStrFromPersons(List<Person> persons) {
        return json.toJson(persons);
    }

    @Override
    public List<Person> getPersonsFromString(String strPersons) {
        Type collectionType = new TypeToken<List<Person>>() {
        }.getType();
        return json.fromJson(strPersons, collectionType);
    }
}