package rev.ca.rev_lib_core_app_plugins.rev_plugins_plugin.rev_pluggable_services;

import android.util.Log;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLangStrings;

public class RevPersSavePluginItemObjects {

    public void revSavePlugins() {
        RevPersJava revPersJava = new RevPersJava();
        RevPersLibRead revPersLibRead = new RevPersLibRead();

        for (IRevPluginRegisterRevPluginItem_SPI iRevPluginRegisterRevPluginItem_spi : IRevPluginRegisterRevPluginItem_SP_Loader.revGetPluggableItems()) {
            RevPluginItemObject revPluginItemObject = new RevPluginItemObject();
            revPluginItemObject.setRevPluginId(iRevPluginRegisterRevPluginItem_spi.getRevPluginId());
            revPluginItemObject.setRevPluginName(iRevPluginRegisterRevPluginItem_spi.getRevPluginName());
            revPluginItemObject.setRevPluginDescription(iRevPluginRegisterRevPluginItem_spi.getRevPluginDescription());
            revPluginItemObject.setRevPluginFeatures(iRevPluginRegisterRevPluginItem_spi.getRevPluginFeatures());
            revPluginItemObject.setRevPluginDependencies(iRevPluginRegisterRevPluginItem_spi.getRevPluginDependencies());
            revPluginItemObject.setRevPluginPublisher(iRevPluginRegisterRevPluginItem_spi.getRevPluginPublisher());

            long revMetadataEntityGUID = revPersLibRead.revGetRevEntityMetadataOwnerGUID_By_MetadataName_MetadataValue("rev_plugin_id", revPluginItemObject.getRevPluginId());

            if (revMetadataEntityGUID > 0) {
                String revMetadataDBValue_S = revPersLibRead.revGetRevEntityMetadataValue_By_RevMetadataName_RevEntityGUID("rev_plugin_id", revMetadataEntityGUID);

                if (!REV_STRINGS_BASE_FUNCTIONS.REV_IS_NULL_OR_EMPTY_STRING(revMetadataDBValue_S)) {
                    Log.v(RevLangStrings.REV_TAG, "Plugin Exists : " + revMetadataDBValue_S);
                }
            } else {
                long revEntityGUID = (long) revPersJava.saveRevEntity_NO_REMOTE_SYNC(revPluginItemObject.getRevPluginItemObjectEntity());
                Log.v(RevLangStrings.REV_TAG, "revEntityGUID >>> " + revEntityGUID);
            }
        }
    }
}
