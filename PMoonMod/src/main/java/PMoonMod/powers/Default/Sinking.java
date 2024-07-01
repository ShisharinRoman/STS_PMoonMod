package PMoonMod.powers.Default;

import PMoonMod.powers.System.PMoonPower;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static PMoonMod.PMoonMod.makeID;

public class Sinking extends PMoonPower {

    public static final String POWER_ID =                   makeID("Sinking");
    private static final AbstractPower.PowerType TYPE =     PowerType.DEBUFF;
    private static final boolean TURN_BASED =               false;
    private static final int SINKING_TO_PANIC =             15;
    private static final int MULTIPLICATION_DAMAGE =        3;
    private static final int SINKING_TO_STRONG_PANIC =      25;
    private static final int MULTIPLICATION_STRONG_DAMAGE = 4;
    private static final int SINKING_DECREASE =             1;

    public Sinking(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, owner, amount);
    }

    @Override
    public void atStartOfTurn() {

        if (amount >= SINKING_TO_PANIC) {
            if (amount < SINKING_TO_STRONG_PANIC) {
                addToTop(
                        new DamageAction(
                                owner,
                                new DamageInfo(owner,amount * MULTIPLICATION_DAMAGE,
                                        DamageInfo.DamageType.HP_LOSS
                                )
                        )
                );
            } else {
                addToTop(
                        new DamageAction(
                                owner,
                                new DamageInfo(owner,amount * MULTIPLICATION_STRONG_DAMAGE,
                                        DamageInfo.DamageType.HP_LOSS
                                )
                        )
                );
            }
            flash();
            remove();
        }
    }

    @Override
    public void atEndOfRound() {

        if (owner.isPlayer) return;
        decreaseAmount();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {

        if (!isPlayer) return;
        decreaseAmount();
    }

    private void decreaseAmount() {

        if ( amount == 1) remove();
        amount --;
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], SINKING_TO_PANIC, SINKING_TO_STRONG_PANIC, SINKING_DECREASE);
    }
}
