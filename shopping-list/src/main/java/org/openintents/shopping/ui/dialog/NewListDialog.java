package org.openintents.shopping.ui.dialog;

import org.openintents.shopping.R;

import android.content.Context;

public class NewListDialog extends RenameListDialog {

	
	public NewListDialog(Context context) {
		super(context);
		
		setTitle(R.string.ask_new_list);
	}
	
	public NewListDialog(Context context, DialogActionListener listener) {
		super(context);
		
		setTitle(R.string.ask_new_list);
		setDialogActionListener(listener);
	}
	
}
