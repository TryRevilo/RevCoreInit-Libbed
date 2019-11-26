package rev.ca.rev_lib_core_views.rev_core_views.rev_input_form_views;

import android.view.View;

import rev.ca.rev_gen_lib_pers.rev_varags_data.RevVarArgsData;
import rev.ca.rev_gen_lib_pers.RevDBModels.RevEntity;

/**
 * Created by rev on 12/18/17.
 */

public interface IRevInputFormView {

    View createRevInputForm();

    RevEntity createRevInputFormData();

    String revInputFormActionName();

    RevVarArgsData REV_VAR_ARGS_DATA();
}
