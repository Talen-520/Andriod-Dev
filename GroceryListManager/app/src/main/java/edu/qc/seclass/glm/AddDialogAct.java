package edu.qc.seclass.glm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.grocerylistmanager.R;

public class AddDialogAct extends AppCompatDialogFragment {

        private EditText itemType;
        private EditText itemname;
        private EditText itemQuantity;
        private searchDialogActListener listener;

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.layout_searchitemdialog,null);

            builder.setView(view).setTitle("Adding an Item");
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String iT = itemType.getText().toString();
                    String iN = itemname.getText().toString();
                    String iQ = itemQuantity.getText().toString();
                    listener.applyTexts(iT, iN, iQ);
                    dialog.dismiss();
                }
            });

            itemType = view.findViewById(R.id.itemType);
            itemname = view.findViewById(R.id.itemname);
            itemQuantity = view.findViewById(R.id.itemQuantity);

            return builder.create();
        }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (searchDialogActListener) context;
        }catch( ClassCastException e ){
            throw new ClassCastException(context.toString() + " must implement searchDialogActListener. ");
        }
    }

    public interface searchDialogActListener{
             void applyTexts(String itemType, String itemName, String ItemQuantity);
        }

}
