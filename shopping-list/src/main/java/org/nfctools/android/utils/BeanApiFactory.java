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
package org.nfctools.android.utils;

import org.nfctools.android.beam.AndroidBeam;
import org.nfctools.android.beam.AndroidBeamApi15;
import org.nfctools.android.beam.BeamHandler;
import org.nfctools.android.ndef.NdefDiscovered;
import org.nfctools.android.ndef.NdefDiscoveredApi15;
import org.nfctools.android.nfc.ForegroundDispatch;
import org.nfctools.android.nfc.ForegroundDispatchApi15;
import org.openintents.shopping.ui.ShoppingActivity;

import android.app.Activity;
import android.os.Build;

public class BeanApiFactory {

	public static AndroidBeam getAndroidBeam(BeamHandler<?> beamHandler) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
			return new AndroidBeamApi15(beamHandler);
		else
			return AndroidBeam.INSTANCE;
	}

	public static NdefDiscovered getNdefDiscovered(ShoppingActivity shoppingActivity) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
			return new NdefDiscoveredApi15(shoppingActivity);
		else
			return NdefDiscovered.INSTANCE;
	}

	public static ForegroundDispatch getForegroundDispatch(Activity activity) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
			return new ForegroundDispatchApi15(activity);
		else
			return ForegroundDispatch.INSTANCE;
	}
}
