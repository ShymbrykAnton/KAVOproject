package blogic.filetype.string.fileTypeConverter.impl;

import blogic.filetype.string.fileTypeConverter.IFileTypeConverter;
import blogic.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.ArrayList;
import java.util.List;

public class YamlConverter implements IFileTypeConverter {
    private final ObjectMapper mapper;

    public YamlConverter() {
        mapper = new ObjectMapper(new YAMLFactory());
    }


    @Override
    public String getStrFromPersons(List<Person> persons) {
        String perssonString = "";
        try {
            perssonString = mapper.writeValueAsString(persons);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return perssonString;
    }

    @Override
    public List<Person> getPersonsFromString(String strPersons) {
        List<Person> personList = new ArrayList<>();
        try {
            personList = mapper.readValue(strPersons, new TypeReference<List<Person>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return personList;
    }
}
