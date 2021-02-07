package com.example.blooddonnerapp1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password, email, phoneno, address, dob, bloodgroup;
    Button cancle, register;
    ImageView image;
    private FirebaseAuth mAuth;
    private StorageReference mstorageRef;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = db.getReference();

        //
        mstorageRef = FirebaseStorage.getInstance().getReference().child("Images");
        try {
            final File localfile = File.createTempFile("1609835184086", "jpeg");
            mstorageRef.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(RegisterActivity.this, " Picture Retrive", Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            (image = findViewById(R.id.iamge1)
                            ).setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterActivity.this, " Picture error", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
//

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        phoneno = findViewById(R.id.phoneno);
        address = findViewById(R.id.Address);
        dob = findViewById(R.id.DOB);
        image = findViewById(R.id.image);
        cancle = findViewById(R.id.cancle);
        register = findViewById(R.id.register);
        bloodgroup = findViewById(R.id.bloodgroup);

        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotification("New User Registered");
                if (isFeildEmpty(username) &&
                        isFeildEmpty(password) &&
                        isEmailvalueEmpty(email) &&
                        isFeildEmpty(address) &&
                        isFeildEmpty(phoneno) &&
                        isFeildEmpty(dob)) {
                    String usernamevalue = username.getText().toString();
                    String passwordvalue = password.getText().toString();
                    String emailvalue = email.getText().toString();
                    String phonenovalue = phoneno.getText().toString();
                    String dobvalue = dob.getText().toString();
                    String addressvalue = address.getText().toString();
                    String bloodgroupvalue = bloodgroup.getText().toString();
                    upload();
                    ModelClass data = new ModelClass(usernamevalue, passwordvalue, emailvalue, phonenovalue, dobvalue, addressvalue, bloodgroupvalue);
                    databaseReference.child("User").push().setValue(data);

                    mAuth.createUserWithEmailAndPassword(emailvalue, passwordvalue).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Register Sucess", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(RegisterActivity.this, "Register fail", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }

            }

            private void addNotification(String messageBody) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                PendingIntent intent1 = PendingIntent.getActivity(RegisterActivity.this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
                String channelId = "commandoit";
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(RegisterActivity.this, channelId)
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
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            }
        });


    }

    Bitmap bitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bitmap);
        }
    }

    public byte[] getBlob(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        byte[] bytes = bos.toByteArray();
        return bytes;
    }

    public static Bitmap getBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


    public byte[] upload() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        DatabaseReference imgref = databaseReference.child("image/");
        byte[] bytes = bos.toByteArray();
        imgref.setValue(image);
        return bytes;
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

    public boolean isEmailvalueEmpty(EditText view) {
        String value = view.getText().toString();
        if (Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            return true;
        } else {
            view.setError("Enter Valid Email");
            view.requestFocus();
            return false;
        }
    }
}