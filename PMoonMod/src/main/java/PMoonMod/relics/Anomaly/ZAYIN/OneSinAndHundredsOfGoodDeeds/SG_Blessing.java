package PMoonMod.relics.Anomaly.ZAYIN.OneSinAndHundredsOfGoodDeeds;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SG_Blessing extends PMoonRelic
{
    private static final String NAME =              "SG:Blessing";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.ZAYIN;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int HEAL_EFFECT = 1;

    public SG_Blessing() {
        super(NAME, "Blessing", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atTurnStart() {

        AbstractPlayer player = AbstractDungeon.player;
        addToTop(new HealAction(player, player, HEAL_EFFECT));

        flash();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], HEAL_EFFECT);
    }
}
