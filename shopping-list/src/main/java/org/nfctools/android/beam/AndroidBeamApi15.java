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

package org.nfctools.android.beam;

import java.util.List;

import org.nfctools.ndef.NdefContext;
import org.nfctools.ndef.ext.ExternalType;
import org.nfctools.ndef.wkt.records.Record;

import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.util.Log;

public class AndroidBeamApi15 extends AndroidBeam implements CreateNdefMessageCallback {

	private Activity activity;
	private NfcAdapter nfcAdapter;
	private BeamHandler<?> beamHandler;

	public AndroidBeamApi15(BeamHandler<?> beamHandler) {
		this.beamHandler = beamHandler;
		this.activity = beamHandler.getActivity();
		nfcAdapter = NfcAdapter.getDefaultAdapter(activity);
	}

	@Override
	public NdefMessage createNdefMessage(NfcEvent event) {
		List<Record> message = beamHandler.createNdefMessage();
		return createNdefMessageWithAAR(message);
	}

	protected NdefMessage createNdefMessageWithAAR(List<Record> records) {
		records.add(new ExternalType("android.com:pkg", "org.openintents.shopping"));
		byte[] ndefBytes = NdefContext.getNdefMessageEncoder().encode(records);

		try {
			NdefMessage ndefMessage = new NdefMessage(ndefBytes);
			return ndefMessage;
		}
		catch (Exception e) {
			Log.wtf("SHOPPING", e.getMessage(), e);
			return null;
		}
	}

	@Override
	public void activateBeam() {
		nfcAdapter.setNdefPushMessageCallback(this, activity);
	}

	@Override
	public void deactivateBeam() {
		nfcAdapter.setNdefPushMessageCallback(null, activity);
	}

}
