package rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_pluggable_menues;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.rev_kiwi_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_widgets.RevCreateKiwiInputForm_WIDGETS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateMultiRevPluggableMenuItems;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableMenuItemVM;
import rev.ca.rev_lib_gen_functions.RevFileExplorer;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLinearLayoutLayoutParams;

/**
 * Created by rev on 1/19/18.
 */

public class RevKiwiPluggableMenuItem implements ICreateMultiRevPluggableMenuItems {

    private Context mContext;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    int margin = RevLibGenConstantine.REV_MARGIN_MEDIUM;

    LinearLayout.LayoutParams revPluggableMenuItemVM_LP;

    private static List<String> imageURLs = new ArrayList();

    public RevKiwiPluggableMenuItem(Context mContext) {
        this.mContext = mContext;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
        revPluggableMenuItemVM_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        revPluggableMenuItemVM_LP.setMargins(margin, 0, 0, 0);
        revPluggableMenuItemVM_LP.gravity = Gravity.CENTER_VERTICAL;
    }

    @Override
    public List<RevPluggableMenuItemVM> create_REV_PLUGGABLE_MENU_ITEM_VM() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        ImageView imageView = new ImageView(mContext);
        imageView.setPadding(0, 0, 0, 0);
        imageView.setImageResource(R.drawable.ic_wb_sunny_black_48dp);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_TINY * 1.5);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(imageSize, imageSize);
        layoutParams.gravity = (Gravity.TOP);
        imageView.setLayoutParams(layoutParams);

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        textView.setText("Announcement");

        LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.gravity = (Gravity.BOTTOM);
        textView_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_TINY * 0.5), 0, 0, 0);
        textView.setLayoutParams(textView_LP);

        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, RevFileExplorer.class);
                mContext.startActivity(myIntent);
            }
        });

        RevPluggableMenuItemVM revPluggableMenuItemVM = new RevPluggableMenuItemVM();
        revPluggableMenuItemVM.setRevPluggableMenuItemName("publish_memo");
        revPluggableMenuItemVM.setRevPluggableMenuName(Arrays.asList("rev_core_gen_publisher_options_menu", "rev_bag_options_menu", "rev_user_options_menu"));
        revPluggableMenuItemVM.setRevPluggableMenuView(linearLayout);

        List<RevPluggableMenuItemVM> revPluggableMenuItemVMList = new ArrayList<>();

        revPluggableMenuItemVMList.add(this.kiwiActivityStreamListingTab());
        revPluggableMenuItemVMList.add(revPluggableMenuItemVM);

        return revPluggableMenuItemVMList;
    }

    private RevPluggableMenuItemVM kiwiActivityStreamListingTab() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();

        LinearLayout.LayoutParams linearLayout_LP = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        linearLayout_LP.setMargins((int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * 1.5) + RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);
        linearLayout_LP.gravity = (Gravity.TOP);

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

        RevPluggableMenuItemVM revPluggableMenuItemVM = new RevPluggableMenuItemVM();
        revPluggableMenuItemVM.setRevPluggableMenuItemName("kiwi_dormant_tab");
        revPluggableMenuItemVM.setRevPluggableMenuName(null);
        revPluggableMenuItemVM.setRevPluggableMenuView(linearLayout);

        return revPluggableMenuItemVM;
    }

    public static class CheckMate extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Intent i = new Intent(this, rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_plugin_views.RevFileExplorer.class);
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

                    RevCreateKiwiInputForm_WIDGETS.getSelectedImages_TV().setText("Selected images : " + imageURLs.size());
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
