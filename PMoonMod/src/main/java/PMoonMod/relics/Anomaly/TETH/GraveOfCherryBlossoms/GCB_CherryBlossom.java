package PMoonMod.relics.Anomaly.TETH.GraveOfCherryBlossoms;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class GCB_CherryBlossom extends PMoonRelic
{
    private static final String NAME =              "GCB:CherryBlossom";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int SKILL_PLAYS_TO_ACTIVATE =  3;
    private static final int STRENGTH_DECREASE_AMT =    1;

    public GCB_CherryBlossom() {
        super(NAME, "CherryBlossom", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atTurnStart() {
        counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {

        if (targetCard.type != AbstractCard.CardType.SKILL || ++counter != SKILL_PLAYS_TO_ACTIVATE) return;

        targetAllMonsters(this::effectToMonster);

        counter = 0;

        flash();
    }

    private Void effectToMonster(AbstractMonster m) {
        AbstractPlayer player = AbstractDungeon.player;
        addToTop(new ApplyPowerAction(m, player, new StrengthPower(m, -STRENGTH_DECREASE_AMT)));
        addToTop(new ApplyPowerAction(m, player, new GainStrengthPower(m, STRENGTH_DECREASE_AMT)));
        return null;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], SKILL_PLAYS_TO_ACTIVATE, STRENGTH_DECREASE_AMT);
    }

}
