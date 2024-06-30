package PMoonMod.relics.Anomaly.ZAYIN.DontTouchMe;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DTM_Button extends PMoonRelic
{
    private static final String NAME =              "DTM:Button";
    private static final RelicTier RARITY =         RelicTier.SPECIAL;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.ZAYIN;
    private static final LandingSound SOUND =       LandingSound.SOLID;

    public DTM_Button() {
        super(NAME, "Button", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onActivationInRun() {

        AbstractPlayer player = AbstractDungeon.player;
        addToTop(new DamageAction(player, new DamageInfo(player, player.maxHealth, DamageInfo.DamageType.HP_LOSS)));

        flash();
    }

    @Override
    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0]);
    }

}
