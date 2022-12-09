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
import androidx.appcompat.app.AppCompatDialogFragment;



public class EditQuantityUnit extends AppCompatDialogFragment {

    private EditText ItemQuantityEditText;
    private EditText ItemUnitEditText;
    private EditQuantityUnitListener listener;
//this class create a dialog with editText that allows the user to interactive with the open dialog through onClick
// which allows edit the quantity and unit of the item.
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();

        int position = args.getInt("position");
        String quantity = Integer.toString(args.getInt("quantity"));
        String quantityUnit = args.getString("quantityUnit");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();


        View view = inflater.inflate(R.layout.edit_quantity_and_unit_dialog, null);

        builder.setView(view).setTitle("Edit Item Quantity/Unit").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int quantity = 0;
                        try {
                            quantity = Integer.parseInt(ItemQuantityEditText.getText().toString());
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Default to 0.", Toast.LENGTH_SHORT).show();
                        }

                        String unit = ItemUnitEditText.getText().toString();

                        listener.ItemQuantityAndUnit(position, quantity, unit);
                    }
                });
        ItemQuantityEditText = view.findViewById(R.id.changeItemQuantityEditText);
        ItemQuantityEditText.setText(quantity);
        ItemUnitEditText = view.findViewById(R.id.changeItemUnitEditText);
        ItemUnitEditText.setText(quantityUnit);

        ItemUnitEditText = view.findViewById(R.id.changeItemUnitEditText);
        return builder.create();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (EditQuantityUnitListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public interface EditQuantityUnitListener {

        void ItemQuantityAndUnit(int position, int quantity, String unit);

    }
}
