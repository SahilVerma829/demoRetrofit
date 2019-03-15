package com.example.ramesh.dempretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewActivity extends AppCompatActivity implements Validator.ValidationListener{
    /** ButterKnife Code **/
    @BindView(R.id.UId)
    EditText UId;
    @BindView(R.id.VBtn)
    Button VBtn;
    /** ButterKnife Code **/
    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
        validator=new Validator(this);
        validator.setValidationListener(this);
    }

//    @OnClick({R.id.UId)
//    void`UId(){
//
//    }

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

    }
}
