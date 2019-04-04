package com.example.ramesh.dempretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ramesh.dempretrofit.ApiInterface.ApiInterrface;
import com.example.ramesh.dempretrofit.ApiManager.ApiClient;
import com.example.ramesh.dempretrofit.ApiResponse.ViewResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewActivity extends AppCompatActivity {
    /** ButterKnife Code **/
    @BindView(R.id.RecylceView)
    android.support.v7.widget.RecyclerView RecylceView;
    /** ButterKnife Code **/
    ApiInterrface apiInterrface;
    ViewAdapter viewAdapter;
    List<ViewResponse.List1> viewResponses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
        getView();

    }

    private void getView() {
        apiInterrface = ApiClient.createService(ApiInterrface.class,"");
        Call<ViewResponse> viewResponseCall=apiInterrface.view("viewusers" );
        viewResponseCall.enqueue(new Callback<ViewResponse>() {

            @Override
            public void onResponse(retrofit2.Call<ViewResponse> call, Response<ViewResponse> response) {
                if (response.body().getStatus() == 200){
                   viewResponses =response.body().getList1();
                    RecylceView.setHasFixedSize(true);
                    RecylceView.setLayoutManager(new LinearLayoutManager(ViewActivity.this));
                    viewAdapter=new ViewAdapter(ViewActivity.this, (ArrayList<ViewResponse.List1>) viewResponses);
                   RecylceView.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ViewResponse> call, Throwable t) {
                Toast.makeText(ViewActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("##",t.getMessage());
            }
        });

    }

}



