package rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_items_service;

public interface IRevPluginRegisterTranslationObject_SPI {

    String getRevTranslationItemName();

    String getRevTranslationItemValue();

    String getRevTranslationItemValueDesc();

    String getRevTranslationPluginName();

    String getRevTranslationPluginBlockName();
}
