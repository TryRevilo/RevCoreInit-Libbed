package rev.ca.rev_lib_core_app_plugins.rev_memo_plugin.rev_plugin_views.rev_full_entity_view;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.ScrollView;

public class MaxHeightScrollView extends ScrollView {

    private Context mContext;
    private int maxHeight;

    public MaxHeightScrollView(Context context) {
        super(context);

        this.mContext = context;

        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);

        this.maxHeight = (int) ((dm.heightPixels) * .582);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}