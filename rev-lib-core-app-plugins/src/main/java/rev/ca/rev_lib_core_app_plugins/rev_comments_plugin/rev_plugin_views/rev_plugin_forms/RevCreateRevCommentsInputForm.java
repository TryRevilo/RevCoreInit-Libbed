package rev.ca.rev_lib_core_app_plugins.rev_comments_plugin.rev_plugin_views.rev_plugin_forms;

import android.view.View;

import rev.ca.rev_lib_core_app_plugins.rev_comments_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_input_form_widgets.RevCreateRevCommentInputForm_WIDGETS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

/**
 * Created by rev on 1/3/18.
 */

public class RevCreateRevCommentsInputForm implements IRevInputFormView {

    private RevVarArgsData revVarArgsData;

    private RevCreateRevCommentInputForm_WIDGETS revCreateRevCommentInputForm_widgets;

    public RevCreateRevCommentsInputForm(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        revCreateRevCommentInputForm_widgets = new RevCreateRevCommentInputForm_WIDGETS(revVarArgsData);
    }

    @Override
    public View createRevInputForm() {
        return revCreateRevCommentInputForm_widgets.getRevInputForm();
    }

    @Override
    public RevEntity createRevInputFormData() {
        return revCreateRevCommentInputForm_widgets.revObjectFormdata();
    }

    @Override
    public String revInputFormActionName() {
        return "RevPublishCommentAction";
    }

    @Override
    public RevVarArgsData REV_VAR_ARGS_DATA() {
        revVarArgsData.setOverrideFormFooter_VARAGS(true);
        return revVarArgsData;
    }
}
