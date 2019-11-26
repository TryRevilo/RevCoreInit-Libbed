package rev.ca.rev_lib_core_app_plugins.rev_im_plugin.rev_plugin_views.rev_object;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_inet.RevCheckRemoteServerConnAsyncTaskService;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_pers_file.DownloadImageTask;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_read_from_server.IRevAsyncJSONResponse;
import rev.ca.rev_gen_lib_pers.rev_server_client.rev_pers.rev_read_from_server.RevAsyncGetJSONResponseTaskService;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_activities_window_views.RevPop;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableOptionsContainerMenuLoader;
import rev.ca.rev_lib_gen_functions.RevLangStrings;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.DynamicImageView;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormEditText;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;
import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

public class RevIMFullEntityView {

    private Context mContext;
    private RevVarArgsData revVarArgsData;

    private RevEntity revEntity;

    RevCoreInputFormTextView revCoreInputFormTextView;
    RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;

    LinearLayout.LayoutParams itemTitle_LP;

    int padding = RevLibGenConstantine.REV_MARGIN_SMALL;

    RevPersLibRead revPersLibRead;

    LinearLayout pastMessagesContainer_LL;

    public RevIMFullEntityView(RevVarArgsData revVarArgsData) {
        this.revVarArgsData = revVarArgsData;

        this.mContext = revVarArgsData.getmContext();
        this.revEntity = revVarArgsData.getRevEntity();

        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);

        itemTitle_LP = new LinearLayout.LayoutParams((int) (RevLibGenConstantine.REV_MARGIN_LARGE * 1.05), ViewGroup.LayoutParams.WRAP_CONTENT);

