package rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rev.ca.rev_lib_core_views.REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevConstantineViews;

public class RevPluggableViewImpl {

    static Context mContext;
    static RevCoreInputFormTextView revCoreInputFormTextView;

    public static void initRevPluggableInlineViewsMap(List<Class> classList) {

        revThisInit();

        Map<String, List<RevPluggableInlineView>> stringListMap = new HashMap<>();

        for (Class aClass : classList) {
            Constructor constructor;
            try {
                constructor = aClass.getConstructor();
                IRevRegisterPluggableInlineView iRevRegisterPluggableInlineView = (IRevRegisterPluggableInlineView) constructor.newInstance();

                List<RevPluggableInlineView> revPluggableInlineViews = iRevRegisterPluggableInlineView.createPluggableRevInlineView();

                for (RevPluggableInlineView revPluggableInlineView : revPluggableInlineViews) {
                    if (revPluggableInlineView == null) continue;

                    String inlineViewName = revPluggableInlineView.getViewName();
                    String targetContainerViewName = revPluggableInlineView.getTargetContainerViewName();

                    if (!RevConstantinePluggableViewsServices.ALL_PLUGGABLE_REV_INLINE_VIEWS.containsKey(inlineViewName))
                        RevConstantinePluggableViewsServices.ALL_PLUGGABLE_REV_INLINE_VIEWS.put(revPluggableInlineView.getViewName(), revPluggableInlineView);

                    if (stringListMap.containsKey(targetContainerViewName)) {
                        stringListMap.get(targetContainerViewName).add(revPluggableInlineView);
                    } else {
                        stringListMap.put(targetContainerViewName, new ArrayList<RevPluggableInlineView>());
                        stringListMap.get(targetContainerViewName).add(revPluggableInlineView);
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        sortItems(stringListMap);

        RevConstantinePluggableViewsServices.PLUGGABLE_REV_INLINE_VIEWS.putAll(stringListMap);

        sortInlineViewBefore(RevConstantinePluggableViewsServices.ALL_PLUGGABLE_REV_INLINE_VIEWS);
        sortInlineViewAfter(RevConstantinePluggableViewsServices.ALL_PLUGGABLE_REV_INLINE_VIEWS);

        revLoadMainCoreViews();
    }

    private static void revLoadMainCoreViews() {
        RevConstantinePluggableViewsServices.ALL_SIMPLE_PLUGGABLE_REV_INLINE_VIEWS.put("REV_BASE_CONTENT_CONTAINER_FL", RevConstantineViews.REV_BASE_CONTENT_CONTAINER_FL);
        RevConstantinePluggableViewsServices.ALL_SIMPLE_PLUGGABLE_REV_INLINE_VIEWS.put("REV_TOP_BAR_FL", RevConstantineViews.REV_TOP_BAR_FL);
        RevConstantinePluggableViewsServices.ALL_SIMPLE_PLUGGABLE_REV_INLINE_VIEWS.put("PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL", RevConstantineViews.PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL);
        RevConstantinePluggableViewsServices.ALL_SIMPLE_PLUGGABLE_REV_INLINE_VIEWS.put("PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_TOP", RevConstantineViews.PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_TOP);
        RevConstantinePluggableViewsServices.ALL_SIMPLE_PLUGGABLE_REV_INLINE_VIEWS.put("PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_TOP_BAR_LEFT", RevConstantineViews.PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_TOP_BAR_LEFT);
        RevConstantinePluggableViewsServices.ALL_SIMPLE_PLUGGABLE_REV_INLINE_VIEWS.put("PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER", RevConstantineViews.PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_CENTER);
        RevConstantinePluggableViewsServices.ALL_SIMPLE_PLUGGABLE_REV_INLINE_VIEWS.put("PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_BOTTOM", RevConstantineViews.PLUGGABLE_REV_MAIN_CENTER_CCT_VIEW_LL_BOTTOM);
    }

    private static void revThisInit() {
        mContext = RevLibGenConstantine.REV_CONTEXT;
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    private static void sortInlineViewBefore(Map<String, RevPluggableInlineView> stringRevPluggableInlineViewMap) {
        Map<String, List<RevPluggableInlineView>> sortedRevPluggableInlineViews = new HashMap<>();

        Iterator iterator = stringRevPluggableInlineViewMap.keySet().iterator();
        while (iterator.hasNext()) {
            String viewName = (String) iterator.next();
            RevPluggableInlineView revPluggableInlineView = stringRevPluggableInlineViewMap.get(viewName);

            String viewBefore = revPluggableInlineView.getViewBefore();

            if (viewBefore != null && !viewBefore.isEmpty()) {
                if (sortedRevPluggableInlineViews.containsKey(viewBefore)) {
                    sortedRevPluggableInlineViews.get(viewBefore).add(revPluggableInlineView);
                } else {
                    sortedRevPluggableInlineViews.put(viewBefore, new ArrayList<RevPluggableInlineView>());
                    sortedRevPluggableInlineViews.get(viewBefore).add(revPluggableInlineView);
                }
            }
        }

        RevConstantinePluggableViewsServices.PLUGGABLE_REV_INLINE_VIEWS_BEFORE.putAll(sortedRevPluggableInlineViews);
    }

    private static void sortInlineViewAfter(Map<String, RevPluggableInlineView> stringRevPluggableInlineViewMap) {
        Map<String, List<RevPluggableInlineView>> sortedRevPluggableInlineViews = new HashMap<>();

        Iterator iterator = stringRevPluggableInlineViewMap.keySet().iterator();
        while (iterator.hasNext()) {
            String viewName = (String) iterator.next();
            RevPluggableInlineView revPluggableInlineView = stringRevPluggableInlineViewMap.get(viewName);

            String viewBefore = revPluggableInlineView.getViewBefore();

            if (viewBefore != null && !viewBefore.isEmpty()) {
                if (sortedRevPluggableInlineViews.containsKey(viewBefore)) {
                    sortedRevPluggableInlineViews.get(viewBefore).add(revPluggableInlineView);
                } else {
                    sortedRevPluggableInlineViews.put(viewBefore, new ArrayList<RevPluggableInlineView>());
                    sortedRevPluggableInlineViews.get(viewBefore).add(revPluggableInlineView);
                }
            }
        }

        RevConstantinePluggableViewsServices.PLUGGABLE_REV_INLINE_VIEWS_AFTER.putAll(sortedRevPluggableInlineViews);
    }

    private static void sortItems(Map<String, List<RevPluggableInlineView>> stringListMap) {
        Iterator iterator = stringListMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            List<RevPluggableInlineView> revPluggableInlineViews = stringListMap.get(key);

            Collections.sort(revPluggableInlineViews, new Comparator<RevPluggableInlineView>() {
                @Override
                public int compare(RevPluggableInlineView lhs, RevPluggableInlineView rhs) {
                    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                    return lhs.getViewPriority() > rhs.getViewPriority() ? -1 : (lhs.getViewPriority() < rhs.getViewPriority()) ? 1 : 0;
                }
            });

        }
    }

    public static List<View> getRevPluggableInlineViews(String targetContainerViewName) {
        List<View> views = new ArrayList<>();

        for (RevPluggableInlineView revPluggableInlineView : RevConstantinePluggableViewsServices.PLUGGABLE_REV_INLINE_VIEWS.get(targetContainerViewName)) {
            views.add(revPluggableInlineView.getInlineView());
        }
        return views;
    }

    public static View REV_GET_REV_PLUGGABLE_INLINE_VIEW(String viewName) {
        if (RevConstantinePluggableViewsServices.ALL_PLUGGABLE_REV_INLINE_VIEWS.get(viewName) != null) {
            return RevConstantinePluggableViewsServices.ALL_PLUGGABLE_REV_INLINE_VIEWS.get(viewName).getInlineView();
        } else {
            TextView nullViewReturn = revCoreInputFormTextView.getRevExtraSmallNormalTextView();
            nullViewReturn.setText("ERROR ~ return rev_null_view");

            return nullViewReturn;
        }
    }

    public static View REV_GET_REV_PLUGGABLE_INLINE_VIEW_NO_PARENT(String viewName) {
        if (RevConstantinePluggableViewsServices.ALL_PLUGGABLE_REV_INLINE_VIEWS.get(viewName) != null) {
            REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_REMOVE_PARENTVIEW(RevConstantinePluggableViewsServices.ALL_PLUGGABLE_REV_INLINE_VIEWS.get(viewName).getInlineView());
        }
        return REV_GET_REV_PLUGGABLE_INLINE_VIEW(viewName);
    }

    public static void REV_RESET_REV_PLUGGABLE_INLINE_VIEW(String viewName, View replacementView) {
        REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_RESET_CONTENT_VIEW(((ViewGroup) REV_GET_REV_PLUGGABLE_INLINE_VIEW(viewName)), replacementView);
    }

    public static View GET_REV_RESET_REV_PLUGGABLE_INLINE_VIEW(String viewName, View replacementView) {
        REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_RESET_CONTENT_VIEW((ViewGroup) REV_GET_REV_PLUGGABLE_INLINE_VIEW(viewName), replacementView);
        return REV_GET_REV_PLUGGABLE_INLINE_VIEW(viewName);
    }
}
