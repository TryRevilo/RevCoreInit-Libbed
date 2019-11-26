package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.rev_profile_types.rev_social_profile;

import android.view.View;

import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_widgets.rev_social_profile.RevSocialProfileInputFormWidget;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

/**
 * Created by rev on 2/23/18.
 */

public class RevCreateInputFormRevProfileSocialCircle implements IRevInputFormView {

    private RevSocialProfileInputFormWidget revSocialProfileInputFormWidget;

    public RevCreateInputFormRevProfileSocialCircle(RevVarArgsData revVarArgsData) {
        revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);

        revSocialProfileInputFormWidget = new RevSocialProfileInputFormWidget(revVarArgsData);
    }

    @Override
    public View createRevInputForm() {
        return revSocialProfileInputFormWidget.getRevSocialProfileInputFormWidget();
    }

    @Override
    public RevEntity createRevInputFormData() {
        return revSocialProfileInputFormWidget.revContainerEntityFormdata();
    }

    @Override
    public String revInputFormActionName() {
        return "RevPublishProfileSocialCircleAction";
    }

    @Override
    public RevVarArgsData REV_VAR_ARGS_DATA() {
        return null;
    }
}
