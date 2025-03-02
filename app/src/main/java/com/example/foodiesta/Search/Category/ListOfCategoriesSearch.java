package com.example.foodiesta.Search.Category;

import com.google.gson.annotations.SerializedName;

public class ListOfCategoriesSearch {

    @SerializedName("strCategory")
    private String categoryName ;
    @SerializedName("strCategoryThumb")
    private String categoryImage ;
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }
}
