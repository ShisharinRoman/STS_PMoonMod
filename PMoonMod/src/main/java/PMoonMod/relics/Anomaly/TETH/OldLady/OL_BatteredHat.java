package PMoonMod.relics.Anomaly.TETH.OldLady;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OL_BatteredHat extends PMoonRelic
{
    private static final String NAME =              "OL:BatteredHat";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.FLAT;

    private final int SINKING_AMT = 1;

    public OL_BatteredHat() {
        super(NAME, "BatteredHat", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atTurnStart() {
        targetAllMonsters(this::effectToMonster);
        flash();
    }

    private Void effectToMonster(AbstractMonster m) {
        // TODO Sinking
        return null;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], SINKING_AMT);
    }
}