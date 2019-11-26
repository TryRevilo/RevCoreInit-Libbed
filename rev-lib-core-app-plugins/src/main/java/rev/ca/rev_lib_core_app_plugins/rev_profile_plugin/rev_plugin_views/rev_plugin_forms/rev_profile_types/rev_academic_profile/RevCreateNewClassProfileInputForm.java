package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.rev_profile_types.rev_academic_profile;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_widgets.RevCreateClassAcademicProfileInputFormWidget;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.RevDBModels.rev_entity_subtypes.RevGroupEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_menues.RevCoreColoredTabs;

/**
 * Created by rev on 12/18/17.
 */

public class RevCreateNewClassProfileInputForm implements IRevInputFormView {

    private Context mContext;
    private RevVarArgsData revVarArgsData;
    private Map<Object, Object> revVarArgsDataMetadataStrings;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    private RevCreateClassAcademicProfileInputFormWidget revCreateClassAcademicProfileInputFormWidget;

    public RevCreateNewClassProfileInputForm(RevVarArgsData revVarArgsData) {
        revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revVarArgsDataMetadataStrings = revVarArgsData.getRevVarArgsDataMetadataStrings();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    @Override
    public View createRevInputForm() {
        LinearLayout revInputFieldsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_ALL();

        this.revCreateClassAcademicProfileInputFormWidget = new RevCreateClassAcademicProfileInputFormWidget(revVarArgsData);
        revInputFieldsContainer_LL.addView(revCreateClassAcademicProfileInputFormWidget.revInputFormView());

        revInputFieldsContainer_LL.addView(this.revInputFormFooter());

        return revInputFieldsContainer_LL;
    }

    private View revInputFormFooter() {
        TextView submitFormTab = new RevCoreColoredTabs(mContext).getRevColoredTab();
        submitFormTab.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));
        submitFormTab.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.deep_purple_dark));
        submitFormTab.setText("sAvE");

        LinearLayout.LayoutParams submitFormTab_LP = (LinearLayout.LayoutParams) submitFormTab.getLayoutParams();
        submitFormTab_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_LARGE * 1.1), RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        submitFormTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevEntity revEntity = revCreateClassAcademicProfileInputFormWidget.getRevEntityData();

                RevGroupEntity revGroupEntity = revEntity.getRevGroupEntity();

                RevConstantinePluggableViewsServices
                        .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevPublishBagAction").initRevAction(revEntity);

                if (revEntity.get_revEntityMetadataList() != null) {
                    for (RevEntityMetadata revEntityMetadata : revEntity.get_revEntityMetadataList()) {
                        Log.v("MyApp", "revGroupEntity : " + revGroupEntity.getName() + " VALUE : " + revGroupEntity.getDescription());
                        Log.v("MyApp", "NAME : " + revEntityMetadata.get_revMetadataName() + " VALUE : " + revEntityMetadata.get_metadataValue());
                    }
                }
            }
        });

        return submitFormTab;
    }

    @Override
    public RevEntity createRevInputFormData() {
        return revCreateClassAcademicProfileInputFormWidget.getRevEntityData();
    }

    @Override
    public String revInputFormActionName() {
        return "RevPublishBagAction";
    }

    @Override
    public RevVarArgsData REV_VAR_ARGS_DATA() {
        revVarArgsDataMetadataStrings.put("submitFormTtlTxt", "Create New Class");

        revVarArgsData.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);
        revVarArgsData.setOverrideFormFooter_VARAGS(true);
        return revVarArgsData;
    }
}
