package rev.ca.rev_lib_core_app_plugins.rev_bags_plugin.rev_plugin_views.rev_plugin_widget_views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import rev.ca.rev_lib_core_app_plugins.R;
import rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views.IRevInputFormView;
import rev.ca.rev_lib_core_views.rev_core_views.rev_page.rev_pers_views.rev_objects_classes_reg.REV_OBJECTS_CLASSES_REG;
import rev.ca.rev_lib_core_views.rev_core_views.rev_pluggable_menues.rev_pluggable_menu_area_views.CreateRevMenuAreaViewContainerPublisher;
import rev.ca.rev_lib_core_views.rev_pluggable_views_impl.RevConstantinePluggableViewsServices;
import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;
import rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers.RevPersRevEntity;
import rev.ca.revlibviews.rev_core_input_forms.RevCoreInputFormTextView;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLayoutsLinearLayout;
import rev.ca.revlibviews.rev_core_layouts.RevCoreLinearLayoutLayoutParams;

/**
 * Created by rev on 10/24/17.
 */

public class RevObjectInfoDetailsWidget {

    private Context mContext;
    private RevEntity revEntity;

    private RevCoreLayoutsLinearLayout revCoreLayoutsLinearLayout;
    private RevCoreInputFormTextView revCoreInputFormTextView;

