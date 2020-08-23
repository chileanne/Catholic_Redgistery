package com.sys.cub360.catholic_redgistery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sys.cub360.catholic_redgistery.Read.PickActivity;
import com.sys.cub360.catholic_redgistery.Read.Read_province;

public class Login_Screen extends AppCompatActivity {
    private EditText mail,pass;
    private CardView mc;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog mp;
    private DatabaseReference mdatabaseref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__screen);

        mp=new ProgressDialog(this);

        mail=findViewById(R.id.logmail);
        pass=findViewById(R.id.logpass);
        mc=findViewById(R.id.submitd);



        //initialize firebaseauth
        firebaseAuth =FirebaseAuth.getInstance();

        mc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mmail=mail.getText().toString().trim();
                String passd=pass.getText().toString().trim();
                if(!TextUtils.isEmpty(mmail)&& !TextUtils.isEmpty(passd)){

                    mp.setMessage("Loading");
                    mp.setCanceledOnTouchOutside(false);
                    mp.setCancelable(false);
                    mp.show();

                    loginuser(mmail,passd);

                }
            }
        });
    }

    private void loginuser(String mmail, String passd) {
        firebaseAuth.signInWithEmailAndPassword(mmail, passd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser currentuser= FirebaseAuth.getInstance().getCurrentUser();
                    String uid=currentuser.getUid();

                    //instatiating the databaseref and ading a users and curent user uid node to the rootview
                    mdatabaseref= FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                    mdatabaseref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String type=dataSnapshot.child("type").getValue().toString();
                            String userpa=dataSnapshot.child("parish").getValue().toString();
                            if(type.equals("Parish")){

                                Intent id = new Intent(getApplicationContext(), User_Form.class);
                                id.putExtra("userparish", userpa);
                                finish();
                                startActivity(id);

                            }else if(type.equals("Denary")){
                                Intent id = new Intent(getApplicationContext(), PickActivity.class);
                                finish();
                                startActivity(id);

                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }else{
                    mp.dismiss();
                    String error=task.getException().getMessage().toString();
                    Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
