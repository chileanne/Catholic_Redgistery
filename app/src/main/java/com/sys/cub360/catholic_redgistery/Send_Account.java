package com.sys.cub360.catholic_redgistery;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sys.cub360.catholic_redgistery.Read.PickActivity;

public class Send_Account extends AppCompatActivity {
    private EditText name,phone,email,parish;
    private CardView mc;
    private CardView mt;
    private DatabaseReference mdatabaseref;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send__account);

        name=findViewById(R.id.men);
        phone=findViewById(R.id.phoneno);
        email=findViewById(R.id.email);
        parish=findViewById(R.id.parris);
        mc=findViewById(R.id.submit);
        mt=findViewById(R.id.gotologin);

        //intalize firebaseauthobject
        firebaseAuth = FirebaseAuth.getInstance();
        //check if the user is currently logged in if yes go to the profile ativity


        if (firebaseAuth.getCurrentUser() != null) {
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






        }

        mt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login_Screen.class));
            }
        });

mc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        work();
    }
});
    }

    private void work() {
        String emails = email.getText().toString().trim();
        String phones = phone.getText().toString().trim();
        String parishs = parish.getText().toString().trim();
        String names=name.getText().toString().trim();


        if(!TextUtils.isEmpty(emails) && !TextUtils.isEmpty(phones) && !TextUtils.isEmpty(parishs) && !TextUtils.isEmpty(names)){
            String[] TO= {"Opendataserver@gmail.com"};
            Intent emailintent=new Intent(Intent.ACTION_SEND);
            emailintent.setData(Uri.parse("mailto:"));
            emailintent.setType("text/plain");
            emailintent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailintent.putExtra(Intent.EXTRA_SUBJECT, "Account Creation" );
            emailintent.putExtra(Intent.EXTRA_TEXT, "email:"+emails +"\n"+ "name:" +names +"\n" +"phoneno:"+ phones+"\n"+ "Message:" + parishs );

            try {
                startActivity(Intent.createChooser(emailintent,"Select Your Email..."));
               finish();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"you don't have any email client",Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Empty Fields",Toast.LENGTH_LONG).show();
        }

    }
}
