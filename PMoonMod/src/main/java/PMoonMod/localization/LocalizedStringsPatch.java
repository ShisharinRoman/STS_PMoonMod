package PMoonMod.localization;

import PMoonMod.PMoonMod;
import PMoonMod.events.System.EventAnomalyStrings;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.LocalizedStrings;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static PMoonMod.PMoonMod.logger;

public final class LocalizedStringsPatch {

    private static Map<String, EventAnomalyStrings> eventsAnomaly;

    private static final String EVENT_ANOMALY_STRINGS = "EventAnomalyStrings.json";

    @SpirePatch(clz = LocalizedStrings.class, method = SpirePatch.CONSTRUCTOR)
    public static class LocalizedStringsConstructorPatch {
        @SpirePostfixPatch
        public static void Postfix(LocalizedStrings instance) {
            Gson gson = new Gson();
            String lang;

            switch (Settings.language) {
                case ENG:
                    lang = "eng";
                    break;
                case RUS:
                    lang = "rus";
                    break;
                default:
                    lang = "www";
            }

            String eventAnomalyPath = PMoonMod.localizationPath(lang, EVENT_ANOMALY_STRINGS);
            Type eventAnomalyType = (new TypeToken<Map<String, EventAnomalyStrings>>() {}).getType();
            String loadJson = Gdx.files.internal(eventAnomalyPath).readString(String.valueOf(StandardCharsets.UTF_8));
            eventsAnomaly = gson.fromJson(loadJson, eventAnomalyType);
        }
    }

    public static EventAnomalyStrings getEventAnomalyString(String eventName) {
        if (eventsAnomaly.containsKey(eventName)) {
            return eventsAnomaly.get(eventName);
        } else {
            logger.info("[ERROR] EventAnomalyString: {} not found", eventName);
            return EventAnomalyStrings.getMockEventAnomalyString();
        }
    }

}






