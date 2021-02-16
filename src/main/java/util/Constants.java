package util;

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
        public final static String YOUR_DB = "Your database: %s";
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
        public final static String EXIT_CONFORMATION_WOLFS = "REMEMBER, BROTHER! IF A WOLF HOWLS IN PAIN, THEN IT HURTS.";
        public final static String LABEL_FORMAT = "%s: ";
        public static final String ILLEGAL_PERSON_ID_IS_BUSY = "This id is busy.";
        public static final String ILLEGAL_PERSON_ID = "Id cannot be negative.";
        public static final String PERSON_ID_NOT_FOUND = "Id not found.";
        public static final String ID_FIELD_IS_EMPTY = "Id field is empty.";
        public static final String ILLEGAL_AGE = "Incorrect age, max age = 100.";
        public static final String ERROR = "Error";
    }

    public static class Errors {

    }

    public static class DataSource {
        public final static String MY_SQL = "MySQL";
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

    public static class SQLBaseQueries {
        public final static String INSERT = "INSERT INTO persons (id,first_name,last_name,age,city) VALUES (?,?,?,?,?)";
        public final static String SELECT = "SELECT * FROM persons";
        public final static String UPDATE = "UPDATE persons SET first_name=?,last_name=?,age=?,city=? WHERE id = ?";
        public final static String DELETE = "DELETE FROM persons WHERE id = ?";
        public final static String CLEAR_ALL = "DELETE FROM persons WHERE id>0";
    }

    public static class H2 {
        public final static String CONNECTION_URL = "jdbc:h2:tcp://localhost/~/H2";
        public final static String LOGIN_DB = "sa";
        public final static String PASSWORD_DB = "";
        public final static String DRIVER_NAME = "org.h2.Driver";
    }

    public static class MySQL {
        public final static String CONNECTION_URL = "jdbc:mysql://localhost:3306/public";
        public final static String LOGIN_DB = "root";
        public final static String PASSWORD_DB = "1234";
        public final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    }

    public static class PostgreSQL {
        public final static String CONNECTION_URL = "jdbc:postgresql://localhost:5432/person";
        public final static String LOGIN_DB = "postgres";
        public final static String PASSWORD_DB = "11.04shekel1948";
        public final static String DRIVER_NAME = "org.postgresql.Driver";
    }

    public static class Cassandra {
        public final static String LOGIN_DB = "localhost";
        public final static String KEYSPACE = "cassandra";

        public final static String INSERT = "INSERT INTO persons (id,first_name,last_name,age,city) VALUES (?,?,?,?,?)";
        public final static String SELECT = "SELECT * FROM persons";
        public final static String UPDATE = "UPDATE persons SET first_name=?,last_name=?,age=?,city=? WHERE id = ?";
        public final static String DELETE = "DELETE FROM persons WHERE id = ?";

        public final static String ID_FIELD = "id";
        public final static String FIRST_NAME_FIELD = "first_name";
        public final static String LAST_NAME_FIELD = "last_name";
        public final static String AGE_FIELD = "age";
        public final static String CITY_FIELD = "city";
    }

    public static class GraphDB {
        public final static String CONNECTION_URL = "bolt://localhost:7687";
        public final static String LOGIN_DB = "neo4j";
        public final static String PASSWORD_DB = "1234";


        public final static String CREATE = "CREATE (n:persons {id: $id, fname: $fname, lname: $lname, age: $age, city: $city})";
        public final static String SELECT = "MATCH (n) RETURN n.id, n.fname, n.lname, n.age, n.city";
        public final static String UPDATE = "MATCH (n { id: $id }) SET n.lname = $lname, n.fname = $fname, n.age = $age, n.city = $city";
        public final static String DELETE = "MATCH (n { id: $id }) DETACH DELETE n";

        public final static String ID_FIELD = "id";
        public final static String FIRST_NAME_FIELD = "fname";
        public final static String LAST_NAME_FIELD = "lname";
        public final static String AGE_FIELD = "age";
        public final static String CITY_FIELD = "city";

    }

    public static class MongoDB {
        public final static String HOST = "localhost";
        public final static int PORT = 27017;
        public final static int DEFAULT_VALUE = 0;
        public final static String NAME_DB = "mongodb";
        public final static String COLLECTION_NAME = "persons";

        public final static String ID_FIELD = "id";
        public final static String FIRST_NAME_FIELD = "fname";
        public final static String LAST_NAME_FIELD = "lname";
        public final static String AGE_FIELD = "age";
        public final static String CITY_FIELD = "city";
    }

    public static class Redis {
        public static final String JEDIS = "localhost";
        public static final String KEY = "persons";
        public static final String SEPARATOR = "\u27a1";
        public static final String REGEX = "\\u27a1";
    }

}
