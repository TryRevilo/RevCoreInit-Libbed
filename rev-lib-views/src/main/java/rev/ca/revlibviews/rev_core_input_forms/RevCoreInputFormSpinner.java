package rev.ca.revlibviews.rev_core_input_forms;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import rev.ca.rev_lib_gen_functions.RevLibGenConstantine;
import rev.ca.revlibviews.R;

public class RevCoreInputFormSpinner {

    public interface IRevCoreInputFormSpinner {
        void revSpinnerOnSelectedCallback(int revSelectedPos);
    }

    private IRevCoreInputFormSpinner iRevCoreInputFormSpinner;

    private Context mContext;
    private Spinner revSpinner;

    private int revCurrentSelection;
    private List<String> revSelectValuesList;

    private RevCoreInputFormTextView revCoreInputFormTextView;

    private TextView revSelectedSpinnerItemValue;

    public RevCoreInputFormSpinner(Context mContext, int revCurrentSelection, List<String> revSelectValuesList, IRevCoreInputFormSpinner iRevCoreInputFormSpinner) {
        this.mContext = mContext;
        this.revCurrentSelection = revCurrentSelection;
        this.revSelectValuesList = revSelectValuesList;
        this.iRevCoreInputFormSpinner = iRevCoreInputFormSpinner;

        this.revSpinner = new Spinner(mContext);

        revCoreInputFormTextView = new RevCoreInputFormTextView(mContext);

        revSelectedSpinnerItemValue = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.8f);
    }

    public Spinner revGetRevCoreInputFormSpinner() {
        //  create an adapter to describe how the items are displayed, adapters are used in several places in android.
        // There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new RevArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, revSelectValuesList.toArray(new String[0]));

        //set the spinners adapter to the previously created one.

        ((LinearLayout.LayoutParams) revSelectedSpinnerItemValue.getLayoutParams()).width = LinearLayout.LayoutParams.WRAP_CONTENT;

        revSpinner.setAdapter(adapter);
        revSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        if (revCurrentSelection > -1) revSpinner.setSelection(revCurrentSelection);

        return revSpinner;
    }

    public class RevArrayAdapter extends ArrayAdapter {

        public RevArrayAdapter(@NonNull Context context, int resource, @NonNull Object[] objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return revSelectedSpinnerItemValue;
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            int revPadding = RevLibGenConstantine.REV_MARGIN_SMALL;

            final TextView listView = revCoreInputFormTextView.getRevExtraSmallBoldTextView_NOPADDING(.8f);
            listView.setTextColor(mContext.getResources().getColor(R.color.teal_400_dark));
            listView.setBackgroundColor(mContext.getResources().getColor(R.color.rev_default_light));
            listView.setGravity(Gravity.CENTER);
            listView.setText(super.getItem(position).toString());
            listView.setPadding(revPadding, revPadding, revPadding, revPadding);

            ((LinearLayout.LayoutParams) listView.getLayoutParams()).width = LinearLayout.LayoutParams.MATCH_PARENT;

            // If this is the selected item position
            if (position == revCurrentSelection) {
                listView.setTextColor(mContext.getResources().getColor(R.color.revWhite));
                listView.setBackgroundColor(mContext.getResources().getColor(R.color.lime_primary));
            }

            return listView;
        }
    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            revSelectedSpinnerItemValue.setText(parent.getItemAtPosition(pos).toString());
            view.setBackgroundColor(mContext.getResources().getColor(R.color.rev_default_light));

            revCurrentSelection = pos;

            iRevCoreInputFormSpinner.revSpinnerOnSelectedCallback(pos);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    }
}
