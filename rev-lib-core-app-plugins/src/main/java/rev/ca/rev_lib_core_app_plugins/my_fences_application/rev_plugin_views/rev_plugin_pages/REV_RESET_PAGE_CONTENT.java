package rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages;

import android.view.View;
import android.view.ViewGroup;

import rev.ca.rev_lib_core_views.REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;

public class REV_RESET_PAGE_CONTENT {

    public static void REV_RESET_PAGE_CONTENT(View replacementView) {
        REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_RESET_CONTENT_VIEW(
                ((ViewGroup) RevPluggableViewImpl.REV_GET_REV_PLUGGABLE_INLINE_VIEW("REV_USER_PROFILE_VIEW_CONTAINER_LL")), replacementView);
    }

    public static void RESET_PAGE_OWNER(View revPageOwnerContent) {
        REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_RESET_CONTENT_VIEW(
                (ViewGroup) RevConstantinePluggableViewsServices.ALL_SIMPLE_PLUGGABLE_REV_INLINE_VIEWS.get("PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER"), revPageOwnerContent);
    }
}
