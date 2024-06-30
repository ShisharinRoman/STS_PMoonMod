package PMoonMod.relics.Anomaly.ZAYIN.WellCheers;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class WC_Soda extends PMoonRelic
{
    private static final String NAME =              "WC:Soda";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.ZAYIN;
    private static final LandingSound SOUND =       LandingSound.CLINK;

    private static final int MAX_HEALTH_INCREASE = 5;

    public WC_Soda() {
        super(NAME, "Soda", RARITY, DANGER_LEVEL, SOUND);
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
