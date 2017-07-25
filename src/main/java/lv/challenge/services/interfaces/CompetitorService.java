package lv.challenge.services.interfaces;

import lv.challenge.database.CompetitorsDAO;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by Daniil on 23.04.2017.
 */
@Transactional
public abstract class CompetitorService<T> {
    protected CompetitorsDAO<T> dao;
    protected Validator<T> validator;

    public Map<String, String> register(T entity) {
        try {
            validator.validate(entity, Validator.Purpose.CREATE);

        } catch (ValidationError validationError) {
            return validationError.getErrorsMap();
        }
        dao.save(entity);
        return new HashMap<>();
    }
    public Map<String, String> saveWithoutValidation(T entity) {
        dao.save(entity);
        return new HashMap<>();
    }

    public List<T> getAll() {
        List<T> list;
        list = dao.getAll();
        return list;
    }

    public List<T> getAllByTeamId(Integer teamId) {
        return dao.getByTeamId(teamId);
    }

    public Map<String, String> update(T entity) {
        try {
            validator.validate(entity, Validator.Purpose.UPDATE);
        } catch (ValidationError validationError) {
            return validationError.getErrorsMap();
        }
        dao.update(entity);
        return new HashMap<>();
    }

    public void updateWithoutValidation(T entity) {
        dao.update(entity);
    }

    public Map<String, String> validate(T entity, Validator.Purpose purpose) {
        try {
            validator.validate(entity, purpose);
        } catch (ValidationError validationError) {
            return validationError.getErrorsMap();
        }
        return new HashMap<>();
    }

    public Optional<T> getById(Integer id) {
        return dao.getById(id);
    }

    public Optional<T> loadById(Integer id) {
        return dao.loadById(id);
    }

    public abstract Optional<T> getByIdWithCollections(Integer id);

    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    public Set<T> getByMultipleIds(List<Integer> ids) {
        return dao.getByMultipleIds(ids);
    }
}
