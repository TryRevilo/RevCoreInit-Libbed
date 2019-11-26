package rev.ca.rev_lib_core_views;

import android.util.ArrayMap;
import android.view.View;

import java.util.ArrayList;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

/**
 * Created by rev on 10/18/17.
 */

public interface IRevPluggableViews {

    RevVarArgsData createRevVarArgsData();

    IRevPluggableViewProperties createIRevPluggableViewProperties();

    View createRevEntityListingOptionsTogglerMenuView();

    View createRevPluggableLoggedOutView();

    ArrayList<View> createPluggableTopBarMenuViewItem();

    ArrayMap<View, View> createPluggableALtTopBarMenuViewItem();

    ArrayList<View> createPluggableRevDrawerMenuView();

    View createPluggableRevMainCenterCctView_LL();

    ArrayList<View> createRevNotificationView();

    ArrayMap<View, View> createRevMerryllStripMenuViewItem();

    View createRevPluggableCenterMainContentOverlayFloatingView();

    ArrayList<View> createRevFooterTab();

    ArrayList<View> createRevPluggableFooterMenueTogglerTab();

    Map<String, View> createPluggableRevInlineView();
}
