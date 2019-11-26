package rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_pluggable_menues;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.REV_RESET_PAGE_CONTENT;
import rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_plugin_views.RevMemoNoticiasListingView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableMenuItemVM;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 1/20/18.
 */

public class RevPluggableOptionsMenuItem_LIST_PROFILE_MEMO implements ICreateRevPluggableMenuItem {

    private RevVarArgsData revVarArgsData;
    private Context mContext;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    private String menuItemName = "memo_profile_listings_menu_item";

    public RevPluggableOptionsMenuItem_LIST_PROFILE_MEMO(RevVarArgsData revVarArgsData) {
        revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);

        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

    }

    @Override
    public String REV_PLUGGABLE_MENU_ITEM_VM_NAME() {
        return menuItemName;
    }

    @Override
    public List<String> REV_PLUGGABLE_MENU_CONTAINER_NAME() {
        return Arrays.asList("rev_profile_floating_options_menu");
    }

    @Override
    public RevPluggableMenuItemVM create_REV_PLUGGABLE_MENU_ITEM_VM() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_TINY * 1.5);

        ImageView imageView = new ImageView(mContext);
        imageView.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_dark));

        Picasso.get()
                .load(R.drawable.icons8_note_64)
                .resize(imageSize, imageSize)
                .centerCrop()
                .into(imageView);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(imageSize, imageSize);
        layoutParams.gravity = (Gravity.TOP);
        imageView.setLayoutParams(layoutParams);

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING_LINK();
        textView.setText("mEmos");

        LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.gravity = (Gravity.BOTTOM);
        textView_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_TINY * 0.5), 0, 0, 0);
        textView.setLayoutParams(textView_LP);

        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                REV_RESET_PAGE_CONTENT.REV_RESET_PAGE_CONTENT(new RevMemoNoticiasListingView(revVarArgsData).getRevMemoNoticiasListingView());
                RevPop.closerevPopUpWin(RevPop.REV_POPUP_WIN_LIST.size());
            }
        });

        RevPluggableMenuItemVM revPluggableMenuItemVM = new RevPluggableMenuItemVM();
        revPluggableMenuItemVM.setRevPluggableMenuItemName(menuItemName);
        revPluggableMenuItemVM.setRevPluggableMenuName(Arrays.asList("rev_profile_floating_options_menu"));
        revPluggableMenuItemVM.setRevPluggableMenuView(linearLayout);

        return revPluggableMenuItemVM;
    }
}
