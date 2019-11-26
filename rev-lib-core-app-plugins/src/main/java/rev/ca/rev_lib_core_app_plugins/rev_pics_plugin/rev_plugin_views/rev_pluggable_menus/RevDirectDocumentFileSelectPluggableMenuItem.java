package rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_pluggable_menus;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableMenuItemVM;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;

/**
 * Created by rev on 1/19/18.
 */

public class RevDirectDocumentFileSelectPluggableMenuItem implements ICreateRevPluggableMenuItem {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    LinearLayout.LayoutParams revPluggableMenuItemVM_LP;

    int margin = RevLibGenConstantine.REV_MARGIN_MEDIUM;

    int imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

    public RevDirectDocumentFileSelectPluggableMenuItem(RevVarArgsData revVarArgsData) {
        revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);

        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revPluggableMenuItemVM_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        revPluggableMenuItemVM_LP.setMargins(margin, 0, 0, 0);
        revPluggableMenuItemVM_LP.gravity = Gravity.CENTER_VERTICAL;
    }

    @Override
    public String REV_PLUGGABLE_MENU_ITEM_VM_NAME() {
        return "rev_direct_document_file_select_menu_item";
    }

    @Override
    public List<String> REV_PLUGGABLE_MENU_CONTAINER_NAME() {
        return Arrays.asList("rev_direct_select_menu_item");
    }

    @Override
    public RevPluggableMenuItemVM create_REV_PLUGGABLE_MENU_ITEM_VM() {
        ImageView photoTab_IV = new ImageView(mContext);
        photoTab_IV.setColorFilter(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.teal_dark));
        photoTab_IV.setLayoutParams(revPluggableMenuItemVM_LP);

        Picasso.get()
                .load(R.drawable.ic_folder_black_48dp)
                .resize(imageSize, imageSize)
                .centerCrop()
                .into(photoTab_IV);

        RevPluggableMenuItemVM revPluggableMenuItemVM = new RevPluggableMenuItemVM();
        revPluggableMenuItemVM.setRevPluggableMenuItemName("rev_direct_document_file_select_menu_item");
        revPluggableMenuItemVM.setRevPluggableMenuName(Arrays.asList("rev_direct_select_menu_item"));
        revPluggableMenuItemVM.setRevPluggableMenuView(photoTab_IV);

        return revPluggableMenuItemVM;
    }
}