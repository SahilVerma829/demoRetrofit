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
import com.example.ramesh.dempretrofit.ApiResponse.UpdateResponse;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity implements Validator.ValidationListener{


    /** ButterKnife Code **/
//    @NotEmpty
//    @BindView(R.id.uid)
//    EditText uid;
    @NotEmpty
    @BindView(R.id.name)
    EditText name;
    @NotEmpty
    @BindView(R.id.lname)
    EditText lName;
    @Email
    @BindView(R.id.email)
    EditText email;
    @NotEmpty
    @BindView(R.id.dob)
    EditText dob;

    @BindView(R.id.gender)
    EditText gender;
    @Password(message = "min 6 char")
    @BindView(R.id.password)
    EditText password;
    @Length(max = 10,min = 10)
    @BindView(R.id.contect)
    EditText contect;
    @BindView(R.id.btn)
    Button btn;
    /** ButterKnife Code **/

    Validator validator;
    ApiInterrface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);
        validator=new Validator(this);
        validator.setValidationListener(this);




    }

    @OnClick(R.id.btn)
    void btn(){
        validator.validate();

    }
    @Override
    public void onValidationSucceeded() {
        update();
    }

    private void update() {
        apiInterface= ApiClient.createService(ApiInterrface.class,"");
        Call<UpdateResponse> updateResponseCall=apiInterface.update("update",
               // uid.getText().toString(),
                name.getText().toString(),
                lName.getText().toString(),
                email.getText().toString(),
                dob.getText().toString(),
                gender.getText().toString(),
                password.getText().toString(),
                contect.getText().toString());

        updateResponseCall.enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                if (response.body().getStatus() == 1) {
                    Toast.makeText(UpdateActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateActivity.this, ViewActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(UpdateActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                Log.e("## Message", t.getMessage().toString());
                Toast.makeText(UpdateActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

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
