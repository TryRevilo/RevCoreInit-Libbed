package rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Iterator;

import rev.ca.rev_lib_core_views.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_page_menue_togglers.RevDropDownPopupMenue;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevPluggableServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 11/5/17.
 */

public class RevPluggableOptionsContainerMenuLoader {

    public View getOptionsMenu(String getContainerMenuName, RevVarArgsData revVarArgsData) {
        Context mContext = revVarArgsData.getmContext();
        int revMarginSmall = RevLibGenConstantine.REV_MARGIN_SMALL;

        final LinearLayout revMenusContainer = new RevCoreLayoutsLinearLayout(mContext).getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        revMenusContainer.setPadding((int) (revMarginSmall * 1.3), revMarginSmall, revMarginSmall, revMarginSmall);
        revMenusContainer.setBackgroundColor(mContext.getResources().getColor(R.color.rcolorAccent_OPAQUE));

        for (final ICreateRevPluggableOptionsMenu createRevPluggableOptionsMenu : RevPluggableServices.getICreateRevPluggableOptionsMenuObjects("createRevPluggableOptionsMenus")) {

            final RevPluggableOptionsMenuVM revPluggableOptionsMenuVM = createRevPluggableOptionsMenu.revCreateRevPluggableOptionsMenue();

            String revPluggableOptionsContainerMenueName = revPluggableOptionsMenuVM.getRevPluggableOptionsMenueName();

            if (getContainerMenuName.equals(revPluggableOptionsContainerMenueName)) {
                final View revPluggableOptionsMenueView = revPluggableOptionsMenuVM.getRevPluggableOptionsMenueView();

                final Iterator iterator = RevConstantinePluggableViewsServices.REV_PLUGGABLE_MENU_ITEMS_MAP.keySet().iterator();

                while (iterator.hasNext()) {
                    Object revPluggableMenuItemName = iterator.next();

                    RevVarArgsData postRVD = new RevVarArgsData();
                    postRVD.setmContext(revVarArgsData.getmContext());
                    postRVD.setRevEntity(revVarArgsData.getRevEntity());
                    postRVD.setRevViewType(String.valueOf(revPluggableMenuItemName));

                    ICreateRevPluggableMenuItem iCreateRevPluggableMenuItem = (ICreateRevPluggableMenuItem) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGGABLE_MENU_ITEMS_MAP(postRVD);

                    if (iCreateRevPluggableMenuItem.REV_PLUGGABLE_MENU_CONTAINER_NAME() != null) {
                        for (String revMenuContainerName : iCreateRevPluggableMenuItem.REV_PLUGGABLE_MENU_CONTAINER_NAME()) {
                            if (getContainerMenuName.equals(revMenuContainerName)) {

                                if (iCreateRevPluggableMenuItem.create_REV_PLUGGABLE_MENU_ITEM_VM() == null)
                                    continue;

                                View menuItemView = iCreateRevPluggableMenuItem.create_REV_PLUGGABLE_MENU_ITEM_VM().getRevPluggableMenuView();

                                if (menuItemView.getLayoutParams() != null) {
                                    ((LinearLayout.LayoutParams) menuItemView.getLayoutParams()).width = ViewGroup.LayoutParams.MATCH_PARENT;
                                } else {
                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    menuItemView.setLayoutParams(layoutParams);
                                }

                                if (revPluggableOptionsMenuVM.getMenuItemsViewType().equals("drop_down")) {

                                    /** BORDERS **/

                                    int imageViewBorderSize = 1;

                                    GradientDrawable imageViewBorderTop = new GradientDrawable();
                                    imageViewBorderTop.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));
                                    imageViewBorderTop.setColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));
                                    imageViewBorderTop.setGradientType(RECTANGLE);

                                    GradientDrawable imageViewBorder = new GradientDrawable();
                                    imageViewBorder.setStroke(imageViewBorderSize, mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));
                                    imageViewBorder.setColor(ContextCompat.getColor(mContext, R.color.greyExtraLight));
                                    imageViewBorder.setGradientType(RECTANGLE);

                                    Drawable[] imageViewLayers = {imageViewBorderTop, imageViewBorder};
                                    LayerDrawable imageViewLayerDrawable = new LayerDrawable(imageViewLayers);
                                    imageViewLayerDrawable.setLayerInset(0, -imageViewBorderSize, imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize);
                                    imageViewLayerDrawable.setLayerInset(1, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize, imageViewBorderSize);

                                    /** END BORDERS **/

                                    menuItemView.setPadding(revMarginSmall, revMarginSmall, RevLibGenConstantine.REV_MARGIN_MEDIUM, revMarginSmall);

                                    revMenusContainer.addView(menuItemView);
                                    REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(menuItemView, imageViewLayerDrawable);

                                    revPluggableOptionsMenueView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            new RevDropDownPopupMenue().showStatusPopup((Activity) RevLibGenConstantine.REV_CONTEXT, revPluggableOptionsMenueView, revMenusContainer);
                                        }
                                    });
                                } else if (revPluggableOptionsMenuVM.getMenuItemsViewType().equals("linear_horizontal")) {
                                    ((LinearLayout) revPluggableOptionsMenuVM.getRevPluggableOptionsMenueView()).setOrientation(LinearLayout.HORIZONTAL);
                                    ((LinearLayout) revPluggableOptionsMenuVM.getRevPluggableOptionsMenueView()).addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(menuItemView));
                                }
                            }
                        }
                    }
                }

                return revPluggableOptionsMenueView;
            }
        }

        return null;

    }
}