package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_pluggable_menues.rev_pluggable_menu_items;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.RevSubmitFormViewContainer;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableMenuItemVM;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;

/**
 * Created by rev on 1/19/18.
 */

public class RevCreateNewBagPluggableMenuItemReg implements ICreateRevPluggableMenuItem {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private String menuItemName = "create_new_bag_menu_item";

    public RevCreateNewBagPluggableMenuItemReg(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();
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
        LinearLayout.LayoutParams contentHeaderItemTab_TV_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentHeaderItemTab_TV_LP.gravity = Gravity.CENTER_VERTICAL;

        TextView createNewBag_TV = new RevCoreInputFormTextView(mContext).getRevVeryExtraSmallNormalTextView_NO_PADDING((float) .8);
        createNewBag_TV.setText("cREATE NEw spAcE");
        createNewBag_TV.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        createNewBag_TV.setLayoutParams(contentHeaderItemTab_TV_LP);

        RevPluggableMenuItemVM revPluggableMenuItemVM = new RevPluggableMenuItemVM();
        revPluggableMenuItemVM.setRevPluggableMenuItemName(menuItemName);
        revPluggableMenuItemVM.setRevPluggableMenuName(null);
        revPluggableMenuItemVM.setRevPluggableMenuView(createNewBag_TV);

        createNewBag_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevVarArgsData passRVD = new RevVarArgsData(mContext);
                passRVD.setRevEntity(revVarArgsData.getRevEntity());
                passRVD.setRevViewType("RevBagTypeChooserInputForm");
                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(passRVD);
                iRevInputFormView.createRevInputForm();
                new RevPop().initiatePopupWindow_ClearBackground(new RevSubmitFormViewContainer(mContext).getRevSubmitFormViewContainer(iRevInputFormView));
            }
        });

        return revPluggableMenuItemVM;
    }
}
