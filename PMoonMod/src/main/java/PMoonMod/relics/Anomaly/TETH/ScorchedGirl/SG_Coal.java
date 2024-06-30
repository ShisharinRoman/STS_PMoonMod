package PMoonMod.relics.Anomaly.TETH.ScorchedGirl;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SG_Coal extends PMoonRelic
{
    private static final String NAME =              "SG:Coal";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.FLAT;

    private static final int DAMAGE = 1;

    public SG_Coal() {
        super(NAME, "Coal", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onManualDiscard() {
        targetAllMonsters(this::effectToMonster);
        flash();
    }

    private Void effectToMonster(AbstractMonster m) {
        addToTop(new DamageAction(m, new DamageInfo(AbstractDungeon.player, DAMAGE, DamageInfo.DamageType.NORMAL)));
        return null;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], DAMAGE);
    }
}
