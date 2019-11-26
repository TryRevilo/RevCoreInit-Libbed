package rev.ca.rev_lib_core_app_plugins.rev_members_plugin.rev_plugin_views.rev_pluggable_menues.menu_item_reg;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityRelationship;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_entity.RevDownloadUserFromServer;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableMenuItemVM;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 1/19/18.
 */

public class RevConnectWithMemberPluggableMenuItemReg implements ICreateRevPluggableMenuItem {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private String menuItemName = "rev_connect_user_menu_item";

    private RevEntity revEntity;
    private String revEntityName, revEntityEmail;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    RevPersLibRead revPersLibRead = new RevPersLibRead();

    public RevConnectWithMemberPluggableMenuItemReg(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();
        this.revEntity = revVarArgsData.getRevEntity();

        if (revEntity == null || !revEntity.get_revEntitySubType().equals("rev_user_entity") || revEntity.get_remoteRevEntityGUID().longValue() == REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid().longValue())
            return;

        revEntityName = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_entity_full_names_value");
        revEntityEmail = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_user_entity_email_value");

        this.revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    @Override
    public String REV_PLUGGABLE_MENU_ITEM_VM_NAME() {
        return menuItemName;
    }

    @Override
    public List<String> REV_PLUGGABLE_MENU_CONTAINER_NAME() {
        List<String> revPluggableMenuAreas = new ArrayList<>();

        if (revEntity != null && revEntity.get_revEntityType().equals("rev_user_entity") && REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid().longValue() != revEntity.get_remoteRevEntityGUID().longValue()) {
            revPluggableMenuAreas.add("rev_profile_content_floating_options_wrapper_menu_area");
        }

        return revPluggableMenuAreas;
    }

    @Override
    public RevPluggableMenuItemVM create_REV_PLUGGABLE_MENU_ITEM_VM() {
        RevPluggableMenuItemVM revPluggableMenuItemVM = new RevPluggableMenuItemVM();
        revPluggableMenuItemVM.setRevPluggableMenuItemName(menuItemName);
        revPluggableMenuItemVM.setRevPluggableMenuName(Arrays.asList("rev_profile_content_floating_options_wrapper_menu_area"));
        revPluggableMenuItemVM.setRevPluggableMenuView(this.revGetConnectOptionTab());

        return revPluggableMenuItemVM;
    }

    private View revGetConnectOptionTab() {
        LinearLayout outerWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        outerWrapper_LL.setPadding(0, (int) (RevLibGenConstantine.REV_MARGIN_TINY * .5), 0, 0);
        outerWrapper_LL.setBackgroundColor(ContextCompat.getColor(mContext, R.color.rcolorAccent_OPAQUE));
        ((LinearLayout.LayoutParams) outerWrapper_LL.getLayoutParams()).setMargins(0, 0, 1, 0);

        final LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        linearLayout.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, 0, (int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.4), 0);
        this.setTabViewStyles(linearLayout);
        outerWrapper_LL.addView(linearLayout);

        final int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        final int revResId;

        final int revEntityRelExists = revPersLibRead.revGetAnyRelExists_By_ResStatus_TypeValueId_RemoteGUIDs(0, "rev_entity_connect_members", REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid(), revEntity.get_remoteRevEntityGUID());
        final int revEntityAcceptedRelExists = revPersLibRead.revGetAnyRelExists_By_ResStatus_TypeValueId_RemoteGUIDs(1, "rev_entity_connect_members", REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid(), revEntity.get_remoteRevEntityGUID());

        if (revEntityRelExists == 1 || revEntityAcceptedRelExists == 1) {
            revResId = R.drawable.baseline_person_add_disabled_black_48dp;
        } else {
            revResId = R.drawable.ic_person_add_black_48dp;
        }

        final ImageView imageView = new ImageView(mContext);
        imageView.setColorFilter(ContextCompat.getColor(mContext, R.color.revWhite));
        imageView.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_TINY, 0);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(imageView);

