package rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.rev_pre_post_pers;

import java.util.ArrayList;
import java.util.List;

import rev.ca.rev_plugin_loader.RevPluginsObjectsRegistry;

/**
 * Created by rev on 12/25/17.
 */

public class RevPluginsPostActionsRegistry {

    public List<IPostRevPersAction> initRevPluginsPostActionsRegistry() {
        List<IPostRevPersAction> iPostRevPersActionArrayList = new ArrayList<>();

        for (Object object : RevPluginsObjectsRegistry.getRevPluginsObjectsRegistry()) {
            if ((object instanceof IPrePostRevPersActionCollections)) {
                for (IPostRevPersAction iPostRevPersAction : ((IPrePostRevPersActionCollections) object).I_POST_REV_PERS_ACTION_LIST()) {
                    if (!iPostRevPersActionArrayList.contains(iPostRevPersAction)) {
                        iPostRevPersActionArrayList.add(iPostRevPersAction);
                    }
                }
            }
        }

        return iPostRevPersActionArrayList;
    }
}
