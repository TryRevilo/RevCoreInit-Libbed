package rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_blocks_service;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_pluggable_services.rev_pluggable_translation_items_service.RevPluginTranslationObject;

public class RevPluginTranslationBlock implements IRevPluginRegisterTranslationBlock {

    private long revPluginTranslationBlockGUID = -1l;

    private String revTranslationBlockName, revTranslationBloackTitle;
    private long revTranslationBlockViewId;
    private View revTranslationBlockView;

    private List<RevPluginTranslationObject> iRevPluginRegisterTranslationObject_spis = new ArrayList<>();

    public RevPluginTranslationBlock() {
    }

    public long getRevPluginTranslationBlockGUID() {
        return revPluginTranslationBlockGUID;
    }

    public void setRevPluginTranslationBlockGUID(long revPluginTranslationBlockGUID) {
        this.revPluginTranslationBlockGUID = revPluginTranslationBlockGUID;
    }

    @Override
    public String getRevTranslationBlockName() {
        return revTranslationBlockName;
    }

    public void setRevTranslationBlockName(String revTranslationBlockName) {
        this.revTranslationBlockName = revTranslationBlockName;
    }

    @Override
    public String getRevTranslationBloackTitle() {
        return revTranslationBloackTitle;
    }

    public void setRevTranslationBloackTitle(String revTranslationBloackTitle) {
        this.revTranslationBloackTitle = revTranslationBloackTitle;
    }

    @Override
    public long getRevTranslationBlockViewId() {
        return revTranslationBlockViewId;
    }

    public void setRevTranslationBlockViewId(long revTranslationBlockViewId) {
        this.revTranslationBlockViewId = revTranslationBlockViewId;
    }

    @Override
    public View getRevTranslationBlockView() {
        return revTranslationBlockView;
    }

    public void setRevTranslationBlockView(View revTranslationBlockView) {
        this.revTranslationBlockView = revTranslationBlockView;
    }

    @Override
    public List<RevPluginTranslationObject> getiRevPluginRegisterTranslationObject_spis() {
        return iRevPluginRegisterTranslationObject_spis;
    }

    public void setiRevPluginRegisterTranslationObject_spis(List<RevPluginTranslationObject> iRevPluginRegisterTranslationObject_spis) {
        this.iRevPluginRegisterTranslationObject_spis = iRevPluginRegisterTranslationObject_spis;
    }
}
