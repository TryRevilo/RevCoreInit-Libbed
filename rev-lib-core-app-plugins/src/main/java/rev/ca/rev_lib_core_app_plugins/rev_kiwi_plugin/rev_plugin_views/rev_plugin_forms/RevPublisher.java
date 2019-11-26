package rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_plugin_forms;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersFilesAsyncTask;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_plugin_views.rev_file_choosers.RevFileChooser;
import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_menues.RevCoreColoredTabs;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;
import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * Created by rev on 3/26/18.
 */

public class RevPublisher {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevEntity revPageOwnerEntity;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;
    private RevCoreInputFormEditText revCoreInputFormEditText;
    private RevCoreColoredTabs revCoreColoredTabs;

    private RevPersJava revPersJava = new RevPersJava();
    private RevPersLibRead revPersLibRead = new RevPersLibRead();

    private LayerDrawable layerDrawable;

    int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_TINY;
    int tinyMargin = RevLibGenConstantine.REV_MARGIN_TINY;
    int mediumMargin = RevLibGenConstantine.REV_MARGIN_MEDIUM;

    private List<String> selectedItems = new ArrayList<>();
    LinearLayout selectedItems_LL;

    EditText kiwiInput_ET;

    public RevPublisher(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        this.revPageOwnerEntity = revVarArgsData.getRevPersEntityInfoWrapperModel().getRevEntity();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
        revCoreInputFormEditText = new RevCoreInputFormEditText(mContext);
        revCoreColoredTabs = new RevCoreColoredTabs(mContext);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, 1, 1, 1, -2);

