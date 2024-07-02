package PMoonMod.events;

import PMoonMod.PMoonMod;
import basemod.abstracts.events.PhasedEvent;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public abstract class PMoonPhasedEvent extends PhasedEvent {

    protected String            eventImageName;
    protected String[]          NAMES;
    protected String[]          DESCRIPTIONS;
    protected String[]          OPTIONS;
    protected String[]          encounters;

    public PMoonPhasedEvent(String ID, String eventImageName, String[] encounters) {
        super(ID, "", null);

        if (!CardCrawlGame.isInARun()) return;

        this.eventImageName = PMoonMod.eventPath(eventImageName);
        this.encounters = encounters;

        initializeStrings();
        initializeEvent();
    }

    protected void initializeStrings() {
    }

    protected void initializeEvent() {
        this.title =            NAMES[0];
    }

    protected void playDefaultBgm() {
    }

    protected void playCustomBgm(String musicName) {
        CardCrawlGame.music.silenceBGM();
        CardCrawlGame.music.fadeOutTempBGM();
        CardCrawlGame.music.playTempBGM(PMoonMod.bgmPath(musicName));
    }

    protected void changeImageDefault() {
    }

    protected void changeImage(String imageName) {
        this.imageEventText.loadImage(PMoonMod.eventPath(imageName));
    }

    protected void changeName(int index) {
        this.title = NAMES[index];
    }

    protected void giveRelicWithChoice(AbstractRelic relic) {
        AbstractDungeon.getCurrRoom().addRelicToRewards(relic);
        AbstractDungeon.combatRewardScreen.open();
    }

    protected void giveRelicWithoutChoice(AbstractRelic relic) {
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(
                (float) Settings.WIDTH / 2,
                (float) Settings.HEIGHT / 2,
                relic);
    }

    protected void onOpenMap() {
        CardCrawlGame.music.fadeOutTempBGM();
        CardCrawlGame.music.unsilenceBGM();
        openMap();
    }
}
