package lv.challenge.database;

/**
 * Created by Daniil on 12.04.2017.
 */
public class DBException extends RuntimeException {
    public DBException(String message) {
        super(message);
    }

    public DBException(Throwable t){
        super(t);
    }
}
