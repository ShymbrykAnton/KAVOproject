package blogic.filetype.executor;

import blogic.model.Person;

import java.io.IOException;
import java.util.List;

public interface Executable {
        void write(String fileName, List<Person> personList) throws IOException;

        List<Person> read(String fileName) throws IOException;

    }


