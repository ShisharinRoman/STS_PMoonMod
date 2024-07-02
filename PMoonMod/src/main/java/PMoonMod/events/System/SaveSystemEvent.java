package PMoonMod.events.System;

import basemod.BaseMod;
import basemod.abstracts.CustomSavable;

import java.util.HashMap;

public final class SaveSystemEvent {

    private static HashMap<String, Boolean> researches;

    public static void initializeSave() {

        BaseMod.addSaveField("AnomalyResearches", new CustomSavable<HashMap<String, Boolean>>() {
            @Override
            public HashMap<String, Boolean> onSave() {
                return researches;
            }

            @Override
            public void onLoad(HashMap<String, Boolean> _researches) {

                if (_researches == null) {
                    researches = new HashMap<>();
                    return;
                }
                researches = _researches;
            }
        });

        initializeSpecialSave();
    }

    public static boolean[] loadResearches(String id, int numb) {

        boolean[] res = new boolean[numb];

        for (int i = 0; i < numb; i++) {

            String researchId = researchId(id, i);

            if (!researches.containsKey(researchId)) continue;

            res[i] = researches.get(researchId);
        }

        return res;
    }

    public static void setResearch(String id, int index) {
        setResearch(id, index, true);
    }

    public static void setResearch(String id, int index, Boolean research) {
        researches.put(researchId(id, index), research);
    }

    private static String researchId(String id, int index) {
        String separator = ":";
        return id + separator + index;
    }

    private static void initializeSpecialSave() {

    }
}
