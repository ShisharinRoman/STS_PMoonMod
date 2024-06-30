package PMoonMod.relics.Anomaly.TETH.ForsakenMurderer;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class FM_ChainedFury extends PMoonRelic
{
    private static final String NAME =              "FM:ChainedFury";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.HEAVY;

    private static final int STRENGTH_AMT = 1;
    private static final int MAX_STRENGTH = 4;

    public FM_ChainedFury() {
        super(NAME, "ChainedFury", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atTurnStart() {
        counter = MAX_STRENGTH;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {

        if (targetCard.type != AbstractCard.CardType.SKILL || counter == 0) return;

        AbstractPlayer player = AbstractDungeon.player;

        addToTop(new ApplyPowerAction(player, player, new StrengthPower(player, STRENGTH_AMT), 0,true));
        addToTop(new ApplyPowerAction(player, player, new LoseStrengthPower(player, STRENGTH_AMT), 0, true));

        flash();
        counter--;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], STRENGTH_AMT, MAX_STRENGTH);
    }

}
