package com.iosd.myapplication;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.iosd.myapplication.qrgenearator.QRGContents;
import com.iosd.myapplication.qrgenearator.QRGEncoder;



public class QE extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityqe);

        String UserId = "916378348558";

        ImageView imvQrCode = (ImageView) findViewById(R.id.img_qr_status);

        Bitmap bitmap = null;
        try {
            bitmap = textToImage(UserId, 500, 500);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        imvQrCode.setImageBitmap(bitmap);
    }

    private Bitmap textToImage(String text, int width, int height) throws WriterException, NullPointerException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.DATA_MATRIX.QR_CODE,
                    width, height, null);
        } catch (IllegalArgumentException Illegalargumentexception) {
            return null;
        }

        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        int colorWhite = 0x00FFFFFF;
        int colorBlack = 0xFCFFFFFF;

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;
            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ? colorBlack : colorWhite;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, width, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

    private class LongOperation extends AsyncTask<Bitmap, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(Bitmap... params) {

            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                @Override
                public void onSuccess(final Account account) {


                    String UserId = "916378348558";

                    ImageView imvQrCode = (ImageView) findViewById(R.id.img_qr_status);

                    Bitmap bitmap = null;
                    try {
                        bitmap = textToImage(UserId, 500, 500);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                    imvQrCode.setImageBitmap(bitmap);

                /*    TextView yourname = findViewById(R.id.your_id);
                    yourname.setText(account.getId());
*/

                }

                @Override
                public void onError(final AccountKitError error) {
                }
            });


            return null;

        }

        @Override
        protected void onPostExecute(Bitmap result) {

            ProgressBar progressBar = findViewById(R.id.prg_bar2);
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onPreExecute() {

            ProgressBar progressBar = findViewById(R.id.prg_bar2);
            progressBar.setVisibility(View.VISIBLE);

        }


    }


}
