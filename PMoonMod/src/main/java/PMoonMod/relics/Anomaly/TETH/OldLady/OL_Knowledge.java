package PMoonMod.relics.Anomaly.TETH.OldLady;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class OL_Knowledge extends PMoonRelic
{
    private static final String NAME =              "OL:Knowledge";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private final int DRAW_CARD =       1;
    private final int DISCARD_CARD =    1;
    private final int DAMAGE =          1;

    public OL_Knowledge() {
        super(NAME, "Knowledge", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atTurnStartPostDraw() {
        AbstractPlayer player = AbstractDungeon.player;

        addToTop(new DamageAction(player, new DamageInfo(player, DAMAGE, DamageInfo.DamageType.HP_LOSS)));
        addToBot(new DrawCardAction(player, DRAW_CARD));
        addToBot(new DiscardAction(player, player, DISCARD_CARD, false));

        flash();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], DISCARD_CARD, DRAW_CARD, DAMAGE);
    }
}
