package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.PluggableServicesCalls;

import android.view.View;

import java.util.List;

import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_blocks_service.IRevPluginRegisterTranslationBlock;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_items_service.RevPluginTranslationObject;

public class RevBAGsPluginRegisterTranslationBlock implements IRevPluginRegisterTranslationBlock {

    @Override
    public String getRevTranslationBlockName() {
        return "rev_spaces";
    }

    @Override
    public String getRevTranslationBloackTitle() {
        return "spAcEs";
    }

    @Override
    public long getRevTranslationBlockViewId() {
        return 0;
    }

    @Override
    public View getRevTranslationBlockView() {
        return null;
    }

    @Override
    public List<RevPluginTranslationObject> getiRevPluginRegisterTranslationObject_spis() {
        return null;
    }
}