        revPersLibRead = new RevPersLibRead();

    }

    View revItemOptionsLayoutTabs() {
        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_WRAP_ALL();

        FrameLayout.LayoutParams textView_LP = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView_LP.gravity = Gravity.TOP | Gravity.RIGHT;
        linearLayout.setLayoutParams(textView_LP);

        linearLayout.addView(this.revCloseTab());

        LinearLayout optionsTabWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
        ((LinearLayout.LayoutParams) optionsTabWrapper_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

        if (new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_bag_options_menu", revVarArgsData) != null) {
            RevVarArgsData postRVD = new RevVarArgsData();
            postRVD.setmContext(revVarArgsData.getmContext());
            postRVD.setRevEntity(revEntity);

            View view = new RevPluggableOptionsContainerMenuLoader().getOptionsMenu("rev_bag_options_menu", postRVD);
            view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ((FrameLayout.LayoutParams) view.getLayoutParams()).setMargins((int) (RevLibGenConstantine.REV_MARGIN_SMALL * .7), RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0);

            optionsTabWrapper_LL.addView(view);
        }

        optionsTabWrapper_LL.addView(this.revPublisherDetailsTab());

        linearLayout.addView(optionsTabWrapper_LL);

        return linearLayout;

    }

    private View revPublisherDetailsTab() {

        /** PUBLISHER ICOM **/

        String imgPath = "/storage/emulated/0/Pictures/Reddit/97e59e6.jpg";
        ImageView ownerEntityImageView = new ImageView(mContext);

        int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_LARGE * .7);

        File userIconFIle = new File(imgPath);

        if (userIconFIle.exists()) {
            Picasso.get()
                    .load(new File(imgPath))
                    .placeholder(R.drawable.ic_account_circle_black_48dp)
                    .resize(imageSize, 0)
                    .into(ownerEntityImageView);
        } else {
            Picasso.get()
                    .load(R.drawable.ic_account_circle_black_48dp)
                    .placeholder(R.drawable.ic_account_circle_black_48dp)
                    .resize((int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * .9), 0)
                    .into(ownerEntityImageView);

            ownerEntityImageView.setColorFilter(ContextCompat.getColor(mContext, R.color.rcolorAccent_OPAQUE));
        }

        int borderSize = 5;
        int ownerEntityImageView_padding = 5;

        ownerEntityImageView.setPadding((int) (ownerEntityImageView_padding * 1.5), (int) (ownerEntityImageView_padding * 1.5), 0, (int) (ownerEntityImageView_padding * 1.5));

        LinearLayout.LayoutParams ownerEntityImageView_LP = new LinearLayout.LayoutParams((int) ((imageSize + borderSize + ownerEntityImageView_padding) * .98), LinearLayout.LayoutParams.WRAP_CONTENT);
        ownerEntityImageView_LP.gravity = (Gravity.TOP | Gravity.RIGHT);

        ownerEntityImageView.setLayoutParams(ownerEntityImageView_LP);

        GradientDrawable border = new GradientDrawable();
        border.setStroke(borderSize, mContext.getResources().getColor(R.color.greyDark));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, borderSize, borderSize, -borderSize, borderSize);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(ownerEntityImageView, layerDrawable);

        /** END PUBLISHER ICOM **/

        return ownerEntityImageView;

    }

    View revCloseTab() {

        int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        /** TAB **/

        Drawable mainTagViewOptions_DR = mContext.getResources().getDrawable(R.drawable.baseline_zoom_out_map_black_48dp);
        mainTagViewOptions_DR.setBounds(0, 0, imgSize, imgSize);
        DrawableCompat.setTint(mainTagViewOptions_DR, ContextCompat.getColor(mContext, R.color.revWhite));

        TextView mainTagViewOptions_TV = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView();
        mainTagViewOptions_TV.setText("CLOSE");
        mainTagViewOptions_TV.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        mainTagViewOptions_TV.setCompoundDrawablePadding(0);
        mainTagViewOptions_TV.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_SMALL);
        mainTagViewOptions_TV.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));
        mainTagViewOptions_TV.setGravity(Gravity.CENTER_VERTICAL);

        mainTagViewOptions_TV.setCompoundDrawables(mainTagViewOptions_DR, null, null, null);

        LinearLayout.LayoutParams ownerEntityImageView_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ownerEntityImageView_LP.gravity = (Gravity.TOP | Gravity.RIGHT);
        mainTagViewOptions_TV.setLayoutParams(ownerEntityImageView_LP);

        mainTagViewOptions_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevPop.getPw().dismiss();
            }
        });

        /** END TAB **/

        return mainTagViewOptions_TV;

    }

    public View getRevIMFullEntityView() {
        FrameLayout frameLayout = new FrameLayout(mContext);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        frameLayout.setBackgroundColor(mContext.getResources().getColor(R.color.revWhite));

        LinearLayout linearLayout = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        linearLayout.setPadding(0, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

        /** MEMO HEADER **/

        LinearLayout chatAreaContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) chatAreaContainer_LL.getLayoutParams()).setMargins(padding, 0, padding, padding);

        chatAreaContainer_LL.addView(this.revIMInputForm());
        chatAreaContainer_LL.addView(this.revPastMessages());

        /** END MEMO HEADER **/

        linearLayout.addView(revPageEntityTitle());
        linearLayout.addView(this.revItemDateTime());
        linearLayout.addView(chatAreaContainer_LL);
        linearLayout.addView(this.revPublisherDetails());

        frameLayout.addView(linearLayout);
        frameLayout.addView(this.revItemOptionsLayoutTabs());

        return frameLayout;
    }

    View revItemDateTime() {

        /** MEMO DATE **/

        TextView chatDate_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING_LINK(.7f);
        chatDate_TV.setText(revEntity.get_timeCreated());

        LinearLayout.LayoutParams memoDate_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        memoDate_LP.setMargins(padding, padding, 0, 0);
        chatDate_TV.setLayoutParams(memoDate_LP);

        return chatDate_TV;

    }

    View revPageEntityTitle() {

        LinearLayout revEntityPageTitle_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        int revImageSizeSmall = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

        ImageView imageView = new ImageView(mContext);
        Picasso.get()
                .load(R.drawable.icons8_collaboration_40)
                .resize(revImageSizeSmall, revImageSizeSmall)
                .centerCrop()
                .into(imageView);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM, RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM);
        layoutParams.gravity = (Gravity.TOP);
        layoutParams.setMargins(RevLibGenConstantine.REV_MARGIN_SMALL, 0, 0, 0);
        imageView.setLayoutParams(layoutParams);

        String profileNameTtl = "i-Message";
        SpannableString profileNameTtlSpan = new SpannableString(profileNameTtl);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan(RevLibGenConstantine.REV_TEXT_SIZE_LARGE), 0, 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new AbsoluteSizeSpan((int) (RevLibGenConstantine.REV_TEXT_SIZE_TINY * .8)), 3, profileNameTtl.length() - 1, SPAN_INCLUSIVE_INCLUSIVE);
        profileNameTtlSpan.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0, profileNameTtl.length(), 0);

        CharSequence finalText = TextUtils.concat(profileNameTtlSpan);

        TextView revEntityPageTitle = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING();
        revEntityPageTitle.setText(finalText);
        revEntityPageTitle.setPadding(padding, (int) (padding * 1.2), RevLibGenConstantine.REV_MARGIN_LARGE, 0);
        revEntityPageTitle.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ((LinearLayout.LayoutParams) revEntityPageTitle.getLayoutParams()).setMargins(-RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

        /** SET BORDERS **/

        int borderSize = 1;

        GradientDrawable border = new GradientDrawable();
        border.setStroke(borderSize, mContext.getResources().getColor(R.color.teal_dark));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -borderSize, -borderSize, -borderSize, borderSize);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(revEntityPageTitle, layerDrawable);

        /** SET BORDERS END **/

        revEntityPageTitle_LL.addView(imageView);
        revEntityPageTitle_LL.addView(revEntityPageTitle);

        return revEntityPageTitle_LL;

    }

    View revIMInputForm() {
        LinearLayout revIMInputFormContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) revIMInputFormContainer_LL.getLayoutParams()).setMargins(0, 0, (int) (RevLibGenConstantine.REV_MARGIN_LARGE * 1.135), 0);

        EditText editText = new RevCoreInputFormEditText(mContext).getRevEditText_NO_PADDING_RIGHT_BORDER_ONLY();
        editText.setHint("  Your message to " + REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_entity_full_names_value"));
        editText.setMinLines(3);
        editText.setMaxLines(5);
        editText.setPadding(0, 0, RevLibGenConstantine.REV_MARGIN_SMALL, 0);
        editText.setGravity(Gravity.TOP | Gravity.LEFT);

        LinearLayout footerWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        int imageSize = RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM;

        ImageView upload_IV = new ImageView(mContext);

        Picasso.get()
                .load(R.drawable.baseline_attachment_black_48dp)
                .resize(imageSize, 0)
                .into(upload_IV);

        footerWrapper_LL.addView(upload_IV);

        TextView sendMsg_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView();
        sendMsg_TV.setText("Send");
        sendMsg_TV.setPadding(RevLibGenConstantine.REV_MARGIN_LARGE, 0, RevLibGenConstantine.REV_MARGIN_LARGE, RevLibGenConstantine.REV_MARGIN_TINY);
        sendMsg_TV.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ((LinearLayout.LayoutParams) sendMsg_TV.getLayoutParams()).setMargins((int) (-RevLibGenConstantine.REV_MARGIN_TINY * 1.1), 0, 0, (int) (RevLibGenConstantine.REV_MARGIN_TINY * .9));
        ((LinearLayout.LayoutParams) sendMsg_TV.getLayoutParams()).gravity = Gravity.BOTTOM;

        sendMsg_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("MyApp", "SENT");
                new RevCheckRemoteServerConnAsyncTaskService(new RevCheckRemoteServerConnAsyncTaskService.IRevCheckConnAsync() {
                    @Override
                    public void processFinishIRevCheckConnAsync(Boolean output) {
                        processFinishIRevCheckConnAsync_GO_INLINE(output);
                    }
                }).execute();

                String apiURL = REV_SESSION_SETTINGS.getRevRemoteServer() + "/api/download?filePath=VTS_03_0.VOB";
                // new RevDownloadFileFromURL(mContext).execute(apiURL);
                Log.v("MyApp", "RECEIVED");
            }
        });

        /** SET BORDERS **/

        int borderSize = 4;

        GradientDrawable border = new GradientDrawable();
        border.setStroke(borderSize, mContext.getResources().getColor(R.color.darkText));
        border.setGradientType(RECTANGLE);

        Drawable[] layers = {border};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(0, -borderSize, -borderSize, -borderSize, borderSize);

        REV_VIEWS_BASE_FUNCTIONS.REV_SAFE_SET_BACKGROUND(sendMsg_TV, layerDrawable);

        /** SET BORDERS END **/

        footerWrapper_LL.addView(sendMsg_TV);

        revIMInputFormContainer_LL.addView(editText);
        revIMInputFormContainer_LL.addView(footerWrapper_LL);

        return revIMInputFormContainer_LL;
    }

    View revPastMessages() {
        pastMessagesContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) pastMessagesContainer_LL.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_SMALL, 0, RevLibGenConstantine.REV_MARGIN_MEDIUM);

        TextView noMsgs_TV = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);
        noMsgs_TV.setText("You do not have any past chat messages to display . . .");
        noMsgs_TV.setTextColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.teal_300_dark));
        noMsgs_TV.setPadding(0, 0, 0, RevLibGenConstantine.REV_MARGIN_SMALL);

        pastMessagesContainer_LL.addView(noMsgs_TV);

        ScrollView scrollView = new ScrollView(mContext);
        scrollView.setVerticalScrollBarEnabled(false);

        scrollView.addView(pastMessagesContainer_LL);

        return scrollView;
    }

    View revPublisherDetails() {

        /** PUBLISHER WRAPPER **/

        TextView revPublisherEntity = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);
        revPublisherEntity.setText("Oliver ");
        revPublisherEntity.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        revPublisherEntity.setGravity(Gravity.CENTER_VERTICAL);

        TextView revPublishTime = revCoreInputFormTextView.getRevVeryExtraSmallNormalTextView_NO_PADDING(.7f);
        revPublishTime.setText(revEntity.get_timeCreated());
        revPublishTime.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        revPublishTime.setGravity(Gravity.CENTER_VERTICAL);
        revPublishTime.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, 0, 0);

        LinearLayout publisherWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        ((LinearLayout.LayoutParams) publisherWrapper_LL.getLayoutParams()).setMargins(0, 1, 0, 0);

        publisherWrapper_LL.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));
        publisherWrapper_LL.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY);

        publisherWrapper_LL.addView(revPublisherEntity);
        publisherWrapper_LL.addView(revPublishTime);

        TextView textView = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView();
        textView.setText("+" + (55) + " chat past messages");

        textView.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.revWhite));
        textView.setCompoundDrawablePadding(0);
        textView.setPadding(RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY, RevLibGenConstantine.REV_MARGIN_TINY);
        textView.setBackgroundColor(ContextCompat.getColor(mContext, rev.ca.revlibviews.R.color.rcolorAccent_OPAQUE));
        textView.setGravity(Gravity.CENTER_VERTICAL);

        publisherWrapper_LL.addView(REV_VIEWS_BASE_FUNCTIONS.REV_SPACER());
        publisherWrapper_LL.addView(textView);

        /** END PUBLISHER WRAPPER **/

        return publisherWrapper_LL;

    }

    public void processFinishIRevCheckConnAsync_GO_INLINE(Boolean output) {
        if (output) {
            String apiURL = REV_SESSION_SETTINGS.getRevRemoteServer() + "/api/rev_entity/single?remoteRevEntityGUID=" + String.valueOf(REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid());
            new RevAsyncGetJSONResponseTaskService(mContext, apiURL, new IRevAsyncJSONResponse() {
                @Override
                public void processFinishAsyncJSONResponse(JSONObject jsonObject) {
                    Log.v(RevLangStrings.REV_TAG, "H.I.A >>> " + jsonObject.toString());

                    Gson gson = new Gson();
                    RevEntity revEntitySingle = gson.fromJson(jsonObject.toString(), RevEntity.class);

                    try {
                        JSONObject revEntityMetadataJSON = jsonObject.getJSONObject("revEntityMetadata");
                        String picImgPath = REV_SESSION_SETTINGS.getRevRemoteImageFilesRoot() + "/" + revEntityMetadataJSON.getString("_metadataValue");

                        ImageView imageView = new DynamicImageView(mContext, null);
                        imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                        int imageSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_LARGE * .6);

                        Picasso.get()
                                .load(R.drawable.baseline_keyboard_arrow_down_black_48dp)
                                .resize(0, imageSize)
                                .centerCrop()
                                .into(imageView);

                        new DownloadImageTask(picImgPath, imageView).execute();

                        pastMessagesContainer_LL.addView(imageView);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).execute();
        } else {
            TextView textView = revCoreInputFormTextView.getRevExtraSmallNormalTextView_NO_PADDING(.9f);
            textView.setText("CampAnn is unable to resolve the remote server.\n\tPlease check your internet connection then try again\n");
            ((LinearLayout.LayoutParams) textView.getLayoutParams()).setMargins(0, RevLibGenConstantine.REV_MARGIN_TINY, 0, 0);

            pastMessagesContainer_LL.addView(textView);
        }

    }
}
