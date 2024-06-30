package PMoonMod.relics.Anomaly.TETH.WomanFacingTheWall;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WFW_Scream extends PMoonRelic
{
    private static final String NAME =              "WFW:Scream";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int ACTIVATION_PER_BATTLE = 1;

    public WFW_Scream() {
        super(NAME, "Scream", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {
        usedDown(getUpdatedDescriptions());
    }

    @Override
    public void onActivationInFight() {

        if (usedUp) return;

        AbstractPlayer player = AbstractDungeon.player;

        clearAllBuff(player);
        clearAllDebuff(player);

        targetAllMonsters(this::effectToMonster);

        flash();
        usedUp();
    }

    private Void effectToMonster(AbstractMonster m) {
        clearAllBuff(m);
        clearAllDebuff(m);
        return null;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0]);
    }
}
