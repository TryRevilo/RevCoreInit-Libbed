package rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_pluggable_menues;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.REV_RESET_PAGE_CONTENT;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.RevSubmitFormViewContainer;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableMenuItemVM;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 1/19/18.
 */

public class RevCreateNewMemoTitleBarPluggableMenuItemReg implements ICreateRevPluggableMenuItem {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private String menuItemName = "publish_new_memo_title_bar_menu_item";

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    public RevCreateNewMemoTitleBarPluggableMenuItemReg(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        this.revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
    }

    @Override
    public String REV_PLUGGABLE_MENU_ITEM_VM_NAME() {
        return menuItemName;
    }

    @Override
    public List<String> REV_PLUGGABLE_MENU_CONTAINER_NAME() {
        return null;
    }

    @Override
    public RevPluggableMenuItemVM create_REV_PLUGGABLE_MENU_ITEM_VM() {
        LinearLayout linearLayoutWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_TINY * 1.5);

        ImageView imageView = new ImageView(mContext);
        imageView.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_dark));

        Picasso.get()
                .load(R.drawable.icons8_note_64)
                .resize(imageSize, imageSize)
                .centerCrop()
                .into(imageView);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(imageSize, imageSize);
        layoutParams.gravity = (Gravity.CENTER_VERTICAL);
        imageView.setLayoutParams(layoutParams);

        linearLayoutWrapper_LL.addView(imageView);

        TextView publishNewItem_TV = new RevCoreInputFormTextView(mContext).getRevExtraSmallBoldTextView_NOPADDING((float) .9);
        publishNewItem_TV.setText("puBLisH NEw mEmo");
        publishNewItem_TV.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        ((LinearLayout.LayoutParams) publishNewItem_TV.getLayoutParams()).gravity = Gravity.BOTTOM;

        linearLayoutWrapper_LL.addView(publishNewItem_TV);

        RevPluggableMenuItemVM revPluggableMenuItemVM = new RevPluggableMenuItemVM();
        revPluggableMenuItemVM.setRevPluggableMenuItemName(menuItemName);
        revPluggableMenuItemVM.setRevPluggableMenuName(null);
        revPluggableMenuItemVM.setRevPluggableMenuView(linearLayoutWrapper_LL);

        publishNewItem_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevVarArgsData postRVD = new RevVarArgsData();
                postRVD.setmContext(revVarArgsData.getmContext());
                postRVD.setRevEntity(revVarArgsData.getRevEntity());

                postRVD.setRevViewType("RevCreateMemoInputForm");
                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(postRVD);
                iRevInputFormView.createRevInputForm();
                REV_RESET_PAGE_CONTENT.REV_RESET_PAGE_CONTENT(new RevSubmitFormViewContainer(mContext).getRevSubmitFormViewContainer(iRevInputFormView));
            }
        });

        return revPluggableMenuItemVM;
    }
}
