package PMoonMod.relics.Anomaly.TETH.SpiderBud;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SB_Vigilance extends PMoonRelic
{
    private static final String NAME =              "SB:Vigilance";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.FLAT;

    private static final int MIN_STRENGTH_DECREASE = 1;
    private static final int MAX_STRENGTH_DECREASE = 2;

    public SB_Vigilance() {
        super(NAME, "Vigilance", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {
        targetAllMonsters(this::effectToMonster);
        flash();
    }

    private Void effectToMonster(AbstractMonster m) {
        int strengthDecrease = AbstractDungeon.relicRng.random(MIN_STRENGTH_DECREASE, MAX_STRENGTH_DECREASE);
        addToTop(new ApplyPowerAction(m, AbstractDungeon.player, new StrengthPower(m, -strengthDecrease)));
        return null;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], MIN_STRENGTH_DECREASE, MAX_STRENGTH_DECREASE);
    }

}
