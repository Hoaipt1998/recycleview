package com.android30112021.recycleview;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<FoodModel> listfoods;
    private OnItemClickListener onItemClickListener;
    private  int ITEM_TYPE =1;
    private  int LOADINGTYPE =0;
    private boolean isLoading;

    public  FoodAdapter (List<FoodModel> listfoods)
    {
        this.listfoods = listfoods;
    }

    @Override
    public int getItemViewType(int position) {
        if(isLoading)
        {
            if(position == listfoods.size()-1){
                return LOADINGTYPE;
            }
        }
        return ITEM_TYPE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == ITEM_TYPE) {
            view = layoutInflater.inflate(R.layout.item_food, parent, false);
            return new FoodViewHolder(view);
        } else {
            view = layoutInflater.inflate(R.layout.item_loading, parent, false);
            return new loadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        FoodModel foodModel = listfoods.get(position);
//
//        holder.bind(foodModel);
        if(holder.getItemViewType() == ITEM_TYPE){
            FoodModel foodModel = listfoods.get(position);
            ((FoodViewHolder) holder).bind((foodModel));
        }



    }

    @Override
    public int getItemCount() {
        if(listfoods == null || listfoods.size() == 0) {
            return 0;
        }
        return listfoods.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView tvName, tvAddress, tvService, tvDiscount, tvDistance,tvOpen;
        Calendar calendar;
        long currentTime;
        SimpleDateFormat simpleDateFormat;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageViewFood);
            tvName = itemView.findViewById(R.id.textViewName);
            tvAddress = itemView.findViewById(R.id.textViewAddress);
            tvService = itemView.findViewById(R.id.textViewService);
            tvDiscount = itemView.findViewById(R.id.textViewDiscount);
            tvDistance = itemView.findViewById(R.id.textViewDistance);
            tvOpen = itemView.findViewById(R.id.textViewOpen);
            calendar = Calendar.getInstance();
            currentTime = calendar.getTimeInMillis();
            simpleDateFormat = new SimpleDateFormat("HH:mm");



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null){
                        onItemClickListener.onClick(getAdapterPosition());
                    }
                       // Log.d("BBB", listfoods.get(getAdapterPosition()).getName());
                }
            });

        }
        public void bind(FoodModel foodModel)
        {
            img.setImageResource(foodModel.getImage());
            tvName.setText(foodModel.getName());
            tvAddress.setText(foodModel.getAddress());
            String service ="";
            for(ServiceEnum element : foodModel.getArrServiceEnum()){
                service += element.toString()+ "/";
            }
            tvService.setText(service.substring(0,service.length()-1));
            String textDiscount="";
            switch (foodModel.getDiscount().getDiscountSessionEnum()){
                case ALLDAY:
                    textDiscount = "<font color=#A9DEF1>"+foodModel.getDiscount().getDiscountSessionEnum().toString()+"</font> - <font color=#F9C29A>"+foodModel.getDiscount().getName()+"</font>";
                    break;
                case DINNER:
                    textDiscount = "<font color=#B59198>"+foodModel.getDiscount().getDiscountSessionEnum().toString()+"</font> - <font color=#F9C29A>"+foodModel.getDiscount().getName()+"</font>";
                    break;
                case LAUNCH:
                    textDiscount = "<font color=#BDE3BF>"+foodModel.getDiscount().getDiscountSessionEnum().toString()+"</font> - <font color=#F9C29A>"+foodModel.getDiscount().getName()+"</font>";
                    break;
            }
            tvDiscount.setText(Html.fromHtml(textDiscount));
            tvDistance.setText(foodModel.getDistance() + "km");
            if (currentTime >= foodModel.getTimeOpen() && currentTime < foodModel.getTimeClose()){
                tvOpen.setVisibility(View.GONE);
                return;
            }
            tvOpen.setVisibility(View.VISIBLE);
            tvOpen.setText("Đóng cửa \n Đặt bàn vào lúc " + simpleDateFormat.format(foodModel.getTimeOpen()));
        }

    }
    class  loadingViewHolder extends  RecyclerView.ViewHolder{

        public loadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public void addLoading(){
        isLoading = true;
        listfoods.add(null);
    }
    public void removeLoading(){
        isLoading = false;
        int position = listfoods.size()-1;
        listfoods.remove(position);
        notifyItemRemoved(position);
    }

}