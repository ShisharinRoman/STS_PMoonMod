package PMoonMod.relics.Anomaly.ZAYIN.WellCheers;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.GainGoldTextEffect;

public class WC_Advertiser extends PMoonRelic
{
    private static final String NAME =              "WC:Advertiser";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.ZAYIN;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int GOLD_REWARD = 5;

    public WC_Advertiser() {
        super(NAME, "Advertiser", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {

        if (target.currentHealth > damageAmount || info.type != DamageInfo.DamageType.NORMAL) return;

        AbstractDungeon.effectList.add(new GainGoldTextEffect(GOLD_REWARD));
        AbstractDungeon.player.gainGold(GOLD_REWARD);

    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], GOLD_REWARD);
    }
}
