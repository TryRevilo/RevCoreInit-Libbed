package rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import java.util.Arrays;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.RevSubmitFormViewContainer;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormButton;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_menues.RevCoreColoredTabs;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;
import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;
import static rev.ca.revlibviews.rev_core_layouts.RevConstantineViews.PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER;

/**
 * Created by rev on 10/5/17.
 */

public class RevLoggedOutFullscreen extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    Context mContext;

    RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    RevCoreInputFormTextView revCoreInputFormTextView;
    RevCoreColoredTabs revCoreColoredTabs;
    RevCoreInputFormEditText revCoreInputFormEditText;
    RevCoreInputFormButton revCoreInputFormButton;

    EditText eMailETPhone_ET, password_ET;

    public RevLoggedOutFullscreen(RevVarArgsData revVarArgsData) {
        super(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
        revCoreColoredTabs = new RevCoreColoredTabs(mContext);
        revCoreInputFormEditText = new RevCoreInputFormEditText(mContext);
        revCoreInputFormButton = new RevCoreInputFormButton(mContext);

    }

    @Override
    public View createRevPluggableLoggedOutView() {
        return this.userLoginReg();
    }

    public View userLoginReg() {
        ((FrameLayout.LayoutParams) PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER.getLayoutParams()).width = RevLibGenConstantine.REV_DIMENS.getPageWidth();
        ((FrameLayout.LayoutParams) PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER.getLayoutParams()).height = RevLibGenConstantine.REV_DIMENS.getPageHeight();

        return this.logInItems();
    }

    public RevEntity revObjectFormdata() {
        RevEntity revEntity = new RevEntity();
        revEntity.set_revEntityType(FeedReaderContract.FeedEntry_REV_USER_ENTITY.TABLE_NAME);
        revEntity.set_revEntitySubType("rev_user_entity");

        String email = eMailETPhone_ET.getText().toString();
        email = email.replaceAll("\\s+", "");

        revEntity.get_revEntityMetadataList().addAll(Arrays.asList(
                new RevEntityMetadata("rev_user_entity_email_value", email)
        ));

        return revEntity;
    }

    private View logInItems() {
        LinearLayout logInItemsInputWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        logInItemsInputWrapper_LL.setPadding((int) (RevLibGenConstantine.REV_MARGIN_LARGE * 1.2), 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_200_dark));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -1, -1, -1, 1);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(logInItemsInputWrapper_LL, layerDrawable);

        eMailETPhone_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING();
        eMailETPhone_ET.setHint("pHoNE / EmAiL");
        eMailETPhone_ET.setMaxLines(1);
        ((LinearLayout.LayoutParams) eMailETPhone_ET.getLayoutParams()).width = (int) (RevLibGenConstantine.REV_DIMENS.getPageWidth() * .3);

        eMailETPhone_ET.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    revLogInAction();
                    return true;
                }
                return false;
            }
        });

        password_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING();
        password_ET.setHint("pAsswoRD");
        password_ET.setMaxLines(1);
        ((LinearLayout.LayoutParams) password_ET.getLayoutParams()).width = (int) (RevLibGenConstantine.REV_DIMENS.getPageWidth() * .3);

        password_ET.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    revLogInAction();
                    return true;
                }
                return false;
            }
        });

        logInItemsInputWrapper_LL.addView(eMailETPhone_ET);
        logInItemsInputWrapper_LL.addView(password_ET);

        LinearLayout logInItemsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
        ((LinearLayout.LayoutParams) logInItemsContainer_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_LARGE, 0, 0);
        ((LinearLayout.LayoutParams) logInItemsContainer_LL.getLayoutParams()).width = (int) (RevLibGenConstantine.REV_DIMENS.getPageWidth() * .8);

        logInItemsContainer_LL.addView(logInItemsInputWrapper_LL);
        logInItemsContainer_LL.addView(this.revLogInTab());
        logInItemsContainer_LL.addView(this.revTellSpaces());
        logInItemsContainer_LL.addView(this.revCreateAccount());
        logInItemsContainer_LL.addView(this.revTerms());

        return logInItemsContainer_LL;

    }

    private View revLogInTab() {
        TextView tab_TV = revCoreColoredTabs.getRevColoredTab();
        tab_TV.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));
        tab_TV.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_200_dark));
        tab_TV.setText("loG iN");

        tab_TV.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ((LinearLayout.LayoutParams) tab_TV.getLayoutParams()).gravity = Gravity.LEFT;
        ((LinearLayout.LayoutParams) tab_TV.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM, -1, 0, 0);

        tab_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revLogInAction();
            }
        });

        return tab_TV;

    }

    private void revLogInAction() {
        RevPersLibRead revPersLibRead = new RevPersLibRead();

        String revEMail = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revObjectFormdata().get_revEntityMetadataList(), "rev_user_entity_email_value");
        long revEntityGUID = revPersLibRead.revGetRevEntityMetadataOwnerGUID_By_MetadataName_MetadataValue("rev_user_entity_email_value", revEMail);
        RevEntity revEntity = revPersLibRead.revPersGetRevEntityByGUID(revEntityGUID);

        if (revEntity != null) {
            RevConstantinePluggableViewsServices
                    .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevCreateUserLoginAction").initRevAction(revEntity);
        } else {
            TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView();
            textView.setText("Wrong username or password. Try again.");
            textView.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.rev_red));
            textView.setBackgroundColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.greyDark));
            textView.setPadding(RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_SMALL);

            Toast myToast = new Toast(mContext);
            myToast.setView(textView);
            myToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
            myToast.setDuration(Toast.LENGTH_LONG);
            myToast.show();
        }

        /** CLEAR **/
        eMailETPhone_ET.setText("");

    }

    private View revTellSpaces() {

        String s = "Keep up with family/ colleagues/ organisations/ your social circles via Spaces on CamppAnn ";
        SpannableString s_Span = new SpannableString(s);
        s_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        s_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, s.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
        s_Span.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, s.length(), 0);

        CharSequence finalText = TextUtils.concat(s_Span);

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        textView.setText(finalText);
        textView.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_primary));

        textView.setPadding(RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_SMALL);

        LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0);
        textView.setLayoutParams(textView_LP);

        return textView;

    }

    private View revCreateAccount() {

        String s = "pLAcE A NEw AccouNT";
        SpannableString s_Span = new SpannableString(s);
        s_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        s_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, s.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
        s_Span.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, s.length(), 0);

        CharSequence finalText = TextUtils.concat(s_Span);

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        textView.setText(finalText);
        textView.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_primary));
        textView.setGravity(Gravity.CENTER_VERTICAL);

        textView.setPadding(RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_LARGE, 0);

        LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0);
        textView.setLayoutParams(textView_LP);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_200_dark));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -1, -1, -1, 1);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(textView, layerDrawable);

        int imgSizeAll = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Drawable defaultCircle_DR = mContext.getResources().getDrawable(R.drawable.baseline_playlist_add_black_48dp);
        defaultCircle_DR.setBounds(0, 0, imgSizeAll, imgSizeAll);
        DrawableCompat.setTint(defaultCircle_DR, ContextCompat.getColor(mContext, R.color.revPurple));

        textView.setCompoundDrawables(defaultCircle_DR, null, null, null);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revVarArgsData.setRevViewType("RevCreateNewUserRegForm");
                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revVarArgsData);
                new RevPop().initiatePopupWindow(new RevSubmitFormViewContainer(mContext).getRevSubmitFormViewContainer(iRevInputFormView));
            }
        });

        return textView;

    }

    private View revTerms() {

        String s = "TERms oF sERvicE";
        SpannableString s_Span = new SpannableString(s);
        s_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        s_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 2, s.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
        s_Span.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, s.length(), 0);

        CharSequence finalText = TextUtils.concat(s_Span);

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        textView.setText(finalText);
        textView.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_primary));
        textView.setGravity(Gravity.RIGHT);

        textView.setPadding(RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY, 0);

        LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0);
        textView_LP.gravity = Gravity.RIGHT;
        textView.setLayoutParams(textView_LP);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_200_dark));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -1, -1, -1, 1);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(textView, layerDrawable);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revVarArgsData.setRevViewType("RevCreateNewUserRegForm");
                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revVarArgsData);
                new RevPop().initiatePopupWindow(new RevSubmitFormViewContainer(mContext).getRevSubmitFormViewContainer(iRevInputFormView));
            }
        });

        return textView;

    }
}
