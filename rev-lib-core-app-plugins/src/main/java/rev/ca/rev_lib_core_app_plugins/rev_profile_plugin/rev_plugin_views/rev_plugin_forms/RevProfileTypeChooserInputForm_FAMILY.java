package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms;

import android.content.Context;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_widgets.RevProfileTypeChooserInputFormWidget_FAMILY;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;

/**
 * Created by rev on 2/5/18.
 */

public class RevProfileTypeChooserInputForm_FAMILY implements IRevInputFormView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    public RevProfileTypeChooserInputForm_FAMILY(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();
    }

    @Override
    public View createRevInputForm() {
        this.revVarArgsData.setmContext(mContext);
        return new RevProfileTypeChooserInputFormWidget_FAMILY(revVarArgsData).getRevProfileTypeChooser();
    }

    @Override
    public RevEntity createRevInputFormData() {
        return null;
    }

    @Override
    public String revInputFormActionName() {
        return "RevBagTypeChooserInputFormAction";
    }

    @Override
    public RevVarArgsData REV_VAR_ARGS_DATA() {
        Map<Object, Object> revVarArgsDataMetadataStrings = new HashMap<>();
        revVarArgsDataMetadataStrings.put("submitFormTtlTxt", "title_override");

        revVarArgsData.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);

        revVarArgsData.setPopUpWindow_VARAGS(true);
        revVarArgsData.setOverrideFormFooter_VARAGS(true);

        return revVarArgsData;
    }
}
