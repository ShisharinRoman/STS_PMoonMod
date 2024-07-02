package PMoonMod.events;

import basemod.abstracts.events.phases.CombatPhase;
import basemod.abstracts.events.phases.TextPhase;
import PMoonMod.events.System.EventAnomalyStrings;
import PMoonMod.localization.LocalizedStringsPatch;
import PMoonMod.events.System.SaveSystemEvent;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public abstract class PMoonAnomalyEvent extends PMoonPhasedEvent {

    protected enum DefaultPhases {
        START,
        LEAVE,
        ANOMALY_LOG,
        ENTER_CAMERA
    }

    protected enum DefaultOptions {
        OPEN_THE_ANOMALY_LOG,
        ENTER_CAMERA,
        LEAVE,
        OPEN_MAP
    }

    protected enum DefaultDescriptions {
        START,
        LEAVE,
        ENTER_CAMERA
    }

    private boolean[]   researches;
    protected int       researchesCount =   0;

    protected boolean   isSeenLogAnomaly =      false;
    protected boolean   isForceEnterCamera =    false;

    private EventAnomalyStrings eventAnomalyStrings;
    protected String[]          ANOMALY_LOGS;
    public String[]             DEFAULT_DESCRIPTIONS;
    public String[]             DEFAULT_OPTIONS;

    public PMoonAnomalyEvent(String ID, String eventImageName, String[] encounters) {
        this(ID, eventImageName, 0, encounters);
    }

    public PMoonAnomalyEvent(String ID, String eventImageName, int researchesCount, String[] encounters) {
        super(ID, eventImageName, encounters);

        if (!CardCrawlGame.isInARun()) return;

        this.researchesCount = researchesCount;

        initializeEvent();
    }

    @Override
    protected void initializeStrings() {

        eventAnomalyStrings =  LocalizedStringsPatch.getEventAnomalyString(id);

        NAMES =                eventAnomalyStrings.NAMES;

        DEFAULT_DESCRIPTIONS = eventAnomalyStrings.DEFAULT_DESCRIPTIONS;
        DESCRIPTIONS =         eventAnomalyStrings.DESCRIPTIONS;

        ANOMALY_LOGS =         eventAnomalyStrings.ANOMALY_LOGS;

        DEFAULT_OPTIONS =      eventAnomalyStrings.DEFAULT_OPTIONS;
        OPTIONS =              eventAnomalyStrings.OPTIONS;
    }

    @Override
    protected void initializeEvent() {

        super.initializeEvent();

        loadResearches();
        changeImageDefault();
        playDefaultBgm();

        if (isForceEnterCamera) {
            forceEnterCameraPath();
        }
        else {
            defaultPath();
        }

        transitionKey(DefaultPhases.START);
    }

    @Override
    protected void playDefaultBgm() {
        playCustomBgm("LobotomyDefaultEvent.ogg");
    }

    @Override
    protected void changeImageDefault() {
        changeImage("LobotomyDefault.png");
    }

    protected void loadResearches() {
        researches = SaveSystemEvent.loadResearches(id, researchesCount);
    }

    private String descAnomalyLog() {
        StringBuilder   res =               new StringBuilder(ANOMALY_LOGS[0]);
        String          separator =         " NL ";
        String          unknownResearch =   "--- Условия не соблюдены ---";

        for (int i = 0; i < researches.length; i++) {
            if (researches[i]) {
                res.append(separator).append(ANOMALY_LOGS[i + 1]);
            } else {
                res.append(separator).append(unknownResearch);
            }
        }

        return res.toString();
    }

    private void defaultPath() {
        registerPhase(
                DefaultPhases.START,
                new TextPhase(DEFAULT_DESCRIPTIONS[DefaultDescriptions.START.ordinal()]).
                        addOption(DEFAULT_OPTIONS[DefaultOptions.ENTER_CAMERA.ordinal()], (i)->onEnterCamera()).
                        addOption(DEFAULT_OPTIONS[DefaultOptions.OPEN_THE_ANOMALY_LOG.ordinal()], (i)->onOpenLog()).
                        addOption(DEFAULT_OPTIONS[DefaultOptions.LEAVE.ordinal()], (i)->onLeave())
        );
        registerPhase(
                DefaultPhases.ANOMALY_LOG,
                new TextPhase(descAnomalyLog()).
                        addOption(DEFAULT_OPTIONS[DefaultOptions.ENTER_CAMERA.ordinal()], (i)->onEnterCamera()).
                        addOption(DEFAULT_OPTIONS[DefaultOptions.LEAVE.ordinal()], (i)->onLeave())
        );
        registerPhase(
                DefaultPhases.LEAVE,
                new TextPhase(DEFAULT_DESCRIPTIONS[DefaultDescriptions.LEAVE.ordinal()]).
                        addOption(DEFAULT_OPTIONS[DefaultOptions.OPEN_MAP.ordinal()], (i)->onOpenMap())
        );

        if (encounters == null) return;

        for (String encounter : encounters) {
            registerPhase(
                    encounter,
                    new CombatPhase(encounter).setNextKey("after/" + encounter)
            );
        }
    }

    private void forceEnterCameraPath() {
        registerPhase(
                DefaultPhases.START,
                new TextPhase(DEFAULT_DESCRIPTIONS[DefaultDescriptions.START.ordinal()]).
                        addOption(DEFAULT_OPTIONS[DefaultOptions.ENTER_CAMERA.ordinal()], (i)->onEnterCamera()).
                        addOption(DEFAULT_OPTIONS[DefaultOptions.OPEN_THE_ANOMALY_LOG.ordinal()], (i)->onOpenLog())
        );
        registerPhase(
                DefaultPhases.ANOMALY_LOG,
                new TextPhase(descAnomalyLog()).
                        addOption(DEFAULT_OPTIONS[DefaultOptions.ENTER_CAMERA.ordinal()], (i)->onEnterCamera())
        );
        registerPhase(
                DefaultPhases.LEAVE,
                new TextPhase(DEFAULT_DESCRIPTIONS[DefaultDescriptions.LEAVE.ordinal()]).
                        addOption(DEFAULT_OPTIONS[DefaultOptions.OPEN_MAP.ordinal()], (i)->onOpenMap())
        );
    }

    protected void onEnterCamera() {
        changeImage(eventImageName);
        transitionKey(DefaultPhases.ENTER_CAMERA);
    }

    protected void onOpenLog() {
        isSeenLogAnomaly = true;
        transitionKey(DefaultPhases.ANOMALY_LOG);
    }

    protected void onLeave() {
        changeImageDefault();
        playDefaultBgm();
        transitionKey(DefaultPhases.LEAVE);
    }
}
