package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {

    public interface EditCityDialogListener {
        void onCityEdited(int position, City updatedCity);
    }

    private EditCityDialogListener listener;

    private static final String ARG_CITY = "city";
    private static final String ARG_PROVINCE = "province";
    private static final String ARG_POSITION = "position";

    public static EditCityFragment newInstance(City city, int position) {
        EditCityFragment fragment = new EditCityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CITY, city.getCityName());
        args.putString(ARG_PROVINCE, city.getProvinceName());
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_edit_city, null);

        EditText editCity = view.findViewById(R.id.edit_text_city);
        EditText editProvince = view.findViewById(R.id.edit_text_province);

        // getting all the arguments
        Bundle args = getArguments();
        String oldCity = args.getString(ARG_CITY);
        String oldProvince = args.getString(ARG_PROVINCE);
        int position = args.getInt(ARG_POSITION);

        //filling old inouts
        editCity.setText(oldCity);
        editProvince.setText(oldProvince);

        builder.setView(view)
                .setTitle("Add/Edit City")
                .setNegativeButton("Cancel", (dialog, which) -> {})
                .setPositiveButton("Save", (dialog, which) -> {
                    String newCity = editCity.getText().toString();
                    String newProvince = editProvince.getText().toString();
                    listener.onCityEdited(position, new City(newCity, newProvince));
                });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EditCityDialogListener) {
            listener = (EditCityDialogListener) context;
        }
        else {
            throw new RuntimeException(context.toString() + " must implement EditCityDialogListener");
        }
    }
}
