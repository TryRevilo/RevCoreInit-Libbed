package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_bag_types_forms;

import android.view.View;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_widgets.RevCreateWorkBAGInputFormWidget;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;

/**
 * Created by rev on 12/18/17.
 */

public class RevCreateNewWorkBAGInputForm implements IRevInputFormView {

    private RevVarArgsData revVarArgsData;

    private RevCreateWorkBAGInputFormWidget revCreateWorkBAGInputFormWidget;

    public RevCreateNewWorkBAGInputForm(RevVarArgsData revVarArgsData) {
        revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
    }

    @Override
    public View createRevInputForm() {
        this.revCreateWorkBAGInputFormWidget = new RevCreateWorkBAGInputFormWidget(revVarArgsData);
        return revCreateWorkBAGInputFormWidget.revInputFormView();
    }

    @Override
    public RevEntity createRevInputFormData() {
        return revCreateWorkBAGInputFormWidget.getRevEntityData();
    }

    @Override
    public String revInputFormActionName() {
        return "RevPublishBagAction";
    }

    @Override
    public RevVarArgsData REV_VAR_ARGS_DATA() {
        revVarArgsData.getRevVarArgsDataMetadataStrings().put("submitFormTtlTxt", "cREATE NEw spAcE");
        revVarArgsData.setPopUpWindow_VARAGS(true);
        revVarArgsData.setOverrideFormFooter_VARAGS(true);
        return revVarArgsData;
    }
}
