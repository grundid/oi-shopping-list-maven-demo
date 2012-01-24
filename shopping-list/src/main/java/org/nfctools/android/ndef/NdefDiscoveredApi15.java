/*
 * Copyright 2012 Adrian Stabiszewski, as@nfctools.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nfctools.android.ndef;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.nfctools.android.shopping.ItemModel;
import org.nfctools.ndef.NdefContext;
import org.nfctools.ndef.mime.TextMimeRecord;
import org.nfctools.ndef.wkt.records.Record;
import org.openintents.shopping.ui.ShoppingActivity;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Parcelable;

public class NdefDiscoveredApi15 extends NdefDiscovered {

	public NdefDiscoveredApi15(ShoppingActivity activity) {
		super(activity);
	}

	@Override
	public boolean canHandle(Intent intent) {
		return intent.getAction().equals(NfcAdapter.ACTION_NDEF_DISCOVERED);
	}

	@Override
	public void handleNdefDiscoveredIntent(Intent intent) {
		NdefMessage[] ndefMessages = getNdefMessages(intent);
		for (NdefMessage ndefMessage : ndefMessages) {
			List<Record> records = NdefContext.getNdefMessageDecoder().decodeToRecords(ndefMessage.toByteArray());
			for (Record record : records) {
				if (record instanceof TextMimeRecord) {
					TextMimeRecord mimeRecord = (TextMimeRecord)record;
					if (mimeRecord.getContentType().equals("text/x-shopping")) {
						handleJsonData(mimeRecord.getContent());
					}
				}
			}
		}
	}

	private void handleJsonData(String content) {
		List<ItemModel> items = new ArrayList<ItemModel>();
		try {
			JSONArray array = new JSONArray(content);
			for (int x = 0; x < array.length(); x++) {
				JSONObject jsonObject = array.getJSONObject(x);
				items.add(new ItemModel(jsonObject));
			}

		}
		catch (JSONException e) {
			throw new RuntimeException(e);
		}
		activity.addShoppingItems(items);
	}

	private NdefMessage[] getNdefMessages(Intent intent) {
		NdefMessage[] msgs = null;
		Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		if (rawMsgs != null) {
			msgs = new NdefMessage[rawMsgs.length];
			for (int i = 0; i < rawMsgs.length; i++) {
				msgs[i] = (NdefMessage)rawMsgs[i];
			}
		}
		else {
			byte[] empty = new byte[] {};
			NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty);
			NdefMessage msg = new NdefMessage(new NdefRecord[] { record });
			msgs = new NdefMessage[] { msg };
		}
		return msgs;
	}

}
