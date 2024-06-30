package PMoonMod.relics.Anomaly.TETH.MeatLantern;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ML_Fangs extends PMoonRelic
{
    private static final String NAME =              "ML:Fangs";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.CLINK;

    private static final int HEAL_EFFECT =          3;
    private static final int HP_PERCENT_TO_ACTIVE = 30;

    public ML_Fangs() {
        super(NAME, "Fangs", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onEquip() {
        this.onLoseHp(0);
    }

    @Override
    public void onLoseHp(int damageAmount) {

        AbstractPlayer player = AbstractDungeon.player;

        int playerHpPercent = (int)((((float)player.currentHealth - damageAmount) / player.maxHealth) * 100F);

        if (playerHpPercent >= HP_PERCENT_TO_ACTIVE) {
            beginLongPulse();
        }
        else {
            stopPulse();
        }
    }

    @Override
    public int onPlayerHeal(int healAmount) {
        this.onLoseHp(-healAmount);
        return healAmount;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if ( usedUp || target.currentBlock >= damageAmount) return;

        AbstractPlayer player = AbstractDungeon.player;
        addToTop(new HealAction(player, player, HEAL_EFFECT));

        flash();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0],  HP_PERCENT_TO_ACTIVE, HEAL_EFFECT);
    }
}

