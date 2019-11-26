package rev.ca.rev_lib_core_app_plugins.rev_cab_plugin.rev_plugin_views.rev_plugin_widget_views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;

/**
 * Created by rev on 10/21/17.
 */

public class RevFooterMenueTogglerTabs extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private Button button;

    public RevFooterMenueTogglerTabs(RevVarArgsData revVarArgsData) {
        super(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData));
        this.revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.mContext = this.revVarArgsData.getmContext();
    }

    @Override
    public ArrayList<View> createRevPluggableFooterMenueTogglerTab() {
        ArrayList<View> tabs = new ArrayList<>();

        tabs.add(this.getBanks());
        tabs.add(this.getHotels());
        tabs.add(this.getShoppingAreas());
        tabs.add(this.getPolice());

        return tabs;
    }

    private View getBanks() {
        button = new Button(mContext);
        button.setText(" Banks & ATMs");
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.font_size_small));
        button.setTextColor(mContext.getResources().getColor(R.color.darkText));

        // Use a GradientDrawable with only one color set, to make it a solid color
        GradientDrawable border = new GradientDrawable();
        border.setColor(ContextCompat.getColor(mContext, R.color.revColorAccentLight)); //white background
        border.setStroke(0, ContextCompat.getColor(mContext, R.color.darkText)); // black border with full opacity
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            button.setBackgroundDrawable(border);
        } else {
            button.setBackground(border);
        }

        int imageSize = mContext.getResources().getDimensionPixelSize(R.dimen.rev_core_views__image_size_mid);
        Drawable listedVehsBttnImg = mContext.getResources().getDrawable(R.drawable.ic_local_atm_black_48dp);
        listedVehsBttnImg.setBounds(0, 0, imageSize, imageSize);
        DrawableCompat.setTint(listedVehsBttnImg, ContextCompat.getColor(mContext, R.color.revGreen));
        button.setCompoundDrawables(null, listedVehsBttnImg, null, null);

        return button;
    }

    private View getHotels() {
        button = new Button(mContext);
        button.setText(" Accommodations");
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.font_size_small));
        button.setTextColor(mContext.getResources().getColor(R.color.revWhite));

        // Use a GradientDrawable with only one color set, to make it a solid color
        GradientDrawable border = new GradientDrawable();
        border.setColor(ContextCompat.getColor(mContext, R.color.actionBarDrawerToggleColor)); // Background
        border.setStroke(0, ContextCompat.getColor(mContext, R.color.darkText)); // Border with full opacity
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            button.setBackgroundDrawable(border);
        } else {
            button.setBackground(border);
        }

        int imageSize = mContext.getResources().getDimensionPixelSize(R.dimen.rev_core_views__image_size_mid);
        Drawable listedVehsBttnImg = mContext.getResources().getDrawable(R.drawable.ic_local_hotel_black_48dp);
        listedVehsBttnImg.setBounds(0, 0, imageSize, imageSize);
        DrawableCompat.setTint(listedVehsBttnImg, ContextCompat.getColor(mContext, R.color.revGreen));
        button.setCompoundDrawables(null, listedVehsBttnImg, null, null);

        return button;
    }

    private View getPolice() {
        button = new Button(mContext);
        button.setText(" Police");
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.font_size_small));
        button.setTextColor(mContext.getResources().getColor(R.color.revWhite));

        // Use a GradientDrawable with only one color set, to make it a solid color
        GradientDrawable border = new GradientDrawable();
        border.setColor(ContextCompat.getColor(mContext, R.color.darkText)); // Background
        border.setStroke(0, ContextCompat.getColor(mContext, R.color.darkText)); // Border with full opacity
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            button.setBackgroundDrawable(border);
        } else {
            button.setBackground(border);
        }

        int imageSize = mContext.getResources().getDimensionPixelSize(R.dimen.rev_core_views__image_size_mid);
        Drawable listedVehsBttnImg = mContext.getResources().getDrawable(R.drawable.ic_report_black_48dp);
        listedVehsBttnImg.setBounds(0, 0, imageSize, imageSize);
        DrawableCompat.setTint(listedVehsBttnImg, ContextCompat.getColor(mContext, R.color.rev_red));
        button.setCompoundDrawables(null, listedVehsBttnImg, null, null);

        return button;
    }

    private View getShoppingAreas() {
        button = new Button(mContext);
        button.setText(" Shopping");
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.font_size_small));
        button.setTextColor(mContext.getResources().getColor(R.color.darkText));

        // Use a GradientDrawable with only one color set, to make it a solid color
        GradientDrawable border = new GradientDrawable();
        border.setColor(ContextCompat.getColor(mContext, R.color.searchBorderColor)); // Background
        border.setStroke(0, ContextCompat.getColor(mContext, R.color.darkText)); // Border with full opacity
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            button.setBackgroundDrawable(border);
        } else {
            button.setBackground(border);
        }

        int imageSize = mContext.getResources().getDimensionPixelSize(R.dimen.rev_core_views__image_size_mid);
        Drawable listedVehsBttnImg = mContext.getResources().getDrawable(R.drawable.ic_shopping_cart_black_48dp);
        listedVehsBttnImg.setBounds(0, 0, imageSize, imageSize);
        DrawableCompat.setTint(listedVehsBttnImg, ContextCompat.getColor(mContext, R.color.revWhite));
        button.setCompoundDrawables(null, listedVehsBttnImg, null, null);

        return button;
    }
}
