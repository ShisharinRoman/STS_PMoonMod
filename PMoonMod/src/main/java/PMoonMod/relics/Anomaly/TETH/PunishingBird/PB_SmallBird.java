package PMoonMod.relics.Anomaly.TETH.PunishingBird;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;

public class PB_SmallBird extends PMoonRelic
{
    private static final String NAME =              "PB:SmallBird";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.CLINK;

    private static final int INCREASE_EVASION = 1;

    public PB_SmallBird() {
        super(NAME, "SmallBird", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {
        // TODO Evasion Increase
        flash();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], INCREASE_EVASION);
    }
}
