package rev.ca.rev_lib_core_app_plugins.rev_pics_plugin.rev_plugin_views.rev_plugin_forms;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_lib_file_functions.IRevFileCrawler;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_lib_file_functions.RevPictureFileCrawler;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.RevDBModels.rev_entity_subtypes.RevObjectEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_menues.RevCoreColoredTabs;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 12/18/17.
 */

public class RevCreateNewObjectAlbumForm implements IRevInputFormView, IRevFileCrawler {

    private RevVarArgsData revVarArgsData;
    private Context mContext;


    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormEditText revCoreInputFormEditText;
    private RevCoreInputFormTextView revCoreInputFormTextView;
    private EditText picAlbumTtl_ET, picAlbumDesc_ET;

    private static ScrollView scrollView;

    private List<String> selectedItems = new ArrayList<>();
    LinearLayout selectedItems_LL;

    public RevCreateNewObjectAlbumForm(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormEditText = new RevCoreInputFormEditText(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        scrollView = new ScrollView(mContext);

        LinearLayout.LayoutParams scrollView_LP = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        scrollView.setLayoutParams(scrollView_LP);

        selectedItems_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
    }

    @Override
    public View createRevInputForm() {
        return this.revInputFieldsContainer();
    }

    @Override
    public RevEntity createRevInputFormData() {
        RevEntity revEntitySuper = new RevEntity();

        revEntitySuper.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
        revEntitySuper.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
        revEntitySuper.set_revEntitySubType("rev_pics_album");
        revEntitySuper.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        revEntitySuper.set_revEntityContainerGUID(revVarArgsData.getRevEntity().get_revEntityGUID());
        revEntitySuper.set_revEntityChildableStatus(3);

        RevObjectEntity revObjectEntity = new RevObjectEntity();
        revObjectEntity.set_revObjectName(String.valueOf(picAlbumTtl_ET.getText()));
        revObjectEntity.set_revObjectDescription(String.valueOf(picAlbumDesc_ET.getText()));

        List<RevEntityMetadata> revEntityMetadataList = new ArrayList<>();

        for (String imageURL : selectedItems) {
            revEntityMetadataList.add(new RevEntityMetadata("image_url", imageURL));
        }

        revEntitySuper.set_revEntityMetadataList(revEntityMetadataList);

        revEntitySuper.setRevObjectEntity(revObjectEntity);

        return revEntitySuper;
    }

    @Override
    public String revInputFormActionName() {
        return "RevPublishPicsAlbumAction";
    }

    @Override
    public RevVarArgsData REV_VAR_ARGS_DATA() {
        Map<Object, Object> revVarArgsDataMetadataStrings = new HashMap<>();
        revVarArgsDataMetadataStrings.put("submitFormTtlTxt", "Create New Photo Album");

        revVarArgsData.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);
        revVarArgsData.setOverrideFormFooter_VARAGS(true);

        return revVarArgsData;
    }

    private View revInputFieldsContainer() {
        LinearLayout revInputFieldsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_ALL();
        ((LinearLayout.LayoutParams) revInputFieldsContainer_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);

        picAlbumTtl_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        picAlbumTtl_ET.setHint("Title");

        picAlbumDesc_ET = revCoreInputFormEditText.getRevEditText_NO_BORDERS_NO_PADDING_TINY_SPACING();
        picAlbumDesc_ET.setHint("Description (optional)");

        revInputFieldsContainer_LL.addView(picAlbumTtl_ET);
        revInputFieldsContainer_LL.addView(picAlbumDesc_ET);

        revInputFieldsContainer_LL.addView(genPublisherWrapper_LL());
        revInputFieldsContainer_LL.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(scrollView));

