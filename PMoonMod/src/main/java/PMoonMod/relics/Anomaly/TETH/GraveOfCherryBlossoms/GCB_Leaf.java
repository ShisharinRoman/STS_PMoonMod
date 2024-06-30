package PMoonMod.relics.Anomaly.TETH.GraveOfCherryBlossoms;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class GCB_Leaf extends PMoonRelic
{
    private static final String NAME =              "GCB:Leaf";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int HEAL_EFFECT =          10;
    private static final int COUNTER_TO_RELOAD =    3;

    public GCB_Leaf() {
        super(NAME, "Leaf", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onEquip() {
        counter = COUNTER_TO_RELOAD;
    }

    @Override
    public void atBattleStart() {
        if (counter < COUNTER_TO_RELOAD) counter++;
    }

    @Override
    public void onActivationInRun() {

        if (counter != COUNTER_TO_RELOAD) return;

        AbstractPlayer player = AbstractDungeon.player;
        addToTop(new HealAction(player, player, HEAL_EFFECT));

        counter = 0;

        flash();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], HEAL_EFFECT);
    }
}
