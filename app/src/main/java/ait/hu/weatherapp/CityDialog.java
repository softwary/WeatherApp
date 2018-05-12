package ait.hu.weatherapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ait.hu.weatherapp.Data.City;

public class CityDialog extends DialogFragment {

    public interface CityHandler {
        void onNewCityCreated(City city);

    }

    private CityHandler cityHandler;
    private EditText etCity;
    private TextView tvCity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof CityHandler) {
            cityHandler = (CityHandler) context;
        } else {
            throw new RuntimeException(
                    "The Activity does not implement the CityHandler interface");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog, null);

        etCity = (EditText) rootView.findViewById(R.id.etCity);

        builder.setView(rootView);

        builder.setPositiveButton("Save City", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();

    }


    @Override
    public void onResume() {
        super.onResume();
        final AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button positiveButton = d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(etCity.getText())) {


                        City city = new City(etCity.getText().toString());
                        cityHandler.onNewCityCreated(city);

                        d.dismiss();
                    } else {
                        etCity.setError(getString(R.string.null_error));
                    }

                }
            });
        }
    }

}
