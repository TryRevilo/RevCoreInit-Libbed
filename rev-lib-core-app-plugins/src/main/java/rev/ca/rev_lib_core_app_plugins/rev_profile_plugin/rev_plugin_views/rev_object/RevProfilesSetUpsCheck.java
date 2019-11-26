package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_object;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.core.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

public class RevProfilesSetUpsCheck {

    private RevVarArgsData revVarArgsData;
    private Context mContext;

    RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    RevCoreInputFormTextView revCoreInputFormTextView;

    int paddingTabsH = RevLibGenConstantine.REV_MARGIN_SMALL;
    int paddingTabsV = 1;

    LinearLayout revItemsContainer_LL;

    RevPersLibRead revPersLibRead;

    public RevProfilesSetUpsCheck(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        revItemsContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        revPersLibRead = new RevPersLibRead();
    }

    public View getRevProfilesSetUpsCheckView() {
        if (this.revTabs() != null) {
            LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
            ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

            linearLayout.addView(revTabs());
            linearLayout.addView(revItemsContainer_LL);

            return linearLayout;
        }

        return null;
    }

    private View revTabs() {
        if (revPersLibRead.revEntitySubtypeExists_BY_OWNER_GUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid(), "rev_entity_info") == -1 ||
                revPersLibRead.revEntitySubtypeExists_BY_OWNER_GUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid(), "rev_entity_social_info") == -1) {

            LinearLayout revTabsWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
            ((LinearLayout.LayoutParams) revTabsWrapper_LL.getLayoutParams()).setMargins(
                    (int) (RevLibGenConstantine.REV_MARGIN_SMALL * .5), RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

            if (revPersLibRead.revEntitySubtypeExists_BY_OWNER_GUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid(), "rev_entity_info") < 0) {
                revTabsWrapper_LL.addView(this.revInfoTab());
                revTabsWrapper_LL.addView(this.revTabsDivider());
            }

            if (revPersLibRead.revEntitySubtypeExists_BY_OWNER_GUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid(), "rev_entity_social_info") < 0) {
                revTabsWrapper_LL.addView(this.revSocialInfoTab());
                revTabsWrapper_LL.addView(this.revTabsDivider());
            }

            revTabsWrapper_LL.addView(this.revFamilyInfoTab());
            revTabsWrapper_LL.addView(this.revTabsDivider());

            revTabsWrapper_LL.addView(this.revAcademicInfoTab());
            revTabsWrapper_LL.addView(this.revTabsDivider());

            revTabsWrapper_LL.addView(this.revWorkInfoTab());
            revTabsWrapper_LL.addView(this.revTabsDivider());

            return revTabsWrapper_LL;
        }

        return null;
    }

    private View revInfoTab() {

        revItemsContainer_LL.removeAllViews();

        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        textView.setText("Info");
        textView.setTextColor(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.revWhite));
        textView.setBackgroundColor(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_100_dark));
        textView.setPadding(paddingTabsH, paddingTabsV, paddingTabsH, paddingTabsV);
        textView.setGravity(Gravity.CENTER_VERTICAL);

        textView.setCompoundDrawablePadding((int) (RevLibGenConstantine.REV_MARGIN_TINY * .5));

        final String s = "Please set up your info details below. This is required";

        revItemsContainer_LL.addView(revInitDescView(R.drawable.ic_person_pin_black_48dp, s));
        revItemsContainer_LL.addView(revInfoTabInit().createRevInputForm());

        textView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                revItemsContainer_LL.removeAllViews();
                revItemsContainer_LL.addView(revInitDescView(R.drawable.ic_person_pin_black_48dp, s));
                revItemsContainer_LL.addView(revInfoTabInit().createRevInputForm());
            }
        });

        return textView;
    }

    private IRevInputFormView revInfoTabInit() {

        RevVarArgsData passRVD = new RevVarArgsData(mContext);
        passRVD.setRevOwnerEntityGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        passRVD.setRevContainerEntityGUID(revVarArgsData.getRevContainerEntityGUID());
        passRVD.setRevViewType("RevCreateRevEditInfoFormObject");

        return (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(passRVD);
    }

    private View revSocialInfoTab() {
        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        textView.setText("sociAL");
        textView.setTextColor(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.revWhite));
        textView.setBackgroundColor(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_200_dark));
        textView.setPadding(paddingTabsH, paddingTabsV, paddingTabsH, paddingTabsV);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setCompoundDrawablePadding((int) (RevLibGenConstantine.REV_MARGIN_TINY * .5));

        final String s = "You need to have at least 30 contacts attached to your account. These can be added by importing them from your email or phone contacts book";

        if (revPersLibRead.revEntitySubtypeExists_BY_OWNER_GUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid(), "rev_entity_info") > 0) {

            revItemsContainer_LL.removeAllViews();

            revItemsContainer_LL.addView(revInitDescView(R.drawable.baseline_nature_people_black_48dp, s));
            revItemsContainer_LL.addView(revSocialInfoTabInit().createRevInputForm());
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                revItemsContainer_LL.removeAllViews();

                revItemsContainer_LL.addView(revInitDescView(R.drawable.baseline_nature_people_black_48dp, s));
                revItemsContainer_LL.addView(revSocialInfoTabInit().createRevInputForm());
            }
        });

        return textView;
    }

    private IRevInputFormView revSocialInfoTabInit() {
        RevVarArgsData passRVD = new RevVarArgsData();
        passRVD.setRevOwnerEntityGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        passRVD.setRevContainerEntityGUID(revVarArgsData.getRevContainerEntityGUID());
        passRVD.setRevViewType("RevCreateInputFormRevProfileSocialCircle");

        return (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(passRVD);
    }

    private View revFamilyInfoTab() {
        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        textView.setText("FAmiLy");
        textView.setTextColor(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.revWhite));
        textView.setBackgroundColor(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_300_dark));
        textView.setPadding(paddingTabsH, paddingTabsV, paddingTabsH, paddingTabsV);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setCompoundDrawablePadding((int) (RevLibGenConstantine.REV_MARGIN_TINY * .5));

        textView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                revItemsContainer_LL.removeAllViews();

                String s = "Attach family members below. This is optional";
                revItemsContainer_LL.addView(revInitDescView(R.drawable.baseline_device_hub_black_48dp, s));

                RevVarArgsData passRVD = new RevVarArgsData();
                passRVD.setRevOwnerEntityGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());

                passRVD.setRevViewType("RevCreateInputFormRevProfileParents");
                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(passRVD);

                revItemsContainer_LL.addView(iRevInputFormView.createRevInputForm());
            }
        });

        return textView;
    }

    private View revAcademicInfoTab() {
        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        textView.setText("AcADEmic");
        textView.setTextColor(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.revWhite));
        textView.setBackgroundColor(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_400_dark));
        textView.setPadding(paddingTabsH, paddingTabsV, paddingTabsH, paddingTabsV);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setCompoundDrawablePadding((int) (RevLibGenConstantine.REV_MARGIN_TINY * .5));

        textView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                revItemsContainer_LL.removeAllViews();

                String s = "Add places you have gone to study. It doesn't have to be a formal school. Informal training camps can also be added";
                revItemsContainer_LL.addView(revInitDescView(R.drawable.ic_school_black_48dp, s));

                RevVarArgsData passRVD = new RevVarArgsData();
                passRVD.setRevOwnerEntityGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());

                Map<Object, Object> revVarArgsDataMetadataStrings = new HashMap<>();

                revVarArgsDataMetadataStrings.put("type_of_academic_profile", "class_academic_profile");
                passRVD.setRevVarArgsDataMetadataStrings(revVarArgsDataMetadataStrings);
                passRVD.setRevViewType("RevCreateNewClassProfileInputForm");

                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(passRVD);

                revItemsContainer_LL.addView(iRevInputFormView.createRevInputForm());
            }
        });

        return textView;
    }

    private View revWorkInfoTab() {
        TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        textView.setText("woRk");
        textView.setTextColor(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.revWhite));
        textView.setBackgroundColor(mContext.getResources().getColor(rev.ca.rev_lib_core_views.R.color.teal_500_dark));
        textView.setPadding(paddingTabsH, paddingTabsV, paddingTabsH, paddingTabsV);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setCompoundDrawablePadding((int) (RevLibGenConstantine.REV_MARGIN_TINY * .5));

        textView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                revItemsContainer_LL.removeAllViews();

                String s = "Add places you have worked at. Independent projects can also be added. This includes anything you have done for someone else that engaged your effort / skills. You don not have to have received pay for it";
                revItemsContainer_LL.addView(revInitDescView(R.drawable.baseline_work_outline_black_48dp, s));

                RevVarArgsData passRVD = new RevVarArgsData();
                passRVD.setRevOwnerEntityGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());

                passRVD.setRevViewType("RevProfileTypeChooserInputForm_ACADEMIC");
                IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(passRVD);

                revItemsContainer_LL.addView(iRevInputFormView.createRevInputForm());
            }
        });

        return textView;
    }

    private View revTabsDivider() {
        LinearLayout.LayoutParams stripTabIcon_IV_LP = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_MARGIN_TINY, 2);
        stripTabIcon_IV_LP.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;

        ImageView stripTabIcon_IV = new ImageView(mContext);
        stripTabIcon_IV.setImageResource(R.drawable.icons8_line_48);
        stripTabIcon_IV.setBackgroundColor(ContextCompat.getColor(mContext, R.color.teal_dark));
        stripTabIcon_IV.setColorFilter(ContextCompat.getColor(mContext, R.color.revWhite));
        stripTabIcon_IV.setLayoutParams(stripTabIcon_IV_LP);

        return stripTabIcon_IV;
    }

    private View revInitDescView(int resId, String s) {
        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        LinearLayout revTabHeaderViewItem_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        revTabHeaderViewItem_LL.setPadding((int) (RevLibGenConstantine.REV_MARGIN_SMALL * .7), RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        ImageView revInitDescView_IV = new ImageView(mContext);

        LinearLayout.LayoutParams imageView_LP = new LinearLayout.LayoutParams(imgSize, imgSize);
        imageView_LP.gravity = Gravity.TOP;
        revInitDescView_IV.setLayoutParams(imageView_LP);

        Picasso.get()
                .load(resId)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(revInitDescView_IV);

        revTabHeaderViewItem_LL.addView(revInitDescView_IV);

        TextView contentHeader_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView();
        contentHeader_TV.setText(s);
        contentHeader_TV.setGravity(Gravity.TOP);
        contentHeader_TV.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

        revTabHeaderViewItem_LL.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(revInitDescView_IV));
        revTabHeaderViewItem_LL.addView(REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(contentHeader_TV));

        return REV_VIEWS_BASE_FUNCTIONS.revRemovedParentView(revTabHeaderViewItem_LL);
    }
}
