package rev.ca.rev_lib_core_app_plugins.rev_announcement_plugin.rev_plugin_views.rev_pluggable_menues;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.RevSubmitFormViewContainer;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableMenuItemVM;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 1/20/18.
 */

public class RevPluggableOptionsMenuItem_PUBLISH_ANNOUNCEMENT implements ICreateRevPluggableMenuItem {

    private RevVarArgsData revVarArgsData;
    private Context mContext;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    private String menuItemName = "publish_announcement_menu_item";

    public RevPluggableOptionsMenuItem_PUBLISH_ANNOUNCEMENT(RevVarArgsData revVarArgsData) {
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
        return Arrays.asList("rev_core_gen_publisher_options_menu", "rev_bag_options_menu", "rev_user_options_menu");
    }

    @Override
    public RevPluggableMenuItemVM create_REV_PLUGGABLE_MENU_ITEM_VM() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        ImageView imageView = new ImageView(mContext);
        imageView.setPadding(0, 0, 0, 0);
        imageView.setImageResource(R.drawable.ic_note_add_black_48dp);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_dark));

        int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_TINY * 1.5);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(imageSize, imageSize);
        layoutParams.gravity = (Gravity.TOP);
        imageView.setLayoutParams(layoutParams);

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING_LINK();
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
                RevVarArgsData postRVD = new RevVarArgsData();
                postRVD.setmContext(revVarArgsData.getmContext());
                postRVD.setRevEntity(revVarArgsData.getRevEntity());

                postRVD.setRevViewType("RevCreateAnnouncementInputForm");
                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(postRVD);
                iRevInputFormView.createRevInputForm();
                RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW(
                        "user_profile_center_cct_view_LL",
                        new RevSubmitFormViewContainer(mContext).getRevSubmitFormViewContainer(iRevInputFormView));
            }
        });

        RevPluggableMenuItemVM revPluggableMenuItemVM = new RevPluggableMenuItemVM();
        revPluggableMenuItemVM.setRevPluggableMenuItemName(menuItemName);
        revPluggableMenuItemVM.setRevPluggableMenuName(Arrays.asList("rev_core_gen_publisher_options_menu", "rev_BAG_options_menu"));
        revPluggableMenuItemVM.setRevPluggableMenuView(linearLayout);

        return revPluggableMenuItemVM;
    }
}
