package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms;

import android.view.View;

import java.util.HashMap;
import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_widgets.RevEditRevEntityInfoForm_WIDGET;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

public class RevCreateRevEditInfoFormObject implements IRevInputFormView {

    private RevVarArgsData revVarArgsData;

    RevEditRevEntityInfoForm_WIDGET revEditRevEntityInfoFormWIDGET;

    public RevCreateRevEditInfoFormObject(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        revEditRevEntityInfoFormWIDGET = new RevEditRevEntityInfoForm_WIDGET(revVarArgsData);
    }

    @Override
    public View createRevInputForm() {
        return revEditRevEntityInfoFormWIDGET.getRevEditInfoForm();
    }

    @Override
    public RevEntity createRevInputFormData() {
        return revEditRevEntityInfoFormWIDGET.getRevEntityFormData();
    }

    @Override
    public String revInputFormActionName() {
        return "RevPublishRevEntityInfoAction";
    }

    @Override
    public RevVarArgsData REV_VAR_ARGS_DATA() {
        Map<Object, Object> revVarArgsDataMetadataStrings = new HashMap<>();
        revVarArgsDataMetadataStrings.put("submitFormTtlTxt", "Set up your profile info");

        revVarArgsData.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);
        revVarArgsData.setOverrideFormFooter_VARAGS(true);

        return revVarArgsData;
    }
}