        selectedItems_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
    }

    public View getRevPublisher() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        linearLayout.setPadding(0, 0, 0, mediumMargin);

        linearLayout.addView(this.revPublisherHeaderView());
        linearLayout.addView(this.revPublisherMidView());
        linearLayout.addView(this.revpublisherFooterView());

        linearLayout.addView(selectedItems_LL);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, 1, 1, 1, -2);

        return linearLayout;
    }

    private View revPublisherHeaderView() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        linearLayout.setPadding(mediumMargin, mediumMargin, mediumMargin, 0);

        GradientDrawable wrapperBorder = new GradientDrawable();
        wrapperBorder.setStroke(1, mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));
        wrapperBorder.setGradientType(RECTANGLE);

        Drawable[] wrapperLayers = {wrapperBorder};
        LayerDrawable wrapperLayerDrawable = new LayerDrawable(wrapperLayers);
        wrapperLayerDrawable.setLayerInset(0, -2, -2, -2, 1);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(linearLayout, wrapperLayerDrawable);

        TextView kiwi_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.8f);
        kiwi_TV.setText("Kiwi");
        kiwi_TV.setPadding(mediumMargin, tinyMargin, mediumMargin, tinyMargin);
        // linearLayout.addView(kiwi_TV);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(kiwi_TV, layerDrawable);

        linearLayout.addView(this.moreHeaderTabs());

        return linearLayout;
    }

    private View moreHeaderTabs() {
        final LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).setMargins(0, 0, mediumMargin, 0);

        TextView include_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.8f);
        include_TV.setText("include");

        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(include_TV);

        Drawable include_DR = mContext.getResources().getDrawable(R.drawable.ic_trending_flat_black_48dp);
        include_DR.setBounds(0, 0, imgSize, imgSize);
        DrawableCompat.setTint(include_DR, ContextCompat.getColor(mContext, R.color.gray_text));

        include_TV.setCompoundDrawablePadding((int) (RevLibGenConstantine.REV_MARGIN_TINY * .5));
        include_TV.setCompoundDrawables(null, null, include_DR, null);
        linearLayout.addView(include_TV);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(tinyMargin, 0, 0, 0);

        // Pic

        TextView includePic_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.8f);
        includePic_TV.setGravity(Gravity.CENTER_VERTICAL);
        includePic_TV.setText("picture");

        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(includePic_TV);

        Drawable includePic_DR = mContext.getResources().getDrawable(R.drawable.ic_add_a_photo_black_48dp);
        includePic_DR.setBounds(0, 0, imgSize, imgSize);
        DrawableCompat.setTint(includePic_DR, ContextCompat.getColor(mContext, R.color.gray_text));

        includePic_TV.setCompoundDrawablePadding((int) (RevLibGenConstantine.REV_MARGIN_TINY * .5));
        includePic_TV.setCompoundDrawables(includePic_DR, null, null, null);

        includePic_TV.setPadding(mediumMargin, tinyMargin, mediumMargin, tinyMargin);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(includePic_TV, layerDrawable);
        includePic_TV.setLayoutParams(layoutParams);

        RevVarArgsData revPassRVD = new RevVarArgsData(mContext);
        revPassRVD.getRevPassViews().add(includePic_TV);

        new RevFileChooser(revPassRVD, selectedItems, selectedItems_LL, new RevFileChooser.IRevFileChooser() {
            @Override
            public void revFileChooser(View revSelectItemsTab) {
                linearLayout.addView(revSelectItemsTab);
            }

            @Override
            public void revSelectedItemsCallBack(List<String> _selectedItems) {
                selectedItems = _selectedItems;
            }
        });

        // Vid

        TextView includeVid_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.8f);
        includeVid_TV.setGravity(Gravity.CENTER_VERTICAL);
        includeVid_TV.setText("video");

        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(includeVid_TV);

        Drawable includeVid_DR = mContext.getResources().getDrawable(R.drawable.ic_video_library_black_48dp);
        includeVid_DR.setBounds(0, 0, imgSize, imgSize);
        DrawableCompat.setTint(includeVid_DR, ContextCompat.getColor(mContext, R.color.gray_text));

        includeVid_TV.setCompoundDrawablePadding((int) (RevLibGenConstantine.REV_MARGIN_TINY * .5));
        includeVid_TV.setCompoundDrawables(includeVid_DR, null, null, null);

        includeVid_TV.setPadding(mediumMargin, tinyMargin, mediumMargin, tinyMargin);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(includeVid_TV, layerDrawable);
        includeVid_TV.setLayoutParams(layoutParams);

        linearLayout.addView(includeVid_TV);

        // More

        TextView includeMore_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView(.8f);
        includeMore_TV.setGravity(Gravity.CENTER_VERTICAL);
        includeMore_TV.setText("more");

        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(includeMore_TV);

        Drawable includeMore_DR = mContext.getResources().getDrawable(R.drawable.ic_filter_list_black_48dp);
        includeMore_DR.setBounds(0, 0, imgSize, imgSize);
        DrawableCompat.setTint(includeMore_DR, ContextCompat.getColor(mContext, R.color.gray_text));

        includeMore_TV.setCompoundDrawablePadding((int) (RevLibGenConstantine.REV_MARGIN_TINY * .1));
        includeMore_TV.setCompoundDrawables(includeMore_DR, null, null, null);
        includeMore_TV.setLayoutParams(layoutParams);
        linearLayout.addView(includeMore_TV);

        return linearLayout;
    }

    private View revPublisherMidView() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        linearLayout.addView(this.kiwiInputView());

        return linearLayout;
    }

    private View revpublisherFooterView() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        GradientDrawable border = new GradientDrawable();
        border.setStroke(1, mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -2, 1, -2, -2);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(linearLayout, layerDrawable);

        TextView postKiwi_TV = revCoreColoredTabs.getRevColoredTab();
        postKiwi_TV.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));
        postKiwi_TV.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.deep_purple_dark));
        postKiwi_TV.setText("Post");
        postKiwi_TV.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8));

        postKiwi_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Long aLong = (Long) RevConstantinePluggableViewsServices
                        .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevPublishKiwiAction").initRevAction(revKiwiObjectFormdata());

                /* THE IMAGES */
                if (aLong > 0 && selectedItems.size() > 0) {
                    RevVarArgsData passRevVarArgsData = new RevVarArgsData(mContext);
                    passRevVarArgsData.setRevOwnerEntityGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
                    passRevVarArgsData.setRevContainerEntityGUID(aLong);

                    new RevPersFilesAsyncTask(passRevVarArgsData, new RevPersFilesAsyncTask.IRevPersAsyncResponse() {
                        @Override
                        public void revPersProcessFinish(Object output) {
                            // Do something with result
                        }
                    }).execute(selectedItems, "rev_picture_of");
                }

                kiwiInput_ET.setText("");
                REV_VIEWS_BASE_FUNCTIONS.hideKeyboard(((Activity) mContext));
                RevPop.getPw().dismiss();
            }
        });

        LinearLayout.LayoutParams postKiwi_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        postKiwi_LP.setMargins(mediumMargin, -1, 0, 0);
        postKiwi_LP.gravity = Gravity.CENTER_VERTICAL;

        postKiwi_TV.setLayoutParams(postKiwi_LP);

        linearLayout.addView(postKiwi_TV);

        return linearLayout;
    }

    private View kiwiInputView() {
        String totConnCountTell_S = "youR THouGHTs Now . . .";

        SpannableString totConnectionsCount_S_Span = new SpannableString(totConnCountTell_S);
        totConnectionsCount_S_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_LARGE * .8)), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        totConnectionsCount_S_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 2, (totConnCountTell_S.length() - (totConnCountTell_S.length() / 2)), SPAN_INCLUSIVE_INCLUSIVE);
        totConnectionsCount_S_Span.setSpan(new ForegroundColorSpan(RevLibGenConstantine.REV_CONTEXT.getResources().getColor(rev.ca.rev_lib_core_app_plugins.R.color.teal_500_dark)), 0, totConnCountTell_S.length(), 0);

        kiwiInput_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING();
        kiwiInput_ET.setHint(totConnCountTell_S);
        kiwiInput_ET.setHintTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.teal_500_dark));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        kiwiInput_ET.setPadding(mediumMargin, tinyMargin, tinyMargin, mediumMargin);

        kiwiInput_ET.setLayoutParams(layoutParams);

        return kiwiInput_ET;
    }

    public RevEntity revKiwiObjectFormdata() {
        long revPageOwnerEntityGUID;

        if (revPageOwnerEntity.get_revEntityGUID() < 0) {
            revPageOwnerEntity.set_revEntityResolveStatus(0);
            revPageOwnerEntityGUID = (long) revPersJava.saveRevEntity_NO_REMOTE_SYNC(revPageOwnerEntity);
        } else {
            revPageOwnerEntityGUID = revPageOwnerEntity.get_revEntityGUID();
        }

        RevEntity revPageOwnerInfoEntity = revPageOwnerEntity.get_revInfoEntity();

        if (revPageOwnerInfoEntity != null && revPersLibRead.revEntityExistsByRemoteEntityGUID(revPageOwnerInfoEntity.get_remoteRevEntityGUID()) == -1) {
            long revPageOwnerInfoEntityGUID = revPageOwnerInfoEntity.get_revEntityGUID();
            if (revPageOwnerInfoEntityGUID == -1) {
                revPageOwnerInfoEntity.set_revEntityOwnerGUID(revPageOwnerEntityGUID);
                revPageOwnerInfoEntity.set_revEntityResolveStatus(0);
                revPersJava.saveRevEntity_NO_REMOTE_SYNC(revPageOwnerInfoEntity);
            }
        }

        RevEntity revPersEntity = new RevEntity();
        revPersEntity.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
        revPersEntity.set_revEntitySubType("rev_kiwi");
        revPersEntity.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        revPersEntity.set_revEntityContainerGUID(revPageOwnerEntityGUID);
        revPersEntity.set_revEntityChildableStatus(3);

        revPersEntity.set_revEntityMetadataList(Arrays.asList(
                new RevEntityMetadata("rev_kiwi_value", kiwiInput_ET.getText().toString())
        ));

        return revPersEntity;
    }
}
