package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_actions;

import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.RevSubmitFormViewContainer;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.IRevPersAction;

/**
 * Created by rev on 2/5/18.
 */

public class RevBagTypeChooserInputFormAction implements IRevPersAction {

    @Override
    public String registerRevActionName() {
        return "RevBagTypeChooserInputFormAction";
    }

    @Override
    public Object initRevAction(RevEntity revEntity) {
        RevVarArgsData revVarArgsData = new RevVarArgsData(RevLibGenConstantine.REV_CONTEXT);
        revVarArgsData.setRevViewType("RevCreateNewUserRegForm");
        IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revVarArgsData);
        new RevPop().initiatePopupWindow(new RevSubmitFormViewContainer(RevLibGenConstantine.REV_CONTEXT).getRevSubmitFormViewContainer(iRevInputFormView));

        return null;
    }
}
