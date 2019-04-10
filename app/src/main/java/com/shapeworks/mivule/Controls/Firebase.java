package com.shapeworks.mivule.Controls;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shapeworks.mivule.LoginActivity;
import com.shapeworks.mivule.ProjectsActivity;
import com.shapeworks.mivule.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by KoomaBenjamin on 04/11/2017.
 */

public class Firebase extends AppCompatActivity {

    private FirebaseStorage mStorage = FirebaseStorage.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private StorageReference mStorageReference = mStorage.getReference();
    private ProgressBar progressBar;
    private EditText inputEmail, inputPassword;
    public String mPassword, mEmail;
    private Context context;

    public void signIn(String email, String password, final Context context){



    }

    public void logout(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        mAuth.signOut();
        context.startActivity(intent);
    }
}
