package lv.challenge.services.linefollower;

import lv.challenge.database.CompetitionDAO;
import lv.challenge.domain.competitions.FreeLinefollower;
import lv.challenge.services.interfaces.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniil on 01.06.2017.
 */
@Component
public class FreeLinefollowerService extends CompetitionService<FreeLinefollower> {
    @Autowired
    public FreeLinefollowerService(CompetitionDAO<FreeLinefollower> dao) {
    this.dao = dao;
    }

    @Override
    protected List<List<String>> prepareTable(List<FreeLinefollower> records) {
        List<List<String>> result = new ArrayList<>();
        for (FreeLinefollower record : records) {
            List<String> stringRecord = new ArrayList<>();
            stringRecord.add(record.getRobot().getName());
            stringRecord.add(Integer.toString(record.getRobot().getRegistered_number()));
            stringRecord.add(String.valueOf(record.getSTime()));
            stringRecord.add(record.getDate().toString());
            result.add(stringRecord);
        }
        return result;
    }
}
