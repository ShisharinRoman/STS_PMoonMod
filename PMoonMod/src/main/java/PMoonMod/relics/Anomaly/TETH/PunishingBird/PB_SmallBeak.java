package PMoonMod.relics.Anomaly.TETH.PunishingBird;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PB_SmallBeak extends PMoonRelic
{
    private static final String NAME =              "PB:SmallBeak";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.CLINK;

    private static final int ENERGY_GAIN =      1;
    private static final int DAMAGE =           5;

    public PB_SmallBeak() {
        super(NAME, "SmallBeak", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onActivationInFight() {

        addToTop(new GainEnergyAction(ENERGY_GAIN));
        AbstractPlayer player = AbstractDungeon.player;
        addToTop(new DamageAction(player, new DamageInfo(player, DAMAGE, DamageInfo.DamageType.HP_LOSS)));

        flash();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], ENERGY_GAIN, DAMAGE);
    }

}
