package com.android30112021.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRcvFood;
    SwipeRefreshLayout swipeRefresh;
    FoodAdapter mFoodAdapter;
    ArrayList<FoodModel> mFoodModels;
    boolean mIsLoading;
    boolean mIsLastPage;
    int mTotalPage = 10;
    int mCurrentPage = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefresh = findViewById(R.id.PullAndRefresh);

        mRcvFood = findViewById(R.id.recyclerViewFood);

        mFoodModels = (ArrayList<FoodModel>) FoodModel.getMock();

        mFoodAdapter = new FoodAdapter(mFoodModels);
        mFoodAdapter.addLoading();
        mRcvFood.setHasFixedSize(true);
        mRcvFood.setAdapter(mFoodAdapter);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(MainActivity.this, "Refresh rồi á :V", Toast.LENGTH_LONG).show();
     //           mFoodModels.i();

//                mFoodModels.addAll(FoodModel.getMock());
//                mIsLoading = false;
//                mFoodAdapter.addLoading();
                swipeRefresh.setRefreshing(false);
                //mTotalPage = 10;
//                mCurrentPage = 1;
//                mFoodAdapter.notifyDataSetChanged();

            }
        });


        mFoodAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                mFoodModels.remove(position);
                mFoodAdapter.notifyItemRemoved(position);
                mFoodAdapter.addLoading();
            }
        });

        mRcvFood.addOnScrollListener(new PaginaltionScrollListener((LinearLayoutManager) mRcvFood.getLayoutManager()) {
            @Override
            public boolean isLastPage() {
                return mIsLastPage;
            }

            @Override
            public boolean isLoading() {
                return mIsLoading;
            }

            @Override
            public void loadMore() {
                mIsLoading = true;
                mCurrentPage += 1;
                loadNextPage();
            }
        });
    }




    private void loadNextPage(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mFoodAdapter.removeLoading();
                int position = mFoodModels.size() - 1;
                mFoodModels.addAll(FoodModel.getMock());
                mFoodAdapter.notifyItemRangeChanged(position, mFoodModels.size());
                mIsLoading = false;

                if (mCurrentPage < mTotalPage){
                    mFoodAdapter.addLoading();
                }else{
                    mIsLastPage = true;
                }
            }
        },1500);
    }
}