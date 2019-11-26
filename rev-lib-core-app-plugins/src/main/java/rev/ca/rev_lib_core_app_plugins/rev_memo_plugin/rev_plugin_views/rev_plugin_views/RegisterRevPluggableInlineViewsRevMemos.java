package rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_plugin_views;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_pluggable_menues.RevMemoNoticiasTopBarView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.IRevRegisterPluggableInlineView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableInlineView;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;

public class RegisterRevPluggableInlineViewsRevMemos implements IRevRegisterPluggableInlineView {

    private static Context mContext = RevLibGenConstantine.REV_CONTEXT;

    @Override
    public List<RevPluggableInlineView> createPluggableRevInlineView() {
        List<RevPluggableInlineView> revPluggableInlineViews = new ArrayList<>();

        revPluggableInlineViews.add(this.revNewNoticiasListingsContainer());

        return revPluggableInlineViews;
    }

    public RevPluggableInlineView revNewNoticiasListingsContainer() {
        RevPluggableInlineView revPluggableInlineView = new RevPluggableInlineView();

        revPluggableInlineView.setInlineView(new RevMemoNoticiasTopBarView(mContext).getRevMemoNoticiasTopBarView());
        revPluggableInlineView.setViewName("rev_new_noticias_listings_container");

        return revPluggableInlineView;
    }
}
