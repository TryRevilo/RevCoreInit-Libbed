package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_object.rev_user_full_profile_view_widgets.rev_profile_subtype_widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.RevSubmitFormViewContainer;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.rev_pluggable_menu_area_views.CreateRevMenuAreaViewContainerPublisher;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_core_views.rev_view_widgets.rev_core_section_widgets.RevRelatedPeopleIconsListing;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 10/24/17.
 */

public class RevUserFullProfileViewWidget_ACADEMIC {

    private RevVarArgsData revVarArgsData;
    private Context mContext;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevUserFullProfileViewWidget_ACADEMIC(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    public View getRevUserFullProfileViewWidget() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_ALL();

        linearLayout.addView(this.addNewAcademicProfile());
        linearLayout.addView(this.yearlyStudyView());
        linearLayout.addView(this.getBAGOptionsView());
        linearLayout.addView(this.yearlyStudyPics());
        linearLayout.addView(this.formerClassmatesView());
        linearLayout.addView(this.revCommentsView());

        ScrollView scrollView = new ScrollView(mContext);
        scrollView.addView(linearLayout);

        scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return scrollView;
    }

    private View addNewAcademicProfile() {
        LinearLayout.LayoutParams contentHeaderItemTab_TV_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentHeaderItemTab_TV_LP.gravity = Gravity.CENTER_VERTICAL;
        contentHeaderItemTab_TV_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_TINY * .1), 0, 0, 0);
        LinearLayout revTabHeaderViewItem_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) revTabHeaderViewItem_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM + RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        ImageView imageView = new ImageView(mContext);

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM;

        Picasso.get()
                .load(R.drawable.ic_school_black_48dp)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(imageView);
        imageView.setColorFilter(mContext.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

        revTabHeaderViewItem_LL.addView(imageView);

        TextView contentHeader_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING((float) .8);
        contentHeader_TV.setText("Add new academic profile");
        contentHeader_TV.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        contentHeader_TV.setLayoutParams(contentHeaderItemTab_TV_LP);

        revTabHeaderViewItem_LL.addView(contentHeader_TV);

        revTabHeaderViewItem_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revVarArgsData.setRevViewType("RevProfileTypeChooserInputForm_ACADEMIC");
                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revVarArgsData);
                iRevInputFormView.createRevInputForm();
                new RevPop().initiatePopupWindow(new RevSubmitFormViewContainer(mContext).getRevSubmitFormViewContainer(iRevInputFormView));
            }
        });

        return revTabHeaderViewItem_LL;
    }

    private View yearlyStudyView() {
        LinearLayout yearlyStudyContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();

        LinearLayout itemTtlTabWrapper = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        itemTtlTabWrapper.setPadding(RevLibGenConstantine.REV_MARGIN_MEDIUM + RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY, 0);
        itemTtlTabWrapper.setBackgroundColor(mContext.getResources().getColor(R.color.rev_yellow_primary));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;

        TextView studyYear_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView();
        studyYear_TV.setLayoutParams(layoutParams);
        studyYear_TV.setText("1996 to 2000");

        itemTtlTabWrapper.addView(studyYear_TV);

        View space = new View(mContext);

        // Width:0dp, Height:1 & Weight: 1.0
        LinearLayout.LayoutParams spaceLP = new LinearLayout.LayoutParams(0, 1, 1.0f);
        space.setLayoutParams(spaceLP);

        itemTtlTabWrapper.addView(space);

        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(layoutParams);

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Picasso.get()
                .load(R.drawable.ic_border_color_black_48dp)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(imageView);

        itemTtlTabWrapper.addView(imageView);

        yearlyStudyContainer_LL.addView(itemTtlTabWrapper);
        yearlyStudyContainer_LL.addView(this.revProfileCctItemsDescView());

        return yearlyStudyContainer_LL;
    }

    private View revProfileCctItemsDescView() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM + RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

        TextView school_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView();
        school_TV.setText("Naivasha Boys Boarding Primary School");

        TextView studyCertificate_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_ITALICS();
        studyCertificate_TV.setText("Kenya Certificate Of Primary Education");

        linearLayout.addView(school_TV);
        linearLayout.addView(studyCertificate_TV);

        return linearLayout;
    }

    private View getBAGOptionsView() {
        LinearLayout BAGOptionsWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        ((LinearLayout.LayoutParams) BAGOptionsWrapper_LL.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        ((LinearLayout.LayoutParams) BAGOptionsWrapper_LL.getLayoutParams()).setMargins(
                0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        BAGOptionsWrapper_LL.setPadding(
                RevLibGenConstantine.REV_MARGIN_MEDIUM + RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);

        BAGOptionsWrapper_LL.setBackgroundColor(mContext.getResources().getColor(R.color.teal_light));

        CreateRevMenuAreaViewContainerPublisher createRevMenuAreaViewContainerPublisher = new CreateRevMenuAreaViewContainerPublisher(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(new RevVarArgsData()));

        if (createRevMenuAreaViewContainerPublisher.newBagPublisherWrapper_LL() != null)
            BAGOptionsWrapper_LL.addView(createRevMenuAreaViewContainerPublisher.newBagPublisherWrapper_LL());

        TextView textView = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING();
        textView.setText("Create a new BAG for this class. You can then use it to keep in touch with your former classmates. They will be invited to join it immediately you create the BAG");
        textView.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.setMargins(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, (int) (RevLibGenConstantine.REV_MARGIN_TINY * .9));
        textView_LP.gravity = Gravity.BOTTOM;
        textView.setLayoutParams(textView_LP);

        BAGOptionsWrapper_LL.addView(textView);

        return BAGOptionsWrapper_LL;
    }

    @SuppressLint("WrongConstant")
    View yearlyStudyPics() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

        if (RevConstantinePluggableViewsServices.REV_PLUGGABLE_MENU_ITEMS_MAP.containsKey("create_new_bag_menu_item")) {
            RevVarArgsData passRVD = new RevVarArgsData();
            passRVD.setRevViewType("rev_direct_pic_select_menu_item");
            passRVD.setRevEntity(revVarArgsData.getRevEntity());

            ICreateRevPluggableMenuItem iCreateRevPluggableMenuItem = (ICreateRevPluggableMenuItem) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGGABLE_MENU_ITEMS_MAP(passRVD);
            View directSelectMediaView = iCreateRevPluggableMenuItem.create_REV_PLUGGABLE_MENU_ITEM_VM().getRevPluggableMenuView();

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);
            layoutParams.gravity = Gravity.CENTER_VERTICAL;
            directSelectMediaView.setLayoutParams(layoutParams);

            linearLayout.addView(directSelectMediaView);
        }

        GradientDrawable border = new GradientDrawable();
        border.setStroke(5, mContext.getResources().getColor(R.color.revExtraLightGreen_OPAQUE));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, 5, 5, 5, 5);

        for (int i = 0; i < 4; i++) {
            LinearLayout imgView_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
            String imgPath = "/storage/emulated/0/DCIM/Camera/IMG_20180116_172844.jpg";
            ImageView imageView = new ImageView(mContext);

            int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * 1.47);

            if (new File(imgPath).exists()) {
                Picasso.get()
                        .load(new File(imgPath))
                        .resize(imgSize, imgSize)
                        .centerCrop()
                        .into(imageView);

                LinearLayout.LayoutParams imageView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                imageView_LP.gravity = Gravity.CENTER_VERTICAL;
                imgView_LL.setLayoutParams(imageView_LP);

                REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(imgView_LL, layerDrawable);
                int padding = 15;
                imgView_LL.setPadding(padding, padding, padding, padding);

                imgView_LL.addView(imageView);
                linearLayout.addView(imgView_LL);
            }
        }

        ImageView imageView = new ImageView(mContext);

        int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * .8);

        Picasso.get()
                .load(R.drawable.ic_keyboard_arrow_right_black_48dp)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(imageView);

        LinearLayout.LayoutParams imageView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView_LP.gravity = Gravity.CENTER_VERTICAL;
        imageView_LP.setMargins(0, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);
        imageView.setLayoutParams(imageView_LP);

        View space = new View(mContext);

        // Width:0dp, Height:1 & Weight: 1.0
        LinearLayout.LayoutParams spaceLP = new LinearLayout.LayoutParams(0, 1, 1.0f);
        space.setLayoutParams(spaceLP);

        linearLayout.addView(space);
        linearLayout.addView(imageView);

        return linearLayout;
    }

    private View formerClassmatesView() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        LinearLayout formerClassmatesTitleWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) formerClassmatesTitleWrapper_LL.getLayoutParams()).setMargins(
                RevLibGenConstantine.REV_MARGIN_MEDIUM + RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        TextView formerClassmatesTitle_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView();
        formerClassmatesTitle_TV.setText("Classmates");

        TextView formerClassmatesTitleYear_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_ITALICS();
        formerClassmatesTitleYear_TV.setText(" 1996 to 2000");

        formerClassmatesTitleWrapper_LL.addView(formerClassmatesTitle_TV);
        formerClassmatesTitleWrapper_LL.addView(formerClassmatesTitleYear_TV);

        linearLayout.addView(formerClassmatesTitleWrapper_LL);

        LinearLayout formerClassmatesWrapper_LL = (LinearLayout) new RevRelatedPeopleIconsListing(mContext).relatedPeopleIconPics();
        ((LinearLayout.LayoutParams) formerClassmatesWrapper_LL.getLayoutParams()).setMargins(
                RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0, 0);
        linearLayout.addView(formerClassmatesWrapper_LL);

        return linearLayout;
    }

    private View revCommentsView() {
        LinearLayout revCommentsContainer = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_SMALL * .7);

        Drawable generalPointingAverageImg = mContext.getResources().getDrawable(R.drawable.ic_mode_comment_black_48dp);
        generalPointingAverageImg.setBounds(0, 0, imgSize, imgSize);
        DrawableCompat.setTint(generalPointingAverageImg, ContextCompat.getColor(mContext, R.color.revPurple));

        EditText commentInput_ET = new RevCoreInputFormEditText(mContext).getRevEditText_L_R_B_PartialBorders();
        commentInput_ET.setHint(" comment about this class");
        commentInput_ET.setCompoundDrawables(generalPointingAverageImg, null, null, null);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, RevLibGenConstantine.REV_MARGIN_SMALL);
        commentInput_ET.setLayoutParams(layoutParams);

        revCommentsContainer.addView(commentInput_ET);

        LinearLayout.LayoutParams imageView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView_LP.gravity = Gravity.CENTER_VERTICAL;

        for (int i = 0; i < 5; i++) {
            LinearLayout linearLayoutClassmateItemWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
            ((LinearLayout.LayoutParams) linearLayoutClassmateItemWrapper_LL.getLayoutParams()).setMargins(
                    RevLibGenConstantine.REV_MARGIN_MEDIUM + RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

            LinearLayout linearLayoutClassmateItemRight_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();
            ((LinearLayout.LayoutParams) linearLayoutClassmateItemRight_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);

            ImageView imageView = new ImageView(mContext);

            int userIconSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

            Picasso.get()
                    .load(R.drawable.ic_account_circle_black_48dp)
                    .resize(userIconSize, userIconSize)
                    .centerCrop()
                    .into(imageView);

            TextView fullNames = revCoreInputFormTextView.getRevExtraSmallBoldTextView();
            fullNames.setText("Oliver Muchai");
            TextView desc = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
            desc.setText("I have a LinearLayout inside a RelativeLayout, and I want to align it to right or left depending of a value");

            linearLayoutClassmateItemRight_LL.addView(fullNames);
            linearLayoutClassmateItemRight_LL.addView(desc);

            linearLayoutClassmateItemWrapper_LL.addView(imageView);
            linearLayoutClassmateItemWrapper_LL.addView(linearLayoutClassmateItemRight_LL);

            revCommentsContainer.addView(linearLayoutClassmateItemWrapper_LL);
        }

        return revCommentsContainer;
    }
}
