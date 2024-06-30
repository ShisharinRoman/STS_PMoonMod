package PMoonMod.relics.Anomaly.TETH.FragmentOfTheUniverse;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FU_Enlightenment extends PMoonRelic
{
    private static final String NAME =              "FU:Enlightenment";
    private static final RelicTier RARITY =         RelicTier.RARE;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private final int SCRY_CARD =   2;
    private final int DAMAGE =      1;

    public FU_Enlightenment() {
        super(NAME, "Enlightenment", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atTurnStartPostDraw() {

        AbstractPlayer player = AbstractDungeon.player;
        addToBot(new ScryAction(SCRY_CARD));
        addToTop(new DamageAction(player, new DamageInfo(player, DAMAGE, DamageInfo.DamageType.HP_LOSS)));

        flash();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], SCRY_CARD, DAMAGE);
    }
}
