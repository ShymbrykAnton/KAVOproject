package blogic.filetype.string.fileTypeConverter.impl;

import blogic.filetype.string.fileTypeConverter.IFileTypeConverter;
import blogic.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static util.Constants.View.*;


public class CsvConverter implements IFileTypeConverter {
    @Override
    public String getStrFromPersons(List<Person> persons) {
        String[][] values = new String[persons.size() + 1][5];
        values[0] = new String[]{ID, FIRST_NAME, LAST_NAME, AGE, CITY};
        int i = 1;
        for (Person person : persons) {
            values[i] = new String[]{String.valueOf(person.getId()), person.getFName(),
                    person.getLName(), String.valueOf(person.getAge()), person.getCity()};
            i++;
        }
        return Arrays.stream(values)
                .map(this::convertToCSV)
                .collect(Collectors.joining("\n"));
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    @Override
    public List<Person> getPersonsFromString(String strPersons) {
        List<Person> persons = new ArrayList<>();
        String[] personsStr = strPersons.split("\n");
        List<String[]> personsArray = new ArrayList<>();
        for (String s : personsStr) {
            personsArray.add(s.split(","));
        }
        int i = 0;
        if (personsArray.get(0)[0].equals(ID)) {
            i = 1;
        }
        while (i < personsArray.size()) {
            String[] value = personsArray.get(i);
            Person person = new Person(Long.parseLong(value[0]), value[1], value[2], Integer.parseInt(value[3]), value[4]);
            persons.add(person);
            i++;
        }
        return persons;
    }
}
