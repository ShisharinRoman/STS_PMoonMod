package PMoonMod.relics.Anomaly.TETH.ForsakenMurderer;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FM_MetalClink extends PMoonRelic
{
    private static final String NAME =              "FM:MetalClink";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.CLINK;

    private static final int DAMAGE =   2;
    private int debuffCount =           0;

    public FM_MetalClink() {
        super(NAME, "MetalClink", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onPlayerEndTurn() {

        debuffCount = debuffsNumb(AbstractDungeon.player);

        if (debuffCount > 0 ) {
            targetAllMonsters(this::effectToMonster);
            flash();
        }
    }

    private Void effectToMonster(AbstractMonster m) {

        addToTop(
            new DamageAction(
                m,
                new DamageInfo(AbstractDungeon.player, DAMAGE * debuffCount, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                true
            )
        );

        return null;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], DAMAGE);
    }

}
