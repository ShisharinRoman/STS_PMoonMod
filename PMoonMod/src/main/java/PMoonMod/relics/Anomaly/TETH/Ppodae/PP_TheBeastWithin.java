package PMoonMod.relics.Anomaly.TETH.Ppodae;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class PP_TheBeastWithin extends PMoonRelic
{
    private static final String NAME =              "PP:TheBeastWithin";
    private static final RelicTier RARITY =         RelicTier.RARE;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.HEAVY;

    private static final int STRENGTH_AMT =     1;
    private static final int DEXTERITY_AMT =    1;

    public PP_TheBeastWithin() {
        super(NAME, "TheBeastWithin", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {

        if (targetCard.type != AbstractCard.CardType.POWER) return;

        AbstractPlayer player = AbstractDungeon.player;

        addToTop(new ApplyPowerAction(player, player, new StrengthPower(player, STRENGTH_AMT)));
        addToTop(new ApplyPowerAction(player, player, new DexterityPower(player, DEXTERITY_AMT)));

        flash();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], STRENGTH_AMT, DEXTERITY_AMT);
    }
}
