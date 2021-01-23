package blogic.filetype.executor;

import blogic.model.Person;
import com.sun.org.apache.bcel.internal.classfile.ConstantString;
import util.Constants;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import blogic.model.Person;

public interface Executable {
    void create(String fileName, List<Person> persons) throws IOException;

    List<Person> read(String fileName) throws IOException, ClassNotFoundException;

    void update(long id, String valueToBeUpdated, String valueToChange) throws IOException, ClassNotFoundException;

    void delete(long id) ;
}




