package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_pluggable_menues.rev_pluggable_menu_items;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.RevSubmitFormViewContainer;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableMenuItemVM;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 1/19/18.
 */

public class RevAttachBagPluggableMenuItemReg implements ICreateRevPluggableMenuItem {

    private Context mContext;
    private Long revEntityGUID;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    int margin = RevLibGenConstantine.REV_MARGIN_MEDIUM;

    LinearLayout.LayoutParams revPluggableMenuItemVM_LP;

    private String menuItemName = "attach_bags_menu_item";

    public RevAttachBagPluggableMenuItemReg(RevVarArgsData revVarArgsData) {
        revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);

        this.mContext = revVarArgsData.getmContext();
        this.revEntityGUID = revVarArgsData.getRevEntityGUID();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
        revPluggableMenuItemVM_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        revPluggableMenuItemVM_LP.setMargins(margin, 0, 0, 0);
        revPluggableMenuItemVM_LP.gravity = Gravity.CENTER_VERTICAL;
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
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        LinearLayout.LayoutParams linearLayout_LP = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        linearLayout_LP.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);

        ImageView imageView = new ImageView(mContext);

        int imageSizeSmall = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Picasso.get()
                .load(R.drawable.ic_group_add_black_48dp)
                .resize(imageSizeSmall, imageSizeSmall)
                .centerCrop()
                .into(imageView);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM, RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM);
        layoutParams.gravity = (Gravity.BOTTOM);
        imageView.setLayoutParams(layoutParams);

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        textView.setText("Attach");

        LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.gravity = (Gravity.BOTTOM);
        textView_LP.setMargins(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, (int) (RevLibGenConstantine.REV_MARGIN_TINY * .8));
        textView.setLayoutParams(textView_LP);

        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        RevPluggableMenuItemVM revPluggableMenuItemVM = new RevPluggableMenuItemVM();
        revPluggableMenuItemVM.setRevPluggableMenuItemName(menuItemName);
        revPluggableMenuItemVM.setRevPluggableMenuName(null);
        revPluggableMenuItemVM.setRevPluggableMenuView(linearLayout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevVarArgsData revVarArgsData = new RevVarArgsData(RevLibGenConstantine.REV_CONTEXT);
                revVarArgsData.setRevEntityGUID(revEntityGUID);

                revVarArgsData.setRevViewType("RevCreateNewWorkBAGInputForm");
                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revVarArgsData);
                iRevInputFormView.createRevInputForm();
                RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW(
                        "rev_user_profile_view_options_tabs_wrapper",
                        new RevSubmitFormViewContainer(mContext).getRevSubmitFormViewContainer(iRevInputFormView));
            }
        });

        return revPluggableMenuItemVM;
    }
}
