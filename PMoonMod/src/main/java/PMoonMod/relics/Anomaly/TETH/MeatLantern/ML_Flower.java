package PMoonMod.relics.Anomaly.TETH.MeatLantern;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class ML_Flower extends PMoonRelic
{
    private static final String NAME =              "ML:Flower";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int WEAK_AMT = 2;

    public ML_Flower() {
        super(NAME, "Flower", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {
        targetAllMonsters(this::effectToMonster);
        flash();
    }

    private Void effectToMonster(AbstractMonster m) {
        addToTop(new ApplyPowerAction(m, AbstractDungeon.player, new WeakPower(m, WEAK_AMT, false)));
        return null;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], WEAK_AMT);
    }
}
