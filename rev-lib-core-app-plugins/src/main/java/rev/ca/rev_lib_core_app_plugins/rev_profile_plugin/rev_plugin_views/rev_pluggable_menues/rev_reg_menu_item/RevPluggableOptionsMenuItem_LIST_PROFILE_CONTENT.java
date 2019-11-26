package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_pluggable_menues.rev_reg_menu_item;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_pages.REV_RESET_PAGE_CONTENT;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.RevSubmitFormViewContainer;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableMenuItemVM;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsContainerMenuLoader;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLangStrings;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 1/20/18.
 */

public class RevPluggableOptionsMenuItem_LIST_PROFILE_CONTENT implements ICreateRevPluggableMenuItem {

    private RevVarArgsData revVarArgsData;
    private Context mContext;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    private String menuItemName = "rev_profile_content_floating_options_menu_item";

    public RevPluggableOptionsMenuItem_LIST_PROFILE_CONTENT(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = RevLibGenConstantine.REV_CONTEXT;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

    }

    @Override
    public String REV_PLUGGABLE_MENU_ITEM_VM_NAME() {
        return menuItemName;
    }

    @Override
    public List<String> REV_PLUGGABLE_MENU_CONTAINER_NAME() {
        return Arrays.asList("rev_profile_content_floating_options_wrapper_menu_area");
    }

    @Override
    public RevPluggableMenuItemVM create_REV_PLUGGABLE_MENU_ITEM_VM() {
        RevPluggableMenuItemVM revPluggableMenuItemVM = new RevPluggableMenuItemVM();
        revPluggableMenuItemVM.setRevPluggableMenuItemName(menuItemName);
        revPluggableMenuItemVM.setRevPluggableMenuName(Arrays.asList("rev_profile_content_floating_options_wrapper_menu_area"));
        revPluggableMenuItemVM.setRevPluggableMenuView(this.revGetTime_lineListingOptions());

        return revPluggableMenuItemVM;
    }

    public View revGetTime_lineListingOptions() {
        LinearLayout outerWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        outerWrapper_LL.setPadding(0, (int) (RevLibGenConstantine.REV_MARGIN_TINY * .5), 0, 0);
        outerWrapper_LL.setBackgroundColor(ContextCompat.getColor(mContext, R.color.rcolorAccent_OPAQUE));
        ((LinearLayout.LayoutParams) outerWrapper_LL.getLayoutParams()).setMargins(0, 0, 1, 0);

        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        linearLayout.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);
        this.setTabViewStyles(linearLayout);
        outerWrapper_LL.addView(linearLayout);

        final ImageView imageView = new ImageView(mContext);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        imageView.setLayoutParams(layoutParams);
        imageView.setColorFilter(ContextCompat.getColor(mContext, R.color.revWhite));

        Picasso.get()
                .load(R.drawable.baseline_keyboard_arrow_down_black_48dp)
                .resize(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL)
                .centerCrop()
                .into(imageView);

        linearLayout.addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout revOptionsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
                revOptionsContainer_LL.setPadding(RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_MEDIUM);

                revOptionsContainer_LL.addView(getRevTimelineListingOptionsHeader());
                // revOptionsContainer_LL.addView(revFindInProfile());

