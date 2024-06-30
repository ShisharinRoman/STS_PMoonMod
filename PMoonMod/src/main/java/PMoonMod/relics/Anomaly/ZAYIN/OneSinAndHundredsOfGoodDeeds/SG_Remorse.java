package PMoonMod.relics.Anomaly.ZAYIN.OneSinAndHundredsOfGoodDeeds;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;

import javax.print.attribute.standard.MediaSize;

public class SG_Remorse extends PMoonRelic
{
    private static final String NAME =              "SG:Remorse";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.ZAYIN;
    private static final LandingSound SOUND =       LandingSound.CLINK;

    private static final int ENERGY_GAIN =              1;
    private static final int COUNTER_TO_ACTIVATION =    3;

    public SG_Remorse() {
        super(NAME, "Remorse", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onEquip() {
        counter = COUNTER_TO_ACTIVATION;
    }

    @Override
    public void atBattleStart() {
        if (counter < COUNTER_TO_ACTIVATION) {
            counter++;
        } else {
            usedDown(getUpdatedDescriptions());
            beginLongPulse();
        }
    }

    @Override
    public void onActivationInFight() {

        addToTop(new GainEnergyAction(ENERGY_GAIN));
        counter = 0;

        flash();
        usedUp();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], ENERGY_GAIN, COUNTER_TO_ACTIVATION);
    }
}
