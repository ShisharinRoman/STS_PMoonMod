package PMoonMod.relics.Anomaly.TETH.MeatLantern;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ML_Lantern extends PMoonRelic
{
    private static final String NAME =              "ML:Lantern";
    private static final RelicTier RARITY =         RelicTier.RARE;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.HE;
    private static final LandingSound SOUND =       LandingSound.FLAT;

    private static final int TURN_ACTIVATE =    5;
    private static final int DAMAGE =           40;

    public ML_Lantern() {
        super(NAME, "Lantern", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {
        counter = 0;
        usedDown(getUpdatedDescriptions());
    }

    @Override
    public void atTurnStart() {

        if (usedUp) return;

        counter++;
        if (counter != TURN_ACTIVATE) return;

        targetAllMonsters(this::effectToMonster);

        counter = -2;

        flash();
        usedUp();
    }

    private Void effectToMonster(AbstractMonster m) {
        addToTop(new DamageAction(m, new DamageInfo(AbstractDungeon.player, DAMAGE, DamageInfo.DamageType.NORMAL), true));
        return null;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], TURN_ACTIVATE, DAMAGE);
    }
}
