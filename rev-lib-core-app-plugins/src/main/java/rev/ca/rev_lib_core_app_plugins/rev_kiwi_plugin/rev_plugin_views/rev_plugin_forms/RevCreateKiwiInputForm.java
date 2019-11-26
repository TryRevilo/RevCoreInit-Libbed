package rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_plugin_forms;

import android.view.View;

import rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_widgets.RevCreateKiwiInputForm_WIDGETS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

/**
 * Created by rev on 1/3/18.
 */

public class RevCreateKiwiInputForm implements IRevInputFormView {

    private RevVarArgsData revVarArgsData;

    private RevCreateKiwiInputForm_WIDGETS revCreateKiwiInputForm_widgets;

    public RevCreateKiwiInputForm(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;

        revCreateKiwiInputForm_widgets = new RevCreateKiwiInputForm_WIDGETS(revVarArgsData);
    }

    @Override
    public View createRevInputForm() {
        return revCreateKiwiInputForm_widgets.getRevkiwiInputForm();
    }

    @Override
    public RevEntity createRevInputFormData() {
        revCreateKiwiInputForm_widgets.revObjectFormdata().set_revEntityContainerGUID(revVarArgsData.getRevContainerEntityGUID());
        return revCreateKiwiInputForm_widgets.revObjectFormdata();
    }

    @Override
    public String revInputFormActionName() {
        return "RevPublishKiwiAction";
    }

    @Override
    public RevVarArgsData REV_VAR_ARGS_DATA() {
        return revVarArgsData;
    }
}
