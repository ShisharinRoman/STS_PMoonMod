package PMoonMod.relics.Anomaly.TETH.BloodBath;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BB_WristCutter extends PMoonRelic
{
    private static final String NAME =              "BB:WristCutter";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.CLINK;

    private static final int MAX_COUNTER =      3;
    private static final int STRENGTH_AMT =     3;

    public BB_WristCutter() {
        super(NAME, "WristCutter", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {
        counter = 0;
    }

    @Override
    public void atTurnStart() {

        if (counter == MAX_COUNTER) {
            counter++;
            return;
        }

        beginLongPulse();
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {

        if (counter != MAX_COUNTER) return;

        addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new StrengthPower(target, -STRENGTH_AMT)));
        addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new GainStrengthPower(target, STRENGTH_AMT)));

        counter = 0;

        flash();
        stopPulse();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], MAX_COUNTER, STRENGTH_AMT);
    }
}