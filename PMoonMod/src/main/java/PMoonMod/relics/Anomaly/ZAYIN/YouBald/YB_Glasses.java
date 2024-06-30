package PMoonMod.relics.Anomaly.ZAYIN.YouBald;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;

public class YB_Glasses extends PMoonRelic
{
    private static final String NAME =              "YB:Glasses";
    private static final RelicTier RARITY =         RelicTier.SPECIAL;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.ALEPH;
    private static final LandingSound SOUND =       LandingSound.CLINK;

    public YB_Glasses() {
        super(NAME, "Glasses", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0]);
    }
}

