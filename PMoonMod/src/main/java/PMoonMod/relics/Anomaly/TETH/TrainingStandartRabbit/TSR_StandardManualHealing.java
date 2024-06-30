package PMoonMod.relics.Anomaly.TETH.TrainingStandartRabbit;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class TSR_StandardManualHealing extends PMoonRelic
{
    private static final String NAME =              "TSR:StandardManualHealing";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int MAX_HEALTH_INCREASE =  10;

    public TSR_StandardManualHealing() {
        super(NAME, "StandardManualHealing", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.increaseMaxHp(MAX_HEALTH_INCREASE, true);
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], MAX_HEALTH_INCREASE);
    }
}
