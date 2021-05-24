package com.example.licenceaid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainAdminActivity extends AppCompatActivity {
    private static final int GALLERY_INTENT_CODE = 1023;
    TextView fullName, email, verifyMsg;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Button logoutBtn;
    Button createAccountBtn, deleteAccountBtn;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_main_admin);

        fullName = findViewById(R.id.mainFullName);
        email = findViewById(R.id.mainEmailAddress);

        //resetPassLocal = findViewById(R.id.resetPasswordLocal);
        createAccountBtn = findViewById(R.id.createAccountBtn);
        deleteAccountBtn = findViewById(R.id.deleteAccountBtn);
        logoutBtn = findViewById(R.id.logoutBtn);



        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();




        //resendCode = findViewById(R.id.resendCode);
        //verifyMsg = findViewById(R.id.verifyMsg);


        userId = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

//        if (!user.isEmailVerified()) {
//            //verifyMsg.setVisibility(View.VISIBLE);
//            //resendCode.setVisibility(View.VISIBLE);
//
//            resendCode.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(final View v) {
//
//                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(v.getContext(), "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.d("tag", "onFailure: Email not sent " + e.getMessage());
//                        }
//                    });
//                }
//            });
//        }
//
//
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(e == null) {
                    if (documentSnapshot.exists()) {

                        fullName.setText(documentSnapshot.getString("fName"));
                        email.setText(documentSnapshot.getString("email"));

                    } else {
                        Log.d("tag", "onEvent: Document does not exist");
                    }
                }

            }
        });


//        resetPassLocal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final EditText resetPassword = new EditText(v.getContext());
//
//                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
//                passwordResetDialog.setTitle("Reset Password ?");
//                passwordResetDialog.setMessage("Enter New Password > 6 Characters long.");
//                passwordResetDialog.setView(resetPassword);
//
//                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // extract the email and send reset link
//                        String newPassword = resetPassword.getText().toString();
//                        user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(MainStudentActivity.this, "Password Reset Successfully.", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(MainStudentActivity.this, "Password Reset Failed.", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
//
//                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // close
//                    }
//                });
//
//                passwordResetDialog.create().show();
//
//            }
//        });
//
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();//logout
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }
}

