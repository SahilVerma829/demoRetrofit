package com.example.ramesh.dempretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ramesh.dempretrofit.ApiInterface.ApiInterrface;
import com.example.ramesh.dempretrofit.ApiManager.ApiClient;
import com.example.ramesh.dempretrofit.ApiResponse.LoginResponse;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity implements Validator.ValidationListener{

    /** ButterKnife Code **/
    @Email
    @BindView(R.id.LEmail)
    EditText LEmail;
    @Password(message = "min 6 char")
    @BindView(R.id.LPassword)
    EditText LPassword;
    @BindView(R.id.LoginBtn)
    Button LoginBtn;
    @BindView(R.id.RegisterBtn)
    Button RegisterBtn;
    /** ButterKnife Code **/
    Validator validator;
    ApiInterrface apiInterrface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);
        validator=new Validator(this);
        validator.setValidationListener(this);

    }
    @OnClick(R.id.LoginBtn)
    void LoginBtn(){
     validator.validate();
    }
    @OnClick(R.id.RegisterBtn)
    void RegisterBtn(){

        Intent a=new Intent(Main2Activity.this,MainActivity.class);
        startActivity(a);
    }


    @Override
    public void onValidationSucceeded() {
        login();

    }

    private void login() {
        apiInterrface= ApiClient.getClient().create(ApiInterrface.class);
        Call<LoginResponse> loginResponseCall=apiInterrface.login("login",LEmail.getText().toString(),
                LPassword.getText().toString());

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.body().getSuccess()==200){
                    Toast.makeText(Main2Activity.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(Main2Activity.this,UpdateActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Main2Activity.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("##message",t.getMessage().toString());
                Toast.makeText(Main2Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);


            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
