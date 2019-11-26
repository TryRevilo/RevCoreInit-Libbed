package rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_plugin_views.rev_file_choosers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_lib_file_functions.IRevFileCrawler;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_lib_file_functions.RevPictureFileCrawler;
import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_plugin_views.rev_plugin_views_overrides.RevGenericSelectedItemsView;
import rev.ca.rev_lib_core_views.REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_gen_functions.REV_STRINGS_BASE_FUNCTIONS;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

public class RevFileChooser {

    public interface IRevFileChooser {
        void revFileChooser(View revSelectItemsTab);

        void revSelectedItemsCallBack(List<String> selectedItems);
    }

    private RevVarArgsData revVarArgsData;
    private Context mContext;
    private IRevFileChooser iRevFileChooser;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    private List<String> selectedItems = new ArrayList<>();

    private LinearLayout selectedItems_LL;


    public RevFileChooser(RevVarArgsData revVarArgsData, List<String> selectedItems, LinearLayout selectedItems_LL, IRevFileChooser iRevFileChooser) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();
        this.selectedItems = selectedItems;
        this.iRevFileChooser = iRevFileChooser;

        this.revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        this.revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        this.selectedItems_LL = selectedItems_LL;

        iRevFileChooser.revFileChooser(this.revFileChooserTab());
    }

    public RevFileChooser(RevVarArgsData revVarArgsData, IRevFileChooser iRevFileChooser) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();
        this.iRevFileChooser = iRevFileChooser;

        this.revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        this.revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        this.selectedItems_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        linearLayout.addView(this.revFileChooserTab());
        linearLayout.addView(selectedItems_LL);

        iRevFileChooser.revFileChooser(linearLayout);
    }

    private View revFileChooserTab() {
        View includePic_TV = this.genPublisherWrapper_LL();

        includePic_TV.setOnClickListener(new View.OnClickListener() {
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

                View revPictureFileCrawler = new RevPictureFileCrawler(revVarArgsData, new IRevFileCrawler() {
                    @Override
                    public void revCallBack(List<String> selectedItems) {
                        RevFileChooser.this.selectedItems = selectedItems;
                        iRevFileChooser.revSelectedItemsCallBack(selectedItems);

                        REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_CLEAR_CONTENT_VIEW(selectedItems_LL);

                        if (selectedItems.size() > 0)
                            selectedItems_LL.addView(new RevGenericSelectedItemsView(revVarArgsData).revSelectedItemsView(selectedItems));
                    }
                }).createRevFileCrawler();

                RevPop.initiatePopupWindow_MATCH_WIDTH(revPictureFileCrawler);
            }
        });

        return includePic_TV;
    }

    private View genPublisherWrapper_LL() {
        View linearLayout = null;

        if (revVarArgsData.getRevPassViews().isEmpty()) {
            linearLayout = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

            int textViewBorderSize = (int) (RevLibGenConstantine.REV_MARGIN_TINY * .4);

            GradientDrawable textViewBorder = new GradientDrawable();
            textViewBorder.setStroke(textViewBorderSize, mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));
            textViewBorder.setColor(ContextCompat.getColor(mContext, rev.ca.rev_lib_core_views.R.color.colorTransparent));
            textViewBorder.setGradientType(RECTANGLE);

            Drawable[] textViewLayers = {textViewBorder};
            LayerDrawable textViewLayerDrawable = new LayerDrawable(textViewLayers);
            textViewLayerDrawable.setLayerInset(0, -textViewBorderSize, -textViewBorderSize, -textViewBorderSize, textViewBorderSize);

            REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(linearLayout, textViewLayerDrawable);

            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(R.drawable.ic_publish_black_48dp);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setColorFilter(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL);
            layoutParams.gravity = (Gravity.BOTTOM);
            layoutParams.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, (int) (-textViewBorderSize * .1));
            imageView.setLayoutParams(layoutParams);

            /** TEXT VIEW  **/

            TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING_LINK(.8f);

            if (revVarArgsData.getRevVarArgsDataMetadataStrings().containsKey("rev_file_selection_tab_text")) {
                textView.setText(String.valueOf(revVarArgsData.getRevVarArgsDataMetadataStrings().get("rev_file_selection_tab_text")));
            } else if (REV_STRINGS_BASE_FUNCTIONS.REV_IS_NULL_OR_EMPTY_STRING(textView.getText().toString()))
                textView.setText("Select pictures");

            textView.setTextColor(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_dark));
            textView.setGravity(Gravity.BOTTOM);
            textView.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_LARGE, textViewBorderSize);

            LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textView_LP.gravity = (Gravity.BOTTOM);
            textView.setLayoutParams(textView_LP);

            ((LinearLayout) linearLayout).addView(imageView);
            ((LinearLayout) linearLayout).addView(textView);
        } else if (revVarArgsData.getRevPassViews().get(0) != null) {
            linearLayout = revVarArgsData.getRevPassViews().get(0);
        } else return null;

        linearLayout.setClickable(true);

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
                }

                RevPictureFileCrawler revPictureFileCrawler = new RevPictureFileCrawler(revVarArgsData, new IRevFileCrawler() {
                    @Override
                    public void revCallBack(List<String> selectedItems) {
                        RevFileChooser.this.selectedItems = selectedItems;
                        REV_CORE_VIEWS_REFACTORING_BASE_FUNCTIONS.REV_CLEAR_CONTENT_VIEW(selectedItems_LL);

                        if (selectedItems.size() > 0)
                            selectedItems_LL.addView(new RevGenericSelectedItemsView(revVarArgsData).revSelectedItemsView(selectedItems));
                    }
                });

                RevPop.initiatePopupWindow_MATCH_WIDTH(revPictureFileCrawler.createRevFileCrawler());
            }
        });

        return linearLayout;
    }
}
