package com.appsynopsis.jarman.victimisedcolorworld;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by nila on 11/16/15.
 */
public class PicModeSelectDialogFragment extends DialogFragment {

    private String[] picMode = {Constants.PicModes.CAMERA, Constants.PicModes.GALLERY};

    private IPicModeSelectListener iPicModeSelectListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(picMode, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (iPicModeSelectListener != null)
                    iPicModeSelectListener.onPicModeSelected(picMode[which]);
            }
        });
        return builder.create();
    }

    public void setiPicModeSelectListener(IPicModeSelectListener iPicModeSelectListener) {
        this.iPicModeSelectListener = iPicModeSelectListener;
    }

    public interface IPicModeSelectListener {
        void onPicModeSelected(String mode);
    }
}