package PMoonMod.relics.Anomaly.TETH.SpiderBud;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SB_Cocoon extends PMoonRelic
{
    private static final String NAME =              "SB:Cocoon";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.HEAVY;

    private static final int PARALYZE_AMT =       1;
    private static final int FRAGILE_AMT =        1;
    private static final int CHANCE_TO_ACTIVATE = 33;

    public SB_Cocoon() {
        super(NAME, "Cocoon", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {

        if (info.type != DamageInfo.DamageType.NORMAL) return;

        int chance = AbstractDungeon.relicRng.random(0, 99);
        if ( chance > CHANCE_TO_ACTIVATE) return;

        AbstractPlayer player = AbstractDungeon.player;

        // TODO Paralysis power
        // TODO Fragile

        flash();
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], CHANCE_TO_ACTIVATE, PARALYZE_AMT, FRAGILE_AMT);
    }
}

