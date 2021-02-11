package com.example.proyecto_sanzpansantonio;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Options extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseUser user;
    TextView inName, inPassword, inMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        this.mAuth = FirebaseAuth.getInstance();
        this.inName  = findViewById(R.id.inChangeName);
        this.inPassword = findViewById(R.id.inChangePass);
        this.inMail = findViewById(R.id.inChangeMail);
    }

    public void getChanges(View view){
        if (inName.getText().toString() != ""){

        }
    }

    private void updateProfile() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(inName.getText().toString())
                //.setPhotoUri(Uri.parse(""))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "User profile updated.");
                            Toast.makeText(Options.this, "Perfil actualizado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updateEmail(inMail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "User email address updated.");
                            Toast.makeText(Options.this, "Mail cambiado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updatePassword() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String newPassword = inPassword.getText().toString();

        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "User password updated.");
                            Toast.makeText(Options.this, "Contraseña cambiada", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void delete() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "User account deleted.");
                        }
                    }
                });
    }
}