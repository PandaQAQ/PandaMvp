
package com.pandaq.appcore.permission.test;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.VoicemailContract;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;


/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :VoiceEmail Permission Tester
 */

public class AddVoiceMailTest extends PermissionTest {

    private ContentResolver mResolver;

    public AddVoiceMailTest(Context context) {
        mResolver = context.getContentResolver();
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public boolean test() throws Throwable {
        try {
            Uri mBaseUri = VoicemailContract.Voicemails.CONTENT_URI;
            ContentValues contentValues = new ContentValues();
            contentValues.put(VoicemailContract.Voicemails.DATE, System.currentTimeMillis());
            contentValues.put(VoicemailContract.Voicemails.NUMBER, "1");
            contentValues.put(VoicemailContract.Voicemails.DURATION, 1);
            contentValues.put(VoicemailContract.Voicemails.SOURCE_PACKAGE, "permission");
            contentValues.put(VoicemailContract.Voicemails.SOURCE_DATA, "permission");
            contentValues.put(VoicemailContract.Voicemails.IS_READ, 0);
            Uri voiceEmailUri = mResolver.insert(mBaseUri, contentValues);
            long id = ContentUris.parseId(voiceEmailUri);
            int count = mResolver.delete(mBaseUri, VoicemailContract.Voicemails._ID + "=?", new String[]{Long.toString(id)});
            return count > 0;
        } catch (Exception e) {
            String message = e.getMessage();
            if (!TextUtils.isEmpty(message)) {
                message = message.toLowerCase();
                return !message.contains("add_voicemail");
            }
            return false;
        }
    }
}