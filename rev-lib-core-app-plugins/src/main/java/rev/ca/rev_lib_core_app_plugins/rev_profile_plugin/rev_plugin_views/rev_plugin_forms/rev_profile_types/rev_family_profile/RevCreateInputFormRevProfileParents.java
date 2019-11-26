package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.rev_profile_types.rev_family_profile;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Map;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_widgets.RevCreateClassAcademicProfileInputFormWidget;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_menues.RevCoreColoredTabs;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * Created by rev on 2/23/18.
 */

public class RevCreateInputFormRevProfileParents implements IRevInputFormView {

    private Context mContext;
    private RevVarArgsData revVarArgsData;
    private Map<Object, Object> revVarArgsDataMetadataStrings;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormEditText revCoreInputFormEditText;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    private RevCreateClassAcademicProfileInputFormWidget revCreateClassAcademicProfileInputFormWidget;

    public RevCreateInputFormRevProfileParents(RevVarArgsData revVarArgsData) {
        revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revVarArgsDataMetadataStrings = revVarArgsData.getRevVarArgsDataMetadataStrings();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormEditText = new RevCoreInputFormEditText(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    @Override
    public View createRevInputForm() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        int padding = RevLibGenConstantine.REV_MARGIN_SMALL;
        linearLayout.setPadding(padding, padding, padding, padding);

        String textOptional = "(optional)";

        SpannableString textOptionalSpan = new SpannableString(textOptional);
        textOptionalSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 0, textOptional.length(), SPAN_INCLUSIVE_INCLUSIVE);
        textOptionalSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, textOptional.length(), 0); // set color

        EditText fullNames_ET = revCoreInputFormEditText.getRevEditText_ONLY_BottomBorders_Small_Pad_Left();
        fullNames_ET.setHint("full names");

        // Sex
        String sSex = "sex";
        String sSexTypes = "male female . . .";

        SpannableString spanSex = new SpannableString(sSex);
        spanSex.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_SMALL), 0, spanSex.length(), SPAN_INCLUSIVE_INCLUSIVE);

        SpannableString textSexTypesSpan = new SpannableString(sSexTypes);
        textSexTypesSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 0, textSexTypesSpan.length(), SPAN_INCLUSIVE_INCLUSIVE);
        textSexTypesSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, sSexTypes.length(), 0); // set color

        // let's put both spans together with a separator and all
        CharSequence sexFinalText = TextUtils.concat(spanSex, " ", textSexTypesSpan);

        EditText sex_ET = revCoreInputFormEditText.getRevEditText_ONLY_BottomBorders_Small_Pad_Left();
        sex_ET.setHint(sexFinalText);

        // Phone
        String sPhone = "phone #";

        SpannableString spanPhone = new SpannableString(sPhone);
        spanPhone.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_SMALL), 0, sPhone.length(), SPAN_INCLUSIVE_INCLUSIVE);

        // let's put both spans together with a separator and all
        CharSequence finalText = TextUtils.concat(spanPhone, " ", textOptionalSpan);

        final EditText phoneContacts_ET = revCoreInputFormEditText.getRevEditText_ONLY_BottomBorders_Small_Pad_Left();
        phoneContacts_ET.setHint(finalText);
        phoneContacts_ET.setGravity(Gravity.CENTER_VERTICAL);

        Drawable phoneContacts_ET_DR = mContext.getResources().getDrawable(R.drawable.icons8_address_book_40);
        phoneContacts_ET_DR.setBounds(0, 0, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL);
        phoneContacts_ET.setCompoundDrawables(null, null, phoneContacts_ET_DR, null);

        phoneContacts_ET.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (phoneContacts_ET.getRight() - phoneContacts_ET.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Log.v("MyApp", "Import CCTS");

                        return true;
                    }
                }
                return false;
            }
        });

        String sEmail = "email";
        SpannableString spanEmail = new SpannableString(sEmail);
        spanEmail.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_SMALL), 0, sEmail.length(), SPAN_INCLUSIVE_INCLUSIVE);

        // let's put both spans together with a separator and all
        CharSequence finalEmailText = TextUtils.concat(spanEmail, " ", textOptionalSpan);

        final EditText emailContacts_ET = revCoreInputFormEditText.getRevEditText_ONLY_BottomBorders_Small_Pad_Left();
        emailContacts_ET.setHint(finalEmailText);
        emailContacts_ET.setGravity(Gravity.CENTER_VERTICAL);

        Drawable img = mContext.getResources().getDrawable(R.drawable.icons8_email_64);
        img.setBounds(0, 0, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL);
        emailContacts_ET.setCompoundDrawables(null, null, img, null);

        emailContacts_ET.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (emailContacts_ET.getRight() - emailContacts_ET.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        Log.v("MyApp", "Import CCTS");

                        return true;
                    }
                }
                return false;
            }
        });

        linearLayout.addView(fullNames_ET);
        linearLayout.addView(sex_ET);

        linearLayout.addView(phoneContacts_ET);
        linearLayout.addView(emailContacts_ET);

        // Upload icon

        String sAddUserIcon = "picture";
        SpannableString spanAddUserIcon = new SpannableString(sAddUserIcon);
        spanAddUserIcon.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_SMALL * .8)), 0, sAddUserIcon.length(), SPAN_INCLUSIVE_INCLUSIVE);
        spanAddUserIcon.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, sAddUserIcon.length(), 0); // set color

        // let's put both spans together with a separator and all
        CharSequence finalAddUserIconText = TextUtils.concat(spanAddUserIcon, " ", textOptionalSpan);

        final TextView userIcon_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING((float) (.8));
        userIcon_TV.setText(finalAddUserIconText);

        ImageView imageView = new ImageView(mContext);
        imageView.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_dark));
        imageView.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_TINY, 0);

        Picasso.get()
                .load(R.drawable.ic_photo_camera_black_48dp)
                .resize(RevLibGenConstantine.REV_IMAGE_SIZE_SMALL, RevLibGenConstantine.REV_IMAGE_SIZE_SMALL)
                .centerCrop()
                .into(imageView);

        LinearLayout uploadPic_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        uploadPic_LL.setPadding((int) (RevLibGenConstantine.REV_MARGIN_LARGE * .95), RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL);
        uploadPic_LL.setBackgroundColor(mContext.getResources().getColor(R.color.rev_default_background));

        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(userIcon_TV);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(imageView);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(uploadPic_LL);

        uploadPic_LL.addView(imageView);
        uploadPic_LL.addView(userIcon_TV);

        linearLayout.addView(uploadPic_LL);

        linearLayout.addView(this.revInputFormFooter());

        return linearLayout;
    }

    private View revInputFormFooter() {
        TextView submitFormTab = new RevCoreColoredTabs(mContext).getRevColoredTab();
        submitFormTab.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));
        submitFormTab.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.deep_purple_dark));
        submitFormTab.setText("sAvE");

        LinearLayout.LayoutParams submitFormTab_LP = (LinearLayout.LayoutParams) submitFormTab.getLayoutParams();
        submitFormTab_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_LARGE * 1.1), RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        return submitFormTab;
    }

    @Override
    public RevEntity createRevInputFormData() {
        return null;
    }

    @Override
    public String revInputFormActionName() {
        return null;
    }

    @Override
    public RevVarArgsData REV_VAR_ARGS_DATA() {
        return null;
    }
}
