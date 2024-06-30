package PMoonMod.relics.Anomaly.TETH.MHZ;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;

public class MHZ_Interference extends PMoonRelic
{
    private static final String NAME =              "MHZ:Interference";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int INTERFERENCE_COUNT = 3;

    public MHZ_Interference() {
        super(NAME, "Interference", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {

        CardGroup drawDeck = AbstractDungeon.player.drawPile;

        for (int i = 0; i < INTERFERENCE_COUNT; i++) {
            AbstractCard c = drawDeck.getRandomCard(false);
            drawDeck.removeCard(c);

            // TODO Card Modificators

            c.modifyCostForCombat(0);
            AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(c,true, false));
        }

        flash();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], INTERFERENCE_COUNT);
    }
}