    public RevObjectInfoDetailsWidget(Context mContext, RevEntity revEntity) {
        this.mContext = mContext;
        this.revEntity = revEntity;

        revCoreLayoutsLinearLayout = new RevCoreLayoutsLinearLayout(mContext);
        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);
    }

    public LinearLayout getRevEntityDetailsWidget() {
        LinearLayout revObjectInfoContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        LinearLayout revDetailsWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        revDetailsWrapper_LL.addView(revObjectIcon_IV());
        revDetailsWrapper_LL.addView(getRevObjectInfo_LL());

        revObjectInfoContainer_LL.addView(revDetailsWrapper_LL);
        revObjectInfoContainer_LL.addView(getRevObjectPublisherMenu_LL());

        String classPath = "rev.ca.rev_lib_core_app_plugins.rev_timeline.rev_plugin_views.rev_plugin_pages.RevTimelinePage";
        Class myClass = REV_OBJECTS_CLASSES_REG.REV_PLUGGABLE_DATA_CLASSES_REG.get(classPath);
        Constructor constructor;
        try {
            constructor = myClass.getConstructor(RevVarArgsData.class);
            Object revTimelinePage = constructor.newInstance(new RevVarArgsData());

            //get method that takes a String as argument
            Method method = myClass.getMethod("revObjectListing_LV", List.class);

            View returnValue = (View) method.invoke(revTimelinePage, new RevPersRevEntity(mContext).revRead_getRevEntityGUIDList_REV_CONTAINER_ENTITY_CHILDREN_GUID(revEntity.get_revEntityGUID()));
            revObjectInfoContainer_LL.addView(returnValue);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return revObjectInfoContainer_LL;
    }

    private View getRevObjectPublisherMenu_LL() {
        RevVarArgsData revVarArgsData = new RevVarArgsData();
        revVarArgsData.setRevContainerEntityGUID(revEntity.get_revEntityGUID());

        CreateRevMenuAreaViewContainerPublisher createRevMenuAreaViewContainerPublisher = new CreateRevMenuAreaViewContainerPublisher(revVarArgsData);
        LinearLayout revObjectPublisherContainer_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();
        LinearLayout revObjectPublisherWrapper_LL = revCoreLayoutsLinearLayout.get_H_RevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        LinearLayout.LayoutParams linearLayout_LP = (LinearLayout.LayoutParams) revObjectPublisherWrapper_LL.getLayoutParams();
        linearLayout_LP.setMargins((int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * 1.4), 0, 0, 0);
        linearLayout_LP.gravity = (Gravity.TOP);

        if (createRevMenuAreaViewContainerPublisher.genPublisherWrapper_LL() != null)
            revObjectPublisherWrapper_LL.addView(createRevMenuAreaViewContainerPublisher.genPublisherWrapper_LL());

        if (createRevMenuAreaViewContainerPublisher.kiwiActivityStreamListingTab() != null) {
            LinearLayout kiwiActivityStreamListingTabWrapper_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();
            LinearLayout.LayoutParams kiwiActivityStreamListingTabWrapper_LL_LP = (LinearLayout.LayoutParams) kiwiActivityStreamListingTabWrapper_LL.getLayoutParams();
            kiwiActivityStreamListingTabWrapper_LL_LP.setMargins(RevLibGenConstantine.REV_MARGIN_TINY * 2, 0, 0, 0);
            kiwiActivityStreamListingTabWrapper_LL_LP.gravity = (Gravity.TOP);

            kiwiActivityStreamListingTabWrapper_LL.addView(createRevMenuAreaViewContainerPublisher.kiwiActivityStreamListingTab());
            revObjectPublisherWrapper_LL.addView(kiwiActivityStreamListingTabWrapper_LL);
        }

        if (createRevMenuAreaViewContainerPublisher.attachBagPublisherWrapper_LL() != null)
            revObjectPublisherWrapper_LL.addView(createRevMenuAreaViewContainerPublisher.attachBagPublisherWrapper_LL());

        if (createRevMenuAreaViewContainerPublisher.moreOptionsWrapper_LL() != null)
            revObjectPublisherWrapper_LL.addView(createRevMenuAreaViewContainerPublisher.moreOptionsWrapper_LL());

        revObjectPublisherContainer_LL.addView(revObjectPublisherWrapper_LL);

        if (RevConstantinePluggableViewsServices.REV_PLUGIN_INPUT_FORMS_MAP.containsKey("RevCreateKiwiInputForm")) {
            revVarArgsData.setRevViewType("RevCreateKiwiInputForm");
            IRevInputFormView iRevInputFormView = (IRevInputFormView) RevConstantinePluggableViewsServices.revViewCreator_REV_PLUGIN_INPUT_FORMS_MAP(revVarArgsData);
            revObjectPublisherContainer_LL.addView(iRevInputFormView.createRevInputForm());
        }

        return revObjectPublisherContainer_LL;
    }

    private ImageView revObjectIcon_IV() {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(R.drawable.ic_people_outline_black_48dp);

        int revObjectProfileIconSize = (int) (RevLibGenConstantine.REV_IMAGE_SIZE_MEDIUM * 1.3);

        LinearLayout.LayoutParams imageView_LP = new LinearLayout.LayoutParams(
                revObjectProfileIconSize, revObjectProfileIconSize
        );
        imageView_LP.setMargins((int) (RevLibGenConstantine.REV_MARGIN_SMALL * 0.7), 0, 0, 0);
        imageView.setLayoutParams(imageView_LP);

        return imageView;
    }

    @SuppressLint("WrongConstant")
    private LinearLayout getRevObjectInfo_LL() {
        LinearLayout revObjectInfoWrapper_LL = revCoreLayoutsLinearLayout.getVerticalRevLinearLayout_MATCH_PARENT_WRAP_CONTENT();

        LinearLayout.LayoutParams revObjectInfoWrapper_LL_LP = RevCoreLinearLayoutLayoutParams.getRev_MATCH_W_Wrap_H_LP();
        revObjectInfoWrapper_LL_LP.setMargins(RevLibGenConstantine.REV_MARGIN_TINY, (int) (RevLibGenConstantine.REV_MARGIN_SMALL * 1.3), 0, RevLibGenConstantine.REV_MARGIN_SMALL);
        revObjectInfoWrapper_LL.setLayoutParams(revObjectInfoWrapper_LL_LP);

        TextView bagName_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView();
        bagName_TV.setText(revEntity.getRevGroupEntity().getName());

        LinearLayout attached_LL = revCoreLayoutsLinearLayout.getHorizontalRevLinearLayout_WRAPPED_ALL();

        TextView attachedTell_TV = revCoreInputFormTextView.getRevExtraSmallNormalTextView();
        attachedTell_TV.setText(" attached BAGS");

        TextView attached_TV = revCoreInputFormTextView.getRevExtraSmallBoldTextView();
        attached_TV.setText("107 ");

        attached_LL.addView(attached_TV);
        attached_LL.addView(attachedTell_TV);

        revObjectInfoWrapper_LL.addView(bagName_TV);
        revObjectInfoWrapper_LL.addView(attached_LL);

        return revObjectInfoWrapper_LL;
    }
}
