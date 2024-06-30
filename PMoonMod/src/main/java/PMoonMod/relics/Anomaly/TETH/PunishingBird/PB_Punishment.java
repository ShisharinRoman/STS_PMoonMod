package PMoonMod.relics.Anomaly.TETH.PunishingBird;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PB_Punishment extends PMoonRelic
{
    private static final String NAME =              "PB:Punishment";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.CLINK;

    private static final int MIN_INCREASE_DAMAGE =      2;
    private static final int MAX_INCREASE_DAMAGE =      4;

    private boolean isTakenHit =            false;
    private boolean isTakenHitPrevTurn =    false;
    private boolean isAttackCard =          false;
    private boolean isFirstAttackPlayed =   false;

    public PB_Punishment() {
        super(NAME, "Punishment", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {

        AbstractPlayer player = AbstractDungeon.player;

        if (info.type == DamageInfo.DamageType.NORMAL && player.currentBlock < damageAmount ) {
            isTakenHit = true;
        }

        return damageAmount;
    }

    @Override
    public void atTurnStart() {

        isFirstAttackPlayed = false;

        if (!isTakenHit) return;

        isTakenHitPrevTurn =    true;
        isTakenHit =            false;

        beginLongPulse();
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {

        if (isFirstAttackPlayed) {
            isTakenHitPrevTurn = false;
        }

        if ((targetCard.type == AbstractCard.CardType.ATTACK) && (!isFirstAttackPlayed)) {
            isFirstAttackPlayed = true;
        }
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {

        if (!isTakenHitPrevTurn || !isFirstAttackPlayed) return super.onAttackedToChangeDamage(info, damageAmount);

        int increaseDamage = AbstractDungeon.relicRng.random(MIN_INCREASE_DAMAGE,MAX_INCREASE_DAMAGE);
        damageAmount += increaseDamage;

        flash();
        stopPulse();

        return super.onAttackedToChangeDamage(info, damageAmount);
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], MIN_INCREASE_DAMAGE, MAX_INCREASE_DAMAGE);
    }
}
