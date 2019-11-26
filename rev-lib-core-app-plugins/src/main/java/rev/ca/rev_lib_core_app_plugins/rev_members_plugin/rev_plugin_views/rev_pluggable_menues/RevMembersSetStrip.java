package rev.ca.rev_lib_core_app_plugins.rev_members_plugin.rev_plugin_views.rev_pluggable_menues;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import androidx.core.content.ContextCompat;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_server_client.RevJSONEntityClassConstructor;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_inet.RevCheckRemoteServerConnAsyncTaskService;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_read_from_server.IRevAsyncJSONResponse;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_read_from_server.RevAsyncGetJSONResponseTaskService;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.rev_members_plugin.rev_plugin_views.rev_pages.RevAllMembersListing;
import rev.ca.rev_lib_core_views.AbstractIRevPluggableViews;
import rev.ca.rev_lib_core_views.rev_core_views.rev_core_animations.RevLoadingGIFView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.RevNetworkResolverViews;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_inline_views.RevPluggableViewImpl;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsFrameLayouts;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 10/28/17.
 */

public class RevMembersSetStrip extends AbstractIRevPluggableViews {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    RevMembersSetStrip revMembersSetStrip;

    RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    RevCoreInputFormTextView revCoreInputFormTextView;

    List<RevEntity> revEntityList;

    public RevMembersSetStrip(RevVarArgsData revVarArgsData) {
        super(revVarArgsData);

        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revMembersSetStrip = this;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    @Override
    public RevVarArgsData createRevVarArgsData() {
        RevVarArgsData argsData = new RevVarArgsData(mContext);

        Map<Object, Object> revVarArgsDataMetadataStrings = new HashMap<>();
        revVarArgsDataMetadataStrings.put("isChildable", new Boolean(false));

        argsData.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);

        return argsData;
    }

    @Override
    public ArrayMap<View, View> createRevMerryllStripMenuViewItem() {
        ArrayMap<View, View> arrayMap = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            arrayMap = new ArrayMap<>();

            arrayMap.put(getRevStripTab_IV(), new View(mContext));
        }
        return arrayMap;
    }

    public ImageView getRevStripTab_IV() {
        int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * 0.7);
        LinearLayout.LayoutParams stripTabIcon_IV_LP = new LinearLayout.LayoutParams(imageSize, imageSize);
        stripTabIcon_IV_LP.setMargins(0, RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0);
        stripTabIcon_IV_LP.gravity = Gravity.CENTER_HORIZONTAL;

        final ImageView stripTabIcon_IV = new ImageView(mContext);
        stripTabIcon_IV.setBackgroundColor(ContextCompat.getColor(mContext, R.color.rev_default_background));
        stripTabIcon_IV.setImageResource(R.drawable.icons8_standing_man_64);
        stripTabIcon_IV.setPadding(0, 0, 0, 0);

        stripTabIcon_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_dark));

        stripTabIcon_IV.setLayoutParams(stripTabIcon_IV_LP);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(10f); // Set corner radius
        gradientDrawable.setColor(ContextCompat.getColor(mContext, R.color.revColorAccentLight));

        stripTabIcon_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("REV_USER_PROFILE_VIEW_CONTAINER_LL", new RevLoadingGIFView(mContext).getRevLoadingGIFView());

                stripTabIcon_IV.setClickable(false);
                stripTabIcon_IV.setColorFilter(Color.argb(0, 255, 0, 0));

                final LinearLayout usersContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
                ((LinearLayout.LayoutParams) usersContainer_LL.getLayoutParams()).setMargins(0, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

                FrameLayout headWrapper_FL = new RevCoreLayoutsFrameLayouts(mContext).getRevCoreLayoutsFrameLayout_MATCH_W_WRAP_H_FL_LP();
                headWrapper_FL.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                ((LinearLayout.LayoutParams) headWrapper_FL.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_TINY * 2.7), 0, 0, 0);

                headWrapper_FL.addView(revTimelineListingOptions());
                usersContainer_LL.addView(headWrapper_FL);

                new RevCheckRemoteServerConnAsyncTaskService(new RevCheckRemoteServerConnAsyncTaskService.IRevCheckConnAsync() {
                    @Override
                    public void processFinishIRevCheckConnAsync(Boolean output) {
                        if (output && !stripTabIcon_IV.isClickable()) {
                            String apiURL = REV_SESSION_SETTINGS.getRevRemoteServer() + "/api/rev_entity/get_all_rev_entity_type?rev_entity_type=rev_user_entity, rev_object";
                            new RevAsyncGetJSONResponseTaskService(mContext, apiURL, new IRevAsyncJSONResponse() {
                                @Override
                                public void processFinishAsyncJSONResponse(JSONObject jsonObject) {
                                    if (jsonObject == null || jsonObject.length() == 0) {
                                        usersContainer_LL.addView(new RevNetworkResolverViews(mContext).getNoRevNullEntityNetworkResolverView());
                                    } else {
                                        revEntityList = new ArrayList<>();

                                        JSONArray jArr = null;
                                        try {
                                            jArr = jsonObject.getJSONArray("filter");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        for (int i = 0; i < jArr.length(); i++) {
                                            JSONObject e;
                                            try {
                                                e = jArr.getJSONObject(i);
                                                RevEntity revEntity = new RevJSONEntityClassConstructor().getClassRevEntity_From_JSON(e);

                                                if (revEntity == null || !revEntity.get_revEntityType().equals("rev_user_entity"))
                                                    continue;

                                                revEntityList.add(revEntity);

                                            } catch (JSONException e1) {
                                                e1.printStackTrace();
                                            }
                                        }

                                        usersContainer_LL.addView(new RevAllMembersListing(revVarArgsData).revObjectListing_LV(revEntityList));

                                        stripTabIcon_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_dark));
                                        stripTabIcon_IV.setClickable(true);
                                    }

                                    RevPluggableViewImpl.REV_RESET_REV_PLUGGABLE_INLINE_VIEW("REV_USER_PROFILE_VIEW_CONTAINER_LL", usersContainer_LL);
                                }
                            }).execute();
                        } else {
                            TextView textView = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.8f);
                            textView.setText("No Data to display!  CampAnn server is unreachable. Check your internet connection then try again");
                            ((LinearLayout.LayoutParams) textView.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0, 0);

                            usersContainer_LL.addView(textView);

                            stripTabIcon_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_dark));
                            stripTabIcon_IV.setClickable(true);
                        }
                    }
                }).execute();
            }
        });

        return stripTabIcon_IV;
    }

    private View revTimelineListingOptions() {
        LinearLayout linearLayout = new RevCoreLayoutsLinearLayout(mContext).getHorizontalRevLinearLayout_WRAPPED_ALL();
        linearLayout.setPadding(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);
        linearLayout.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));

        FrameLayout.LayoutParams linearLayout_LP = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout_LP.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
        linearLayout_LP.setMargins(0, -8, (int) (RevLibGenConstantine.REV_MARGIN_MEDIUM * .85), 0);
        linearLayout.setLayoutParams(linearLayout_LP);

        int borderSize = 8;

        GradientDrawable border = new GradientDrawable();
        border.setStroke(borderSize, mContext.getResources().getColor(R.color.teal_200_dark));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -borderSize, borderSize, -borderSize, -borderSize);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(linearLayout, layerDrawable);

        ImageView imageView = new ImageView(mContext);

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

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(imageView, imageViewLayerDrawable);

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

        return linearLayout;
    }
}
