package com.th.samsen.tunyaporn.firebasebellz.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.th.samsen.tunyaporn.firebasebellz.MainActivity;
import com.th.samsen.tunyaporn.firebasebellz.R;
import com.th.samsen.tunyaporn.firebasebellz.utility.MyAlertDialog;


/**
 * Created by TunyapornSamsen on 11/25/2017 AD.
 */

public class RegisterFragment extends Fragment {

    private String TAG = "RegisterFragment";
    private String nameString,emailString, passwordString;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase databaseReference;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Setup Firebase
        setupFirebase();

//        Create Toolbar
        createToolbar();

//        Create Menu Icon
        setHasOptionsMenu(true);

    }

    private void setupFirebase() {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mnuSave:
//                Check Space
                checkSpace();



                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void checkSpace() {
//        Initial View

        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText emailEditText = getView().findViewById(R.id.edtEmail);
        EditText passwordEditText = getView().findViewById(R.id.edtPassword);

        nameString = nameEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        if (nameString.isEmpty() || emailString.isEmpty() || passwordString.isEmpty()) {
            MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
            myAlertDialog.myNormalDialog("Have Space",getString(R.string.sub_register));
        }else{
            updateFirebase();
        }


    }

    private void updateFirebase() {
//        Setup ProgressDialog
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Waiting...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    saveDisplayNameToFirebase();
                    Toast.makeText(getActivity(), "Update Firebase Successful", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().popBackStack();

                } else {
                    MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                    myAlertDialog.myNormalDialog("Update Firebase Fail",task.getException().getMessage());
                }
            }
        });
    }

    private void saveDisplayNameToFirebase() {

//        Get UID Firebase
        firebaseUser = firebaseAuth.getCurrentUser();

        showLog();
    }

    private void showLog() {

        Log.d(TAG, "showLog: UID ==> " + firebaseUser.getUid() + " Email ==> " + firebaseUser.getEmail());

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_save,menu);

        super.onCreateOptionsMenu(menu, inflater);


    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.tbrRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

//        Set Title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(R.string.new_register);

//        Back Icon
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}
