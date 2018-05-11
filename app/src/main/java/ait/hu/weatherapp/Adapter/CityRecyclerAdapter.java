package ait.hu.weatherapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import ait.hu.weatherapp.Data.AppDatabase;
import ait.hu.weatherapp.Data.City;
import ait.hu.weatherapp.MainActivity;
import ait.hu.weatherapp.R;
import ait.hu.weatherapp.WeatherDetails;
import ait.hu.weatherapp.touch.CityTouchHelperAdapter;


public class CityRecyclerAdapter
        extends RecyclerView.Adapter<CityRecyclerAdapter.ViewHolder>
        implements CityTouchHelperAdapter {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCityRow;
        public Button btnArrow;

        public ViewHolder(View cityView) {
            super(cityView);
            tvCityRow = cityView.findViewById(R.id.tvCityRow);
            btnArrow = cityView.findViewById(R.id.btnArrow);
        }
    }

    private List<City> cityList;
    private Context context;
    //private int lastPosition = -1;

    public CityRecyclerAdapter(List<City> cities, Context context) {
        cityList = cities;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        View viewRow = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.city_row, parent, false);
        return new ViewHolder(viewRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,
                                 int position) {

        holder.tvCityRow.setText(
                cityList.get(holder.getAdapterPosition()).getCityName());


        holder.btnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), WeatherDetails.class);
                intent.putExtra("cityWeather", cityList.get(holder.getAdapterPosition()).getCityName());
                context.startActivity(intent);
                Log.i("clickDetails", "This is the city getting passed: "+
                        cityList.get(holder.getAdapterPosition()).getCityName());
            }
        });


    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public void addCity(City city) {
        cityList.add(city);
        notifyDataSetChanged();
    }

    @Override
    public void onCityDismiss(final int position) {
        final City cityToDelete = cityList.get(position);
        cityList.remove(cityToDelete);
        notifyItemRemoved(position);
        new Thread() {
            @Override
            public void run() {
                AppDatabase.getAppDatabase(context).cityDAO().delete(
                        cityToDelete);
            }
        }.start();
    }

    @Override
    public void onCityMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(cityList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(cityList, i, i - 1);
            }
        }

        //notifyDataSetChanged();
        notifyItemMoved(fromPosition, toPosition);
    }





}
