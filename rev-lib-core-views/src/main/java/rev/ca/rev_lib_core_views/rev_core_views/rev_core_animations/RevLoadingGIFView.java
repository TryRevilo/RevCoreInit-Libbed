package rev.ca.rev_lib_core_views.rev_core_views.rev_core_animations;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_inet.RevCheckRemoteServerConnAsyncTaskService;
import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.RevNetworkResolverViews;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_lib_core_views.REV_DEC_STRING_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

public class RevLoadingGIFView {

    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevLoadingGIFView(Context mContext) {
        this.mContext = mContext;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    public View getRevLoadingGIFView() {
        final LinearLayout linearLayoutContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.9f);
        textView.setText(REV_DEC_STRING_VIEWS_BASE_FUNCTIONS.makeRevDecString("LoADiNG DATA"));
        ((LinearLayout.LayoutParams) textView.getLayoutParams()).gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;

        GifImageView gifImageView = new GifImageView(mContext);
        gifImageView.setBackgroundResource(R.drawable.rev_gif_16_356_stars_sphere);

        LinearLayout.LayoutParams gifImageView_LP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        gifImageView_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);
        gifImageView_LP.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;
        gifImageView.setLayoutParams(gifImageView_LP);

        linearLayoutContainer_LL.addView(textView);
        linearLayoutContainer_LL.addView(gifImageView);

        new RevCheckRemoteServerConnAsyncTaskService(new RevCheckRemoteServerConnAsyncTaskService.IRevCheckConnAsync() {
            @Override
            public void processFinishIRevCheckConnAsync(Boolean output) {
                if (!output) {
                    REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_RESET_CONTENT_VIEW(linearLayoutContainer_LL, new RevNetworkResolverViews(mContext).getNoRevNetworkResolverView());
                }
            }
        }).execute();

        return linearLayoutContainer_LL;
    }
}
