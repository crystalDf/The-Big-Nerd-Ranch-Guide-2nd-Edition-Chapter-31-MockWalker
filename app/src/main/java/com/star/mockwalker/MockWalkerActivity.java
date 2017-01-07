package com.star.mockwalker;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MockWalkerActivity extends SingleFragmentActivity {

    private static final int REQUEST_ERROR = 0;

    @Override
    protected Fragment createFragment() {
        return MockWalkerFragment.newInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();

        int errorCode = googleApiAvailability.isGooglePlayServicesAvailable(this);

        if (errorCode != ConnectionResult.SUCCESS) {
            Dialog errorDialog = googleApiAvailability.getErrorDialog(this, errorCode,
                    REQUEST_ERROR, new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                                // Leave if services are unavailable.
                                finish();
                            }
                        });

            errorDialog.show();
        }
    }

}
