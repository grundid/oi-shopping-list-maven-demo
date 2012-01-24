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
package org.nfctools.android.nfc;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.tech.Ndef;

public class ForegroundDispatchApi15 extends ForegroundDispatch {

	private Activity activity;
	private NfcAdapter nfcAdapter;

	private PendingIntent pendingIntent;
	private String[][] techListsArrays;
	private IntentFilter[] intentFiltersArrays;

	public ForegroundDispatchApi15(Activity activity) {
		this.activity = activity;
		this.nfcAdapter = NfcAdapter.getDefaultAdapter(activity);
		createForegroundDispatchFilters();
	}

	private void createForegroundDispatchFilters() {
		pendingIntent = PendingIntent.getActivity(activity, 0,
				new Intent(activity, activity.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

		IntentFilter tech = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
		intentFiltersArrays = new IntentFilter[] { tech };

		techListsArrays = new String[][] { new String[] { Ndef.class.getName() } };
	}

	@Override
	public void enableForegroundDispatch() {
		nfcAdapter.enableForegroundDispatch(activity, pendingIntent, intentFiltersArrays, techListsArrays);
	}

	@Override
	public void disableForegroundDispatch() {
		nfcAdapter.disableForegroundDispatch(activity);
	}

}
