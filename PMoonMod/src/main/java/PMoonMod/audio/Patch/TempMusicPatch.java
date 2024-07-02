package PMoonMod.audio.Patch;

import PMoonMod.PMoonMod;
import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.audio.TempMusic;

@SpirePatch(clz = TempMusic.class, method = "getSong")
public class TempMusicPatch {
    @SpirePrefixPatch
    public static SpireReturn<Music> Prefix(TempMusic instance, String key) {
        if (!key.contains(PMoonMod.modID)) return SpireReturn.Continue();

        return SpireReturn.Return(MainMusic.newMusic(key));
    }
}
