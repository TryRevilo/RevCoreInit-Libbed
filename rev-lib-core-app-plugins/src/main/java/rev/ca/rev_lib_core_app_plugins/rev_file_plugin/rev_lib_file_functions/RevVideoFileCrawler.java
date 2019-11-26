package rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_lib_file_functions;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_lib_core_app_plugins.rev_file_plugin.rev_plugin_views.rev_file_choosers.RevVideoFilesFileWalker;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibpersistence.R;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

public class RevVideoFileCrawler {

    private RevVarArgsData revVarArgsData;
    private Context mContext;
    private IRevFileCrawler iRevFileCrawler;

    RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    RevCoreInputFormTextView revCoreInputFormTextView;

    int position = 0;

    TextView totalImagesCount_TV;

    List<String> selectedFiles = new ArrayList<>();

    int selectedCount = 0;

    int smallMargin = RevLibGenConstantine.REV_MARGIN_SMALL;
    int imgSizeSmall = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;
    int imgSizeMedium = RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM;

    int marginTiny = RevLibGenConstantine.REV_MARGIN_TINY;

    public RevVideoFileCrawler(RevVarArgsData revVarArgsData, IRevFileCrawler iRevFileCrawler) {
        this.revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.mContext = this.revVarArgsData.getmContext();
        this.iRevFileCrawler = iRevFileCrawler;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        totalImagesCount_TV = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView_NO_PADDING();

    }

    public View createRevVideoFileCrawler() {
        final LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_ALL();

        linearLayout.addView(this.dataSourcesTabs());
        linearLayout.addView(this.imagesListing(RevVideoFilesFileWalker.getFiles()));

        return linearLayout;
    }

