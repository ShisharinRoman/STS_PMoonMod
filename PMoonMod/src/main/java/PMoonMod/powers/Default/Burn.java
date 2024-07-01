package PMoonMod.powers.Default;

import PMoonMod.powers.System.PMoonPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static PMoonMod.PMoonMod.makeID;

public class Burn extends PMoonPower
{
    public static final String POWER_ID =               makeID("Burn");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED =           false;
    private static final int DECREASE_BURN =            3;

    public Burn(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {

        if (isPlayer) {
            addToBot(
                    new DamageAction(
                            owner,
                            new DamageInfo(owner, amount, DamageInfo.DamageType.HP_LOSS),
                            AbstractGameAction.AttackEffect.FIRE
                    )
            );
            decreaseAmount();
        }
    }

    @Override
    public void atEndOfRound() {

        if (!owner.isPlayer) {
            addToBot(
                    new DamageAction(
                            owner,
                            new DamageInfo(owner, amount, DamageInfo.DamageType.HP_LOSS),
                            AbstractGameAction.AttackEffect.FIRE
                    )
            );
            decreaseAmount();
        }
    }

    private void decreaseAmount() {

        if ( amount <= 3) remove();

        amount /= DECREASE_BURN;

        flash();
        updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], amount, DECREASE_BURN);
    }
}
