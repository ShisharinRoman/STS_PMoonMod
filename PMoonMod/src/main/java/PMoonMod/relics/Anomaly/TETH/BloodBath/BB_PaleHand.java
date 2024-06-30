package PMoonMod.relics.Anomaly.TETH.BloodBath;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class BB_PaleHand extends PMoonRelic
{
    private static final String NAME =              "BB:PaleHand";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.FLAT;

    private static final int DECREASE_DEXTERITY =      1;
    private static final int PER_TURN_ACTIVATION =     4;

    public BB_PaleHand() {
        super(NAME, "PaleHand", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atTurnStart() {

        counter++;

        if (counter < PER_TURN_ACTIVATION) return;

        targetAllMonsters(this::effectToMonster);
        flash();
        counter = 0;
    }

    private Void effectToMonster(AbstractMonster m) {
        addToTop(new ApplyPowerAction(m, AbstractDungeon.player, new DexterityPower(m, -DECREASE_DEXTERITY)));
        return null;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], PER_TURN_ACTIVATION, DECREASE_DEXTERITY);
    }
}
