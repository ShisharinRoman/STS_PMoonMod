package PMoonMod.relics.Anomaly.TETH.VoidDream;

import PMoonMod.powers.Default.Sinking;
import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class VD_Sheep extends PMoonRelic
{
    private static final String NAME =              "VD:Sheep";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int MIN_SINKING_AMT =      1;
    private static final int MAX_SINKING_AMT =      2;

    private boolean isFirstAttacked = true;

    public VD_Sheep() {
        super(NAME, "Sheep", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atTurnStart() {
        isFirstAttacked = true;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {

        AbstractPlayer player = AbstractDungeon.player;

        if (player.currentBlock >= damageAmount
                || info.type != DamageInfo.DamageType.NORMAL
                || !isFirstAttacked) return damageAmount;

        targetAllMonsters(this::effectToMonster);
        isFirstAttacked = false;

        flash();

        return damageAmount;
    }

    private Void effectToMonster(AbstractMonster m) {

        int sinking = AbstractDungeon.relicRng.random(MIN_SINKING_AMT,MAX_SINKING_AMT);

        addToTop(new ApplyPowerAction(m, null, new Sinking(m, sinking)));

        return null;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], MIN_SINKING_AMT, MAX_SINKING_AMT);
    }
}
