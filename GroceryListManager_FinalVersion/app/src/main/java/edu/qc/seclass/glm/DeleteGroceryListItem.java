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



public class DeleteGroceryListItem extends AppCompatDialogFragment {
    private TextView deleteGroceryListItemTextView;
    private DeleteItemViewListener listener;
    //class that allows the interaction for the delete dialog for deleting item from the list after clicking on the button through onClick
    //When item is delete, this functionality is completed and it closes
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        int position = args.getInt("position");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.delete_grocery_item_dialog, null);
        //builidng the dialog from the layout
        builder.setView(view).setTitle("Delete Grocery List Item").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Delete Item", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.deleteGroceryListItem(position);
            }
        });

        deleteGroceryListItemTextView = view.findViewById(R.id.deleteGroceryListItemTextView);
        deleteGroceryListItemTextView.setText("Are you sure you want to delete this item?");

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (DeleteItemViewListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public interface DeleteItemViewListener {
        void deleteGroceryListItem(int position);//get the position of the item being deleted
    }
}
