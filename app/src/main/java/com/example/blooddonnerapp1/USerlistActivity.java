package com.example.blooddonnerapp1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;

public class USerlistActivity extends AppCompatActivity {
    Button cancle, find;
    EditText name, phone, address, location, bloodgroup;
    Uri filepath;
    Bitmap bitmap;
  ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_serlist);
        cancle = findViewById(R.id.cancle);
        find = findViewById(R.id.find);
        name= findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        location = findViewById(R.id.location);
        bloodgroup = findViewById(R.id.bloodgroup);
        image = findViewById(R.id.image_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Find Donner");


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = db.getReference();
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(USerlistActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Select image file"), 111);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });
        find.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isFeildEmpty(name) &&
                        isFeildEmpty(phone) &&
                        isFeildEmpty(address) &&
                        isFeildEmpty(location) &&
                        isFeildEmpty(bloodgroup)) {
                    addNotification("Blood Needer");
                    String namevalue = name.getText().toString();
                    String phonevalue = phone.getText().toString();
                    String addressvalue = address.getText().toString();
                    String locationvalue = location.getText().toString();
                    String bloodgroupvalue = bloodgroup.getText().toString();

                    Model data = new Model(namevalue, phonevalue, addressvalue, locationvalue, bloodgroupvalue);
                    Toast.makeText(USerlistActivity.this, "Request Sucess" + namevalue, Toast.LENGTH_SHORT).show();

                    databaseReference.child("Patent").push().setValue(data);
                }
            }

            private void addNotification(String messageBody) {
                Intent intent = new Intent(USerlistActivity.this, PatenetinfoActivity.class);
                PendingIntent intent1 = PendingIntent.getActivity(USerlistActivity.this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
                String channelId = "commandoit";
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(USerlistActivity.this, channelId)
                        .setContentText(messageBody)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setAutoCancel(true)
                        .setContentIntent(intent1);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(channelId, "COMMANDOIT", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel);
                }
                notificationManager.notify(123, notificationBuilder.build());
            }

        });


    }

    public boolean isFeildEmpty(EditText view) {
        String value = view.getText().toString();
        if (value.length() > 0) {
            return true;
        } else {
            view.setError("Enter Value");
            view.requestFocus();
            return false;

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 111 && resultCode == RESULT_OK) {

            filepath = data.getData();
            try {
                InputStream inp = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inp);
                image.setImageBitmap(bitmap);

            } catch (Exception ex) {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }
}