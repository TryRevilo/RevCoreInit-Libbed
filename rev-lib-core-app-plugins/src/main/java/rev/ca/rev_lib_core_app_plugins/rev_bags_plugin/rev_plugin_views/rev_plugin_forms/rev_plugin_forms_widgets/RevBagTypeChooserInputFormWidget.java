package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_widgets;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.RevSubmitFormViewContainer;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormButton;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 2/5/18.
 */

public class RevBagTypeChooserInputFormWidget {

    private Context mContext;
    private RevVarArgsData revVarArgsData;
    private Map<Object, Object> revVarArgsDataMetadataStrings = new HashMap<>();

    private RevCoreInputFormButton revCoreInputFormButton;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    private LinearLayout.LayoutParams tab_LP;

    public RevBagTypeChooserInputFormWidget(RevVarArgsData revVarArgsData) {
        revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.revVarArgsData.setRevViewType("RevCreateNewWorkBAGInputForm");

        this.mContext = this.revVarArgsData.getmContext();

        revCoreInputFormButton = new RevCoreInputFormButton(mContext);
        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);

        tab_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tab_LP.gravity = Gravity.CENTER_HORIZONTAL;
    }

    public View getRevBagTypeChooser() {
        LinearLayout getRevBagTypeChooserContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.gravity = (Gravity.CENTER_VERTICAL) | (Gravity.CENTER_HORIZONTAL);

        TextView submitFormTtl = new RevCoreInputFormTextView(mContext).getRevExtraSmallBoldTextView();
        submitFormTtl.setText("sELEcT TypE oF spAcE");
        submitFormTtl.setTextColor(mContext.getResources().getColor(R.color.revWhite));
        submitFormTtl.setBackgroundColor(Color.TRANSPARENT);
        submitFormTtl.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL);

        submitFormTtl.setLayoutParams(textView_LP);

        getRevBagTypeChooserContainer_LL.addView(submitFormTtl);
        getRevBagTypeChooserContainer_LL.addView(this.familySelectTab());
        getRevBagTypeChooserContainer_LL.addView(this.socialSelectTab());
        getRevBagTypeChooserContainer_LL.addView(this.academiaSelectTab());
        getRevBagTypeChooserContainer_LL.addView(this.workSelectTab());
        getRevBagTypeChooserContainer_LL.addView(this.organisationMaintenanceSelectTab());

        TextView helpBagTypesTtl = new RevCoreInputFormTextView(mContext).getRevExtraSmallBoldTextView();
        helpBagTypesTtl.setText("HELp");
        helpBagTypesTtl.setTextColor(mContext.getResources().getColor(R.color.revWhite));
        helpBagTypesTtl.setBackgroundColor(mContext.getResources().getColor(R.color.ight_green_dark));
        helpBagTypesTtl.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL);
        helpBagTypesTtl.setLayoutParams(tab_LP);
        helpBagTypesTtl.setGravity((Gravity.CENTER_VERTICAL) | (Gravity.CENTER_HORIZONTAL));

        getRevBagTypeChooserContainer_LL.addView(helpBagTypesTtl);

        return getRevBagTypeChooserContainer_LL;
    }

    private View workSelectTab() {
        Button button = revCoreInputFormButton.getFormInputSubmitButton_BRIGHT();
        button.setText("woRk");
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);
        button.setLayoutParams(tab_LP);
        button.setTextColor(mContext.getResources().getColor(R.color.revWhite));
        button.setBackgroundColor(mContext.getResources().getColor(R.color.teal_400_dark));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPop.getPw().dismiss();

                RevVarArgsData postRVD = new RevVarArgsData();
                postRVD.setmContext(revVarArgsData.getmContext());
                postRVD.setRevEntity(revVarArgsData.getRevEntity());
                postRVD.setRevViewType("RevCreateNewWorkBAGInputForm");

                revVarArgsDataMetadataStrings.put("type_of_bag", "work_bag");
                postRVD.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);

                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revVarArgsData);

                RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW(
                        "REV_USER_PROFILE_VIEW_CONTAINER_LL",
                        new RevSubmitFormViewContainer(mContext).getRevSubmitFormViewContainer(iRevInputFormView));
            }
        });

        return button;
    }

    private View organisationMaintenanceSelectTab() {
        Button button = revCoreInputFormButton.getFormInputSubmitButton_BRIGHT();
        button.setText("Organisation & Maintenance");
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);
        button.setLayoutParams(tab_LP);
        button.setTextColor(mContext.getResources().getColor(R.color.revWhite));
        button.setBackgroundColor(mContext.getResources().getColor(R.color.teal_500_dark));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPop.getPw().dismiss();

                RevVarArgsData postRVD = new RevVarArgsData();
                postRVD.setmContext(revVarArgsData.getmContext());
                postRVD.setRevEntity(revVarArgsData.getRevEntity());
                postRVD.setRevViewType("RevCreateNewWorkBAGInputForm");

                revVarArgsDataMetadataStrings.put("type_of_bag", "work_bag");
                postRVD.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);

                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revVarArgsData);

                RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW(
                        "REV_USER_PROFILE_VIEW_CONTAINER_LL",
                        new RevSubmitFormViewContainer(mContext).getRevSubmitFormViewContainer(iRevInputFormView));
            }
        });

        return button;
    }

    private View academiaSelectTab() {
        Button button = revCoreInputFormButton.getFormInputSubmitButton_BRIGHT();
        button.setText("AcADEmic");
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);
        button.setLayoutParams(tab_LP);
        button.setTextColor(mContext.getResources().getColor(R.color.revWhite));
        button.setBackgroundColor(mContext.getResources().getColor(R.color.teal_300_dark));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPop.getPw().dismiss();

                RevVarArgsData postRVD = new RevVarArgsData();
                postRVD.setmContext(revVarArgsData.getmContext());
                postRVD.setRevEntity(revVarArgsData.getRevEntity());
                postRVD.setRevViewType("RevCreateNewWorkBAGInputForm");

                revVarArgsDataMetadataStrings.put("type_of_bag", "academic_bag");
                postRVD.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);

                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(postRVD);
                iRevInputFormView.createRevInputForm();
                RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW(
                        "REV_USER_PROFILE_VIEW_CONTAINER_LL",
                        new RevSubmitFormViewContainer(mContext).getRevSubmitFormViewContainer(iRevInputFormView));
            }
        });

        return button;
    }

    private View socialSelectTab() {
        Button button = revCoreInputFormButton.getFormInputSubmitButton_BRIGHT();
        button.setText("sociAL");
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);
        button.setLayoutParams(tab_LP);
        button.setTextColor(mContext.getResources().getColor(R.color.revWhite));
        button.setBackgroundColor(mContext.getResources().getColor(R.color.teal_100_dark));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPop.getPw().dismiss();

                RevVarArgsData postRVD = new RevVarArgsData();
                postRVD.setmContext(revVarArgsData.getmContext());
                postRVD.setRevEntity(revVarArgsData.getRevEntity());
                postRVD.setRevViewType("RevCreateNewWorkBAGInputForm");

                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(postRVD);
                iRevInputFormView.createRevInputForm();
                RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW(
                        "REV_USER_PROFILE_VIEW_CONTAINER_LL",
                        new RevSubmitFormViewContainer(mContext).getRevSubmitFormViewContainer(iRevInputFormView));
            }
        });

        return button;
    }

    private View familySelectTab() {
        Button button = revCoreInputFormButton.getFormInputSubmitButton_BRIGHT();
        button.setText("FAmiLy");
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, RevLibGenConstantine.REV_TEXT_SIZE_TINY);
        button.setLayoutParams(tab_LP);
        button.setTextColor(mContext.getResources().getColor(R.color.revWhite));
        button.setBackgroundColor(mContext.getResources().getColor(R.color.teal_200_dark));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPop.getPw().dismiss();

                RevVarArgsData postRVD = new RevVarArgsData();
                postRVD.setmContext(revVarArgsData.getmContext());
                postRVD.setRevEntity(revVarArgsData.getRevEntity());
                postRVD.setRevViewType("RevCreateNewWorkBAGInputForm");

                revVarArgsDataMetadataStrings.put("type_of_bag", "social_bag");
                postRVD.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);

                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(postRVD);
                iRevInputFormView.createRevInputForm();
                RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW(
                        "REV_USER_PROFILE_VIEW_CONTAINER_LL",
                        new RevSubmitFormViewContainer(mContext).getRevSubmitFormViewContainer(iRevInputFormView));
            }
        });

        return button;
    }
}
