package PMoonMod.relics.Anomaly.TETH.Ppodae;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;

public class PP_SOCUTE extends PMoonRelic
{
    private static final String NAME =              "PP:SOCUTE";
    private static final RelicTier RARITY =         RelicTier.SPECIAL;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.ZAYIN;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    public PP_SOCUTE() {
        super(NAME, "SOCUTE", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0]);
    }
}
