package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;

import androidx.core.content.ContextCompat;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersFilesAsyncTask;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_server_client_services.RevPostPersServices;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_lib_file_functions.IRevFileCrawler;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_lib_file_functions.RevPictureFileCrawler;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_plugin_views.rev_plugin_views_overrides.RevGenericSelectedItemsView;
import rev.ca.rev_lib_core_views.REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_menues.RevCoreColoredTabs;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 10/25/17.
 */

public class RevEditRevEntityInfoForm_WIDGET {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    RevCoreInputFormTextView revCoreInputFormTextView;
    RevCoreInputFormEditText revCoreInputFormEditText;

    List<String> selectedItems = new ArrayList<>();
    LinearLayout selectedItems_LL;

    EditText myBrief_ET, sex_ET, height_ET, race_ET;

    public RevEditRevEntityInfoForm_WIDGET(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);

        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
        revCoreInputFormEditText = new RevCoreInputFormEditText(mContext);

        selectedItems_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
    }

    public LinearLayout getRevEditInfoForm() {
        return this.getRevEditInfoFormInputs();
    }

    public RevEntity getRevEntityFormData() {
        RevEntity revPersEntity = new RevEntity();
        revPersEntity.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
        revPersEntity.set_revEntitySubType("rev_entity_info");
        revPersEntity.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        revPersEntity.set_revEntityContainerGUID(revVarArgsData.getRevContainerEntityGUID());
        revPersEntity.set_revEntityChildableStatus(3);

        revPersEntity.set_revEntityMetadataList(Arrays.asList(
                new RevEntityMetadata("rev_info_desc_value", myBrief_ET.getText().toString()),
                new RevEntityMetadata("rev_info_sex_value", sex_ET.getText().toString()),
                new RevEntityMetadata("rev_info_sex_value", height_ET.getText().toString()),
                new RevEntityMetadata("rev_info_height_value", height_ET.getText().toString()),
                new RevEntityMetadata("rev_info_race_value", race_ET.getText().toString())
        ));

        return revPersEntity;
    }

    private LinearLayout getRevEditInfoFormInputs() {
        LinearLayout revInputFieldsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revInputFieldsContainer_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);

        myBrief_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        myBrief_ET.setHint("About me . . . . . (max 21 characters)");

        sex_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        sex_ET.setSingleLine(true);
        sex_ET.setMaxLines(1);
        sex_ET.setHint(R.string.user_sex_title);

        height_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        height_ET.setSingleLine(true);
        height_ET.setMaxLines(1);
        height_ET.setHint(R.string.user_height_title);

        race_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        race_ET.setSingleLine(true);
        race_ET.setMaxLines(1);
        race_ET.setHint(R.string.user_race_title);

        revInputFieldsContainer_LL.addView(myBrief_ET);
        revInputFieldsContainer_LL.addView(sex_ET);
        revInputFieldsContainer_LL.addView(height_ET);
        revInputFieldsContainer_LL.addView(race_ET);
        revInputFieldsContainer_LL.addView(genPublisherWrapper_LL());

        revInputFieldsContainer_LL.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(selectedItems_LL));

        revInputFieldsContainer_LL.addView(this.submitRevInputFormBtn());

        return revInputFieldsContainer_LL;
    }

    private View submitRevInputFormBtn() {
        TextView submitFormTab = new RevCoreColoredTabs(mContext).getRevColoredTab();
        submitFormTab.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));
        submitFormTab.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.deep_purple_dark));
        submitFormTab.setText("sAvE");

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        submitFormTab.setLayoutParams(layoutParams);
        ((LinearLayout.LayoutParams) submitFormTab.getLayoutParams()).setMargins(
                (int) (RevLibGenConstantine.REV_MARGIN_SMALL * .75), RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        submitFormTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long revInfoEntityGUID = (Long) RevConstantinePluggableViewsServices
                        .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevPublishRevEntityInfoAction").initRevAction(getRevEntityFormData());

                if (revInfoEntityGUID < 0) return;

                /* THE IMAGES */

                if (selectedItems.size() > 0) {
                    RevVarArgsData passRevVarArgsData = new RevVarArgsData(mContext);
                    passRevVarArgsData.setRevOwnerEntityGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
                    passRevVarArgsData.setRevContainerEntityGUID(revInfoEntityGUID);

                    new RevPersFilesAsyncTask(passRevVarArgsData, new RevPersFilesAsyncTask.IRevPersAsyncResponse() {
                        @Override
                        public void revPersProcessFinish(Object output) {
                            new RevPostPersServices(mContext).revPostLocalPersRemoteSyncServices();
                        }
                    }).execute(selectedItems, "rev_picture_of");
                }
            }
        });

        return submitFormTab;
    }

    public LinearLayout genPublisherWrapper_LL() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        int textViewBorderSize = (int) (RevLibGenConstantine.REV_MARGIN_TINY * .4);

        GradientDrawable textViewBorder = new GradientDrawable();
        textViewBorder.setStroke(textViewBorderSize, mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));
        textViewBorder.setColor(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.colorTransparent));
        textViewBorder.setGradientType(RECTANGLE);

        Drawable[] textViewLayers = {textViewBorder};
        LayerDrawable textViewLayerDrawable = new LayerDrawable(textViewLayers);
        textViewLayerDrawable.setLayerInset(0, -textViewBorderSize, -textViewBorderSize, -textViewBorderSize, textViewBorderSize);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(linearLayout, textViewLayerDrawable);

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(R.drawable.ic_publish_black_48dp);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setColorFilter(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL);
        layoutParams.gravity = (Gravity.BOTTOM);
        layoutParams.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, (int) (-textViewBorderSize * .1));
        imageView.setLayoutParams(layoutParams);

        /** TEXT VIEW  **/

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING_LINK(.8f);
        textView.setText("Select profile pictures");
        textView.setTextColor(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));
        textView.setGravity(Gravity.BOTTOM);
        textView.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_LARGE, 0);

        LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.gravity = (Gravity.CENTER_VERTICAL);
        textView.setLayoutParams(textView_LP);

        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if (selectedItems != null) {
                    Iterator iterator = revVarArgsData.getRevVarArgsDataMetadataStrings().keySet().iterator();
                    while (iterator.hasNext()) {
                        Object key = iterator.next();

                        if (!selectedItems.contains(key))
                            iterator.remove();
                    }
                }

                RevPictureFileCrawler revPictureFileCrawler = new RevPictureFileCrawler(revVarArgsData, new IRevFileCrawler() {
                    @Override
                    public void revCallBack(List<String> selectedItems) {
                        RevEditRevEntityInfoForm_WIDGET.this.selectedItems = selectedItems;
                        REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_CLEAR_CONTENT_VIEW(selectedItems_LL);

                        if (selectedItems.size() > 0)
                            selectedItems_LL.addView(new RevGenericSelectedItemsView(revVarArgsData).revSelectedItemsView(selectedItems));
                    }
                });

                RevPop.initiatePopupWindow_MATCH_WIDTH(revPictureFileCrawler.createRevFileCrawler());
            }
        });

        return linearLayout;
    }
}
