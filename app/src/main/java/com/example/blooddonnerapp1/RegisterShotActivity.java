package com.example.blooddonnerapp1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.auth.FirebaseAuth;
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


public class RegisterShotActivity extends AppCompatActivity {
    EditText name, phone, address, dob, email, bloodgroup, password;
    ImageView image;
    Button browse, register;
    Uri filepath;
    Bitmap bitmap;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_shot);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Register Here");
        image = findViewById(R.id.image);
        browse = findViewById(R.id.browse);
        register = findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(RegisterShotActivity.this)
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
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    uploadtofirebase();

            }


        });

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

    private void uploadtofirebase() {
            if (filepath != null) {

                ProgressDialog dialog = new ProgressDialog(this);
                dialog.setTitle("File uloader");
                dialog.show();
                name = (EditText) findViewById(R.id.name);
                password = (EditText) findViewById(R.id.password);
                email = (EditText) findViewById(R.id.email);
                phone = (EditText) findViewById(R.id.phone);
                address = (EditText) findViewById(R.id.address);
                dob = (EditText) findViewById(R.id.dob);
                bloodgroup = (EditText) findViewById(R.id.bloodgroup);
                image = (ImageView) findViewById(R.id.image);

                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference uploader1 = storage.getReference("Student");
                StorageReference uploader = uploader1.child(System.currentTimeMillis() + "." + GetFileExtension(filepath));
                uploader.putFile(filepath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                                    @Override
                                    public void onSuccess(Uri uri) {
                                        if (ispassword(password) &&
                                                isFeildEmpty(name)&&
                                                isbloodGroup(bloodgroup)&&
                                                ispasswordEmpty(phone)&&
                                                isEmailvalueEmpty(email)) {
                                        dialog.dismiss();
                                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                                        DatabaseReference obj = db.getReference().child("Student");
                                        dataholder dh = new dataholder(name.getText().toString(), password.getText().toString(), email.getText().toString(), phone.getText().toString(), address.getText().toString(), dob.getText().toString(), bloodgroup.getText().toString(), uri.toString());
                                        obj.push().setValue(dh);


                                        name.setText("");
                                        phone.setText("");
                                        address.setText("");
                                        dob.setText("");
                                        image.setImageResource(R.drawable.ic_launcher_background);
                                        Toast.makeText(RegisterShotActivity.this, "Resut sucess", Toast.LENGTH_SHORT).show();


                                            mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(RegisterShotActivity.this, "Register Sucess" + name, Toast.LENGTH_SHORT).show();

                                                    } else {
                                                        Toast.makeText(RegisterShotActivity.this, "Register fail", Toast.LENGTH_SHORT).show();

                                                    }
                                                }

                                            });
                                        }
                                    }
                                });
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                                float parcent = (100 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                                dialog.setMessage("upladed :" + (int) parcent + "%");
                            }
                        });
            } else {
                Toast.makeText(RegisterShotActivity.this, "Please Select Image and Fill all Empty Field", Toast.LENGTH_LONG).show();
            }
        }

    public  boolean isFeildEmpty(EditText view){
        String value=view.getText().toString();
        if(value.length()>6){
            return  true;
        }else {
            view.setError("Enter Valid Name");
            view.requestFocus();
            return  false;

        }
    }

    public  boolean ispassword(EditText view){
        String value=view.getText().toString();
        if(value.length()>7){
            return  true;
        }else {
            view.setError("Enter Strong Password");
            view.requestFocus();
            return  false;

        }
    }

    public  boolean isbloodGroup(EditText view){
        String value=view.getText().toString();
        if(value.length()>1){
            return  true;
        }else {
            view.setError("Enter Blood Group");
            view.requestFocus();
            return  false;

        }
    }


    public  boolean isEmailvalueEmpty(EditText view){
        String value=view.getText().toString();
        if(Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            return true;
        }else {
            view.setError("Enter Register Email");
            view.requestFocus();
            return false;
        }
    }
    public  boolean ispasswordEmpty(EditText view){
        String value=view.getText().toString();
        if(Patterns.PHONE.matcher(value).matches()){
            return true;
        }else {
            view.setError("Enter valid phone");
            view.requestFocus();
            return false;
        }
    }
}