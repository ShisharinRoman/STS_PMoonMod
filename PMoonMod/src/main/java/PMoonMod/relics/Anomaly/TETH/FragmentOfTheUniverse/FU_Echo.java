package PMoonMod.relics.Anomaly.TETH.FragmentOfTheUniverse;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;


public class FU_Echo extends PMoonRelic
{
    private static final String NAME =              "FU:Echo";
    private static final RelicTier RARITY =         RelicTier.COMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int SINKING_AMT = 8;

    public FU_Echo() {
        super(NAME, "Echo", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {
        ArrayList<AbstractMonster> monsters = AbstractDungeon.getMonsters().monsters;
        int sinkingMax = SINKING_AMT;
        int monstersSize = monsters.size();

        for (int i = 0; i < monstersSize; i++) {

            AbstractMonster monster = monsters.get(i);

            if ( i == monstersSize - 1) {

                // TODO Sinking

                break;
            } else {
                int sinking = AbstractDungeon.relicRng.random(0, sinkingMax);
                sinkingMax -= sinking;

                // TODO Sinking

            }

            if (sinkingMax == 0) break;
        }

        flash();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], SINKING_AMT);
    }

}