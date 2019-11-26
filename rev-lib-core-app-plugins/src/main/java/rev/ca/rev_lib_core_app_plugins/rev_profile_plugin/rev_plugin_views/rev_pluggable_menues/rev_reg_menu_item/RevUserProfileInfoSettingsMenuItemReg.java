package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_item;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableMenuItemVM;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

public class RevUserProfileInfoSettingsMenuItemReg implements ICreateRevPluggableMenuItem {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private String menuItemName = "rev_user_profile_info_settings_menu_item";

    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevUserProfileInfoSettingsMenuItemReg(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        this.revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    @Override
    public String REV_PLUGGABLE_MENU_ITEM_VM_NAME() {
        return menuItemName;
    }

    @Override
    public List<String> REV_PLUGGABLE_MENU_CONTAINER_NAME() {
        return Arrays.asList("rev_main_user_settings_menu_wrapper");
    }

    @Override
    public RevPluggableMenuItemVM create_REV_PLUGGABLE_MENU_ITEM_VM() {
        TextView textView = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.8f);
        textView.setText("iNFo sETTiNGs");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

                int revPadding = RevLibGenConstantine.REV_MARGIN_SMALL;

                for (int i = 0; i < 155; i++) {
                    TextView revTV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_ITALICS();
                    revTV.setText("HOLDER : " + revVarArgsData.getRevEntity().get_revEntitySubType() + " >>>>>>>> ::::::::: >>>>>>>>>>>>>> +++++++++++ <<<<<<<<<<<<<<<< _________________ ---------------");
                    revTV.setPadding(revPadding, revPadding, revPadding, revPadding);

                    linearLayout.addView(revTV);
                }

                RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("rev_settings_item_view_container_pluggable_scroll_view", linearLayout);
            }
        });

        RevPluggableMenuItemVM revPluggableMenuItemVM = new RevPluggableMenuItemVM();
        revPluggableMenuItemVM.setRevPluggableMenuItemName(menuItemName);
        revPluggableMenuItemVM.setRevPluggableMenuName(null);
        revPluggableMenuItemVM.setRevPluggableMenuView(textView);

        return revPluggableMenuItemVM;
    }
}
