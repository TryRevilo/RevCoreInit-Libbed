package rev.ca.rev_lib_core_app_plugins.rev_translate.rev_plugin_views.rev_plugin_forms;

import android.view.View;

import java.util.HashMap;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.rev_translate.rev_plugin_views.rev_plugin_forms.rev_input_form_widgets.RevCreateNewLanguageInputForm_WIDGET;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;

public class RevCreateNewLanguageInputForm implements IRevInputFormView {

    private RevVarArgsData revVarArgsData;

    private RevCreateNewLanguageInputForm_WIDGET revCreateNewLanguageInputForm_widget;

    public RevCreateNewLanguageInputForm(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.revCreateNewLanguageInputForm_widget = new RevCreateNewLanguageInputForm_WIDGET(revVarArgsData);
    }

    @Override
    public View createRevInputForm() {
        return this.revCreateNewLanguageInputForm_widget.getRevInputFormWidgetsContainer();
    }

    @Override
    public RevEntity createRevInputFormData() {
        return revCreateNewLanguageInputForm_widget.getRevEntityFormData();
    }

    @Override
    public String revInputFormActionName() {
        return "RevCreateNewLanguageInputFormAction";
    }

    @Override
    public RevVarArgsData REV_VAR_ARGS_DATA() {
        Map<Object, Object> revVarArgsDataMetadataStrings = new HashMap<>();
        revVarArgsDataMetadataStrings.put("submitFormTtlTxt", "puBLisH NEw LANGuAGE");

        revVarArgsData.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);

        return revVarArgsData;
    }
}
