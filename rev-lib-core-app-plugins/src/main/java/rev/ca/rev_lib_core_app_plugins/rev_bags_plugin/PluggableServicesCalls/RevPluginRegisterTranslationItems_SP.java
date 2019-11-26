package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.PluggableServicesCalls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_items_service.IRevPluginRegisterTranslationItems_SPI;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_items_service.IRevPluginRegisterTranslationObject_SPI;

public class RevPluginRegisterTranslationItems_SP implements IRevPluginRegisterTranslationItems_SPI {
    @Override
    public List<IRevPluginRegisterTranslationObject_SPI> I_REV_PLUGIN_REGISTER_TRANSLATION_OBJECT_SPIS() {
        List<IRevPluginRegisterTranslationObject_SPI> iRevPluginRegisterTranslationObject_spis = new ArrayList<>();

        String revPluginName_S = "rev_bags_plugin";
        String revSpaceDesc_S = "A Space is a CampANn utility that helps Block of people keep in touch";

        List<String[]> revTranslationObjects = new ArrayList(Arrays.asList(
                new String[]{"rev_space", "Words & Phrases related to spAcEs . . . .", "", revPluginName_S, "rev_spaces"},
                new String[]{"rev_space", "pRoFiLE", "", revPluginName_S, "rev_spaces"},
                new String[]{"rev_space", "iNFo", "", revPluginName_S, "rev_spaces"},
                new String[]{"rev_space", "BRiEF sTuDy Topics", "", revPluginName_S, "rev_spaces"},
                new String[]{"rev_space_simple", "Space", revSpaceDesc_S, revPluginName_S, "rev_spaces"},
                new String[]{"rev_space", "spAcE", "Space letters Upper & Lower Case Mix", revPluginName_S, "rev_spaces"},
                new String[]{"rev_space", "cREATE NEw spAcE", "", revPluginName_S, "rev_spaces"},
                new String[]{"rev_space", "sHARE picTuREs, viDEos, impoRTANT DATEs oR EvENTs wiTH FRiENDs/ FAmiLy/ coLLEAGues viA spAcEs", "", revPluginName_S, "rev_spaces"},
                new String[]{"rev_space", "spAcEs I HAvE JoiNED", "", revPluginName_S, "rev_spaces"},
                new String[]{"rev_space", "i HAvE NoT yET JoiNED ANY spAcEs.", "", revPluginName_S, "rev_spaces"},
                new String[]{"rev_space", "spAcEs {revUser} HAs JoiNED", "", revPluginName_S, "rev_spaces"},
                new String[]{"rev_space", "REccommENDED spAcEs", "", revPluginName_S, "rev_spaces"},
                new String[]{"rev_space", "THERE ARE No suGGEsTioNs so FAR....", "", revPluginName_S, "rev_spaces"},
                new String[]{"rev_space", "No AcTiviTiEs posTED yET. pLEAsE puBLisH somETHiNG.", "", revPluginName_S, "rev_spaces"},
                new String[]{"rev_space", "No AcTiviTiEs posTED yET FRom {revUser}", "", revPluginName_S, "rev_spaces"},
                new String[]{"rev_space", "BRiEF sTuDy Topics", "", revPluginName_S, "rev_spaces"},
                new String[]{"rev_space", "READ moRE", "", revPluginName_S, "rev_spaces"}
        ));

        for (String[] strings : revTranslationObjects) {
            iRevPluginRegisterTranslationObject_spis.add(new RevPluginRegisterTranslationObject_SP(strings));
        }

        return iRevPluginRegisterTranslationObject_spis;
    }

    private class RevPluginRegisterTranslationObject_SP implements IRevPluginRegisterTranslationObject_SPI {

        private String revTranslationItemName, revTranslationItemValue, revTranslationItemValueDesc, revTranslationPluginName, revTranslationPluginBlockName;

        public RevPluginRegisterTranslationObject_SP(String[] strings) {
            if (strings.length > 0)
                this.revTranslationItemName = strings[0];

            if (strings.length > 1)
                this.revTranslationItemValue = strings[1];

            if (strings.length > 2)
                this.revTranslationItemValueDesc = strings[2];

            if (strings.length > 3)
                this.revTranslationPluginName = strings[3];

            if (strings.length > 4)
                this.revTranslationPluginBlockName = strings[4];
        }

        @Override
        public String getRevTranslationItemName() {
            return revTranslationItemName;
        }

        @Override
        public String getRevTranslationItemValue() {
            return revTranslationItemValue;
        }

        @Override
        public String getRevTranslationItemValueDesc() {
            return revTranslationItemValueDesc;
        }

        @Override
        public String getRevTranslationPluginName() {
            return revTranslationPluginName;
        }

        @Override
        public String getRevTranslationPluginBlockName() {
            return revTranslationPluginBlockName;
        }
    }
}
