package PMoonMod.events.ZAYIN;

import PMoonMod.events.System.SaveSystemEvent;
import basemod.abstracts.events.phases.TextPhase;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import PMoonMod.PMoonMod;
import PMoonMod.events.PMoonAnomalyEvent;
import PMoonMod.relics.Anomaly.ZAYIN.OneSinAndHundredsOfGoodDeeds.SG_Blessing;
import PMoonMod.relics.Anomaly.ZAYIN.OneSinAndHundredsOfGoodDeeds.SG_Remorse;
import PMoonMod.relics.Anomaly.ZAYIN.OneSinAndHundredsOfGoodDeeds.SG_TheCrownOfThorns;

import java.util.ArrayList;

import static PMoonMod.PMoonMod.makeID;

public class OneSinAndHundredsOfGoodDeedsEvent extends PMoonAnomalyEvent {

    public static final String ID =                 makeID("OneSinAndHundredsOfGoodDeedsEvent");
    private static final String EVENT_IMAGE_NAME =  "OneSinAndHundredsOfGoodDeeds.png";
    private static final int RESEARCH_COUNT =       4;

    enum Phases {
        COME_CLOSER,
        PRAY,
        FIND_SELF,
        FIND_NOTHING,
        CONFESS_SINS,
        GIFT,
        PUNISHMENT
    }

    enum Descriptions {
        COME_CLOSER,
        PRAY,
        FIND_SELF,
        FIND_NOTHING,
        CONFESS_SINS,
        GIFT,
        PUNISHMENT
    }

    enum Options {
        COME_CLOSER,
        PRAY,
        SELF,
        NOTHING,
        CONFESS_SINS,
        START_CONFESSION,
        GIFT,
        PUNISHMENT
    }

    private static final int DEFAULT_CHANCE_TO_LIE =    10;
    private static final int DAMAGE =                   5;
    private static final int HEAL =                     20;
    private static final int MAX_HP_INCREASE =          5;

    public OneSinAndHundredsOfGoodDeedsEvent() {
        super(ID, EVENT_IMAGE_NAME, RESEARCH_COUNT, null);
    }

    @Override
    protected void initializeEvent() {
        super.initializeEvent();

        enterCameraPhase();
        prayPath();
        confessPath();

        if (researchesCount >= 2) changeName(1);

    }

    @Override
    protected void onEnterCamera() {

        playCustomBgm("OneSinAndHundredsOfGoodDeeds.ogg");
        super.onEnterCamera();

    }

    private void enterCameraPhase() {

        String descEnterCamera =    DEFAULT_DESCRIPTIONS[DefaultPhases.ENTER_CAMERA.ordinal()];
        String optCloser =          OPTIONS[Options.COME_CLOSER.ordinal()];
        String optLeave =           DEFAULT_OPTIONS[DefaultOptions.LEAVE.ordinal()];

        registerPhase(
                DefaultPhases.ENTER_CAMERA,
                new TextPhase(descEnterCamera).
                        addOption(optCloser, (i)-> onComeCloser()).
                        addOption(optLeave, (i)->onLeave())
        );

        String descCloser = DESCRIPTIONS[Descriptions.COME_CLOSER.ordinal()];
        String optPray =    OPTIONS[Options.PRAY.ordinal()];
        String optConfess = OPTIONS[Options.CONFESS_SINS.ordinal()];

        registerPhase(
                Phases.COME_CLOSER,
                new TextPhase(descCloser).
                        addOption(optPray, (i)-> onPray()).
                        addOption(optConfess, (i)-> onConfessSins()).
                        addOption(optLeave, (i)->transitionKey(DefaultOptions.LEAVE))
        );
    }

    private void onComeCloser() {
        transitionKey(Phases.COME_CLOSER);
    }

    private void onPray() {
        transitionKey(Phases.PRAY);
    }

    private void onConfessSins() {
        transitionKey(Phases.CONFESS_SINS);
    }

