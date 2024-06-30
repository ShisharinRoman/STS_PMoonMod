package PMoonMod.relics.Anomaly.TETH.BeautyAndTheBeast;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BAB_Beauty extends PMoonRelic
{
    private static final String NAME =              "BAB:Beauty";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.FLAT;

    private static final int STATUS_AMT = 2;

    public BAB_Beauty() {
        super(NAME, "Beauty", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {
        targetAllMonsters(this::effectToMonster);
        flash();
    }

    private Void effectToMonster(AbstractMonster m) {
        // TODO Gain enemy cards
        return null;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], STATUS_AMT);
    }

}