        revInputFieldsContainer_LL.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(selectedItems_LL));

        revInputFieldsContainer_LL.addView(this.submitRevInputFormBtn());

        return revInputFieldsContainer_LL;
    }

    private View submitRevInputFormBtn() {
        TextView submitFormTab = new RevCoreColoredTabs(mContext).getRevColoredTab();
        submitFormTab.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));
        submitFormTab.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.deep_purple_dark));
        submitFormTab.setText("sAvE");

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);
        submitFormTab.setLayoutParams(layoutParams);

        LinearLayout.LayoutParams submitFormTab_LP = (LinearLayout.LayoutParams) submitFormTab.getLayoutParams();
        submitFormTab_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_SMALL * .75), RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        submitFormTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevConstantinePluggableViewsServices
                        .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevPublishPicsAlbumAction").initRevAction(createRevInputFormData());
            }
        });

        return submitFormTab;
    }

    public LinearLayout genPublisherWrapper_LL() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        /** TITLE BORDERS **/

        int imageViewBorderSize = 8;

        GradientDrawable imageViewBorder = new GradientDrawable();
        imageViewBorder.setStroke(imageViewBorderSize, mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));
        imageViewBorder.setColor(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.revWhite));
        imageViewBorder.setGradientType(RECTANGLE);

        Drawable[] imageViewLayers = {imageViewBorder};
        LayerDrawable imageViewLayerDrawable = new LayerDrawable(imageViewLayers);
        imageViewLayerDrawable.setLayerInset(0, -imageViewBorderSize, -imageViewBorderSize, -imageViewBorderSize, imageViewBorderSize);

        ImageView imageView = new ImageView(mContext);
        imageView.setPadding(0, 0, 0, 0);
        imageView.setImageResource(R.drawable.ic_publish_black_48dp);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setColorFilter(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(imageView, imageViewLayerDrawable);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL);
        layoutParams.gravity = (Gravity.BOTTOM);
        layoutParams.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);
        imageView.setLayoutParams(layoutParams);

        /** TEXT VIEW  **/

        int textViewBorderSize = 8;

        GradientDrawable textViewBorder = new GradientDrawable();
        textViewBorder.setStroke(textViewBorderSize, mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));
        textViewBorder.setColor(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.colorTransparent));
        textViewBorder.setGradientType(RECTANGLE);

        Drawable[] textViewLayers = {textViewBorder};
        LayerDrawable textViewLayerDrawable = new LayerDrawable(textViewLayers);
        textViewLayerDrawable.setLayerInset(0, -textViewBorderSize, -textViewBorderSize, -textViewBorderSize, textViewBorderSize);

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING_LINK(.9f);
        textView.setText("Select Pictures");
        textView.setTextColor(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_LARGE, RevLibGenConstantine.REV_MARGIN_TINY);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(textView, textViewLayerDrawable);

        LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.gravity = (Gravity.CENTER_VERTICAL);
        textView.setLayoutParams(textView_LP);

        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        final RevCreateNewObjectAlbumForm revPublisher = this;

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if (selectedItems != null) {
                    Iterator iterator = revVarArgsData.getRevVarArgsDataMetadataStrings().keySet().iterator();
                    while (iterator.hasNext()) {
                        Object key = iterator.next();

                        if (!selectedItems.contains(key))
                            iterator.remove();
                    }

                    for (String path : selectedItems)
                        revVarArgsData.getRevVarArgsDataMetadataStrings().put(path, "imagePath");
                }

                final RevPictureFileCrawler revPictureFileCrawler = new RevPictureFileCrawler(revVarArgsData, revPublisher);
                RevPop.initiatePopupWindow_MATCH_WIDTH(revPictureFileCrawler.createRevFileCrawler());
            }
        });

        return linearLayout;
    }

    @Override
    public void revCallBack(List<String> selectedItems) {
        this.selectedItems = selectedItems;

        REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_CLEAR_CONTENT_VIEW(selectedItems_LL);

        if (selectedItems.size() > 0)
            selectedItems_LL.addView(this.someProfileNetworkPips(selectedItems));
    }

    private View someProfileNetworkPips(List<String> selectedItems) {
        final LinearLayout someProfileNetworkPipsWrapper = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) someProfileNetworkPipsWrapper.getLayoutParams()).setMargins(
                0, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

        final HorizontalScrollView scrollView = new HorizontalScrollView(mContext);
        scrollView.setHorizontalScrollBarEnabled(false);

        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_SMALL * 1.4);

        for (int i = 0; i < selectedItems.size(); i++) {
            final String imgPath = selectedItems.get(i);

            if (new File(imgPath).exists()) {
                ImageView imageView = new ImageView(mContext);
                REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(imageView);

                Picasso.get()
                        .load(new File(imgPath))
                        .resize(RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM, RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM)
                        .centerCrop()
                        .into(imageView);

                linearLayout.addView(imageView);
            }
        }

        ImageView leftScroll_IV = new ImageView(mContext);
        leftScroll_IV.setColorFilter(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.teal_dark));
        leftScroll_IV.setPadding(0, 0, 0, 0);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(leftScroll_IV);

        Picasso.get()
                .load(rev.ca.rev_lib_core_views.R.drawable.ic_chevron_left_black_48dp)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(leftScroll_IV);

        ImageView rightScroll_IV = new ImageView(mContext);
        rightScroll_IV.setColorFilter(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.teal_dark));
        rightScroll_IV.setPadding(0, 0, 0, 0);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(rightScroll_IV);

        Picasso.get()
                .load(rev.ca.rev_lib_core_views.R.drawable.ic_chevron_right_black_48dp)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(rightScroll_IV);

        scrollView.addView(linearLayout);

        someProfileNetworkPipsWrapper.addView(leftScroll_IV);
        someProfileNetworkPipsWrapper.addView(scrollView);
        someProfileNetworkPipsWrapper.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
        someProfileNetworkPipsWrapper.addView(rightScroll_IV);

        ViewTreeObserver observer = someProfileNetworkPipsWrapper.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                LinearLayout.LayoutParams scrollView_LP = new LinearLayout.LayoutParams(
                        (int) (someProfileNetworkPipsWrapper.getWidth() - (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * 1.5)), ViewGroup.LayoutParams.WRAP_CONTENT);
                scrollView.setLayoutParams(scrollView_LP);
                someProfileNetworkPipsWrapper.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
            }
        });

        return someProfileNetworkPipsWrapper;
    }
}