    private void prayPath() {

        String descPray =   DESCRIPTIONS[Descriptions.PRAY.ordinal()];
        String optSelf =    OPTIONS[Options.SELF.ordinal()];
        String optNothing = OPTIONS[Options.NOTHING.ordinal()];

        registerPhase(
                Phases.PRAY,
                new TextPhase(descPray).
                        addOption(optSelf, (i)->onFindSelf()).
                        addOption(optNothing, (i)->onFindNothing())
        );

        String descSelf =   DESCRIPTIONS[Descriptions.FIND_SELF.ordinal()];
        String optLeave =   DEFAULT_OPTIONS[DefaultOptions.LEAVE.ordinal()];

        registerPhase(
                Phases.FIND_SELF,
                new TextPhase(descSelf).
                        addOption(optLeave, (i)->onLeave())
        );

        String descNothing =   DESCRIPTIONS[Descriptions.FIND_NOTHING.ordinal()];

        registerPhase(
                Phases.FIND_NOTHING,
                new TextPhase(descNothing).
                        addOption(optLeave, (i)->onLeave())
        );
    }

    private void onFindSelf() {

        AbstractDungeon.player.increaseMaxHp(MAX_HP_INCREASE, true);
        transitionKey(Phases.FIND_SELF);

    }

    private void onFindNothing() {
        int randomRelic = AbstractDungeon.eventRng.random(1,3);

        switch (randomRelic) {
            case 1:
                giveRelicWithChoice(new SG_Remorse());
                break;
            case 2:
                giveRelicWithChoice(new SG_Blessing());
                break;
            case 3:
            default:
                giveRelicWithChoice(new SG_TheCrownOfThorns());
                break;
        }

        SaveSystemEvent.setResearch(id, 0);

        transitionKey(Phases.FIND_NOTHING);
    }

    private void confessPath() {

        String descConfessSins =    DESCRIPTIONS[Descriptions.CONFESS_SINS.ordinal()];
        String optConfessSins =     OPTIONS[Options.START_CONFESSION.ordinal()];

        registerPhase(
                Phases.CONFESS_SINS,
                new TextPhase(descConfessSins).
                        addOption(optConfessSins, (i)->onStartConfession())
        );

        String descGift =   DESCRIPTIONS[Descriptions.GIFT.ordinal()];
        String optGift =    OPTIONS[Options.GIFT.ordinal()];

        registerPhase(
                Phases.GIFT,
                new TextPhase(descGift).
                        addOption(optGift, (i)->onGift())
        );

        String descPunishment = DESCRIPTIONS[Descriptions.PUNISHMENT.ordinal()];
        String optPunishment =  OPTIONS[Options.PUNISHMENT.ordinal()];

        registerPhase(
                Phases.PUNISHMENT,
                new TextPhase(descPunishment).
                        addOption(optPunishment, (i)->onPunishment())
        );
    }

    private void onStartConfession() {
        int chance =        AbstractDungeon.eventRng.random(1, 100);
        int chanceToLie =   DEFAULT_CHANCE_TO_LIE;

        ArrayList<AbstractRelic> relics = AbstractDungeon.player.relics;

        for (AbstractRelic relic : relics) {
            if (relic.relicId.contains(PMoonMod.modID) && relic.relicId.contains("SG:")) {
                chanceToLie = (int)((float)chanceToLie * 0.8F);
            }
            if (relic.relicId.contains(PMoonMod.modID) && relic.relicId.contains("PD:")) {
                chanceToLie = 100;
                SaveSystemEvent.setResearch(id, 3);
                break;
            }
        }

        if (chance <= chanceToLie) {
            SaveSystemEvent.setResearch(id, 2);
            transitionKey(Phases.PUNISHMENT);
        } else {
            SaveSystemEvent.setResearch(id, 1);
            transitionKey(Phases.GIFT);
        }
    }

    private void onGift() {
        AbstractDungeon.player.heal(HEAL, true);
        onLeave();
    }

    private void onPunishment() {
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, false);
        CardCrawlGame.sound.play("ORB_LIGHTNING_EVOKE");

        int damage = Math.min(DAMAGE,  AbstractDungeon.player.currentHealth - 1);
        AbstractDungeon.player.damage(new DamageInfo(null, damage));
        onLeave();
    }
}
