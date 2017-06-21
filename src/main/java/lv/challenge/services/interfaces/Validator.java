package lv.challenge.services.interfaces;

/**
 * Created by Daniil on 20.04.2017.
 */
public interface Validator<T> {
enum Purpose{CREATE, UPDATE}
 void validate(T entity, Purpose purpose) throws ValidationError;
}
