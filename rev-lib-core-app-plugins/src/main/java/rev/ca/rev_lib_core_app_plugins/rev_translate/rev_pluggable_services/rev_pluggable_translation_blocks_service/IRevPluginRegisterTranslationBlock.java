package rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_blocks_service;

import android.view.View;

import java.util.List;

import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_items_service.RevPluginTranslationObject;

public interface IRevPluginRegisterTranslationBlock {

    String getRevTranslationBlockName();

    String getRevTranslationBloackTitle();

    long getRevTranslationBlockViewId();

    View getRevTranslationBlockView();

    List<RevPluginTranslationObject> getiRevPluginRegisterTranslationObject_spis();
}
