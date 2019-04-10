package com.shapeworks.mivule;


import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class ContentActivity extends AppCompatActivity {


    private FirebaseDatabase mDatabaseRef = FirebaseDatabase.getInstance();
    private FirebaseStorage mStorageRef = FirebaseStorage.getInstance();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);


        final StorageReference tickleStore = mStorageRef.getReference("content/images/kitenderi/02.png");
        final String uuid = mAuth.getCurrentUser().getUid();
        tickleStore.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
            @Override
            public void onSuccess(StorageMetadata storageMetadata) {
                String name = storageMetadata.getName();
//                if(uuid != name ){
//                    tickleStore = mStorageRef.getReference("content/images/ntambi/o3.png");
//                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Uh-oh, an error occurred!
            }
        });

//        InputStream stream = null;
//        try {
//            stream = new FileInputStream(new File("Kooma's Macbook Pro/Untitled/Users/koomabenjamin/Desktop/05.png"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        StorageReference tickleStore = mStorageRef.getReference("tickles").child("store");
//            UploadTask uploadTask = tickleStore.putStream(stream);
//            uploadTask.addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    // Handle unsuccessful uploads
//                }
//            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
//                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                }
//            });


    }
}
