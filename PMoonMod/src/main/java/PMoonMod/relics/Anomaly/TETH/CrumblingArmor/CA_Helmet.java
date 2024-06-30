package PMoonMod.relics.Anomaly.TETH.CrumblingArmor;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CA_Helmet extends PMoonRelic
{
    private static final String NAME =              "CA:Helmet";
    private static final RelicTier RARITY =         RelicTier.RARE;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.HEAVY;

    public CA_Helmet() {
        super(NAME, "Helmet", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {

        AbstractPlayer player = AbstractDungeon.player;

        if (player.currentBlock > 0 || usedUp || player.currentHealth > damageAmount) return damageAmount;

        damageAmount = 0;

        flash();
        usedUp();

        return damageAmount;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0]);
    }
}
