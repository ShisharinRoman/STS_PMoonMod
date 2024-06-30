package PMoonMod.relics.Anomaly.TETH.WomanFacingTheWall;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class WFW_Mumbling extends PMoonRelic
{
    private static final String NAME =              "WFW:Mumbling";
    private static final RelicTier RARITY =         RelicTier.RARE;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.HE;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int CHANCE_TO_ACTIVATE =       35;
    private static final int SINKING_AMT_TO_PLAYER =    1;
    private static final int SINKING_AMT_TO_TARGET =    2;
    private static final int PARALYSIS_AMT_TO_TARGET =  1;

    public WFW_Mumbling() {
        super(NAME, "Mumbling", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {

        if (info.type != DamageInfo.DamageType.NORMAL || info.owner.isPlayer) return damageAmount;

        int chance = AbstractDungeon.relicRng.random(1,100);

        if ( chance <= CHANCE_TO_ACTIVATE) {
            AbstractPlayer player = AbstractDungeon.player;

            // TODO Paralyse
            // TODO Sinking

            flash();
        }

        return damageAmount;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], CHANCE_TO_ACTIVATE, SINKING_AMT_TO_TARGET, PARALYSIS_AMT_TO_TARGET, SINKING_AMT_TO_PLAYER);
    }
}
