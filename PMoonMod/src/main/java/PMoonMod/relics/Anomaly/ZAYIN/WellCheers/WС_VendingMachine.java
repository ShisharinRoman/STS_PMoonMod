package PMoonMod.relics.Anomaly.ZAYIN.WellCheers;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import static PMoonMod.PMoonMod.makeID;


public class WС_VendingMachine extends PMoonRelic
{
    private static final String NAME =              "WC:VendingMachine";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.ZAYIN;
    private static final LandingSound SOUND =       LandingSound.HEAVY;

    private static final  int GOLD_COST =           20;

    public WС_VendingMachine() {
        super(NAME, "VendingMachine", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onActivationInFight() {

        AbstractPlayer player = AbstractDungeon.player;
        int playerGold = player.gold;

        if (playerGold >= GOLD_COST) {
            AbstractCard c = CardLibrary.getCopy(makeID("WC:Soda"));
            AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(c));
            player.loseGold(GOLD_COST);
        }
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], GOLD_COST);
    }
}
