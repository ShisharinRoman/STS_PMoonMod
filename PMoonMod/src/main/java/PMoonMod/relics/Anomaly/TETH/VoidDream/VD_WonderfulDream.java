package PMoonMod.relics.Anomaly.TETH.VoidDream;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;

public class VD_WonderfulDream extends PMoonRelic
{
    private static final String NAME =              "VD:WonderfulDream";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int MAX_HEALTH_INCREASE =  30;

    public VD_WonderfulDream() {
        super(NAME, "WonderfulDream", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {

        AbstractCard c = CardLibrary.getCopy("Void");

        c.isEthereal = false;

        // TODO Normal card description

        AbstractDungeon.effectList.add( new ShowCardAndAddToDrawPileEffect(c, true, false));
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.increaseMaxHp(MAX_HEALTH_INCREASE, true);
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], MAX_HEALTH_INCREASE);
    }
}
