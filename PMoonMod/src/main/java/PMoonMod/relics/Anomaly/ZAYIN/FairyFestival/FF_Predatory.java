package PMoonMod.relics.Anomaly.ZAYIN.FairyFestival;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class FF_Predatory extends PMoonRelic
{
    private static final String NAME =              "FF:Predatory";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.ZAYIN;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int DAMAGE_DEAL =  3;
    private static final int STRENGTH_AMT = 2;

    public FF_Predatory() {
        super(NAME, "Predatory", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {

        AbstractPlayer player = AbstractDungeon.player;
        addToTop(new DamageAction(player, new DamageInfo(player, DAMAGE_DEAL, DamageInfo.DamageType.HP_LOSS)));
        addToTop(new ApplyPowerAction(player, player, new StrengthPower(player, STRENGTH_AMT)));

        flash();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], DAMAGE_DEAL,  STRENGTH_AMT);
    }
}
