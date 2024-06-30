package PMoonMod.relics.Anomaly.TETH.ForsakenMurderer;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FM_Cruelty extends PMoonRelic
{
    private static final String NAME =              "FM:Cruelty";
    private static final RelicTier RARITY =         RelicTier.RARE;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.HEAVY;

    private static final int MIN_INCREASE_DAMAGE = -1;
    private static final int MAX_INCREASE_DAMAGE =  3;

    public FM_Cruelty() {
        super(NAME, "Cruelty", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {

        int bonusDamage = AbstractDungeon.relicRng.random(MIN_INCREASE_DAMAGE, MAX_INCREASE_DAMAGE);

        return damageAmount + bonusDamage;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], MIN_INCREASE_DAMAGE, MAX_INCREASE_DAMAGE);
    }
}
