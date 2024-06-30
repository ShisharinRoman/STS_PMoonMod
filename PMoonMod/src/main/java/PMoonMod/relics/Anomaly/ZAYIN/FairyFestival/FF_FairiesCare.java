package PMoonMod.relics.Anomaly.ZAYIN.FairyFestival;

import PMoonMod.PMoonMod;
import PMoonMod.cards.BaseCard;
import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FF_FairiesCare extends PMoonRelic
{

    private static final String NAME =              "FF:FairiesCare";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.ZAYIN;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int HEAL_EFFECT =  3;
    private static final int DAMAGE_DEAL =  12;

    public FF_FairiesCare() {
        super(NAME, "FairiesCare", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {
        super.usedDown(getUpdatedDescriptions());
    }

    @Override
    public void atTurnStart() {
        if (usedUp) return;

        AbstractPlayer player = AbstractDungeon.player;
        addToTop(new HealAction(player, player, HEAL_EFFECT));
        flash();
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {

        if (!targetCard.cardID.contains(PMoonMod.modID) || usedUp) return;

        BaseCard c = (BaseCard) targetCard;

        // TODO: Anomaly card type

        AbstractPlayer player = AbstractDungeon.player;
        addToTop(new DamageAction(player, new DamageInfo(player, DAMAGE_DEAL, DamageInfo.DamageType.HP_LOSS)));

        flash();
        usedUp();

        if (tips.size() == 1) {
            addDescription(getUpdatedDescription(1));
        }
    }

    @Override
    public String getUpdatedDescription(int index) {
        switch (index) {
            case 1:
                return String.format(DESCRIPTIONS[1], DAMAGE_DEAL);
            case 0:
            default:
                return String.format(DESCRIPTIONS[0], HEAL_EFFECT,  DAMAGE_DEAL);
        }
    }

}
