package PMoonMod.relics.Anomaly.TETH.BloodBath;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BB_ScarsRelic extends PMoonRelic
{
    private static final String NAME =              "BB:Scars";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.CLINK;

    private static final int CHANCE_REDUCTION_DAMAGE =   50;
    private static final int MIN_REDUCTION =             1;
    private static final int MAX_REDUCTION =             5;

    public BB_ScarsRelic() {
        super(NAME, "Scars", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {

        if (info.type != DamageInfo.DamageType.NORMAL) return damageAmount;

        int chanceToReductionDamage = AbstractDungeon.relicRng.random(1,100);

        if (chanceToReductionDamage <= CHANCE_REDUCTION_DAMAGE) {

            int damageReduction = AbstractDungeon.relicRng.random(MIN_REDUCTION, MAX_REDUCTION) + MIN_REDUCTION;
            int playerCurrentBlock = AbstractDungeon.player.currentBlock;
            int unblockedDamage = damageAmount - playerCurrentBlock;

            damageAmount = damageAmount - Math.min(unblockedDamage, damageReduction);

            flash();
        }

        return damageAmount;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], CHANCE_REDUCTION_DAMAGE, MIN_REDUCTION, MAX_REDUCTION);
    }
}
