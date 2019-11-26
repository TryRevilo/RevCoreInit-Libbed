package rev.ca.rev_lib_core_app_plugins.rev_settings_plugin.rev_plugin_views.rev_registered_inline_views;

import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.IRevRegisterPluggableInlineView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableInlineView;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;

public class RegisterRevSettingsPluggableInlineViews implements IRevRegisterPluggableInlineView {

    @Override
    public List<RevPluggableInlineView> createPluggableRevInlineView() {
        return new ArrayList<>(Arrays.asList(
                getRevStripProfileIconPluggableView()
        ));
    }

    public RevPluggableInlineView getRevStripProfileIconPluggableView() {
        ScrollView scrollView = new ScrollView(RevLibGenConstantine.REV_CONTEXT);
        scrollView.setScrollbarFadingEnabled(false);
        scrollView.setVerticalScrollBarEnabled(false);

        RevPluggableInlineView revPluggableInlineView = new RevPluggableInlineView();

        revPluggableInlineView.setInlineView(scrollView);
        revPluggableInlineView.setViewName("rev_settings_item_view_container_pluggable_scroll_view");

        return revPluggableInlineView;
    }
}
