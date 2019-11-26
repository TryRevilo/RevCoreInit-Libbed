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
import java.util.Arrays;
import java.util.List;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_plugin_views_overrides.RevCustomListingViewRecommendedBagsListingsView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.RevSubmitFormViewContainer;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.rev_pluggable_menu_area_views.CreateRevMenuAreaViewContainerPublisher;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_core_views.rev_view_widgets.rev_core_section_widgets.RevRelatedPeopleIconsListing;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Created by rev on 2/18/18.
 */

public class RevUserFullProfileViewWidget_WORK {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    private LinearLayout.LayoutParams centerVertical_LP;
    private LinearLayout.LayoutParams insetMarginFirst_LP;

    public RevUserFullProfileViewWidget_WORK(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        centerVertical_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        centerVertical_LP.gravity = Gravity.CENTER_VERTICAL;

        insetMarginFirst_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        insetMarginFirst_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_MEDIUM * 1.1), 0, 0, 0);
    }

    public View getRevUserFullProfileViewWidget() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        LinearLayout addSocialsWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) addSocialsWrapper_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM + RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);
        addSocialsWrapper_LL.addView(this.addNewCircleProfile());

        View space = new View(mContext);

        // Width:0dp, Height:1 & Weight: 1.0
        LinearLayout.LayoutParams spaceLP = new LinearLayout.LayoutParams(0, 1, 1.0f);
        space.setLayoutParams(spaceLP);

        addSocialsWrapper_LL.addView(space);

        addSocialsWrapper_LL.addView(this.addNewContacts());

        linearLayout.addView(addSocialsWrapper_LL);
        linearLayout.addView(this.friendsWidget());
        linearLayout.addView(this.peopleYouMayKnowWidget());
        linearLayout.addView(this.getBAGOptionsView());
        linearLayout.addView(this.suggestedBags());

        linearLayout.addView(this.yearlyStudyPics());

        ScrollView scrollView = new ScrollView(mContext);
        scrollView.addView(linearLayout);

        scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return scrollView;
    }

    private View suggestedBags() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Drawable joinImg_DR = mContext.getResources().getDrawable(R.drawable.ic_filter_list_black_48dp);
        joinImg_DR.setBounds(0, 0, imgSize, imgSize);
        DrawableCompat.setTint(joinImg_DR, ContextCompat.getColor(mContext, R.color.gray_text));

        TextView textView = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING();
        textView.setText("Recommended Bags");
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setCompoundDrawablePadding(RevLibGenConstantine.REV_MARGIN_TINY);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins((int) (RevLibGenConstantine.REV_MARGIN_LARGE * .75), 0, 0, 0);
        textView.setLayoutParams(layoutParams);

        textView.setCompoundDrawables(joinImg_DR, null, null, null);

        linearLayout.addView(textView);

        List<RevEntity> revEntityList = Arrays.asList(new RevPersLibRead().revPersGetALLRevEntityTYPE("rev_group_entity"));

        for (RevEntity revEntity : revEntityList) {
            RevVarArgsData reccmndRevVarags = new RevVarArgsData(mContext);
            reccmndRevVarags.setRevEntity(revEntity);
            linearLayout.addView(new RevCustomListingViewRecommendedBagsListingsView(reccmndRevVarags).getRevCustomListingViewRecommendedBagsListingsView());
        }

        return linearLayout;
    }

    private View addNewCircleProfile() {
        LinearLayout.LayoutParams contentHeaderItemTab_TV_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentHeaderItemTab_TV_LP.gravity = Gravity.CENTER_VERTICAL;
        contentHeaderItemTab_TV_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_TINY * .1), 0, 0, 0);
        LinearLayout revTabHeaderViewItem_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        ImageView imageView = new ImageView(mContext);

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM;

        Picasso.get()
                .load(R.drawable.icons8_global_citizen_80)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(imageView);

        revTabHeaderViewItem_LL.addView(imageView);

        TextView contentHeader_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING((float) .8);
        contentHeader_TV.setText("Add places you've worked");
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

    private View addNewContacts() {
        LinearLayout.LayoutParams contentHeaderItemTab_TV_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentHeaderItemTab_TV_LP.gravity = Gravity.CENTER_VERTICAL;
        contentHeaderItemTab_TV_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_TINY * .1), 0, 0, 0);
        LinearLayout revTabHeaderViewItem_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) revTabHeaderViewItem_LL.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        ImageView imageView = new ImageView(mContext);

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Picasso.get()
                .load(R.drawable.baseline_playlist_add_black_48dp)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(imageView);

        revTabHeaderViewItem_LL.addView(imageView);

        TextView contentHeader_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING((float) .8);
        contentHeader_TV.setText("cREATE NEw spAcE");
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

    @SuppressLint("WrongConstant")
    private View peopleYouMayKnowWidget() {
        LinearLayout peopleYouMayKnowWidgetContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        LinearLayout peopleYouMayKnowTtlWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        peopleYouMayKnowTtlWrapper_LL.setPadding(0, 0, 0, (int) (RevLibGenConstantine.REV_MARGIN_TINY * .1));
        ((LinearLayout.LayoutParams) peopleYouMayKnowTtlWrapper_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0, 0);

        TextView peopleYouMayKnowTtl_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView();
        peopleYouMayKnowTtl_TV.setText("People you may know");
        peopleYouMayKnowTtl_TV.setLayoutParams(centerVertical_LP);

        TextView peopleYouMayKnowCount = revCoreInputFormTextView.getRevExtraSmallNormalTextView_ITALICS();
        peopleYouMayKnowCount.setText(" 155");
        peopleYouMayKnowTtl_TV.setLayoutParams(centerVertical_LP);

        peopleYouMayKnowTtlWrapper_LL.addView(peopleYouMayKnowTtl_TV);
        peopleYouMayKnowTtlWrapper_LL.addView(peopleYouMayKnowCount);

        peopleYouMayKnowWidgetContainer_LL.addView(peopleYouMayKnowTtlWrapper_LL);

        LinearLayout formerClassmatesWrapper_LL = (LinearLayout) new RevRelatedPeopleIconsListing(mContext).relatedPeopleIconPics();
        ((LinearLayout.LayoutParams) formerClassmatesWrapper_LL.getLayoutParams()).setMargins(
                (int) (RevLibGenConstantine.REV_MARGIN_MEDIUM * .9), 0, 0, 0);
        peopleYouMayKnowWidgetContainer_LL.addView(formerClassmatesWrapper_LL);

        peopleYouMayKnowWidgetContainer_LL.addView(this.findPeopleWidgetView());

        return peopleYouMayKnowWidgetContainer_LL;
    }

    private View findPeopleWidgetView() {
        int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_SMALL * .7);

        Drawable generalPointingAverageImg = mContext.getResources().getDrawable(R.drawable.ic_find_in_page_black_48dp);
        generalPointingAverageImg.setBounds(0, 0, imgSize, imgSize);
        DrawableCompat.setTint(generalPointingAverageImg, ContextCompat.getColor(mContext, R.color.revPurple));

        EditText commentInput_ET = new RevCoreInputFormEditText(mContext).getRevEditText_L_R_B_PartialBorders();
        commentInput_ET.setHint(" Find people on BAGplaces");
        commentInput_ET.setCompoundDrawables(generalPointingAverageImg, null, null, null);

        commentInput_ET.setLayoutParams(insetMarginFirst_LP);
        ((LinearLayout.LayoutParams) commentInput_ET.getLayoutParams()).setMargins(
                0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        return commentInput_ET;
    }

    @SuppressLint("WrongConstant")
    private View friendsWidget() {
        LinearLayout friendsWidgetContainer_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) friendsWidgetContainer_LL.getLayoutParams()).setMargins(
                0, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        LinearLayout peopleYouMayKnowTtlWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) peopleYouMayKnowTtlWrapper_LL.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_MEDIUM, 0, 0, 0);
        ((LinearLayout.LayoutParams) peopleYouMayKnowTtlWrapper_LL.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        TextView peopleYouMayKnowCount = revCoreInputFormTextView.getRevExtraSmallNormalTextView_ITALICS();
        peopleYouMayKnowCount.setText("+155 ");

        TextView peopleYouMayKnowTtl_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView();
        peopleYouMayKnowTtl_TV.setText("Colleagues");

        peopleYouMayKnowTtlWrapper_LL.addView(peopleYouMayKnowCount);
        peopleYouMayKnowTtlWrapper_LL.addView(peopleYouMayKnowTtl_TV);

        friendsWidgetContainer_LL.addView(peopleYouMayKnowTtlWrapper_LL);

        ImageView imageViewPointer = new ImageView(mContext);

        int imgSize_imageViewPointer = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        Picasso.get()
                .load(R.drawable.ic_keyboard_arrow_right_black_48dp)
                .resize(imgSize_imageViewPointer, imgSize_imageViewPointer)
                .centerCrop()
                .into(imageViewPointer);
        imageViewPointer.setColorFilter(mContext.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

        LinearLayout imgSize_imageViewPointer_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) imgSize_imageViewPointer_LL.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        imgSize_imageViewPointer_LL.addView(imageViewPointer);

        friendsWidgetContainer_LL.addView(imgSize_imageViewPointer_LL);

        LinearLayout formerClassmatesWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) formerClassmatesWrapper_LL.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

        for (int i = 0; i < 5; i++) {
            LinearLayout imgView_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
            String imgPath = "/storage/emulated/0/DCIM/Camera/IMG_20180116_172844.jpg";
            ImageView imageView = new ImageView(mContext);

            int imgSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * .95);

            if (new File(imgPath).exists()) {
                Picasso.get()
                        .load(new File(imgPath))
                        .resize(imgSize, imgSize)
                        .centerCrop()
                        .into(imageView);

                LinearLayout.LayoutParams imageView_LP = (LinearLayout.LayoutParams) imgView_LL.getLayoutParams();
                imageView_LP.setMargins(2, 0, 0, 0);
                imageView_LP.gravity = Gravity.CENTER_VERTICAL;
                imgView_LL.setLayoutParams(imageView_LP);

                imgView_LL.addView(imageView);
                formerClassmatesWrapper_LL.addView(imgView_LL);
            }
        }

        friendsWidgetContainer_LL.addView(formerClassmatesWrapper_LL);

        ImageView imageView = new ImageView(mContext);

        int imgSizeAll = RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM;

        Picasso.get()
                .load(R.drawable.ic_keyboard_arrow_right_black_48dp)
                .resize(imgSizeAll, imgSizeAll)
                .centerCrop()
                .into(imageView);

        LinearLayout.LayoutParams imageView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView_LP.gravity = Gravity.CENTER_VERTICAL;
        imageView.setLayoutParams(imageView_LP);

        View space = new View(mContext);

        // Width:0dp, Height:1 & Weight: 1.0
        LinearLayout.LayoutParams spaceLP = new LinearLayout.LayoutParams(0, 1, 1.0f);
        space.setLayoutParams(spaceLP);

        friendsWidgetContainer_LL.addView(space);

        friendsWidgetContainer_LL.addView(imageView);

        return friendsWidgetContainer_LL;
    }

    private View getBAGOptionsView() {
        LinearLayout BAGOptionsWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        ((LinearLayout.LayoutParams) BAGOptionsWrapper_LL.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        ((LinearLayout.LayoutParams) BAGOptionsWrapper_LL.getLayoutParams()).setMargins(
                0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        BAGOptionsWrapper_LL.setPadding(
                RevLibGenConstantine.REV_MARGIN_MEDIUM + RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);

        BAGOptionsWrapper_LL.setBackgroundColor(mContext.getResources().getColor(R.color.teal_light));

        CreateRevMenuAreaViewContainerPublisher createRevMenuAreaViewContainerPublisher = new CreateRevMenuAreaViewContainerPublisher(RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(new RevVarArgsData()));

        if (createRevMenuAreaViewContainerPublisher.newBagPublisherWrapper_LL() != null) {
            View view = createRevMenuAreaViewContainerPublisher.newBagPublisherWrapper_LL();

            LinearLayout.LayoutParams view_LP = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM, RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM);
            view_LP.gravity = Gravity.CENTER_VERTICAL;
            view.setLayoutParams(view_LP);

            BAGOptionsWrapper_LL.addView(view);
        }

        TextView textView = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING();
        textView.setText("Create new Spaces then invite your past, or present colleagues to join them. You can then use them to keep in touch and share content like pictures, videos & important dates or events. They will be invited to join them immediately you create the BAGS");
        textView.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams textView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.setMargins(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);
        textView_LP.gravity = Gravity.CENTER_VERTICAL;
        textView.setLayoutParams(textView_LP);

        BAGOptionsWrapper_LL.addView(textView);

        return BAGOptionsWrapper_LL;
    }

    @SuppressLint("WrongConstant")
    View yearlyStudyPics() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_MEDIUM * 1.1), 0, 0, 0);

        if (RevConstantinePluggableViewsServices.REV_PLUGGABLE_MENU_ITEMS_MAP.containsKey("create_new_bag_menu_item")) {
            RevVarArgsData passRVD = new RevVarArgsData();
            passRVD.setRevViewType("rev_direct_pic_select_menu_item");
            passRVD.setRevEntity(passRVD.getRevEntity());

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
}
