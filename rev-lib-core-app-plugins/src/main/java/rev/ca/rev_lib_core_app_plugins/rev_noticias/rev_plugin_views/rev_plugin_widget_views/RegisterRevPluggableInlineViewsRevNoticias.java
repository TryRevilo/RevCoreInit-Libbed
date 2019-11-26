package rev.ca.rev_lib_core_app_plugins.rev_noticias.rev_plugin_views.rev_plugin_widget_views;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.IRevRegisterPluggableInlineView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableInlineView;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

public class RegisterRevPluggableInlineViewsRevNoticias implements IRevRegisterPluggableInlineView {

    private static Context mContext = RevLibGenConstantine.REV_CONTEXT;

    @Override
    public List<RevPluggableInlineView> createPluggableRevInlineView() {
        List<RevPluggableInlineView> revPluggableInlineViews = new ArrayList<>();

        revPluggableInlineViews.add(this.revNewNoticiasListingsContainer());
        revPluggableInlineViews.add(this.revNewNoticiasCountTab());

        return revPluggableInlineViews;
    }

    public RevPluggableInlineView revNewNoticiasListingsContainer() {
        LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        RevPluggableInlineView revPluggableInlineView = new RevPluggableInlineView();

        revPluggableInlineView.setInlineView(linearLayout);
        revPluggableInlineView.setViewName("rev_new_noticias_listings_container");

        return revPluggableInlineView;
    }

    public RevPluggableInlineView revNewNoticiasCountTab() {
        TextView textView = new RevCoreInputFormTextView(mContext).getRevExtraSmallBoldTextView(.7f);

        RevPluggableInlineView revPluggableInlineView = new RevPluggableInlineView();

        revPluggableInlineView.setInlineView(textView);
        revPluggableInlineView.setViewName("rev_new_noticias_count_tab");

        return revPluggableInlineView;
    }
}
