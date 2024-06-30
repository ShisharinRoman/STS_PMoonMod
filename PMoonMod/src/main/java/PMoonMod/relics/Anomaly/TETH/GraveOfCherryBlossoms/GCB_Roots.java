package PMoonMod.relics.Anomaly.TETH.GraveOfCherryBlossoms;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GCB_Roots extends PMoonRelic
{
    private static final String NAME =              "GCB:Roots";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.HE;
    private static final LandingSound SOUND =       LandingSound.FLAT;

    private static final int TURN_TO_ACTIVATE =   3;
    private static final int DAMAGE =             30;

    public GCB_Roots() {
        super(NAME, "Roots", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {
        usedDown(getUpdatedDescriptions());
    }

    @Override
    public void atTurnStart() {

        if (++counter != TURN_TO_ACTIVATE) return;

        AbstractMonster monster = AbstractDungeon.getRandomMonster();
        addToTop(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, DAMAGE)));

        flash();
        usedUp();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], TURN_TO_ACTIVATE, DAMAGE);
    }

}
