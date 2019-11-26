package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_widgets;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormButton;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 2/5/18.
 */

public class RevProfileTypeChooserInputFormWidget_FAMILY {

    private Context mContext;
    private RevVarArgsData revVarArgsData;
    private Map<Object, Object> revVarArgsDataMetadataStrings = new HashMap<>();
    private Long revOwnerEntityGUID;

    private RevCoreInputFormButton revCoreInputFormButton;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    private LinearLayout.LayoutParams tab_LP;

    public RevProfileTypeChooserInputFormWidget_FAMILY(RevVarArgsData revVarArgsData) {
        revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.revVarArgsData.setRevViewType("RevCreateNewClassProfileInputForm");

        this.mContext = this.revVarArgsData.getmContext();
        this.revOwnerEntityGUID = revVarArgsData.getRevOwnerEntityGUID();

        revCoreInputFormButton = new RevCoreInputFormButton(mContext);
        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);

        tab_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tab_LP.gravity = Gravity.CENTER_HORIZONTAL;
    }

    public View getRevProfileTypeChooser() {
        LinearLayout getRevBagTypeChooserContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        getRevBagTypeChooserContainer_LL.addView(this.academiaSelectTab());
        getRevBagTypeChooserContainer_LL.addView(this.socialSelectTab());
        getRevBagTypeChooserContainer_LL.addView(this.cousinsSelectTab());
        getRevBagTypeChooserContainer_LL.addView(this.familySelectTab());

        return getRevBagTypeChooserContainer_LL;
    }

    private View academiaSelectTab() {
        Button button = revCoreInputFormButton.getFormInputSubmitButton_BRIGHT();
        button.setText("Parent");
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);
        button.setLayoutParams(tab_LP);
        button.setTextColor(mContext.getResources().getColor(R.color.revWhite));
        button.setBackgroundColor(mContext.getResources().getColor(R.color.rev_academic_btn));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPop.getPw().dismiss();
                RevVarArgsData revVarArgsData = new RevVarArgsData();
                revVarArgsData.setRevOwnerEntityGUID(revOwnerEntityGUID);

                revVarArgsData.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);
                revVarArgsData.setRevViewType("RevCreateInputFormRevProfileParents");

                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revVarArgsData);
                iRevInputFormView.createRevInputForm();
                new RevPop().initiatePopupWindow(iRevInputFormView.createRevInputForm());
            }
        });

        return button;
    }

    private View socialSelectTab() {
        Button button = revCoreInputFormButton.getFormInputSubmitButton_BRIGHT();
        button.setText("Sibling");
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);
        button.setLayoutParams(tab_LP);
        button.setTextColor(mContext.getResources().getColor(R.color.revWhite));
        button.setBackgroundColor(mContext.getResources().getColor(R.color.rev_social_btn));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPop.getPw().dismiss();
                RevVarArgsData revVarArgsData = new RevVarArgsData();
                revVarArgsData.setRevOwnerEntityGUID(revOwnerEntityGUID);

                revVarArgsData.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);
                revVarArgsData.setRevViewType("RevCreateInputFormRevProfileParents");

                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revVarArgsData);
                iRevInputFormView.createRevInputForm();
                new RevPop().initiatePopupWindow(iRevInputFormView.createRevInputForm());
            }
        });

        return button;
    }

    private View cousinsSelectTab() {
        Button button = revCoreInputFormButton.getFormInputSubmitButton_BRIGHT();
        button.setText("Cousin");
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);
        button.setLayoutParams(tab_LP);
        button.setTextColor(mContext.getResources().getColor(R.color.revWhite));
        button.setBackgroundColor(mContext.getResources().getColor(R.color.rev_cousins_btn));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPop.getPw().dismiss();
                RevVarArgsData revVarArgsData = new RevVarArgsData();
                revVarArgsData.setRevOwnerEntityGUID(revOwnerEntityGUID);

                revVarArgsData.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);
                revVarArgsData.setRevViewType("RevCreateInputFormRevProfileParents");

                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revVarArgsData);
                iRevInputFormView.createRevInputForm();
                new RevPop().initiatePopupWindow(iRevInputFormView.createRevInputForm());
            }
        });

        return button;
    }

    private View familySelectTab() {
        Button button = revCoreInputFormButton.getFormInputSubmitButton_BRIGHT();
        button.setText("Aunt / Uncle");
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);
        button.setLayoutParams(tab_LP);
        button.setTextColor(mContext.getResources().getColor(R.color.revWhite));
        button.setBackgroundColor(mContext.getResources().getColor(R.color.rev_family_btn));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPop.getPw().dismiss();
                RevVarArgsData revVarArgsData = new RevVarArgsData();
                revVarArgsData.setRevOwnerEntityGUID(revOwnerEntityGUID);

                revVarArgsData.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);
                revVarArgsData.setRevViewType("RevCreateInputFormRevProfileParents");

                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revVarArgsData);
                iRevInputFormView.createRevInputForm();
                new RevPop().initiatePopupWindow(iRevInputFormView.createRevInputForm());
            }
        });

        return button;
    }
}
