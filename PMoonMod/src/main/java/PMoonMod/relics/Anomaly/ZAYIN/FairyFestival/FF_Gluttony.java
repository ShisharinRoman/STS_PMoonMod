package PMoonMod.relics.Anomaly.ZAYIN.FairyFestival;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FF_Gluttony extends PMoonRelic
{
    private static final String NAME =              "FF:Gluttony";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.ZAYIN;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    public FF_Gluttony() {
        super(NAME, "Gluttony", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atTurnStart() {
        AbstractCreature m = AbstractDungeon.getRandomMonster();
        // TODO Food status
        flash();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0]);
    }
}
