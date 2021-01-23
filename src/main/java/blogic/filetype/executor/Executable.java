package blogic.filetype.executor;

import blogic.model.Person;
import com.sun.org.apache.bcel.internal.classfile.ConstantString;
import util.Constants;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import blogic.model.Person;

public interface Executable {

        void write(String fileName, List<Person> personList) throws IOException;

        List<Person> read(String fileName) throws IOException;

        void delete(long id) throws IOException, ClassNotFoundException;

    default List<Person> createPerson(List<Person> personList) throws IOException {
        Scanner scanner=new Scanner(System.in);
        int count = 0;
        Person person = new Person();
        System.out.println(Constants.Cmd.ENTER_ID);
        person.setId(scanner.nextInt());
        for (Person p : personList) {
            if (p.getId() == person.getId()) {
                count++;
                updatePerson(personList,p.getId());

            }
        }
        if (count == 0) {
            System.out.println(Constants.Cmd.ENTER_FIRST_NAME);
            person.setFName(scanner.next());
            System.out.println(Constants.Cmd.ENTER_LAST_NAME);
            person.setLName(scanner.next());
            System.out.println(Constants.Cmd.ENTER_CITY);
            person.setCity(scanner.next());
            System.out.println(Constants.Cmd.ENTER_AGE);
            person.setAge(scanner.nextInt());

            personList.add(person);
            count++;
        }

        return personList;
    }

        default List<Person> updatePerson(List<Person> personList,long id) throws IOException {
        Scanner scanner=new Scanner(System.in);

        int count = 0;
        for (Person p : personList) {
            if (count == 0) {
                if (p.getId() == id) {
                    p.setId(id);
                    System.out.println(Constants.Cmd.ENTER_FIRST_NAME);
                    p.setFName(scanner.next());
                    System.out.println(Constants.Cmd.ENTER_LAST_NAME);
                    p.setLName(scanner.next());
                    System.out.println(Constants.Cmd.ENTER_CITY);
                    p.setCity(scanner.next());
                    System.out.println(Constants.Cmd.ENTER_AGE);
                    p.setAge(scanner.nextInt());
                    count++;
                } }else if (count <= 0) {
                System.out.println("Person is not found!");
            }
        }  return personList;}
        default List<Person> deleteOnePerson(List<Person> personList) throws IOException {

        Scanner scanner=new Scanner(System.in);
                int count = 0;
                Person personForDelete = new Person();
                System.out.println(Constants.Cmd.ENTER_ID);
                int id = scanner.nextInt();

                for (Person p : personList) {
                    if (p.getId() == id) {
                        personForDelete = p;
                        count++;

                    }

                }
                if (count == 0) {
                    System.out.println("Person not found!");


                }
                personList.remove(personForDelete);
                return personList;
    }}




