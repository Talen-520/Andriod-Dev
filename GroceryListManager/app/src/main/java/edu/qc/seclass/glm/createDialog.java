package edu.qc.seclass.glm;

import android.annotation.SuppressLint;
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


public class createDialog extends AppCompatDialogFragment {
    private EditText createGroceryListEditText;
    private CreateListDialogListener listener;

    // Creating the CreateListDialog
    //@SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

       View view = inflater.inflate(R.layout.createdialog, null);

       builder.setView(view).setTitle("Create GroceryList").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Create List", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (createGroceryListEditText.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Invalid name, please try entering again", Toast.LENGTH_SHORT).show();
                }
                else {
                    String groceryListName = createGroceryListEditText.getText().toString();
                    listener.createList(groceryListName);
                }
            }
        });

        createGroceryListEditText = view.findViewById(R.id.createGroceryListEditText);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (CreateListDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement CreateGroceryListDialogListener");
        }
    }

    //Custom listener whose logic is defined in MainActivity, will create the list with the given name
    public interface CreateListDialogListener {
        void createList(String gListName);
    }
}