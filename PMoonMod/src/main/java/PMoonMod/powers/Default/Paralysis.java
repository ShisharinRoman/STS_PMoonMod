package PMoonMod.powers.Default;

import PMoonMod.powers.System.PMoonPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static PMoonMod.PMoonMod.makeID;
import static java.lang.Math.min;

public class Paralysis extends PMoonPower
{
    public static final String POWER_ID =               makeID("Paralysis");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED =           false;
    private static final int MAX_PARALYSIS_ON_ATTACK =  5;

    public Paralysis(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, owner, amount);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {

        if (info.type != DamageInfo.DamageType.NORMAL) return;

        int applyParalysis = min(amount,MAX_PARALYSIS_ON_ATTACK);

        if (amount <= MAX_PARALYSIS_ON_ATTACK) {
            addToTop(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        }
        else {
            amount -= applyParalysis;
        }

        flash();
        updateDescription();
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {

        if (type != DamageInfo.DamageType.NORMAL) return damage;

        int applyParalysis = min(amount, MAX_PARALYSIS_ON_ATTACK);

        damage *= (1 - (float)applyParalysis / (float)MAX_PARALYSIS_ON_ATTACK);

        return damage;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) remove();
    }

    @Override
    public void atEndOfRound() {
        if (!owner.isPlayer) remove();
    }

    public void updateDescription() {
        this.description = String.format(
                DESCRIPTIONS[0],
                (int)((float)Math.min(MAX_PARALYSIS_ON_ATTACK, amount) / (float)MAX_PARALYSIS_ON_ATTACK * 100),
                MAX_PARALYSIS_ON_ATTACK);
    }
}
