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
package org.nfctools.android.shopping;

import org.json.JSONException;
import org.json.JSONObject;

public class ItemModel {

	private String itemName;
	private boolean checked;

	public ItemModel() {
	}

	public ItemModel(JSONObject json) {
		try {
			itemName = json.getString("itemName");
			checked = json.getBoolean("checked");
		}
		catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public JSONObject toJson() {
		try {
			JSONObject json = new JSONObject();
			json.put("itemName", itemName);
			json.put("checked", checked);
			return json;
		}
		catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
}
