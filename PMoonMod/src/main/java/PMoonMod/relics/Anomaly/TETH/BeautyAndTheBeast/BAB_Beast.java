package PMoonMod.relics.Anomaly.TETH.BeautyAndTheBeast;

import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.GainGoldTextEffect;

public class BAB_Beast extends PMoonRelic
{
    private static final    String NAME =               "BAB:Beast";
    private static final    RelicTier RARITY =          RelicTier.UNCOMMON;
    private static final    DangerLevel DANGER_LEVEL =  DangerLevel.TETH;
    private static final    LandingSound SOUND =        LandingSound.HEAVY;

    private static final int STRENGTH_AMT = 1;
    private static final int GOLD_REWARD =  20;

    public BAB_Beast() {
        super(NAME, "Beast", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void atBattleStart() {

        targetAllMonsters(this::effectToMonster);

        AbstractDungeon.effectList.add(new GainGoldTextEffect(GOLD_REWARD));
        AbstractDungeon.player.gainGold(GOLD_REWARD);

        flash();
    }

    private Void effectToMonster(AbstractMonster m) {
        addToTop(new ApplyPowerAction(m, AbstractDungeon.player, new StrengthPower(m, STRENGTH_AMT)));
        return null;
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], STRENGTH_AMT, GOLD_REWARD);
    }

}
