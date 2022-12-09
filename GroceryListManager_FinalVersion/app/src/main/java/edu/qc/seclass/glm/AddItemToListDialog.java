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



public class AddItemToListDialog extends AppCompatDialogFragment {
    private EditText addItemQuantityEditText;
    private EditText addItemUnitTypeEditText;
    private AddItemToListDialogListener listener;
    //class that open a dialog from clicking the button to add the item, this allows the user to enter the #quantity/unit to be added
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        int position = args.getInt("position");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.add_item_dialog, null);

        builder.setView(view).setTitle("Add the Item to list").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Add Item to List", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(addItemQuantityEditText.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                String quantityUnit = addItemUnitTypeEditText.getText().toString();
                listener.AddItemToList(quantity, quantityUnit, position);
            }
        });

        addItemQuantityEditText = view.findViewById(R.id.addItemQuantityEditText);
        addItemUnitTypeEditText = view.findViewById(R.id.addItemUnitTypeEditText);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (AddItemToListDialogListener) context;
        } catch (ClassCastException e) {
         }
    }

    public interface AddItemToListDialogListener {
        void AddItemToList(int quantity, String quantityUnit, int position);
    }
}