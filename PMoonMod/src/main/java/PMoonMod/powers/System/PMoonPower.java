package PMoonMod.powers.System;

import PMoonMod.powers.BasePower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public abstract class PMoonPower extends BasePower {

    protected boolean isCanRemove = true;

    public PMoonPower(String id, PowerType type, boolean turnBased, AbstractCreature owner) {
        this(id, type, turnBased, owner, owner, 0);
    }

    public PMoonPower(String id, PowerType type, boolean turnBased, AbstractCreature owner, int amount) {
        this(id, type, turnBased, owner, owner, amount);
    }

    public PMoonPower(String id, PowerType type, boolean turnBased, AbstractCreature owner, AbstractCreature source) {
        this(id, type, turnBased, owner, source, 0);
    }

    public PMoonPower(String id, PowerType type, boolean turnBased, AbstractCreature owner, AbstractCreature source, int amount) {
        super(id, type, turnBased, owner, source, amount);
    }

    public boolean isCanRemove() {
        return isCanRemove;
    }

    protected void remove() {
        addToTop(new RemoveSpecificPowerAction(owner, owner, ID));
    }
}
