package rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_forms;

import android.content.Context;
import android.view.View;

import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_forms.rev_plugin_form_widgets.RevCreateUserLoginForm_WIDGETS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

/**
 * Created by rev on 10/8/17.
 */

public class RevCreateUserLoginForm implements IRevInputFormView {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCreateUserLoginForm_WIDGETS revCreateUserLoginForm_widgets;

    public RevCreateUserLoginForm(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revCreateUserLoginForm_widgets = new RevCreateUserLoginForm_WIDGETS(this.mContext);
    }

    @Override
    public View createRevInputForm() {
        return revCreateUserLoginForm_widgets.getPageForm();
    }

    @Override
    public RevEntity createRevInputFormData() {
        return revCreateUserLoginForm_widgets.revObjectFormdata();
    }

    @Override
    public String revInputFormActionName() {
        return "RevCreateUserLoginAction";
    }

    @Override
    public RevVarArgsData REV_VAR_ARGS_DATA() {
        revVarArgsData.setPopUpWindow_VARAGS(true);
        revVarArgsData.setOverrideFormFooter_VARAGS(true);

        return revVarArgsData;
    }
}