        Picasso.get()
                .load(revResId)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (revEntityRelExists < 0) {
                    revConnectMembers();

                    Picasso.get()
                            .load(R.drawable.ic_person_add_black_48dp)
                            .resize(imgSize, imgSize)
                            .centerCrop()
                            .into(imageView);

                } else {
                    revActionMsgCall("You have already contacted " + revEntityName + ". Please wait for a reply");

                    Picasso.get()
                            .load(R.drawable.baseline_person_add_disabled_black_48dp)
                            .resize(imgSize, imgSize)
                            .centerCrop()
                            .into(imageView);
                }

                REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_RESET_CONTENT_VIEW(linearLayout, imageView);
            }
        });

        linearLayout.addView(imageView);

        return outerWrapper_LL;
    }

    private void revConnectMembers() {
        new RevDownloadUserFromServer(mContext, revEntityEmail, new RevDownloadUserFromServer.IRevDownloadUserFromServer() {
            @Override
            public void onRevDownloadUserProcessFinish(RevEntity revSavedRevEntity) {
                String revOptText;

                revSavedRevEntity.set_revEntityResolveStatus(0);

                RevEntityRelationship revEntityRelationship = new RevEntityRelationship("rev_entity_connect_members", REV_SESSION_SETTINGS.getRevLoggedInEntityGuid(), revSavedRevEntity.get_revEntityGUID());
                revEntityRelationship.set_revResolveStatus(-1);
                revEntityRelationship.set_remoteRevEntityTargetGUID(revSavedRevEntity.get_remoteRevEntityGUID());
                revEntityRelationship.set_remoteRevEntitySubjectGUID(REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid());

                long revRelationshipId = (long) new RevPersJava().saveRevEntity(revEntityRelationship);

                if (revRelationshipId > 0) {
                    revOptText = "Please wait for " + revEntityName + " to reply";
                } else {
                    revOptText = "revRelationshipId : " + revSavedRevEntity.get_revEntityGUID() + " There was a problem connecting with" + revEntityName;
                }

                revActionMsgCall(revOptText);
            }
        });
    }

    private void revActionMsgCall(String revMsg) {
        TextView textView = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView();
        textView.setText(revMsg);
        textView.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        textView.setBackgroundColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.teal_500_dark));
        textView.setPadding(RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_SMALL);

        Toast myToast = new Toast(mContext);
        myToast.setView(textView);
        myToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        myToast.setDuration(Toast.LENGTH_LONG);
        myToast.show();
    }

    private void setTabViewStyles(View view) {
        int borderSize = 8;

        GradientDrawable border = new GradientDrawable();
        border.setStroke(borderSize, mContext.getResources().getColor(R.color.teal_200_dark));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -borderSize, borderSize, -borderSize, -borderSize);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(view, layerDrawable);

        int imageViewBorderSize = 8;

        GradientDrawable imageViewBorderTop = new GradientDrawable();
        imageViewBorderTop.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.rev_red));
        imageViewBorderTop.setColor(ContextCompat.getColor(mContext, R.color.teal_300_dark));
        imageViewBorderTop.setGradientType(RECTANGLE);

        GradientDrawable imageViewBorder = new GradientDrawable();
        imageViewBorder.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.teal_dark));
        imageViewBorder.setColor(ContextCompat.getColor(mContext, R.color.teal_dark));
        imageViewBorder.setGradientType(RECTANGLE);

        Drawable[] imageViewLayers = {imageViewBorderTop, imageViewBorder};
        LayerDrawable imageViewLayerDrawable = new LayerDrawable(imageViewLayers);
        imageViewLayerDrawable.setLayerInset(0, -imageViewBorderSize, imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize);
        imageViewLayerDrawable.setLayerInset(1, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize, imageViewBorderSize);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(view, imageViewLayerDrawable);
    }
}
