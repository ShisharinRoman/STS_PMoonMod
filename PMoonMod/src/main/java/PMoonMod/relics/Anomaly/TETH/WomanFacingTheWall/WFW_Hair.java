package PMoonMod.relics.Anomaly.TETH.WomanFacingTheWall;

import PMoonMod.powers.Default.Sinking;
import PMoonMod.relics.System.PMoonRelic;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WFW_Hair extends PMoonRelic
{
    private static final String NAME =              "WFW:Hair";
    private static final RelicTier RARITY =         RelicTier.UNCOMMON;
    private static final DangerLevel DANGER_LEVEL = DangerLevel.TETH;
    private static final LandingSound SOUND =       LandingSound.MAGICAL;

    private static final int SINKING_AMT = 3;

    public WFW_Hair() {
        super(NAME, "Hair", RARITY, DANGER_LEVEL, SOUND);
    }

    @Override
    public void onExhaust(AbstractCard card) {
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        addToTop(new ApplyPowerAction(m, null, new Sinking(m, SINKING_AMT)));
    }

    @Override
    public String getUpdatedDescription(int index) {
        return String.format(DESCRIPTIONS[0], SINKING_AMT);
    }
}
