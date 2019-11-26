package rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action.rev_pre_post_pers;

import java.util.List;

/**
 * Created by rev on 1/12/18.
 */

public interface IPrePostRevPersActionCollections {

    List<IPreRevPersAction> I_PRE_REV_PERS_ACTION_LIST();

    List<IPostRevPersAction> I_POST_REV_PERS_ACTION_LIST();

}
