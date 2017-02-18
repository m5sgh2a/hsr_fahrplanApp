package group9.hsr.ch.businesstravelagent.Model;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

class DialogBox {

    void ShowOkMessage(String title, String messsage, Activity activity)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(messsage);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
