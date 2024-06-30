package PMoonMod.relics.Anomaly.ZAYIN.OneSinAndHundredsOfGoodDeeds;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SG_TheCrownOfThorns extends PMoonRelic
{

    private static final String NAME =              "SG:TheCrownOfThorns";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.ZAYIN;
    private static final LandingSound SOUND =       LandingSound.FLAT;

    private static final int MAX_CLEAR_DEBUFF = 3;

    public SG_TheCrownOfThorns() {
        super(NAME, "TheCrownOfThorns", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onEquip() {
        counter = MAX_CLEAR_DEBUFF;
    }

    @Override
    public void atBattleStart() {
        counter = MAX_CLEAR_DEBUFF;
        usedDown(getUpdatedDescriptions());
    }

    @Override
    public void atTurnStart() {

        if (usedUp) return;

        AbstractPlayer player = AbstractDungeon.player;
        clearRandomDebuff(player);

        counter--;
        if (counter == 0) usedUp();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], MAX_CLEAR_DEBUFF);
    }
}

