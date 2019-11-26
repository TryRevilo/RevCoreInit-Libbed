package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_pluggable_menues.rev_pluggable_menu_items;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_gen_lib_pers.RevDBModels.REV_PERS_REVMETADATA_GEN_FUNCTIONS;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntityRelationship;
import rev.ca.rev_gen_lib_pers.c_libs_core.RevPersLibRead;
import rev.ca.rev_gen_lib_pers.c_libs_core.rev_java_lib.RevPersJava;
import rev.ca.rev_gen_lib_pers.rev_varags_data.REV_SESSION_SETTINGS;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.ICreateRevPluggableMenuItem;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.RevPluggableMenuItemVM;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.REV_VIEWS_BASE_FUNCTIONS;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;

/**
 * Created by rev on 1/19/18.
 */

public class RevJoinBagPluggableMenuItemReg implements ICreateRevPluggableMenuItem {

    private Context mContext;
    private RevEntity revEntity;

    private String menuItemName = "rev_join_bag_menu_item";

    private RevPersJava revPersJava = new RevPersJava();
    private RevPersLibRead revPersLibRead = new RevPersLibRead();

    private ImageView imageView;

    private int imgSize = RevLibGenConstantine.REV_IMAGE_SIZE_SMALL;

    private int revEntityRelExists =-1;

    public RevJoinBagPluggableMenuItemReg(RevVarArgsData revVarArgsData) {
        this.mContext = revVarArgsData.getmContext();
        this.revEntity = revVarArgsData.getRevEntity();

        this.imageView = new ImageView(mContext);
    }

    @Override
    public String REV_PLUGGABLE_MENU_ITEM_VM_NAME() {
        return menuItemName;
    }

    @Override
    public List<String> REV_PLUGGABLE_MENU_CONTAINER_NAME() {
        List<String> revPluggableMenuAreas = new ArrayList<>();

        revPluggableMenuAreas.add("rev_bag_options_menu_wrapper_area");

        if (revEntity != null && revEntity.get_revEntitySubType().equals("rev_bag"))
            revPluggableMenuAreas.add("rev_profile_content_floating_options_wrapper_menu_area");

        return revPluggableMenuAreas;
    }

    @Override
    public RevPluggableMenuItemVM create_REV_PLUGGABLE_MENU_ITEM_VM() {

        final int revResId, revEntityRelExists;

        if (revEntity.get_remoteRevEntityGUID() < 1) {
            revEntityRelExists = revPersLibRead.revGetAnyRelExists_By_TypeValueId_LocalGUIDs("rev_entity_space_member", REV_SESSION_SETTINGS.getRevLoggedInEntityGuid(), revEntity.get_revEntityGUID());
        } else {
            revEntityRelExists = revPersLibRead.revGetAnyRelExists_By_TypeValueId_RemoteGUIDs("rev_entity_space_member", REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid(), revEntity.get_remoteRevEntityGUID());
        }

        if (revEntityRelExists == 1) {
            revResId = R.drawable.baseline_clear_black_48dp;
        } else {
            revResId = R.drawable.baseline_playlist_add_black_48dp;
        }

        imageView.setColorFilter(ContextCompat.getColor(mContext, R.color.teal_dark));
        imageView.setPadding(RevLibGenConstantine.REV_MARGIN_TINY, 0, RevLibGenConstantine.REV_MARGIN_TINY, 0);
        REV_VIEWS_BASE_FUNCTIONS.CENTER_VIEW_VERTICALLY(imageView);

        Picasso.get()
                .load(revResId)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (revEntityRelExists == -1) {
                    revJoinSpace();

                    Picasso.get()
                            .load(R.drawable.baseline_playlist_add_black_48dp)
                            .resize(imgSize, imgSize)
                            .centerCrop()
                            .into(imageView);
                }
            }
        });

        RevPluggableMenuItemVM revPluggableMenuItemVM = new RevPluggableMenuItemVM();
        revPluggableMenuItemVM.setRevPluggableMenuItemName(menuItemName);
        revPluggableMenuItemVM.setRevPluggableMenuName(null);
        revPluggableMenuItemVM.setRevPluggableMenuView(imageView);

        return revPluggableMenuItemVM;
    }

    private void revJoinSpace() {

        TextView textView = new RevCoreInputFormTextView(mContext).getRevExtraSmallNormalTextView();
        textView.setTextColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.rev_red));
        textView.setBackgroundColor(mContext.getResources().getColor(rev.ca.revlibviews.R.color.greyDark));
        textView.setPadding(RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_SMALL, RevLibGenConstantine.REV_MARGIN_MEDIUM, RevLibGenConstantine.REV_MARGIN_SMALL);

        Toast myToast = new Toast(mContext);
        myToast.setView(textView);
        myToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        myToast.setDuration(Toast.LENGTH_LONG);

        String revEntityName = REV_PERS_REVMETADATA_GEN_FUNCTIONS.REV_GET_METADATA_VALUE(revEntity.get_revEntityMetadataList(), "rev_entity_full_names_value");

        String revOptText = "you ALREADy ARE A mEmBER of " + revEntityName;

        if (revEntityRelExists == 1) {
            textView.setText(revOptText);
            myToast.show();

            return;
        }

        long revTargetGUID = revEntity.get_revEntityGUID();

        if (revTargetGUID < 1) {
            revTargetGUID = revPersLibRead.getLocalRevEntityGUID_By_RemoteRevEntityGUID(revEntity.get_remoteRevEntityGUID());
        }

        if (revTargetGUID < 1) {
            revEntity.set_revEntityResolveStatus(0);
            revTargetGUID = (long) revPersJava.saveRevEntity_NO_REMOTE_SYNC(revEntity);
        }

        RevEntityRelationship revEntityRelationship = new RevEntityRelationship("rev_entity_space_member", REV_SESSION_SETTINGS.getRevLoggedInEntityGuid(), revTargetGUID);
        revEntityRelationship.set_remoteRevEntitySubjectGUID(REV_SESSION_SETTINGS.getRevLoggedInRemoteEntityGuid());

        if (revEntity.get_remoteRevEntityGUID() > 0)
            revEntityRelationship.set_remoteRevEntityTargetGUID(revEntity.get_remoteRevEntityGUID());

        long revRelationshipId = (long) revPersJava.saveRevEntity(revEntityRelationship);

        if (revRelationshipId > 0 && !revEntityName.equals("")) {
            revOptText = "you HAvE succEssFuLLy joiNED " + revEntityName + " spAcE";
        } else if (revRelationshipId > 0) {
            revOptText = "you HAvE succEssFuLLy joiNED spAcE";
        } else {
            revOptText = "soRRy ! THERE wAs A pRoBLEm ADDiNG you To THE " + revEntityName + " spAcE";
        }

        textView.setText(revOptText);

        Picasso.get()
                .load(R.drawable.baseline_clear_black_48dp)
                .resize(imgSize, imgSize)
                .centerCrop()
                .into(imageView);

        myToast.show();
    }
}
