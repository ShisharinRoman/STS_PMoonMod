package PMoonMod.powers.Default;

import PMoonMod.powers.System.PMoonPower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static PMoonMod.PMoonMod.makeID;

public class Flimsiness extends PMoonPower {

    public static final String POWER_ID =               makeID("Flimsiness");
    private static final AbstractPower.PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED =           false;
    private static final int MAX_AMOUNT =               10;

    public Flimsiness(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, owner, amount);
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {

        if (type != DamageInfo.DamageType.NORMAL) return damage;

        float increaseDamage = (float)Math.min(MAX_AMOUNT, amount) / 10F;

        damage *= (1 + increaseDamage);

        flash();

        return damage;
    }

    @Override
    public void atEndOfRound() {
        if (owner.isPlayer) remove();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!owner.isPlayer) remove();
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], (int)((float)Math.min(MAX_AMOUNT, amount) / 10F * 100));
    }
}
