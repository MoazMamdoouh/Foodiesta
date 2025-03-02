package com.example.foodiesta.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodiesta.Search.Country.ListOfCountriesSearch;
import com.example.foodiesta.R;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private Context context ;
    private List<ListOfCountriesSearch> listOfCountriesSearches ;
    private OnSearchItemClicked onSearchItemClicked ;

    public CountryAdapter(Context context, List<ListOfCountriesSearch> listOfCountriesSearches , OnSearchItemClicked onSearchItemClicked) {
        this.context = context;
        this.listOfCountriesSearches = listOfCountriesSearches;
        this.onSearchItemClicked = onSearchItemClicked ;
    }

    void setCountyList(List<ListOfCountriesSearch> listOfCountriesSearches){
        this.listOfCountriesSearches = listOfCountriesSearches ;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_search_show_list , parent , false) ;
        SearchViewHolder searchViewHolder = new SearchViewHolder(view) ;

        return searchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        if (listOfCountriesSearches != null && listOfCountriesSearches.get(position).getCountryName() != null) {
            String countryCode = getCountryCode(listOfCountriesSearches.get(position).getCountryName());
            String flagUrl = "https://www.themealdb.com/images/icons/flags/big/64/" + countryCode + ".png";
            holder.mealName.setText(listOfCountriesSearches.get(position).getCountryName());
            Glide.with(context)
                    .load(flagUrl)
                    .placeholder(R.drawable.food)
                    .error(R.drawable.fooderror)
                    .into(holder.searchMealImage);
            holder.searchCardView.setOnClickListener(clicked ->{
                onSearchItemClicked.onFilterItemClicked(listOfCountriesSearches.get(position).getCountryName() , "country");
            });
        }
    }

    @Override
    public int getItemCount() {
        return listOfCountriesSearches.size();
    }

    private String getCountryCode(String countryName) {
        if (countryName == null) return "xx";

        countryName = countryName.toLowerCase();

        if (countryName.equals("american")) return "us";
        else if (countryName.equals("british")) return "gb";
        else if (countryName.equals("canadian")) return "ca";
        else if (countryName.equals("chinese")) return "cn";
        else if (countryName.equals("croatian")) return "hr";
        else if (countryName.equals("dutch")) return "nl";
        else if (countryName.equals("egyptian")) return "eg";
        else if (countryName.equals("french")) return "fr";
        else if (countryName.equals("greek")) return "gr";
        else if (countryName.equals("indian")) return "in";
        else if (countryName.equals("irish")) return "ie";
        else if (countryName.equals("italian")) return "it";
        else if (countryName.equals("jamaican")) return "jm";
        else if (countryName.equals("japanese")) return "jp";
        else if (countryName.equals("kenyan")) return "ke";
        else if (countryName.equals("malaysian")) return "my";
        else if (countryName.equals("mexican")) return "mx";
        else if (countryName.equals("moroccan")) return "ma";
        else if (countryName.equals("polish")) return "pl";
        else if (countryName.equals("portuguese")) return "pt";
        else if (countryName.equals("russian")) return "ru";
        else if (countryName.equals("spanish")) return "es";
        else if (countryName.equals("thai")) return "th";
        else if (countryName.equals("tunisian")) return "tn";
        else if (countryName.equals("turkish")) return "tr";
        else if (countryName.equals("vietnamese")) return "vn";
        else return "x";
    }
}
