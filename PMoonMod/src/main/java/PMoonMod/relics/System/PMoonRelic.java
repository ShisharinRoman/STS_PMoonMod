package PMoonMod.relics.System;

import PMoonMod.PMoonMod;
import PMoonMod.util.DangerLevel;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;
import java.util.function.Function;

public abstract class PMoonRelic extends BaseRelic {

    public DangerLevel  dangerLevel;
    public int          level;
    public int          maxLevel;

    protected boolean   isActivated;
    private boolean     isJustActivated;

    public PMoonRelic(String id, String imageName, RelicTier tier, DangerLevel dangerLevel, LandingSound sfx) {

        super(id, PMoonMod.addDangerLevelPath(imageName, dangerLevel), tier, sfx);

        this.level =        0;
        this.maxLevel =     0;
        this.dangerLevel =  dangerLevel;
    }

    public void onActivation() {
    }

    public void onActivationInRun() {
    }

    public void onActivationInFight() {
    }

    @Override
    public void update() {

        super.update();
        updateActivationRelic();

        if (isActivated && !usedUp) {
            onActivation();
        }

        if (!CardCrawlGame.isInARun()) return;

        if (isActivated && !usedUp) {
            onActivationInRun();
        }

        if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) return;

        if (isActivated && !usedUp) {
            onActivationInFight();
        }
    }

    protected void targetAllMonsters(Function<AbstractMonster, Void> functionEffect) {

        ArrayList<AbstractMonster> monsters = AbstractDungeon.getMonsters().monsters;

        for (AbstractMonster monster : monsters) {
            functionEffect.apply(monster);
        }
    }

    protected void targetRandomMonsters(Function<ArrayList<AbstractMonster>, Void> function, int numb) {

        ArrayList<AbstractMonster> monsters = AbstractDungeon.getMonsters().monsters;

        if ( monsters.size() <= numb) {
            function.apply(monsters);
            return;
        }

        ArrayList<AbstractMonster> selectMonsters = new ArrayList<>();

        for (int i = 0; i < numb; i++) {
            int index =         AbstractDungeon.relicRng.random(0, monsters.size()-1);
            AbstractMonster m = monsters.get(index);
            selectMonsters.add(m);
            monsters.remove(index);
        }

        function.apply(selectMonsters);
    }

    protected void clearAllDebuff(AbstractCreature target)  {

        ArrayList<AbstractPower> debuffs = getDebuffs(target);

        for (AbstractPower debuff : debuffs) {
            addToTop(new RemoveSpecificPowerAction(target, target, debuff.ID));
        }
    }

    protected void clearRandomDebuff(AbstractCreature target) {

        ArrayList<AbstractPower> debuffs = getDebuffs(target);

        if (debuffs.isEmpty()) return;

        AbstractPower debuff = debuffs.get(AbstractDungeon.miscRng.random(0, debuffs.size() - 1));
        addToTop(new RemoveSpecificPowerAction(target, target, debuff.ID));
    }

    protected void clearAllBuff(AbstractCreature target)  {

        ArrayList<AbstractPower> buffs = getBuffs(target);

        for (AbstractPower buff : buffs) {
            addToTop(new RemoveSpecificPowerAction(target, target, buff.ID));
        }
    }

    protected void clearRandomBuff(AbstractCreature target) {

        ArrayList<AbstractPower> buffs = getBuffs(target);

        if (buffs.isEmpty()) return;

        AbstractPower buff = buffs.get(AbstractDungeon.miscRng.random(0, buffs.size() - 1));
        addToTop(new RemoveSpecificPowerAction(target, target, buff.ID));
    }

    protected void usedDown(String[] descriptions) {

        grayscale = false;
        usedUp = false;
        updateTips();
        updateAllDescriptions(descriptions);
        initializeTips();
    }

    protected void updateTips() {
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    protected void addDescription() {
        this.tips.add(new PowerTip(this.name, description));
    }

    protected void updateAllDescriptions(String[] descriptions) {

        for (int i = 0; i < tips.size() && i < descriptions.length; i++) {
            updateDescription(descriptions[i], i);
        }
    }

    protected void updateDescription(String description) {

        this.description = description;
        updateDescription(description, 0);
        initializeTips();
    }

    protected void updateDescription(String description, int index) {

        PowerTip tip = this.tips.get(index);
        tip.body = description;
    }

    private void updateActivationRelic() {

        if (InputHelper.justReleasedClickRight && this.isJustActivated) {
            if (hb.hovered) {
                this.isActivated = true;
            }
            this.isJustActivated = false;
        }

        if (hb.hovered && InputHelper.justClickedRight) {
            this.isJustActivated = true;
        }
    }

    private ArrayList<AbstractPower> getDebuffs(AbstractCreature target) {

        ArrayList<AbstractPower> result =  new ArrayList<>();

        for (AbstractPower p : target.powers) {
            if (p.type == AbstractPower.PowerType.DEBUFF) result.add(p);
        }
        return result;
    }

    private ArrayList<AbstractPower> getBuffs(AbstractCreature target) {

        ArrayList<AbstractPower> result =  new ArrayList<>();

        for (AbstractPower p : target.powers) {
            if (p.type == AbstractPower.PowerType.BUFF) result.add(p);
        }
        return result;
    }
}
