package rev.ca.rev_lib_core_app_plugins.rev_members_plugin.rev_plugin_views.rev_pluggable_menues.menu_item_reg;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableMenuItemVM;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 1/19/18.
 */

public class RevCreateMemberProfileConnectPluggableMenuItemReg implements ICreateRevPluggableMenuItem {

    private Context mContext;
    private RevEntity revEntity;

    private String menuItemName = "member_profile_connect_menu_item";

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    public RevCreateMemberProfileConnectPluggableMenuItemReg(RevVarArgsData revVarArgsData) {
        this.mContext = revVarArgsData.getmContext();
        this.revEntity = revVarArgsData.getRevEntity();

        this.revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    @Override
    public String REV_PLUGGABLE_MENU_ITEM_VM_NAME() {
        return menuItemName;
    }

    @Override
    public List<String> REV_PLUGGABLE_MENU_CONTAINER_NAME() {
        List<String> revPluggableMenuAreas = new ArrayList<>();

        if (revEntity != null && revEntity.get_revEntitySubType().equals("rev_user_entity")) {
            long revLoggedInGUID = REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid();
            long revPageOwnerGUID = REV_SESSION_SETTINGS.getRevCurrentPageRevVarArgsData().getRevEntity().get_remoteRevEntityGUID();

            if (revLoggedInGUID != revPageOwnerGUID) {
                revPluggableMenuAreas.add("rev_profile_content_floating_options_wrapper_menu_area_");
            }
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

        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        linearLayout.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, 0, (int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.4), 0);
        this.setTabViewStyles(linearLayout);
        outerWrapper_LL.addView(linearLayout);

        final ImageView imageView = new ImageView(mContext);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        imageView.setLayoutParams(layoutParams);
        imageView.setColorFilter(ContextCompat.getColor(mContext, R.color.revWhite));

        Picasso.get()
                .load(R.drawable.ic_person_add_black_48dp)
                .resize(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL)
                .centerCrop()
                .into(imageView);

        linearLayout.addView(imageView);

        return outerWrapper_LL;
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
