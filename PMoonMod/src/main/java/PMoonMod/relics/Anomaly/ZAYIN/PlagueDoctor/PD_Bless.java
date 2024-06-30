package PMoonMod.relics.Anomaly.ZAYIN.PlagueDoctor;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class PD_Bless extends PMoonRelic
{
    private static final String NAME =              "PD:Bless";
    private static final RelicTier RARITY =         RelicTier.SPECIAL;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.ZAYIN;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int MAX_HEALTH_INCREASE =  6;
    private static final int STRENGTH_AMT =         6;
    private static final int DEXTERITY_AMT =        6;

    public PD_Bless() {
        super(NAME, "Bless", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {
        AbstractPlayer player = AbstractDungeon.player;

        addToTop(new ApplyPowerAction(player, player, new StrengthPower(player, STRENGTH_AMT)));
        addToTop(new ApplyPowerAction(player, player, new LoseStrengthPower(player, STRENGTH_AMT)));
        addToTop(new ApplyPowerAction(player, player, new DexterityPower(player, DEXTERITY_AMT)));
        addToTop(new ApplyPowerAction(player, player, new LoseDexterityPower(player, DEXTERITY_AMT)));
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.increaseMaxHp(MAX_HEALTH_INCREASE, true);
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], MAX_HEALTH_INCREASE, STRENGTH_AMT, DEXTERITY_AMT);
    }
}
