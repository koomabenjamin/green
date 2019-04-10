package com.shapeworks.mivule.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.shapeworks.mivule.R;

/**
 * Created by koomabenjamin on 12/13/17.
 */

public class DialogManager {

    public void showAlertDialog(Context context, String title, String message,
                                Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        if(status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.ic_security_black_24dp : R.drawable.ic_action_add);

        alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener(

        ) {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
