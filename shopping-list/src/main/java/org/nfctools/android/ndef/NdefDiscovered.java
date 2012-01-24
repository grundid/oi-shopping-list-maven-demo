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

import org.nfctools.android.utils.AbstractActivityHolder;
import org.openintents.shopping.ui.ShoppingActivity;

import android.content.Intent;

public class NdefDiscovered extends AbstractActivityHolder<ShoppingActivity> {

	public static final NdefDiscovered INSTANCE = new NdefDiscovered(null);

	public NdefDiscovered(ShoppingActivity activity) {
		super(activity);
	}

	public boolean canHandle(Intent intent) {
		return false;
	}

	public void handleNdefDiscoveredIntent(Intent intent) {

	}
}
