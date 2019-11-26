package rev.ca.rev_lib_core_app_plugins.rev_calendar_plugin.rev_plugin_views.rev_plugin_widget_views.rev_plugin_forms;

import android.view.View;

import java.util.HashMap;
import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.rev_calendar_plugin.rev_plugin_views.rev_plugin_widget_views.rev_plugin_forms.rev_input_form_widgets.RevCreateCalendarInputForm_WIDGETS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

/**
 * Created by rev on 1/3/18.
 */

public class RevCreateCalendarInputForm implements IRevInputFormView {

    private RevVarArgsData revVarArgsData;

    private RevCreateCalendarInputForm_WIDGETS revCreateCalendarInputForm_widgets;

    public RevCreateCalendarInputForm(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;

        revCreateCalendarInputForm_widgets = new RevCreateCalendarInputForm_WIDGETS(revVarArgsData);
    }

    @Override
    public View createRevInputForm() {
        return revCreateCalendarInputForm_widgets.getRevInputFormWidgetsContainer();
    }

    @Override
    public RevEntity createRevInputFormData() {
        return RevPersGenFunctions.SET_REV_OWNER_REV_CONTAINER_ENTITY_GUID(revCreateCalendarInputForm_widgets.revObjectFormdata(), revVarArgsData);
    }

    @Override
    public String revInputFormActionName() {
        return "RevPublishCalendarAction";
    }

    @Override
    public RevVarArgsData REV_VAR_ARGS_DATA() {
        Map<Object, Object> revVarArgsDataMetadataStrings = new HashMap<>();
        revVarArgsDataMetadataStrings.put("submitFormTtlTxt", "New calendar entry");

        revVarArgsData.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);
        revVarArgsData.setPopUpWindow_VARAGS(true);
        // revVarArgsData.setOverrideFormTitle_VARAGS(true);
        revVarArgsData.setOverrideFormFooter_VARAGS(true);

        return revVarArgsData;
    }
}
