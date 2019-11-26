package rev.ca.rev_lib_core_app_plugins.rev_contacts_plugin.rev_plugin_views.rev_pluggable_menus;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_gen_functions.RevDimens;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 10/28/17.
 */

public class RevSetStripRevContacts extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevDimens revDimens;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;
    private LinearLayout.LayoutParams insetMarginFirst_LP;

    public RevSetStripRevContacts(RevVarArgsData revVarArgsData) {
        super(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData));
        this.revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.mContext = this.revVarArgsData.getmContext();

        revDimens = new RevDimens(mContext);

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        insetMarginFirst_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        insetMarginFirst_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_MEDIUM * 1.1), 0, 0, 0);
    }

    public ArrayMap<View, View> createRevMerryllStripMenuViewItem() {
        ArrayMap<View, View> arrayMap = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            arrayMap = new ArrayMap<>();

            LinearLayout contactsViewContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_ALL();
            ((LinearLayout.LayoutParams) contactsViewContainer_LL.getLayoutParams()).setMargins(
                    0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

            contactsViewContainer_LL.addView(this.findPeopleWidgetView());

            ScrollView scrollView = new ScrollView(mContext);
            scrollView.setLayoutParams(
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

            int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

            GradientDrawable border = new GradientDrawable();
            border.setStroke(1, mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));
            border.setGradientType(RECTANGLE);

            Drawable[] layers = {border};
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            layerDrawable.setLayerInset(0, -2, -2, -2, 1);

            // Width:0dp, Height:1 & Weight: 1.0
            LinearLayout.LayoutParams spaceLP = new LinearLayout.LayoutParams(0, 1, 1.0f);

            Map<String, String> stringLongMap = new HashMap<>(); // new RevListContacts(revVarArgsData).getContactList();
            Iterator iterator = stringLongMap.keySet().iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object value = stringLongMap.get(key);

                // Contact wrapper
                LinearLayout contactItemWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
                ((LinearLayout.LayoutParams) contactItemWrapper_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

                contactItemWrapper_LL.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, RevLibGenConstantine.REV_MARGIN_TINY);
                contactItemWrapper_LL.setBackground(layerDrawable);

                ImageView imageView = new ImageView(mContext);
                Picasso.get()
                        .load(R.drawable.ic_account_circle_black_48dp)
                        .resize(imgSize, imgSize)
                        .centerCrop()
                        .into(imageView);
                contactItemWrapper_LL.addView(imageView);

                TextView contactName_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView();
                contactName_TV.setText(String.valueOf(key));

                TextView contactNumber_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING((float) .8);
                contactNumber_TV.setText(String.valueOf(value));

                LinearLayout contactsDetailsContainer = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
                contactsDetailsContainer.addView(contactName_TV);
                contactsDetailsContainer.addView(contactNumber_TV);

                contactItemWrapper_LL.addView(contactsDetailsContainer);

                linearLayout.addView(contactItemWrapper_LL);

                View space = new View(mContext);
                space.setLayoutParams(spaceLP);

                contactItemWrapper_LL.addView(space);

                LinearLayout.LayoutParams options_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                options_LP.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, 0);

                ImageView imageViewCall = new ImageView(mContext);
                imageViewCall.setLayoutParams(options_LP);
                Picasso.get()
                        .load(R.drawable.ic_call_black_48dp)
                        .resize(imgSize, imgSize)
                        .centerCrop()
                        .into(imageViewCall);

                ImageView imageViewMsg = new ImageView(mContext);
                imageViewMsg.setLayoutParams(options_LP);
                Picasso.get()
                        .load(R.drawable.ic_message_black_48dp)
                        .resize(imgSize, imgSize)
                        .centerCrop()
                        .into(imageViewMsg);

                contactItemWrapper_LL.addView(imageViewCall);
                contactItemWrapper_LL.addView(imageViewMsg);
            }

            scrollView.addView(linearLayout);
            contactsViewContainer_LL.addView(scrollView);
            arrayMap.put(getRevStripTab_IV(), contactsViewContainer_LL);
        }
        return arrayMap;
    }

    private View findPeopleWidgetView() {
        int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_SMALL * .7);

        Drawable generalPointingAverageImg = mContext.getResources().getDrawable(R.drawable.ic_find_in_page_black_48dp);
        generalPointingAverageImg.setBounds(0, 0, imgSize, imgSize);
        DrawableCompat.setTint(generalPointingAverageImg, ContextCompat.getColor(mContext, R.color.revPurple));

        EditText commentInput_ET = new RevCoreInputFormEditText(mContext).getRevEditText_L_R_B_PartialBorders();
        commentInput_ET.setHint(" Search contacts");
        commentInput_ET.setBackgroundColor(Color.TRANSPARENT);
        commentInput_ET.setCompoundDrawables(generalPointingAverageImg, null, null, null);
        ((LinearLayout.LayoutParams) commentInput_ET.getLayoutParams()).setMargins(
                (int) (RevLibGenConstantine.REV_MARGIN_TINY * 2.7), RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        return commentInput_ET;
    }

    public ImageView getRevStripTab_IV() {
        int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * 0.7);
        LinearLayout.LayoutParams stripTabIcon_IV_LP = new LinearLayout.LayoutParams(
                imageSize, imageSize);
        stripTabIcon_IV_LP.setMargins(0, revDimens.getRevMarginMedium(), 0, 0);
        stripTabIcon_IV_LP.gravity = Gravity.CENTER_HORIZONTAL;

        ImageView stripTabIcon_IV = new ImageView(mContext);
        stripTabIcon_IV.setBackgroundColor(ContextCompat.getColor(mContext, R.color.rev_default_background));
        stripTabIcon_IV.setImageResource(R.drawable.ic_contacts_black_48dp);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            stripTabIcon_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_dark));
        }
        stripTabIcon_IV.setLayoutParams(stripTabIcon_IV_LP);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(10f); // Set corner radius
        gradientDrawable.setColor(ContextCompat.getColor(mContext, R.color.revColorAccentLight));

        return stripTabIcon_IV;
    }
}
