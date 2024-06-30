package PMoonMod.relics.Anomaly.TETH.ScorchedGirl;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SG_Ash extends PMoonRelic
{
    private static final String NAME =              "SG:Ash";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.FLAT;

    private final int CARD_IN_DECK_FOR_STATUS = 10;

    public SG_Ash() {
        super(NAME, "Ash", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {
        AbstractPlayer player = AbstractDungeon.player;
        counter = player.masterDeck.size() / CARD_IN_DECK_FOR_STATUS;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {

        if (counter == 0 ) return;

        // TODO Additional status for cards

        counter--;
        flash();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], CARD_IN_DECK_FOR_STATUS);
    }
}
