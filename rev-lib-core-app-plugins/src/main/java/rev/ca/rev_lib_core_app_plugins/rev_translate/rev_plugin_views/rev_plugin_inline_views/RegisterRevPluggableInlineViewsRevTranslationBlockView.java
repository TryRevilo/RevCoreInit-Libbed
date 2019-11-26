package rev.ca.rev_lib_core_app_plugins.rev_translate.rev_plugin_views.rev_plugin_inline_views;

import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.IRevRegisterPluggableInlineView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableInlineView;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

public class RegisterRevPluggableInlineViewsRevTranslationBlockView implements IRevRegisterPluggableInlineView {
    @Override
    public List<RevPluggableInlineView> createPluggableRevInlineView() {
        LinearLayout revTranslationBlockContainer_LL = new RevCoreLayoutsLinearLayout(RevLibGenConstantine.REV_CONTEXT).getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        RevPluggableInlineView revPluggableInlineView = new RevPluggableInlineView();
        revPluggableInlineView.setInlineView(revTranslationBlockContainer_LL);
        revPluggableInlineView.setViewName("REV_TRANSLATION_BLOCK_CONTAINER_LL");
        List<RevPluggableInlineView> revPluggableInlineViews = new ArrayList<>();

        revPluggableInlineViews.add(revPluggableInlineView);

        return revPluggableInlineViews;
    }
}
