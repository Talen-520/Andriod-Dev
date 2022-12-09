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

//This is rename Dialog with the purpose of renaming a list from clicking the rename button

public class renameDialog extends AppCompatDialogFragment {
    private EditText renameGroceryListEditText;
    private RenameDialogListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

       int position = args.getInt("position");
        //It register onClick from the user using positions
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();


        View view = inflater.inflate(R.layout.editdialog, null);

          builder.setView(view).setTitle("Rename Grocery List").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Rename List", new DialogInterface.OnClickListener() {
            @Override//if the user goes through, the list will be renamed
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
            try {
                throw new IllegalAccessException("Error");
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }
     public interface RenameDialogListener {
        void renameGroceryList(String gListName, int position);
    }
}