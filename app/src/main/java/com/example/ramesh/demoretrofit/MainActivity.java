package com.example.ramesh.demoretrofit;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramesh.demoretrofit.ApiInterface.ApiInterrface;
import com.example.ramesh.demoretrofit.ApiManager.ApiClient;
import com.example.ramesh.demoretrofit.ApiResponse.Responser;
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

import static com.example.ramesh.demoretrofit.ApiManager.ApiClient.createService;


public class MainActivity extends AppCompatActivity implements Validator.ValidationListener {

    /** ButterKnife Code **/
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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        validator = new Validator(this);
        validator.setValidationListener(this);

    }

    @OnClick(R.id.btn)
    void btn(){
        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {
    register();
    }

    private void register() {
        apiInterface = ApiClient.createService(ApiInterrface.class,"");
        Call<Responser> registerResponseCall = apiInterface.registration("register", name.getText().toString(),
                lName.getText().toString(),
                email.getText().toString(),
                dob.getText().toString(),
                gender.getText().toString(),
                password.getText().toString(),
                contect.getText().toString());


        registerResponseCall.enqueue(new Callback<Responser>() {
            @Override
            public void onResponse(Call<Responser> call, retrofit2.Response<Responser> response) {
                if (response.body().getStatus() == 1) {
//                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(MainActivity.this, ViewActivity.class);
//                    startActivity(intent);
//                    finish();

                    // Create customdialog dialog object
                    final Dialog dialog = new Dialog(MainActivity.this);
                    // Include dialog.xml file
                    dialog.setContentView(R.layout.customdialog);
                    // Set dialog title
                    dialog.setTitle("Custom Dialog");

                    // set values for customdialog dialog components - text, image and button
                    TextView text = (TextView) dialog.findViewById(R.id.textDialog);
                    text.setText("Registrion is Complated");
                    ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
                    image.setImageResource(R.drawable.verified);

                    dialog.show();

                    Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
                    // if decline button is clicked, close the customdialog dialog
                    declineButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Close dialog
//                            dialog.dismiss();
                            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                            startActivity(intent);
                        }
                    });

                }




         else {
                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<Responser> call, Throwable t) {
                Log.e("## Message", t.getMessage().toString());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();


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