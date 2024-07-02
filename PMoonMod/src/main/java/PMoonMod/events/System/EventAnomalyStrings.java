package PMoonMod.events.System;

import com.megacrit.cardcrawl.localization.LocalizedStrings;

public class EventAnomalyStrings {

    private static final int MOCK_SIZE = 12;

    public String[] NAMES;

    public String[] DEFAULT_DESCRIPTIONS;
    public String[] DESCRIPTIONS;

    public String[] ANOMALY_LOGS;

    public String[] DEFAULT_OPTIONS;
    public String[] OPTIONS;

    public EventAnomalyStrings() {
    }

    public static EventAnomalyStrings getMockEventAnomalyString() {

        EventAnomalyStrings retVal =    new EventAnomalyStrings();

        retVal.NAMES =                  LocalizedStrings.createMockStringArray(MOCK_SIZE);
        retVal.ANOMALY_LOGS =           LocalizedStrings.createMockStringArray(MOCK_SIZE);
        retVal.DESCRIPTIONS =           LocalizedStrings.createMockStringArray(MOCK_SIZE);
        retVal.OPTIONS =                LocalizedStrings.createMockStringArray(MOCK_SIZE);
        retVal.DEFAULT_DESCRIPTIONS =   LocalizedStrings.createMockStringArray(MOCK_SIZE);
        retVal.DEFAULT_OPTIONS =        LocalizedStrings.createMockStringArray(MOCK_SIZE);

        return retVal;
    }
}
