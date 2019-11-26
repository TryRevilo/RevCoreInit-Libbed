package rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import java.util.Map;

import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_page_menue_togglers.RevObjectListingOptionsTogglerMenu;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevConstantineViews;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_menues.RevCoreColoredTabs;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 12/19/17.
 */

public class RevSubmitFormViewContainer {

    private Context mContext;

    private RevVarArgsData revVarArgsData;

    private boolean revVarArgsData_EMPTY;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;
    private RevCoreColoredTabs revCoreColoredTabs;

    public RevSubmitFormViewContainer(Context mContext) {
        this.mContext = mContext;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
        revCoreColoredTabs = new RevCoreColoredTabs(mContext);
    }

    public View getRevSubmitFormViewContainer(final IRevInputFormView iRevInputFormView) {
        LinearLayout revSubmitFormViewContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        revVarArgsData = iRevInputFormView.REV_VAR_ARGS_DATA();
        String submitFormTtl_Txt = null;

        // myVariable = (testCondition) ? someValue : anotherValue;
        revVarArgsData_EMPTY = (iRevInputFormView.REV_VAR_ARGS_DATA() == null) ? true : false;

        if (!revVarArgsData_EMPTY) {
            revVarArgsData = iRevInputFormView.REV_VAR_ARGS_DATA();
            Map<Object, Object> revVarArgsDataMetadataStrings = revVarArgsData.getRevVarArgsDataMetadataStrings();

            submitFormTtl_Txt = (String) revVarArgsDataMetadataStrings.get("submitFormTtlTxt");

            if (!revVarArgsData.isPopUpWindow_VARAGS()) {
                // revSubmitFormViewContainer_LL.addView(RevObjectListingOptionsTogglerMenu.getRevObjectListingOptionsTogglerMenu());
            }
        } else {
            revSubmitFormViewContainer_LL.addView(RevObjectListingOptionsTogglerMenu.getRevObjectListingOptionsTogglerMenu());
        }

        if (submitFormTtl_Txt != null && !(submitFormTtl_Txt.equals("title_override")) && !submitFormTtl_Txt.isEmpty()) {
            final LinearLayout formTitleWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
            LinearLayout.LayoutParams allHistoryTab_LLLayoutParams = (LinearLayout.LayoutParams) formTitleWrapper_LL.getLayoutParams();
            allHistoryTab_LLLayoutParams.gravity = Gravity.CENTER_VERTICAL;
            allHistoryTab_LLLayoutParams.setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);
            formTitleWrapper_LL.setPadding((int) (RevLibGenConstantine.REV_MARGIN_MEDIUM * .8), RevLibGenConstantine.REV_MARGIN_TINY, 4, 0);

            /** TITLE BORDERS **/

            int imageViewBorderSize = 1;

            GradientDrawable imageViewBorder = new GradientDrawable();
            imageViewBorder.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));
            imageViewBorder.setGradientType(RECTANGLE);

            Drawable[] imageViewLayers = {imageViewBorder};
            LayerDrawable imageViewLayerDrawable = new LayerDrawable(imageViewLayers);
            imageViewLayerDrawable.setLayerInset(0, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize, imageViewBorderSize);

            REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(formTitleWrapper_LL, imageViewLayerDrawable);

            /** END TITLE BORDERS **/

            int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_TINY * 1.3);

            ImageView addHeaderIcon_IV = new ImageView(mContext);

            Picasso.get()
                    .load(R.drawable.ic_gamepad_black_48dp)
                    .resize(imageSize, imageSize)
                    .centerCrop()
                    .into(addHeaderIcon_IV);

            LinearLayout.LayoutParams addHeaderIcon_IV_LP = new LinearLayout.LayoutParams(imageSize, imageSize);
            addHeaderIcon_IV_LP.gravity = (Gravity.CENTER_VERTICAL);
            addHeaderIcon_IV.setLayoutParams(addHeaderIcon_IV_LP);

            REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(addHeaderIcon_IV);

            LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textView_LP.gravity = (Gravity.CENTER_VERTICAL);

            TextView submitFormTtl = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.9f);
            submitFormTtl.setText(submitFormTtl_Txt);
            submitFormTtl.setLayoutParams(textView_LP);
            submitFormTtl.setGravity(Gravity.CENTER_VERTICAL);

            REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(submitFormTtl);

            LinearLayout goBackWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

            TextView goBack_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.8f);
            goBack_TV.setText("Go bAck");
            goBack_TV.setPadding(0, 0, RevLibGenConstantine.REV_MARGIN_TINY, 0);
            ((LinearLayout.LayoutParams) goBack_TV.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

            ImageView goBackIcon_IV = new ImageView(mContext);

            int goBackIcon_IV_Size = (int) (imageSize * 1.5);

            Picasso.get()
                    .load(R.drawable.icons8_return_512)
                    .resize(goBackIcon_IV_Size, goBackIcon_IV_Size)
                    .centerCrop()
                    .into(goBackIcon_IV);

            goBackWrapper_LL.addView(goBack_TV);
            goBackWrapper_LL.addView(goBackIcon_IV);

            if (!revVarArgsData.isOverrideFormTitle_VARAGS()) {
                formTitleWrapper_LL.addView(addHeaderIcon_IV);
                formTitleWrapper_LL.addView(submitFormTtl);
                formTitleWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
                formTitleWrapper_LL.addView(goBackWrapper_LL);
            }

            revSubmitFormViewContainer_LL.addView(formTitleWrapper_LL);
        }

        revSubmitFormViewContainer_LL.addView(iRevInputFormView.createRevInputForm());

        if ((revVarArgsData != null) && !revVarArgsData.isOverrideFormFooter_VARAGS()) {
            TextView submitFormTab = revCoreColoredTabs.getRevColoredTab();
            submitFormTab.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));
            submitFormTab.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.deep_purple_dark));
            submitFormTab.setText("sAvE");

            LinearLayout.LayoutParams submitFormTab_LP = (LinearLayout.LayoutParams) submitFormTab.getLayoutParams();
            submitFormTab_LP.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

            submitFormTab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RevConstantinePluggableViewsServices
                            .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get(iRevInputFormView.revInputFormActionName()).initRevAction(iRevInputFormView.createRevInputFormData());
                }
            });

            revSubmitFormViewContainer_LL.addView(submitFormTab);
        }

        REV_VIEWS_BASE_FUNCTIONS.revRemoveParentView(RevConstantineViews.REV_PHOTOGRID_VIEW_LL);
        revSubmitFormViewContainer_LL.addView(RevConstantineViews.REV_PHOTOGRID_VIEW_LL);

        return revSubmitFormViewContainer_LL;
    }
}
