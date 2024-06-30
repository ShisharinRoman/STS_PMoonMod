package PMoonMod.relics.Anomaly.ZAYIN.YouBald;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;

public class YB_Glock extends PMoonRelic
{
    private static final String NAME =              "YB:Glock";
    private static final RelicTier RARITY =         RelicTier.SPECIAL;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.ZAYIN;
    private static final LandingSound SOUND =       LandingSound.FLAT;

    private static final float SHAKE_DURATION =   5;

    public YB_Glock() {
        super(NAME, "Glock", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onActivationInRun() {
        AbstractDungeon.actionManager.addToTop(
                new ShakeScreenAction(SHAKE_DURATION, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.HIGH));
        usedUp();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0]);
    }
}

