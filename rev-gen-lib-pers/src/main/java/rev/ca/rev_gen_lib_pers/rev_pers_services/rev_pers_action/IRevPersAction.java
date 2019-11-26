package rev.ca.rev_gen_lib_pers.rev_pers_services.rev_pers_action;

import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;

/**
 * Created by rev on 12/20/17.
 */

public interface IRevPersAction {

    String registerRevActionName();

    Object initRevAction(RevEntity revEntity);
}
