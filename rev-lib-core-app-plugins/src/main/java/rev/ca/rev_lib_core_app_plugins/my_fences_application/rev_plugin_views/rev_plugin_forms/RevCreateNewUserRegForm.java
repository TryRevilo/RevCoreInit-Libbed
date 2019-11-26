package rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_forms;

import android.content.Context;
import android.view.View;

import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_forms.rev_plugin_form_widgets.RevCreateNewUserRegForm_WIDGETS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;

/**
 * Created by rev on 10/8/17.
 */

public class RevCreateNewUserRegForm implements IRevInputFormView {

    private Context mContext;
    private RevVarArgsData revVarArgsData;

    private RevCreateNewUserRegForm_WIDGETS revCreateNewUserRegForm_widgets;

    public RevCreateNewUserRegForm(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revCreateNewUserRegForm_widgets = new RevCreateNewUserRegForm_WIDGETS(this.mContext);
    }

    @Override
    public View createRevInputForm() {
        return revCreateNewUserRegForm_widgets.getPageForm();
    }

    @Override
    public RevEntity createRevInputFormData() {
        return revCreateNewUserRegForm_widgets.revObjectFormdata();
    }

    @Override
    public String revInputFormActionName() {
        return "RevCreateNewUserRegAction";
    }

    @Override
    public RevVarArgsData REV_VAR_ARGS_DATA() {
        revVarArgsData.setPopUpWindow_VARAGS(true);
        revVarArgsData.setOverrideFormFooter_VARAGS(true);

        return revVarArgsData;
    }
}
