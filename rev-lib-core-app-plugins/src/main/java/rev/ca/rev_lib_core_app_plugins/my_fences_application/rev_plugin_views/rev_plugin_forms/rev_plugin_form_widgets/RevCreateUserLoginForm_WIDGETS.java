package rev.ca.rev_lib_core_app_plugins.my_fences_application.rev_plugin_views.rev_plugin_forms.rev_plugin_form_widgets;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.Arrays;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityMetadata;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.revlibpersistence.rev_persistence.FeedReaderContract;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormButton;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditTextPassword;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLinearLayoutLayoutParams;

/**
 * Created by rev on 10/7/17.
 */

public class RevCreateUserLoginForm_WIDGETS {

    private Context mContext;
    Activity mActivity;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormEditText revCoreInputFormEditText;
    private RevCoreInputFormEditTextPassword revCoreInputFormEditTextPassword;
    private RevCoreInputFormButton revCoreInputFormButton;

    private EditText eMailETPhone_ET, password_ET;

    int width, height;

    private LinearLayout revLogInContainerLL;

    public RevCreateUserLoginForm_WIDGETS(Context mContext) {
        this.mContext = mContext;
        this.mActivity = (Activity) mContext;

        revCoreInputFormEditText = new RevCoreInputFormEditText(mContext);
        revCoreInputFormEditTextPassword = new RevCoreInputFormEditTextPassword(mContext);
        revCoreInputFormButton = new RevCoreInputFormButton(mContext);
        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);

        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        width = dm.widthPixels;
        height = dm.heightPixels;

        revLogInContainerLL = revCoreLayoutsLinearLayout.getVerticalFormRevLinearLayout();
        revLogInContainerLL.setLayoutParams(RevCoreLinearLayoutLayoutParams.getRev_MATCH_W_Wrap_H_LP());

        revLogInContainerLL.addView(this.getLogInTextTell());

        this.revPageFormItems();
        this.revLogInFormFooter();
        this.signUpOptions();
    }

    public LinearLayout getPageForm() {
        return revLogInContainerLL;
    }

    private TextView getLogInTextTell() {
        LinearLayout.LayoutParams revLogInFormDefaultInner_LP = RevCoreLinearLayoutLayoutParams.getRev_WRAP_ALL_LP();
        revLogInFormDefaultInner_LP.setMargins(50, 0, 0, 0);

        TextView logInTextTell = new RevCoreInputFormTextView(mContext).getRevFormHeader1TextView();
        logInTextTell.setText(R.string.log_in);
        logInTextTell.setLayoutParams(revLogInFormDefaultInner_LP);
        logInTextTell.setPadding(0, 32, 0, 0);

        return logInTextTell;
    }

    public void revPageFormItems() {
        LinearLayout revLogInFormItemsLL = new LinearLayout(mContext);
        revLogInFormItemsLL.setOrientation(LinearLayout.VERTICAL);

        eMailETPhone_ET = revCoreInputFormEditText.getRevEditText_NO_Borders();
        eMailETPhone_ET.setHint(R.string.email_or_phone_number);

        password_ET = revCoreInputFormEditTextPassword.getRevPasswordEditTextPass_ONLY_BottomBorders();
        password_ET.setHint(R.string.password_hint_txt);

        revLogInFormItemsLL.addView(eMailETPhone_ET);
        revLogInFormItemsLL.addView(password_ET);

        revLogInContainerLL.addView(revLogInFormItemsLL);
    }

    public RevEntity revObjectFormdata() {
        RevEntity revEntity = new RevEntity();
        revEntity.set_revEntityType(FeedReaderContract.FeedEntry_REV_USER_ENTITY.TABLE_NAME);
        revEntity.set_revEntitySubType("rev_user_entity");

        String email = eMailETPhone_ET.getText().toString();
        email = email.replaceAll("\\s+", "");

        revEntity.get_revEntityMetadataList().addAll(Arrays.asList(
                new RevEntityMetadata("rev_user_entity_email_value", email)
        ));

        return revEntity;
    }

    public void revLogInFormFooter() {
        LinearLayout revLogInFormFooter_LL = revCoreLayoutsLinearLayout.getHorizontalFormFooterRevLinearLayout();

        Button logInBttn = revCoreInputFormButton.getFormInputSubmitButton();
        logInBttn.setText(R.string.log_in);

        logInBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevConstantinePluggableViewsServices
                        .REV_PLUGIN_START_REV_PERS_ACTIONS_MAP.get("RevCreateUserLoginAction").initRevAction(revObjectFormdata());
            }
        });

        Button closeWin = revCoreInputFormButton.getFormInputCancelButton(RevPop.getPw());
        closeWin.setText(R.string.cancel);

        closeWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPop.getPw().dismiss();
            }
        });

        revLogInFormFooter_LL.addView(logInBttn);
        revLogInFormFooter_LL.addView(closeWin);
        revLogInContainerLL.addView(revLogInFormFooter_LL);
    }

    void signUpOptions() {
        LinearLayout signUpOptionsLL = revCoreLayoutsLinearLayout.getHorizontalFormFooterRevLinearLayout();

        LinearLayout.LayoutParams signUpOptionsLL_LP = RevCoreLinearLayoutLayoutParams.getRev_WRAP_ALL_LP();
        signUpOptionsLL_LP.setMargins(72, 42, 0, 0);

        signUpOptionsLL.setLayoutParams(signUpOptionsLL_LP);

        TextView optionOr = new RevCoreInputFormTextView(mContext).getRevMediumBoldTextView();
        optionOr.setText(R.string.or);

        LinearLayout.LayoutParams params = RevCoreLinearLayoutLayoutParams.getRev_WRAP_ALL_LP();
        params.setMargins(22, 0, 0, 0);

        TextView signUpTxt = new TextView(mContext);
        signUpTxt.setText(R.string.create_new_account);
        signUpTxt.setTextColor(ContextCompat.getColor(mContext, R.color.blue));
        signUpTxt.setLayoutParams(params);

        signUpOptionsLL.addView(optionOr);
        signUpOptionsLL.addView(signUpTxt);

        revLogInContainerLL.addView(signUpOptionsLL);
    }
}
