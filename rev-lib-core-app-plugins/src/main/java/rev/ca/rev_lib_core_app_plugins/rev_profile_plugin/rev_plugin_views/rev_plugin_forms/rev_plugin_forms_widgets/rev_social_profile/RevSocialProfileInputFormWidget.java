package rev.ca.rev_lib_core_app_plugins.rev_profile_plugin.rev_plugin_views.rev_plugin_forms.rev_plugin_forms_widgets.rev_social_profile;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.Arrays;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevPersGenFunctions;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_menues.RevCoreColoredTabs;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * Created by rev on 2/5/18.
 */

public class RevSocialProfileInputFormWidget {


    private Context mContext;
    private RevVarArgsData revVarArgsData;
    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormEditText revCoreInputFormEditText;

    EditText emailContacts_ET, phoneContacts_ET;

    public RevSocialProfileInputFormWidget(RevVarArgsData revVarArgsData) {
        revVarArgsData = RevPersGenFunctions.REV_VAR_ARGS_DATA_RESOLVER(revVarArgsData);
        this.revVarArgsData = revVarArgsData;
        this.mContext = revVarArgsData.getmContext();

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormEditText = new RevCoreInputFormEditText(mContext);
    }

    public View getRevSocialProfileInputFormWidget() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        int padding = 2;
        linearLayout.setPadding(padding, padding, padding, padding);

        String textOptional = "(opTioNAL)";

        SpannableString textOptionalSpan = new SpannableString(textOptional);
        textOptionalSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 0, textOptional.length(), SPAN_INCLUSIVE_INCLUSIVE);
        textOptionalSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, textOptional.length(), 0); // set color

        // Phone
        String sPhone = "pHoNE #s";

        SpannableString spanPhone = new SpannableString(sPhone);
        spanPhone.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_SMALL), 0, sPhone.length(), SPAN_INCLUSIVE_INCLUSIVE);

        // let's put both spans together with a separator and all
        CharSequence finalText = TextUtils.concat(spanPhone, " ", textOptionalSpan);

        phoneContacts_ET = revCoreInputFormEditText.getRevEditText_ONLY_BottomBorders_Small_Pad_Left();
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

        String sEmail = "EmAiLs";
        SpannableString spanEmail = new SpannableString(sEmail);
        spanEmail.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_SMALL), 0, sEmail.length(), SPAN_INCLUSIVE_INCLUSIVE);

        // let's put both spans together with a separator and all
        CharSequence finalEmailText = TextUtils.concat(spanEmail, " ", textOptionalSpan);

        emailContacts_ET = revCoreInputFormEditText.getRevEditText_ONLY_BottomBorders_Small_Pad_Left();
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

        linearLayout.addView(phoneContacts_ET);
        linearLayout.addView(emailContacts_ET);

        linearLayout.addView(this.revInputFormFooter());

        return linearLayout;
    }

    private View revInputFormFooter() {
        TextView submitFormTab = new RevCoreColoredTabs(mContext).getRevColoredTab();
        submitFormTab.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.revWhite));
        submitFormTab.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.deep_purple_dark));
        submitFormTab.setText("sAvE");

        submitFormTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevConstantinePluggableViewsServices
                        .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevPublishProfileSocialCircleAction").initRevAction(revContainerEntityFormdata());
            }
        });

        LinearLayout.LayoutParams submitFormTab_LP = (LinearLayout.LayoutParams) submitFormTab.getLayoutParams();
        submitFormTab_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_LARGE * 1.1), RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        return submitFormTab;
    }

    public RevEntity revContainerEntityFormdata() {
        RevEntity revPersEntity = new RevEntity();

        revPersEntity.set_revEntityType(FeedReaderContract.FeedEntry_REV_OBJECT_ENTITY.TABLE_NAME);
        revPersEntity.set_revEntitySubType("rev_entity_social_info");

        revPersEntity.set_revEntityOwnerGUID(REV_SESSION_SETTINGS.getRevLoggedInEntityGuid());
        revPersEntity.set_revEntityContainerGUID(revVarArgsData.getRevContainerEntityGUID());

        revPersEntity.set_revEntityMetadataList(Arrays.asList(
                new RevEntityMetadata("rev_entity_social_info_phone_contacts_circles", String.valueOf(phoneContacts_ET.getText())),
                new RevEntityMetadata("rev_entity_social_info_email_contacts_circles", String.valueOf(emailContacts_ET.getText()))
        ));

        return revPersEntity;

    }
}
