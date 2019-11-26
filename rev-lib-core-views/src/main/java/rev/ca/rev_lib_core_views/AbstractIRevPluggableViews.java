package rev.ca.rev_lib_core_views;

import android.content.Context;
import android.util.ArrayMap;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rev.ca.rev_lib_core_views.rev_view_widgets.rev_core_section_widgets.rev_view_widget_services.IRevObjectListingFooterOptions;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.rev_pre_post_pers.IPostRevPersAction;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.rev_pre_post_pers.IPreRevPersAction;

/**
 * Created by rev on 10/18/17.
 */

public abstract class AbstractIRevPluggableViews implements IRevPluggableViews, IRevObjectListingFooterOptions, IPreRevPersAction, IPostRevPersAction {

    private RevVarArgsData revVarArgsData;
    private final Context mContext;


    public AbstractIRevPluggableViews(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();
    }

    protected Context getmContext() {
        return this.mContext;
    }

    @Override
    public RevVarArgsData createRevVarArgsData() {
        return new RevVarArgsData();
    }

    @Override
    public IRevPluggableViewProperties createIRevPluggableViewProperties() {
        return new IRevPluggableViewProperties(mContext);
    }

    @Override
    public ArrayList<View> createPluggableTopBarMenuViewItem() {
        ArrayList<View> views = new ArrayList<>();
        return views;
    }

    @Override
    public View createRevEntityListingOptionsTogglerMenuView() {
        return null;
    }

    @Override
    public View createRevObjectListingFooterOptionView() {
        return null;
    }

    @Override
    public ArrayMap<View, View> createPluggableALtTopBarMenuViewItem() {
        ArrayMap<View, View> arrayMap = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            arrayMap = new ArrayMap<>();
        }
        return arrayMap;
    }

    @Override
    public ArrayList<View> createPluggableRevDrawerMenuView() {
        ArrayList<View> views = new ArrayList<>();
        return views;
    }

    @Override
    public ArrayMap<View, View> createRevMerryllStripMenuViewItem() {
        ArrayMap<View, View> arrayMap = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            arrayMap = new ArrayMap<>();
        }
        return arrayMap;
    }

    @Override
    public View createPluggableRevMainCenterCctView_LL() {
        return null;
    }

    @Override
    public View createRevPluggableCenterMainContentOverlayFloatingView() {
        return null;
    }

    @Override
    public ArrayList<View> createRevNotificationView() {
        ArrayList<View> views = new ArrayList<>();
        return views;
    }

    @Override
    public View createRevPluggableLoggedOutView() {
        return null;
    }

    @Override
    public ArrayList<View> createRevFooterTab() {
        ArrayList<View> views = new ArrayList<>();
        return views;
    }

    @Override
    public ArrayList<View> createRevPluggableFooterMenueTogglerTab() {
        ArrayList<View> views = new ArrayList<>();
        return views;
    }

    @Override
    public Map<String, View> createPluggableRevInlineView() {
        Map hashMap = new HashMap();
        return hashMap;
    }

    @Override
    public void initPrePersRevAction(RevEntity revEntity) {

    }

    @Override
    public void initPostPersRevAction(RevEntity revEntity) {

    }
}
