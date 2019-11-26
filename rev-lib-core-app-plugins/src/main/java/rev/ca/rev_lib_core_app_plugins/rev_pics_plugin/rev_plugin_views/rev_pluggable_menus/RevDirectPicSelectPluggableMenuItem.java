package rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_pluggable_menus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_plugin_views.RevFileExplorer;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableMenuItemVM;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

/**
 * Created by rev on 1/19/18.
 */

public class RevDirectPicSelectPluggableMenuItem implements ICreateRevPluggableMenuItem {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private static List<String> imageURLs = new ArrayList();

    public RevDirectPicSelectPluggableMenuItem(RevVarArgsData revVarArgsData) {
        revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();
    }

    @Override
    public String REV_PLUGGABLE_MENU_ITEM_VM_NAME() {
        return "rev_direct_pic_select_menu_item";
    }

    @Override
    public List<String> REV_PLUGGABLE_MENU_CONTAINER_NAME() {
        return Arrays.asList("rev_direct_select_menu_item");
    }

    @Override
    public RevPluggableMenuItemVM create_REV_PLUGGABLE_MENU_ITEM_VM() {
        ImageView imageView = new ImageView(mContext);

        int imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;
        LinearLayout.LayoutParams imageView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView_LP.setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, (int) (RevLibGenConstantine.REV_MARGIN_TINY * .9), 0);
        imageView_LP.gravity = Gravity.CENTER_VERTICAL;
        imageView.setLayoutParams(imageView_LP);

        imageView.setColorFilter(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.teal_dark));

        Picasso.get()
                .load(R.drawable.ic_add_a_photo_black_48dp)
                .resize(imageSize, imageSize)
                .centerCrop()
                .into(imageView);

        RevPluggableMenuItemVM revPluggableMenuItemVM = new RevPluggableMenuItemVM();
        revPluggableMenuItemVM.setRevPluggableMenuItemName("rev_direct_pic_select_menu_item");
        revPluggableMenuItemVM.setRevPluggableMenuName(Arrays.asList("rev_direct_select_menu_item"));
        revPluggableMenuItemVM.setRevPluggableMenuView(imageView);
        revPluggableMenuItemVM.setRevreturnData(imageURLs);

        return revPluggableMenuItemVM;
    }

    public static class CheckMate extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Intent i = new Intent(this, RevFileExplorer.class);
            startActivityForResult(i, 1);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            ArrayList<Uri> mArrayUri = data.getParcelableArrayListExtra("selected_images");

            if (requestCode == 1) {
                if (resultCode == Activity.RESULT_OK) {
                    for (Uri uri : mArrayUri) {
                        imageURLs.add(getPath(uri));
                    }
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

        public String getPath(Uri uri) {
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(uri, projection, null, null, null);
            startManagingCursor(cursor);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
    }
}