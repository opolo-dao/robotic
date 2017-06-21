package lv.challenge.domain.competitions;

/**
 * Created by Daniil on 30.05.2017.
 */
public enum CompetitionType {
    miniSumo, freeLabyrinth, freeFolkrace, freeLinefollower, legoSumo, legoLabyrinth, legoLinefollower, legoFolkrace;

    Class<?> getRelatedClass() {
        switch (this) {
            case miniSumo:
                return lv.challenge.domain.competitions.MiniSumo.class;
            case legoSumo:
                return lv.challenge.domain.competitions.LegoSumo.class;
            case freeLinefollower:
                return lv.challenge.domain.competitions.FreeLinefollower.class;
            case legoLinefollower:
                return lv.challenge.domain.competitions.LegoLinefollower.class;
            case freeLabyrinth:
                return lv.challenge.domain.competitions.FreeLabyrinth.class;
            case legoLabyrinth:
                return lv.challenge.domain.competitions.LegoLabyrinth.class;
            case freeFolkrace:
                return lv.challenge.domain.competitions.FreeFolkrace.class;
            case legoFolkrace:
                return lv.challenge.domain.competitions.LegoFolkrace.class;
        }
        return null;
    }

    public String goodLook() {
        String str = super.toString();
        int capPos = 0;
        while(capPos < str.length() && Character.isLowerCase(str.charAt(capPos))){
            capPos++;
        }

        return Character.toUpperCase(str.charAt(0))+str.substring(1,capPos) +" "+ str.substring(capPos);
    }
}
