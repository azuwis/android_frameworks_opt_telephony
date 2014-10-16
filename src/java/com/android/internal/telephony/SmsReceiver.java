/*
 * Copyright (c) 2012-2014, The Linux Foundation. All rights reserved.
 * Not a Contribution.
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.internal.telephony;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony.Sms.Intents;

public class SmsReceiver extends BroadcastReceiver {
    private final ComponentName componentName;

    public SmsReceiver(ComponentName componentName) {
        this.componentName = componentName;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intents.SMS_RECEIVED_ACTION)) {
            intent.setAction(Intents.SMS_DELIVER_ACTION);
        } else if (intent.getAction().equals(Intents.WAP_PUSH_RECEIVED_ACTION)) {
            intent.setAction(Intents.WAP_PUSH_DELIVER_ACTION);
        }
        intent.setComponent(componentName);

        context.sendBroadcast(intent);
    }

    public ComponentName getComponentName() {
        return componentName;
    }
}
