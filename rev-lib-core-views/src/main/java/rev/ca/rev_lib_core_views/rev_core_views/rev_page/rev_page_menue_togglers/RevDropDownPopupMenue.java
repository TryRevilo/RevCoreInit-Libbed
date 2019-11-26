package rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_page_menue_togglers;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

/**
 * Created by rev on 1/10/18.
 */

public class RevDropDownPopupMenue {

    public void showStatusPopup(final Activity context, View v) {
        LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(context).getHorizontalRevLinearLayout_WRAPPED_ALL();

        TextView textView = new TextView(context);
        textView.setText("HELLO WORLD! The Time Is Now, Moloko.");

        linearLayout.addView(textView);

        // Creating the PopupWindow
        PopupWindow changeStatusPopUp = new PopupWindow(context);
        changeStatusPopUp.setContentView(linearLayout);
        changeStatusPopUp.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        changeStatusPopUp.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        changeStatusPopUp.setFocusable(true);

        // Some offset to align the popup a bit to the left, and a bit down, relative to button's position.
        int OFFSET_X = 0;
        int OFFSET_Y = v.getHeight();

        //Clear the default translucent background
        changeStatusPopUp.setBackgroundDrawable(new BitmapDrawable());


        int[] location = new int[2];
        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.
        v.getLocationOnScreen(location);

        //Initialize the Point with x, and y positions
        Point point = new Point();
        point.x = location[0];
        point.y = location[1];

        // Displaying the popup at the specified location, + offsets.
        changeStatusPopUp.showAtLocation(linearLayout, Gravity.NO_GRAVITY, point.x + OFFSET_X, point.y + OFFSET_Y);
    }

    public void showStatusPopup(final Activity context, View v, View linearLayout) {

        // Creating the PopupWindow
        PopupWindow changeStatusPopUp = new PopupWindow(context);
        changeStatusPopUp.setContentView(linearLayout);
        changeStatusPopUp.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        changeStatusPopUp.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        changeStatusPopUp.setFocusable(true);

        // Some offset to align the popup a bit to the left, and a bit down, relative to button's position.
        int OFFSET_X = 0;
        int OFFSET_Y = v.getHeight();

        //Clear the default translucent background
        changeStatusPopUp.setBackgroundDrawable(new BitmapDrawable());


        int[] location = new int[2];
        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.
        v.getLocationOnScreen(location);

        //Initialize the Point with x, and y positions
        Point point = new Point();
        point.x = location[0];
        point.y = location[1];

        // Displaying the popup at the specified location, + offsets.
        changeStatusPopUp.showAtLocation(linearLayout, Gravity.NO_GRAVITY, point.x + OFFSET_X, point.y + OFFSET_Y);
    }
}
