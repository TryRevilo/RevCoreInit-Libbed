package rev.ca.rev_lib_core_app_plugins.rev_cab_plugin.rev_plugin_views.rev_plugin_widget_views;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;

/**
 * Created by rev on 11/5/17.
 */

public class RevNotifications extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    public RevNotifications(RevVarArgsData revVarArgsData) {
        super(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData));
        this.revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.mContext = this.revVarArgsData.getmContext();
    }

    @Override
    public ArrayList<View> createRevNotificationView() {
        ArrayList<View> notificationViews = new ArrayList<>();

        TextView textView = new RevCoreInputFormTextView(mContext).getRevSmallNormalTextView();
        textView.setPadding(RevLibGenConstantine.REV_DIMENS.getRevMarginMedium(), 0, 0, 0);
        textView.setText("Hello World");

        notificationViews.add(textView);

        return notificationViews;
    }
}
