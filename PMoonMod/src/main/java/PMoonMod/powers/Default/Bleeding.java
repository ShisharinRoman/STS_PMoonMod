package PMoonMod.powers.Default;

import PMoonMod.powers.System.PMoonPower;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import static PMoonMod.PMoonMod.makeID;


public class Bleeding extends PMoonPower
{
    public static final String POWER_ID =               makeID("Bleeding");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED =           true;
    private static final int DECREASE_BLEEDING =        3;

    public Bleeding(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, owner, amount);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {

        if (info.type != DamageInfo.DamageType.NORMAL) return;

        addToBot(new DamageAction(owner, new DamageInfo(owner, amount, DamageInfo.DamageType.HP_LOSS)));

        flash();
        decreaseAmount();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) decreaseAmount();
    }

    @Override
    public void atEndOfRound() {
        if (!owner.isPlayer) decreaseAmount();
    }

    private void decreaseAmount() {

        if ( amount <= 3) remove();

        amount /= DECREASE_BLEEDING;

        updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], amount, DECREASE_BLEEDING);
    }
}
