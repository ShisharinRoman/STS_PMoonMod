package PMoonMod.relics.Anomaly.TETH.Ppodae;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PP_Bone extends PMoonRelic
{
    private static final String NAME =              "PP:Bone";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.SOLID;

    public PP_Bone() {
        super(NAME, "Bone", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atTurnStart() {
        AbstractCreature m = AbstractDungeon.getRandomMonster();

        //TODO Bone status

        flash();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0]);
    }
}
