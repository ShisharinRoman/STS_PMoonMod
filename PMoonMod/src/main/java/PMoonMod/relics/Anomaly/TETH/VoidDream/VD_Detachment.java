package PMoonMod.relics.Anomaly.TETH.VoidDream;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.cards.DamageInfo;

public class VD_Detachment extends PMoonRelic
{
    private static final String NAME =              "VD:Detachment";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.SOLID;

    private static final int DECREASE_DAMAGE = 1;

    public VD_Detachment() {
        super(NAME, "Detachment", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {

        flash();

        return damageAmount - DECREASE_DAMAGE;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], DECREASE_DAMAGE);
    }
}