                new RevPop().initiatePopupWindow(revOptionsContainer_LL);
            }
        });

        return outerWrapper_LL;
    }

    private View revFindInProfile() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        final EditText editText = new RevCoreInputFormEditText(mContext).getRevEditText_ONLY_BottomBorders_Small_Pad_Left_Right();
        editText.setHint(" sEARcH . . .");
        editText.setLines(1);
        editText.setMaxLines(1);
        editText.setSingleLine(true);
        ((LinearLayout.LayoutParams) editText.getLayoutParams()).width = LinearLayout.LayoutParams.MATCH_PARENT;

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Drawable footerTab_DR = mContext.getResources().getDrawable(R.drawable.icons8_search_64);
        footerTab_DR.setBounds(0, 0, imgSize, imgSize);
        editText.setCompoundDrawables(null, null, footerTab_DR, null);

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_RIGHT = 2;

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getRawX() >= (editText.getRight() - editText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        //Here is your code when you click drawable right
                        InputMethodManager imm = (InputMethodManager) ((RevLibGenConstantine.REV_ACTIVITY).getSystemService(Context.INPUT_METHOD_SERVICE));
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                        Log.v(RevLangStrings.REV_TAG, ">>> CLICKED INSIDE ::: " + editText.getText().toString());

                        editText.setText("");

                        return true;
                    }
                }
                return false;
            }
        });

        linearLayout.addView(editText);

        return linearLayout;
    }

    private void setTabViewStyles(View view) {
        int borderSize = 8;

        GradientDrawable border = new GradientDrawable();
        border.setStroke(borderSize, mContext.getResources().getColor(R.color.teal_200_dark));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -borderSize, borderSize, -borderSize, -borderSize);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(view, layerDrawable);

        int imageViewBorderSize = 8;

        GradientDrawable imageViewBorderTop = new GradientDrawable();
        imageViewBorderTop.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.rev_red));
        imageViewBorderTop.setColor(ContextCompat.getColor(mContext, R.color.teal_300_dark));
        imageViewBorderTop.setGradientType(RECTANGLE);

        GradientDrawable imageViewBorder = new GradientDrawable();
        imageViewBorder.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.teal_dark));
        imageViewBorder.setColor(ContextCompat.getColor(mContext, R.color.teal_dark));
        imageViewBorder.setGradientType(RECTANGLE);

        Drawable[] imageViewLayers = {imageViewBorderTop, imageViewBorder};
        LayerDrawable imageViewLayerDrawable = new LayerDrawable(imageViewLayers);
        imageViewLayerDrawable.setLayerInset(0, -imageViewBorderSize, imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize);
        imageViewLayerDrawable.setLayerInset(1, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize, imageViewBorderSize);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(view, imageViewLayerDrawable);
    }

    private View getRevTimelineListingOptionsHeader() {
        LinearLayout revGetrevTimelineListingOptionsHeaderWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        revGetrevTimelineListingOptionsHeaderWrapper_LL.addView(this.revProfileSearchTab());
        revGetrevTimelineListingOptionsHeaderWrapper_LL.addView(this.revProfileSpacesTab());
        revGetrevTimelineListingOptionsHeaderWrapper_LL.addView(this.revProfileMailTab());
        revGetrevTimelineListingOptionsHeaderWrapper_LL.addView(this.revProfileLibsTab());
        revGetrevTimelineListingOptionsHeaderWrapper_LL.addView(this.revProfileAttachTab());
        revGetrevTimelineListingOptionsHeaderWrapper_LL.addView(this.revProfileSettingsTab());

        View revGetListingOptionsTabArea = new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_profile_floating_options_publisher_menu", revVarArgsData);
        revGetrevTimelineListingOptionsHeaderWrapper_LL.addView(revGetListingOptionsTabArea);

        final HorizontalScrollView scrollView = new HorizontalScrollView(mContext);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 7.5f));
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.addView(revGetrevTimelineListingOptionsHeaderWrapper_LL);

        LinearLayout linearLayout = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        linearLayout.addView(scrollView);

        View revGetListingSortTab = new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_profile_floating_options_menu", revVarArgsData);
        ((LinearLayout.LayoutParams) revGetListingSortTab.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);
        ((LinearLayout.LayoutParams) revGetListingSortTab.getLayoutParams()).width = 0;
        ((LinearLayout.LayoutParams) revGetListingSortTab.getLayoutParams()).weight = 1.5f;
        linearLayout.addView(revGetListingSortTab);

        return linearLayout;
    }

    private View revProfileSearchTab() {
        ImageView userIcon_IV = new ImageView(mContext);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        layoutParams.setMargins(0, 0, (int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.4), 0);
        userIcon_IV.setLayoutParams(layoutParams);

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Picasso.get()
                .load(R.drawable.icons8_search_64)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(userIcon_IV);

        userIcon_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevVarArgsData postRVD = new RevVarArgsData();
                postRVD.setmContext(revVarArgsData.getmContext());
                postRVD.setRevEntity(revVarArgsData.getRevEntity());

                postRVD.setRevViewType("RevCreateMemoInputForm");
                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(postRVD);
                iRevInputFormView.createRevInputForm();
                REV_RESET_PAGE_CONTENT.REV_RESET_PAGE_CONTENT(new RevSubmitFormViewContainer(mContext).getRevSubmitFormViewContainer(iRevInputFormView));

                RevPop.closerevPopUpWin(RevPop.REV_POPUP_WIN_LIST.size());
            }
        });

        return userIcon_IV;
    }

    private View revProfileSpacesTab() {
        ImageView userIcon_IV = new ImageView(mContext);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        layoutParams.setMargins(0, 0, (int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.4), 0);
        userIcon_IV.setLayoutParams(layoutParams);

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Picasso.get()
                .load(R.drawable.icons8_safety_collection_place_40)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(userIcon_IV);

        return userIcon_IV;
    }

    private View revProfileMailTab() {
        ImageView userIcon_IV = new ImageView(mContext);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        layoutParams.setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);
        userIcon_IV.setLayoutParams(layoutParams);

        int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * .7);

        Picasso.get()
                .load(R.drawable.icons8_email_64)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(userIcon_IV);

        return userIcon_IV;
    }

    private View revProfileLibsTab() {
        ImageView userIcon_IV = new ImageView(mContext);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        layoutParams.setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);
        userIcon_IV.setLayoutParams(layoutParams);

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM;

        Picasso.get()
                .load(R.drawable.icons8_view_100)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(userIcon_IV);

        return userIcon_IV;
    }

    private View revProfileAttachTab() {
        ImageView userIcon_IV = new ImageView(mContext);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        layoutParams.setMargins(0, 0, (int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.4), 0);
        userIcon_IV.setLayoutParams(layoutParams);

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Picasso.get()
                .load(R.drawable.icons8_genealogy_48)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(userIcon_IV);

        return userIcon_IV;
    }

    private View revProfileSettingsTab() {
        ImageView userIcon_IV = new ImageView(mContext);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        layoutParams.setMargins(0, 0, (int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.4), 0);
        userIcon_IV.setLayoutParams(layoutParams);

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Picasso.get()
                .load(R.drawable.icons8_settings_64)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(userIcon_IV);

        return userIcon_IV;
    }
}
