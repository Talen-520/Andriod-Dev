package edu.qc.seclass.glm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

//removeDialog serves to delete a grocery list, this will delete it from the UI along with its saved items

public class removeDialog extends AppCompatDialogFragment {
    private TextView removeGroceryListTextView;
    private RemoveDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        // The position of the list in the recycler View is acquired from the position of the list when it is selected
        int position = args.getInt("position");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.deletedialog, null);
        //onClickListener choices are set to delete or cancel
        builder.setView(view).setTitle("Delete Grocery List").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Delete List", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.deleteGroceryList(position);
            }
        });

        removeGroceryListTextView = view.findViewById(R.id.removeGroceryListTextView);
        removeGroceryListTextView.setText("Are you sure you want to delete this list?");

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (RemoveDialogListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface RemoveDialogListener {
        void deleteGroceryList(int position);
    }
}