package PMoonMod.relics.Anomaly.TETH.CrumblingArmor;

import PMoonMod.powers.Default.Flimsiness;
import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class CA_Courage extends PMoonRelic
{
    private static final String NAME =              "CA:Courage";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.CLINK;

    private static final int MAX_LEVEL =            3;
    private static final int MAX_HEALTH_DECREASE =  5;

    private int strengthAmt =   1;
    private int fragileAmt =    1;

    public CA_Courage() {
        super(NAME, "Courage", RARITY, DANGER_LEVEL, SOUND);

        maxLevel = MAX_LEVEL;

        updateDescription(getUpdatedDescription());
    }

    @Override
    public void atBattleStart() {
        AbstractPlayer player = AbstractDungeon.player;
        addToTop(new ApplyPowerAction(player, null, new StrengthPower(player, strengthAmt)));
    }

    @Override
    public void atTurnStart() {

        AbstractPlayer player = AbstractDungeon.player;
        addToTop(new ApplyPowerAction(player, null, new Flimsiness(player, fragileAmt)));
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.decreaseMaxHealth(MAX_HEALTH_DECREASE);
    }

    @Override
    public void upgrade() {

        switch (level) {
            case 0:
                strengthAmt++;
                break;
            case 1:
            case 2:
                strengthAmt++;
                fragileAmt++;
                break;
            case MAX_LEVEL:
                AbstractPlayer player = AbstractDungeon.player;
                addToTop(new DamageAction(player, new DamageInfo(player, player.maxHealth * 2, DamageInfo.DamageType.HP_LOSS)));
                fragileAmt++; // :)
                break;
            default:
        }

        if ( level <= MAX_LEVEL) {
            AbstractDungeon.player.decreaseMaxHealth(MAX_HEALTH_DECREASE);
            updateDescription(getUpdatedDescription());
            level++;
            flash();
        }
    }

    @Override
    public void onActivationInRun() {
        upgrade();
    }

    @Override
    public String getUpdatedDescription(int index) {
        if (level < 3)
            return String.format(DESCRIPTIONS[0], MAX_HEALTH_DECREASE * (level + 1), MAX_HEALTH_DECREASE, strengthAmt, fragileAmt);
        else if (level == 3)
            return String.format(DESCRIPTIONS[1], MAX_HEALTH_DECREASE * (level + 1), strengthAmt, fragileAmt);
        else
            return String.format(DESCRIPTIONS[2], MAX_HEALTH_DECREASE * (level + 1), strengthAmt, fragileAmt);
    }
}