    View dataSourcesTabs() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LayoutParams) linearLayout.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM, marginTiny, 0, 0);
        linearLayout.setPadding(marginTiny, marginTiny, 0, marginTiny);

        linearLayout.addView(new RevVideoFileCrawler.SEP().getSep());
        linearLayout.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
        linearLayout.addView(this.okNextTab());

        return linearLayout;
    }

    class SEP {
        View getSep() {
            ImageView sep_IV = new ImageView(mContext);
            sep_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.gray_text));
            sep_IV.setPadding((int) (-RevLibGenConstantine.REV_MARGIN_TINY * 1.2), 0, (int) (-RevLibGenConstantine.REV_MARGIN_TINY * 1.2), 0);
            REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(sep_IV);

            Picasso.get()
                    .load(R.drawable.ic_remove_black_48dp)
                    .resize(imgSizeSmall, imgSizeSmall)
                    .centerCrop()
                    .into(sep_IV);

            return sep_IV;
        }
    }

    View okNextTab() {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(ContextCompat.getColor(mContext, R.color.deep_purple_primary_extra_light)); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius((float) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.4));
        gd.setStroke(1, ContextCompat.getColor(mContext, R.color.deep_purple_dark));

        Drawable[] layers = {gd};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, 1, 1, -2, 1);

        TextView dataSource_TV_Tab = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.8f);
        dataSource_TV_Tab.setGravity(Gravity.CENTER_VERTICAL);
        dataSource_TV_Tab.setPadding(smallMargin, 1, smallMargin, 1);
        dataSource_TV_Tab.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white_OPAQUE));
        dataSource_TV_Tab.setText("NEXT");

        dataSource_TV_Tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iRevFileCrawler.revCallBack(selectedFiles);
            }
        });

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(dataSource_TV_Tab, layerDrawable);

        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(dataSource_TV_Tab);

        imgSizeSmall = (int) (imgSizeSmall * 1.2);

        Drawable dataSource_TV_Tab_DR = mContext.getResources().getDrawable(R.drawable.ic_trending_flat_black_48dp);
        dataSource_TV_Tab_DR.setBounds(0, 0, imgSizeSmall, imgSizeSmall);
        DrawableCompat.setTint(dataSource_TV_Tab_DR, ContextCompat.getColor(mContext, R.color.deep_purple_dark));

        dataSource_TV_Tab.setCompoundDrawablePadding(0);
        dataSource_TV_Tab.setCompoundDrawables(null, null, dataSource_TV_Tab_DR, null);

        return dataSource_TV_Tab;
    }

    void selectedItems() {

        /* Count */

        String s_TotalCount = String.valueOf(selectedCount);

        SpannableString s_totalCount_Span = new SpannableString(s_TotalCount);
        s_totalCount_Span.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_TINY), 0, s_TotalCount.length(), SPAN_INCLUSIVE_INCLUSIVE);
        s_totalCount_Span.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, s_TotalCount.length(), 0); // set color
        s_totalCount_Span.setSpan(new StyleSpan(Typeface.BOLD), 0, s_totalCount_Span.length(), 0);

        /* txt */
        String s_membersTell = "pictures selected";

        if (selectedCount == 1)
            s_membersTell = "picture selected";

        SpannableString s_membersTell_Span = new SpannableString(s_membersTell);
        s_membersTell_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 0, s_membersTell.length(), SPAN_INCLUSIVE_INCLUSIVE);

        // let's put both spans together with a separator and all
        CharSequence finalText = TextUtils.concat(s_TotalCount, " ", s_membersTell_Span);

        if (selectedCount == 0) {
            finalText = "No pictures selected so far. Tap on a picture below to select it";

            SpannableString s_null_totalCount_Span = new SpannableString(finalText);
            s_null_totalCount_Span.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 0, finalText.length(), SPAN_INCLUSIVE_INCLUSIVE);
            s_null_totalCount_Span.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, finalText.length(), 0); // set color

            finalText = s_null_totalCount_Span;
        }

        totalImagesCount_TV.setText(finalText);

    }

    private View imagesListing(final List<String> imgFiles) {
        final LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_ALL();

        final ScrollView scrollView = new ScrollView(mContext);

        LayoutParams scrollView_LP = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        scrollView.setLayoutParams(scrollView_LP);

        LayoutParams totalImagesCount_TV_LP = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, RevLibGenConstantine.REV_MARGIN_MEDIUM);
        totalImagesCount_TV_LP.setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM, marginTiny, 0, marginTiny);
        totalImagesCount_TV.setLayoutParams(totalImagesCount_TV_LP);

        final LinearLayout selectPicsWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        int filesCount = imgFiles.size();

        totalImagesCount_TV.setText("VIDEOS : " + filesCount);

        linearLayout.addView(totalImagesCount_TV);

        final LinearLayout left_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
        final LinearLayout center_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
        final LinearLayout right_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
        final LinearLayout _4_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
        final LinearLayout _5_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();

        selectPicsWrapper_LL.addView(left_LL);
        selectPicsWrapper_LL.addView(center_LL);
        selectPicsWrapper_LL.addView(right_LL);
        selectPicsWrapper_LL.addView(_4_LL);
        selectPicsWrapper_LL.addView(_5_LL);

        ViewTreeObserver observer = selectPicsWrapper_LL.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                int width = (selectPicsWrapper_LL.getWidth() / 5);

                left_LL.getLayoutParams().width = width;
                center_LL.getLayoutParams().width = width;
                right_LL.getLayoutParams().width = width;
                _4_LL.getLayoutParams().width = width;
                _5_LL.getLayoutParams().width = width;

                selectPicsWrapper_LL.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
            }
        });

        int cycle = 0;

        for (int i = 0; i < filesCount; i++) {
            if (cycle < 4) {
                cycle++;
            } else cycle = 0;

            final FrameLayout frameLayout = new FrameLayout(mContext);
            LinearLayout.LayoutParams frameLayout_LP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            frameLayout.setLayoutParams(frameLayout_LP);

            final ImageView coolFilesImages_IV = new ImageView(mContext);
            coolFilesImages_IV.setPadding(1, 0, 0, 0);

            if (cycle == 4)
                coolFilesImages_IV.setPadding(1, 0, 1, 0);

            coolFilesImages_IV.setLayoutParams(new LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));

            final String imgPath = imgFiles.get(i);

            if (new File(imgPath).exists()) {

                Bitmap thumb = ThumbnailUtils.createVideoThumbnail(imgPath, MediaStore.Images.Thumbnails.FULL_SCREEN_KIND);
                final BitmapDrawable bitmapDrawable = new BitmapDrawable(thumb);

                coolFilesImages_IV.getViewTreeObserver()
                        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            // Wait until layout to call Picasso
                            @Override
                            public void onGlobalLayout() {
                                // Ensure we call this only once
                                coolFilesImages_IV.getViewTreeObserver()
                                        .removeOnGlobalLayoutListener(this);
                                coolFilesImages_IV.setBackgroundDrawable(bitmapDrawable);
                                coolFilesImages_IV.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            }
                        });

                frameLayout.addView(coolFilesImages_IV);

                final int finalI = i;

                if (revVarArgsData.getRevVarArgsDataMetadataStrings().containsKey(imgPath)) {
                    ImageView added_IV = new ImageView(mContext);
                    added_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.revBrightGreen));
                    added_IV.setId(finalI);

                    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    lp.gravity = Gravity.CENTER;

                    added_IV.setLayoutParams(lp);

                    Picasso.get()
                            .load(R.drawable.ic_done_black_48dp)
                            .resize(imgSizeMedium, imgSizeMedium)
                            .centerCrop()
                            .into(added_IV);

                    if (frameLayout.findViewById(finalI) == null) {
                        frameLayout.addView(added_IV);
                        selectedCount++;
                        selectedFiles.add(imgPath);
                    } else {
                        frameLayout.removeView(frameLayout.findViewById(finalI));
                        selectedCount--;
                        selectedFiles.remove(selectedFiles.indexOf(imgPath));
                    }

                    selectedItems();
                }
                coolFilesImages_IV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageView added_IV = new ImageView(mContext);
                        added_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.revBrightGreen));
                        added_IV.setId(finalI);

                        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                        lp.gravity = Gravity.CENTER;

                        added_IV.setLayoutParams(lp);

                        Picasso.get()
                                .load(R.drawable.ic_done_black_48dp)
                                .resize(imgSizeMedium, imgSizeMedium)
                                .centerCrop()
                                .into(added_IV);

                        if (frameLayout.findViewById(finalI) == null) {
                            frameLayout.addView(added_IV);
                            selectedCount++;
                            selectedFiles.add(imgPath);
                        } else {
                            frameLayout.removeView(frameLayout.findViewById(finalI));
                            selectedCount--;
                            selectedFiles.remove(selectedFiles.indexOf(imgPath));
                        }

                        selectedItems();
                    }
                });

                if (cycle == 0) {
                    left_LL.addView(frameLayout);
                } else if (cycle == 1) {
                    center_LL.addView(frameLayout);
                } else if (cycle == 2) {
                    right_LL.addView(frameLayout);
                } else if (cycle == 3) {
                    _4_LL.addView(frameLayout);
                } else {
                    _5_LL.addView(frameLayout);
                }
            }
        }

        scrollView.addView(selectPicsWrapper_LL);
        linearLayout.addView(scrollView);

        return linearLayout;
    }
}
