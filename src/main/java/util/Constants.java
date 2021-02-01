package util;

import blogic.model.Person;

import java.util.List;

public class Constants {

    public static class View {
        public final static String ID = "Id";
        public final static String FIRST_NAME = "Fname";
        public final static String LAST_NAME = "Lname";
        public final static String AGE = "Age";
        public final static String CITY = "City";

        public final static String CREATE = "Create";
        public final static String UPDATE = "Update";
        public final static String DELETE = "Delete";
        public final static String CLEAR_ALL = "Clear all";
        public final static String EXIT = "Exit    <3";

        public final static String BINARY = "Bin";
        public final static String JSON = "Json";
        public final static String CSV = "Csv";
        public final static String XML = "Xml";
        public final static String YAML = "Yaml";
    }

    public static class Messages {
        public final static String PROGRAM_NAME = "CRUD by KABO";
        public final static String CONFIRM_WINDOW = "Confirm window";
        public final static String FILE_CONTENTS = "File contents";
        public final static String CONTROL_PANEL = "Control panel";
        public final static String ENTER_FILENAME = "Enter filename";
        public final static String CHOOSE_FILENAME = "Choose your filename";
        public final static String MENU_HEADLINES = "Databases, File types";
        public final static String FILE_FORMAT = "%s.%s";
        public final static String YOUR_FILE = "Your file: %s";
        public final static String CLEAR_FILE = "Clearing file";
        public final static String CLEAR_WARNING = "Do you really want to delete all data in %s.";
        public final static String EXIT_CONFORMATION = "Are you sure you want to close the application?";
        public final static String LABEL_FORMAT = "%s: ";
        public static final String ILLEGAL_PERSON_ID = "This id is busy.";
        public static final String ILLEGAL_AGE = "Incorrect age, max age = 100.";
    }

    public static class Errors {

    }

    public static class DataSource {
        public final static String MY_SQL = "mysql";
        public final static String POSTGRE_SQL = "PostgreSQL";
        public final static String H2 = "H2";
        public final static String MONGO_DB = "MongoDB";
        public final static String REDIS = "Redis";
        public final static String CASSANDRA = "Cassandra";
        public final static String GRAPH_DB = "GraphDB";
        public final static String BINARY_TYPE = "bin";
        public final static String JSON_TYPE = "json";
        public final static String CSV_TYPE = "csv";
        public final static String XML_TYPE = "xml";
        public final static String YAML_TYPE = "yaml";
    }

    public static class Config {
    }
}
