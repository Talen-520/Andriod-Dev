package edu.qc.seclass.glm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.grocerylistmanager.R;


public class renameDialog extends AppCompatDialogFragment {
    private EditText renameGroceryListEditText;
    private RenameDialogListener listener;

    // Method for creating the RenameListDialog
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        // The position of the list in the recycler View is acquired from the arguments Bundle
        int position = args.getInt("position");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        //The view for the dialog is inflated
        View view = inflater.inflate(R.layout.editdialog, null);

        //The title is set, and negative and positive buttons are set with onClickListeners
        builder.setView(view).setTitle("Rename Grocery List").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Rename List", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (renameGroceryListEditText.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "No name was entered, please enter a new name", Toast.LENGTH_SHORT).show();
                }
                else {
                    String groceryListName = renameGroceryListEditText.getText().toString();
                    listener.renameGroceryList(groceryListName, position);
                }
            }
        });

        renameGroceryListEditText = view.findViewById(R.id.renameGroceryListEditText);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (RenameDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement RenameGroceryListDialogListener");
        }
    }

    //Custom listener whose logic is defined in MainActivity, will rename the list to the given name
    public interface RenameDialogListener {
        void renameGroceryList(String gListName, int position);
    }
}