package PMoonMod.powers.Default;

import PMoonMod.powers.System.PMoonPower;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static PMoonMod.PMoonMod.makeID;

public class Charge extends PMoonPower {

    public static final String POWER_ID =                   makeID("Charge");
    private static final AbstractPower.PowerType TYPE =     PowerType.BUFF;
    private static final boolean TURN_BASED =               false;
    private static final int MAX_CHARGE =                   20;
    private static final int ENERGY_AMOUNT =                1;
    private static final int CHARGE_DECREASE =              1;

    public Charge(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, owner, amount);
    }

    @Override
    public void atStartOfTurn() {

        if (!owner.isPlayer) return;

        if (amount >= MAX_CHARGE) {
            addToTop(new GainEnergyAction(ENERGY_AMOUNT));
            flash();
            remove();
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        decreaseAmount();
    }

    private void decreaseAmount() {
        amount -= CHARGE_DECREASE;
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], MAX_CHARGE, ENERGY_AMOUNT, CHARGE_DECREASE);
    }
}
