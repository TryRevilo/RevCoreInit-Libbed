package rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_pluggable_menues;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableMenuItemVM;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLinearLayoutLayoutParams;

/**
 * Created by rev on 1/19/18.
 */

public class RevKiwiPluggableMenuItem_DORMANT_TAB implements ICreateRevPluggableMenuItem {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private String menuName = "kiwi_dormant_tab";
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    int margin = RevLibGenConstantine.REV_MARGIN_MEDIUM;

    LinearLayout.LayoutParams revPluggableMenuItemVM_LP;

    public RevKiwiPluggableMenuItem_DORMANT_TAB(RevVarArgsData revVarArgsData) {
        revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.mContext = revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revPluggableMenuItemVM_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        revPluggableMenuItemVM_LP.setMargins(margin, 0, 0, 0);
        revPluggableMenuItemVM_LP.gravity = Gravity.CENTER_VERTICAL;
    }

    @Override
    public String REV_PLUGGABLE_MENU_ITEM_VM_NAME() {
        return menuName;
    }

    @Override
    public List<String> REV_PLUGGABLE_MENU_CONTAINER_NAME() {
        return null;
    }

    @Override
    public RevPluggableMenuItemVM create_REV_PLUGGABLE_MENU_ITEM_VM() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();

        LinearLayout.LayoutParams contentLLItems_LP = RevCoreLinearLayoutLayoutParams.getRev_WRAP_ALL_LP();
        contentLLItems_LP.gravity = (Gravity.CENTER_HORIZONTAL);

        TextView extrasTxtOverlayTV = new RevCoreInputFormTextView(mContext).getRevSmallNormalTextView();
        extrasTxtOverlayTV.setText("Kiwi");
        int padding = RevLibGenConstantine.REV_MARGIN_SMALL;
        extrasTxtOverlayTV.setPadding(padding, padding, padding, padding);

        GradientDrawable gd = new GradientDrawable();
        // gd.setColor(0xFF00FF00); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(5);
        gd.setStroke(1, 0xFF000000);
        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(extrasTxtOverlayTV, gd);

        extrasTxtOverlayTV.setLayoutParams(contentLLItems_LP);

        ImageView kiwiPointer_IV = new ImageView(mContext);

        int imageSizeSmall = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Picasso.get()
                .load(R.drawable.ic_arrow_downward_black_48dp)
                .resize(imageSizeSmall, imageSizeSmall)
                .centerCrop()
                .into(kiwiPointer_IV);

        LinearLayout.LayoutParams kiwiPointer_IV_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        kiwiPointer_IV_LP.gravity = (Gravity.CENTER);
        kiwiPointer_IV.setLayoutParams(kiwiPointer_IV_LP);

        linearLayout.addView(extrasTxtOverlayTV);
        linearLayout.addView(kiwiPointer_IV);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, RevKiwiPluggableMenuItem.CheckMate.class);
                mContext.startActivity(myIntent);
            }
        });

        RevPluggableMenuItemVM revPluggableMenuItemVM = new RevPluggableMenuItemVM();
        revPluggableMenuItemVM.setRevPluggableMenuItemName(menuName);
        revPluggableMenuItemVM.setRevPluggableMenuName(null);
        revPluggableMenuItemVM.setRevPluggableMenuView(linearLayout);

        return revPluggableMenuItemVM;
    }
}
