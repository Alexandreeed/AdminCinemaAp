package com.example.phoneauth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class StorageActivity extends AppCompatActivity {
    private ImageView imageView;
    private Uri imageUrl;
    FirebaseStorage storage;
    // Create a storage reference from our app
    StorageReference storageRef;
    FirebaseFirestore db;
    EditText name,time,price,row,seat,zal;
    Button button,addtime;

    TextView t;

    String TAG = "TAG";

    Movie LAmovie, movie;

    DocumentSnapshot document;
    int n = 0;
    Map<String, Object> data;
    ArrayList<String> joke= new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        imageView = findViewById(R.id.imageView);
        addtime = findViewById(R.id.addtime);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosepicture();
            }
        });
        storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        storageRef = storage.getReference();
        db = FirebaseFirestore.getInstance();
        name = findViewById(R.id.name);

        t = findViewById(R.id.text24);

        time = findViewById(R.id.time);
        price = findViewById(R.id.price);
        zal = findViewById(R.id.zal);
        button = findViewById(R.id.send);


        addtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = time.getText().toString();
                joke.add(m);
                time.setText("");
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nam = name.getText().toString();
                String pric = price.getText().toString();
                String za = zal.getText().toString();
                movie = new Movie(nam, joke,pric,za);
                DocumentReference docRef = db.collection("movies").document("LA");
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        db.collection("movies").document("LA5").set(Objects.requireNonNull(documentSnapshot.getData()));
                        db.collection("movies").document("LA4").set(Objects.requireNonNull(documentSnapshot.getData()));
                        db.collection("movies").document("LA3").set(Objects.requireNonNull(documentSnapshot.getData()));
                        db.collection("movies").document("LA2").set(Objects.requireNonNull(documentSnapshot.getData()));
                        db.collection("movies").document("LA").set(Objects.requireNonNull(movie));
                    }
                });

            }
        });
    }

    private void choosepicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUrl = data.getData();
            uploadpicture();
        }
    }

    private void uploadpicture() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Upload image...");
        progressDialog.show();

        final String randomKey= UUID.randomUUID().toString();
        StorageReference riversRef = storageRef.child("images/" + randomKey);
        riversRef.putFile(imageUrl)

// Register observers to listen for when the download is done or if it fails
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Snackbar.make(findViewById(android.R.id.content),"Image uploaded failed",Snackbar.LENGTH_LONG).show();
                // Handle unsuccessful uploads
                progressDialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                Toast.makeText(StorageActivity.this, "Upload sucsesfull", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progressPercent =(100.00 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                        progressDialog.setMessage("Percantage "+ (int)progressPercent+"%");
                    }
                });
    }
}