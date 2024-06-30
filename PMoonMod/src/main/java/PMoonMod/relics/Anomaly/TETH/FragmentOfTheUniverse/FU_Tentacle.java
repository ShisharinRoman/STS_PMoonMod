package PMoonMod.relics.Anomaly.TETH.FragmentOfTheUniverse;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class FU_Tentacle extends PMoonRelic
{
    private static final String NAME =              "FU:Tentacle";
    private static final RelicTier RARITY =         RelicTier.RARE;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       AbstractRelic.LandingSound.CLINK;

    private static int MAX_TURN_ACTIVATION_PER_TURN =   3;
    private static int AWARENESS_AMT =                  1;

    public FU_Tentacle() {
        super(NAME, "Tentacle", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onEquip() {
        counter = MAX_TURN_ACTIVATION_PER_TURN;
    }

    @Override
    public void atTurnStart() {
        counter = MAX_TURN_ACTIVATION_PER_TURN;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {

        if (counter == 0 || target.currentBlock != 0 ) return;

        // TODO Awareness

        counter--;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], AWARENESS_AMT, MAX_TURN_ACTIVATION_PER_TURN);
    }

}
