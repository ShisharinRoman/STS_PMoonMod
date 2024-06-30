package PMoonMod.relics.Anomaly.ZAYIN.ArmyInPink;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AP_Protection extends PMoonRelic
{
    private static final String NAME =              "AP:Protection";
    private static final RelicTier RARITY =         RelicTier.RARE;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.ZAYIN;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int BLOCK_AMT =            15;
    private static final int MAX_COUNT_USE =        3;

    public AP_Protection() {
        super(NAME, "Protection", RARITY, DANGER_LEVEL, SOUND);

        counter = MAX_COUNT_USE;
    }

    @Override
    public void onActivationInFight() {
        AbstractPlayer player = AbstractDungeon.player;
        addToTop(new GainBlockAction(player, BLOCK_AMT));

        counter--;

        flash();

        if (counter == 0) usedUp();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], MAX_COUNT_USE, BLOCK_AMT);
    }
}
