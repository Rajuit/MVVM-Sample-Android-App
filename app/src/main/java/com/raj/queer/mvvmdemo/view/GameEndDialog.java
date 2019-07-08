package com.raj.queer.mvvmdemo.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.raj.queer.mvvmdemo.R;
import static java.security.AccessController.getContext;

public class GameEndDialog extends DialogFragment {

    private View rootView;
    private GameActivity activity;
    private String winnerName;

    public static GameEndDialog newInstance(GameActivity activity, String winnerName) {
        GameEndDialog dialog = new GameEndDialog();
        dialog.activity = activity;
        dialog.winnerName = winnerName;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            alertDialog = new AlertDialog.Builder(getContext())
                    .setView(rootView)
                    .setCancelable(false)
                    .setPositiveButton(R.string.done, ((dialog, which) -> onNewGame()))
                    .create();
        }
        if (alertDialog != null) {
            alertDialog.setCanceledOnTouchOutside(false);
        }
        if (alertDialog != null) {
            alertDialog.setCancelable(false);
        }
        return alertDialog;
    }

    private void initViews() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            rootView = LayoutInflater.from(getContext())
                    .inflate(R.layout.game_end_dialog, null, false);
        }
        ((TextView) rootView.findViewById(R.id.tv_winner)).setText(winnerName);
    }

    private void onNewGame() {
        dismiss();
        activity.promptForPlayers();
    }
}
