package com.example.proyecto_sanzpansantonio;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Options extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        this.mAuth = FirebaseAuth.getInstance();
    }

    public void showAlertDialogNewName(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.change_user_name);
        final View customLayout = getLayoutInflater().inflate(R.layout.fragment_name_form, null);
        builder.setView(customLayout);
        builder.setPositiveButton(R.string.ACEPT, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView inChangeName = (TextView) customLayout.findViewById(R.id.inChangeName);
                String newName = inChangeName.getText().toString();
                updateProfile(newName);
            }
        });
        builder.setNegativeButton(R.string.CANCEL, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void updateProfile(String newName) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(newName)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "User profile updated.");
                            Toast.makeText(Options.this, R.string.user_profile_updated, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void showAlertDialogNewMail(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.change_user_mail);
        final View customLayout = getLayoutInflater().inflate(R.layout.fragment_mail_form, null);
        builder.setView(customLayout);
        builder.setPositiveButton(R.string.ACEPT, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView inChangeMail = (TextView) customLayout.findViewById(R.id.inChangeMail);
                String newMail = inChangeMail.getText().toString();
                updateEmail(newMail);
            }
        });
        builder.setNegativeButton(R.string.CANCEL, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void updateEmail(String newMail) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updateEmail(newMail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "User email address updated.");
                            Toast.makeText(Options.this, R.string.user_mail_updated, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void showAlertDialogNewPass(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.change_user_password);
        final View customLayout = getLayoutInflater().inflate(R.layout.fragment_password_form, null);
        builder.setView(customLayout);
        builder.setPositiveButton(R.string.ACEPT, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView inChangePass = (TextView) customLayout.findViewById(R.id.inChangePass);
                TextView inChangePassConfirm = (TextView) customLayout.findViewById(R.id.inChangePassConfirm);

                //if (inChangePass.getText().toString() == inChangePassConfirm.getText().toString())
                if (inChangePass.getText().toString().equals(inChangePassConfirm.getText().toString()))
                {
                    updatePassword(inChangePass.getText().toString());
                } else {
                    Toast.makeText(Options.this, "Las contraseñas deben ser iguales", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton(R.string.CANCEL, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void updatePassword(String newPassword) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "User password updated.");
                            Toast.makeText(Options.this, R.string.user_password_updated, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void showAlertDialogDeleteAccount(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.delete_user_account);
        final View customLayout = getLayoutInflater().inflate(R.layout.fragment_delete_account, null);
        builder.setView(customLayout);
        builder.setPositiveButton(R.string.ACEPT, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView tve = (TextView) customLayout.findViewById(R.id.inMailDelete);
                TextView tvp = (TextView) customLayout.findViewById(R.id.inPassDelete);
                String e = tve.getText().toString();
                String p = tvp.getText().toString();

                reauthenticateWithCredential(e, p);
            }
        });
        builder.setNegativeButton(R.string.CANCEL, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void reauthenticateWithCredential(String emailAuth, String passwordAuth) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider.getCredential(emailAuth, passwordAuth);

        // Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //Log.d(TAG, "User re-authenticated.");
                        delete();
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
                            Toast.makeText(Options.this, R.string.user_account_deleted, Toast.LENGTH_LONG).show();
                            resetApp();
                        }
                    }
                });
    }

    public void goIndex(View v) {
        Intent i = new Intent(this, Index.class);
        startActivity(i);
    }

    private void logOut(View view) {
        goLogin();
    }

    private void resetApp() {
        Intent i = new Intent(this, Login.class);
        Login.UID = "";
        startActivity(i);
    }

    private void goLogin() {
        mAuth.signOut();
        Intent i = new Intent(this, Login.class);
        Login.UID = "";
        startActivity(i);
    }
}