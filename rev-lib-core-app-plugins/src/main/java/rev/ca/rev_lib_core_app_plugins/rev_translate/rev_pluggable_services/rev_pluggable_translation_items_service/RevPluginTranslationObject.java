package rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_items_service;

public class RevPluginTranslationObject implements IRevPluginRegisterTranslationObject_SPI {

    private long revPluginTranslationObjectGUID = -1l;
    private String revTranslationItemName, revTranslationItemValue, revTranslationItemValueDesc, revTranslationPluginName, revTranslationPluginBlockName;

    public RevPluginTranslationObject() {
    }

    public long getRevPluginTranslationObjectGUID() {
        return revPluginTranslationObjectGUID;
    }

    public void setRevPluginTranslationObjectGUID(long revPluginTranslationObjectGUID) {
        this.revPluginTranslationObjectGUID = revPluginTranslationObjectGUID;
    }

    @Override
    public String getRevTranslationItemName() {
        return revTranslationItemName;
    }

    public void setRevTranslationItemName(String revTranslationItemName) {
        this.revTranslationItemName = revTranslationItemName;
    }

    @Override
    public String getRevTranslationItemValue() {
        return revTranslationItemValue;
    }

    public void setRevTranslationItemValue(String revTranslationItemValue) {
        this.revTranslationItemValue = revTranslationItemValue;
    }

    @Override
    public String getRevTranslationItemValueDesc() {
        return revTranslationItemValueDesc;
    }

    public void setRevTranslationItemValueDesc(String revTranslationItemValueDesc) {
        this.revTranslationItemValueDesc = revTranslationItemValueDesc;
    }

    @Override
    public String getRevTranslationPluginName() {
        return revTranslationPluginName;
    }

    public void setRevTranslationPluginName(String revTranslationPluginName) {
        this.revTranslationPluginName = revTranslationPluginName;
    }

    @Override
    public String getRevTranslationPluginBlockName() {
        return revTranslationPluginBlockName;
    }

    public void setRevTranslationPluginBlockName(String revTranslationPluginBlockName) {
        this.revTranslationPluginBlockName = revTranslationPluginBlockName;
    }
}
