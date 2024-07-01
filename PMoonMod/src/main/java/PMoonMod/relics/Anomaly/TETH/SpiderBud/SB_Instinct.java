package PMoonMod.relics.Anomaly.TETH.SpiderBud;

import PMoonMod.powers.Default.Endurance;
import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class SB_Instinct extends PMoonRelic
{
    private static final String NAME =              "SB:Instinct";
    private static final RelicTier RARITY =         RelicTier.RARE;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.HE;
    private static final LandingSound SOUND =       LandingSound.HEAVY;

    private static final int PROTECTION_PER_ATTACK_AMT = 3;
    private int blockAmt = 0;

    public SB_Instinct() {
        super(NAME, "Instinct", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {
        counter = 0;
    }

    @Override
    public void atTurnStart() {

        AbstractPlayer player = AbstractDungeon.player;
        addToTop(new ApplyPowerAction(player, null, new Endurance(player, counter)));
        counter = 0;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {

        AbstractPlayer player = AbstractDungeon.player;

        if (info.type != DamageInfo.DamageType.NORMAL || player.currentBlock >= damageAmount) return onAttacked(info, damageAmount);

        counter += PROTECTION_PER_ATTACK_AMT;

        return onAttacked(info, damageAmount);
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], PROTECTION_PER_ATTACK_AMT);
    }
}
