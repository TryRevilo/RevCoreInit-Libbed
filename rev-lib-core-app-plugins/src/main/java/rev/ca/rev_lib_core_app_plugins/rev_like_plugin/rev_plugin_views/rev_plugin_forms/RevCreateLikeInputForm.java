package rev.ca.rev_lib_core_app_plugins.rev_like_plugin.rev_plugin_views.rev_plugin_forms;

import android.view.View;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.rev_like_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_input_form_widgets.RevCreateRevLikeInputForm_WIDGETS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;

/**
 * Created by rev on 1/3/18.
 */

public class RevCreateLikeInputForm implements IRevInputFormView {

    private RevVarArgsData revVarArgsData;

    public RevCreateLikeInputForm(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
    }

    @Override
    public View createRevInputForm() {
        return new RevCreateRevLikeInputForm_WIDGETS(revVarArgsData).getRevInputForm();
    }

    @Override
    public RevEntity createRevInputFormData() {
        return revVarArgsData.getRevEntity();
    }

    @Override
    public String revInputFormActionName() {
        return "RevPublishLikeAction";
    }

    @Override
    public RevVarArgsData REV_VAR_ARGS_DATA() {
        revVarArgsData.setOverrideFormFooter_VARAGS(true);
        return revVarArgsData;
    }
}
