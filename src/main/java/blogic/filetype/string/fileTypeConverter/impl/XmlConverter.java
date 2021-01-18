package blogic.filetype.string.fileTypeConverter.impl;

import blogic.filetype.string.fileTypeConverter.IFileTypeConverter;
import blogic.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlConverter implements IFileTypeConverter {
    private final XmlMapper xmlMapper;

    public XmlConverter() {
        xmlMapper = new XmlMapper();
    }

    @Override
    public String getStrFromPersons(List<Person> persons) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            xmlMapper.writeValue(byteArrayOutputStream, persons);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();
    }

    @Override
    public List<Person> getPersonsFromString(String strPersons) {
        List<Person> personList = new ArrayList<>();
        try {
            personList = xmlMapper.readValue(strPersons, new TypeReference<List<Person>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return personList;
    }
}
