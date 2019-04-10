package com.shapeworks.mivule.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import com.shapeworks.mivule.R;


public class dialog_item_00 extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        // Use the Builder class for convenient dialog construction
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setMessage(R.string.app_name)
//                .setPositiveButton(R.string.app_name, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // FIRE ZE MISSILES!
//                    }
//                })
//                .setNegativeButton(R.string.app_name, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User cancelled the dialog
//                    }
//                });
//        // Create the AlertDialog object and return it
//        return builder.create();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_item_00, null))
                // Add action buttons
                .setPositiveButton(R.string.app_name, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton(R.string.app_name, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog_item_00.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
//    AlertDialog.Builder builder;
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
//                } else {
//                builder = new AlertDialog.Builder(context);
//                }
//                builder.setTitle("Delete entry")
//                .setMessage("Are you sure you want to delete this entry?")
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//public void onClick(DialogInterface dialog, int which) {
//        // continue with delete
//        }
//        })
//        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//public void onClick(DialogInterface dialog, int which) {
//        // do nothing
//        }
//        })
//        .setIcon(android.R.drawable.ic_dialog_alert)
//        .show();
//        return builder.create();
//        }