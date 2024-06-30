package PMoonMod.relics.Anomaly.TETH.ScorchedGirl;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SG_Match extends PMoonRelic
{
    private static final String NAME =              "SG:Match";
    private static final RelicTier RARITY =         RelicTier.RARE;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.FLAT;

    private static final int BURN_AMT = 3;

    public SG_Match() {
        super(NAME, "Match", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onExhaust(AbstractCard card) {
        targetAllMonsters(this::effectToMonster);
        flash();
    }

    private Void effectToMonster(AbstractMonster m) {
        // TODO Burn power
        return null;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], BURN_AMT);
    }
}