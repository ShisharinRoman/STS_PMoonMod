package PMoonMod.relics.Anomaly.TETH.OldLady;

import PMoonMod.powers.Default.Sinking;
import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class OL_Loneliness extends PMoonRelic
{
    private static final String NAME =              "OL:Loneliness";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private final int SINKING_AMT =         3;
    private final int MAX_LIVE_MONSTERS =   1;

    public OL_Loneliness() {
        super(NAME, "Loneliness", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atTurnStart() {

        ArrayList<AbstractMonster> monsters =   AbstractDungeon.getMonsters().monsters;
        AbstractMonster liveMonster =           null;

        int numbLiveMonsters = 0;

        for (AbstractMonster monster : monsters) {
            if (!monster.isDead) {
                liveMonster = monster;
                numbLiveMonsters++;
            }
        }

        if (numbLiveMonsters == MAX_LIVE_MONSTERS ) {
            addToTop(new ApplyPowerAction(liveMonster, null, new Sinking(liveMonster, SINKING_AMT)));
            flash();
        }
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], MAX_LIVE_MONSTERS, SINKING_AMT);
    }
}
