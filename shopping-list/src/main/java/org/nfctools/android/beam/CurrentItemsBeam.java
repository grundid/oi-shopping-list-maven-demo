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

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.nfctools.android.shopping.ItemModel;
import org.nfctools.ndef.mime.TextMimeRecord;
import org.nfctools.ndef.wkt.records.Record;
import org.openintents.shopping.ui.ShoppingActivity;

public class CurrentItemsBeam extends BeamHandler<ShoppingActivity> {

	public CurrentItemsBeam(ShoppingActivity activity) {
		super(activity);
	}

	@Override
	public List<Record> createNdefMessage() {
		JSONArray array = new JSONArray();
		List<ItemModel> shoppingItems = activity.getShoppingItems();
		for (ItemModel itemModel : shoppingItems) {
			array.put(itemModel.toJson());
		}
		List<Record> list = new ArrayList<Record>();
		list.add(new TextMimeRecord("text/x-shopping", array.toString()));
		return list;
	}

}
