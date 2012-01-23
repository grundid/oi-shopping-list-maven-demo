package org.openintents.shopping.automation;

import org.openintents.shopping.library.provider.ShoppingContract;
import org.openintents.shopping.library.provider.ShoppingContract.Contains;
import org.openintents.shopping.library.provider.ShoppingContract.Status;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

public class AutomationActions {

	public static void cleanUpList(Context context, Uri uri) {

		if (uri != null) {
			long id = Integer.parseInt(uri.getLastPathSegment());
			
			// by changing state
			ContentValues values = new ContentValues();
			values.put(Contains.STATUS, Status.REMOVED_FROM_LIST);
			boolean nothingdeleted = context.getContentResolver().update(
					Contains.CONTENT_URI,
					values,
					ShoppingContract.Contains.LIST_ID + " = " + id + " AND "
							+ ShoppingContract.Contains.STATUS + " = "
							+ ShoppingContract.Status.BOUGHT, null) == 0;
		}
	}

}
