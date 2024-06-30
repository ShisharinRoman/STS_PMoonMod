package PMoonMod.relics.Anomaly.TETH.CrumblingArmor;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CA_Katana extends PMoonRelic
{
    private static final String NAME =              "CA:Katana";
    private static final RelicTier RARITY =         RelicTier.RARE;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.HE;
    private static final LandingSound SOUND =       LandingSound.CLINK;

    private static final int PERCENT_MAX_HP_DAMAGE =    7;
    private static final int DAMAGE_FOR_COWARDICE =     15;

    public CA_Katana() {
        super(NAME, "Katana", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {
        usedDown(getUpdatedDescriptions());
    }

    @Override
    public void onPlayerEndTurn() {

        AbstractPlayer player = AbstractDungeon.player;

        if (player.currentBlock == 0 || usedUp) return;

        addToTop(new DamageAction(player, new DamageInfo(player, DAMAGE_FOR_COWARDICE, DamageInfo.DamageType.HP_LOSS)));

        flash();
        usedUp();
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {

        if (targetCard.type != AbstractCard.CardType.ATTACK || useCardAction.target == null || usedUp) return;

        AbstractCreature target =   useCardAction.target;
        AbstractPlayer player =     AbstractDungeon.player;
        int bonusDamage =           (int)(target.maxHealth * ((float)PERCENT_MAX_HP_DAMAGE / 100));

        addToBot(new DamageAction(target, new DamageInfo(player, bonusDamage), AbstractGameAction.AttackEffect.SLASH_DIAGONAL, true));

        flash();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], PERCENT_MAX_HP_DAMAGE, DAMAGE_FOR_COWARDICE);
    }

}
