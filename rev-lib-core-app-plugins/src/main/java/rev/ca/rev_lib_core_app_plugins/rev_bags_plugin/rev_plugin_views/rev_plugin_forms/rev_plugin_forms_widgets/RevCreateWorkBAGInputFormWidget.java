package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersFilesAsyncTask;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_server_client_services.RevPostPersServices;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_plugin_views.rev_file_choosers.RevFileChooser;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_menues.RevCoreColoredTabs;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 2/5/18.
 */

public class RevCreateWorkBAGInputFormWidget {

    private Context mContext;
    private RevVarArgsData revVarArgsData;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormEditText revCoreInputFormEditText;
    private EditText bagName_ET, companyName_ET, departmentName_ET;

    private List<String> selectedItems = new ArrayList<>();

    public RevCreateWorkBAGInputFormWidget(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = this.revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(this.mContext);
        revCoreInputFormEditText = new RevCoreInputFormEditText(this.mContext);

        bagName_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        bagName_ET.setHint("Name Of Space");

        companyName_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        companyName_ET.setHint("Company | Business name");

        departmentName_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        departmentName_ET.setHint("Department");
    }

    public View revInputFormView() {
        final LinearLayout revInputFormViewContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revInputFormViewContainer_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);

        revInputFormViewContainer_LL.addView(bagName_ET);
        revInputFormViewContainer_LL.addView(companyName_ET);
        revInputFormViewContainer_LL.addView(departmentName_ET);
        revInputFormViewContainer_LL.addView(this.genPublisherWrapper_LL());

        RevVarArgsData revPassRVD = new RevVarArgsData(mContext);
        revPassRVD.getRevVarArgsDataMetadataStrings().put("rev_file_selection_tab_text", "sELEcT spAcE pRoFiLE picTuREs");

        new RevFileChooser(revPassRVD, new RevFileChooser.IRevFileChooser() {
            @Override
            public void revFileChooser(View revSelectItemsTab) {
                revInputFormViewContainer_LL.addView(revSelectItemsTab);
            }

            @Override
            public void revSelectedItemsCallBack(List<String> _selectedItems) {
                selectedItems = _selectedItems;
            }
        });

        revInputFormViewContainer_LL.addView(this.submitRevInputFormBtn());

        return revInputFormViewContainer_LL;
    }

    public RevEntity getRevEntityData() {
        RevEntity revPersEntity = new RevEntity();
        revPersEntity.set_revEntityType("rev_group_entity");
        revPersEntity.set_revEntitySubType("rev_bag");
        revPersEntity.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        revPersEntity.set_revEntityChildableStatus(1);

        revPersEntity.set_revEntityMetadataList(Arrays.asList(
                new RevEntityMetadata("rev_grp_entity_type_of_bag", "work_bag"),
                new RevEntityMetadata("rev_entity_full_names_value", String.valueOf(bagName_ET.getText()))
        ));

        return revPersEntity;
    }

    public RevEntity getRevEntityInfoFormData() {
        RevEntity revPersEntity = new RevEntity();
        revPersEntity.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
        revPersEntity.set_revEntitySubType("rev_space_entity_info");
        revPersEntity.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        revPersEntity.set_revEntityChildableStatus(3);

        revPersEntity.set_revEntityMetadataList(Arrays.asList(
                new RevEntityMetadata("rev_grp_entity_space_name", String.valueOf(bagName_ET.getText())),
                new RevEntityMetadata("rev_grp_entity_company_name", String.valueOf(companyName_ET.getText())),
                new RevEntityMetadata("rev_grp_entity_department", String.valueOf(departmentName_ET.getText()))
        ));

        return revPersEntity;
    }

    public LinearLayout genPublisherWrapper_LL() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        /** TITLE BORDERS **/

        int imageViewBorderSize = 8;

        GradientDrawable imageViewBorder = new GradientDrawable();
        imageViewBorder.setStroke(imageViewBorderSize, mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));
        imageViewBorder.setColor(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.revWhite));
        imageViewBorder.setGradientType(RECTANGLE);

        Drawable[] imageViewLayers = {imageViewBorder};
        LayerDrawable imageViewLayerDrawable = new LayerDrawable(imageViewLayers);
        imageViewLayerDrawable.setLayerInset(0, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize, imageViewBorderSize);

        ImageView imageView = new ImageView(mContext);
        imageView.setPadding(0, 0, 0, 0);
        imageView.setImageResource(R.drawable.ic_publish_black_48dp);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setColorFilter(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(imageView, imageViewLayerDrawable);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL);
        layoutParams.gravity = (Gravity.BOTTOM);
        layoutParams.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);
        imageView.setLayoutParams(layoutParams);


        return linearLayout;
    }

    private View submitRevInputFormBtn() {
        TextView submitFormTab = new RevCoreColoredTabs(mContext).getRevColoredTab();
        submitFormTab.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));
        submitFormTab.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.deep_purple_dark));
        submitFormTab.setText("puBLisH spAcE");

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        submitFormTab.setLayoutParams(layoutParams);
        ((LinearLayout.LayoutParams) submitFormTab.getLayoutParams()).setMargins(
                (int) (RevLibGenConstantine.REV_MARGIN_SMALL * .75), RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        submitFormTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long revSpaceEntityGUID = (Long) RevConstantinePluggableViewsServices.REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevPublishBagAction").initRevAction(getRevEntityData());

                RevEntity revEntityInfo = getRevEntityInfoFormData();
                revEntityInfo.set_revEntityContainerGUID(revSpaceEntityGUID);

                Long revInfoEntityGUID = (Long) new RevPersJava().saveRevEntity(revEntityInfo);

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
}
