package com.example.foodiesta.Model.Details;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DetailsMealResponse {
    @SerializedName("idMeal")
    private int mealId ;
    @SerializedName("strMealThumb")
    private String mealImage ;
    @SerializedName("strMeal")
    private String mealName ;
    @SerializedName("strCategory")
    private String mealCategory;
    @SerializedName("strArea")
    private String mealCountry ;
    @SerializedName("strInstructions")
    private String mealInstructions ;
    @SerializedName("strYoutube")
    private String mealVideo ;
    private String strIngredient1 ;
    private String strIngredient2 ;
    private String strIngredient3 ;
    private String strIngredient4 ;
    private String strIngredient5 ;
    private String strIngredient6 ;
    private String strIngredient7 ;
    private String strIngredient8 ;
    private String strIngredient9 ;
    private String strIngredient10 ;
    private String strIngredient11 ;
    private String strIngredient12 ;
    private String strIngredient13 ;
    private String strIngredient14 ;
    private String strIngredient15 ;
    private String strIngredient16 ;
    private String strIngredient17 ;
    private String strIngredient18 ;
    private String strIngredient19 ;
    private String strIngredient20 ;

    private String strMeasure1 ;
    private String strMeasure2 ;
    private String strMeasure3 ;
    private String strMeasure4 ;
    private String strMeasure5 ;
    private String strMeasure6 ;
    private String strMeasure7 ;
    private String strMeasure8 ;
    private String strMeasure9 ;
    private String strMeasure10 ;
    private String strMeasure11 ;
    private String strMeasure12;
    private String strMeasure13 ;
    private String strMeasure14;
    private String strMeasure15;
    private String strMeasure16;
    private String strMeasure17;
    private String strMeasure18;
    private String strMeasure19;
    private String strMeasure20;

    private List<String> ingreientList = new ArrayList<>() ;
    private List<String> measureList = new ArrayList<>() ;

    public List<String> getIngreientList() {
        setIngreientList(this.ingreientList);
        return ingreientList;
    }

    public void setIngreientList(List<String> ingreientList) {
        if(getStrIngredient1() != null && !getStrIngredient1().isEmpty()) {
            this.ingreientList.add(getStrIngredient1());
        }
        if(getStrIngredient2() != null && !getStrIngredient2().isEmpty()) {
            this.ingreientList.add(getStrIngredient2());
        }
        if(getStrIngredient3() != null && !getStrIngredient3().isEmpty()) {
            this.ingreientList.add(getStrIngredient3());
        }
        if(getStrIngredient4() != null && !getStrIngredient4().isEmpty()) {
            this.ingreientList.add(getStrIngredient4());
        }
        if(getStrIngredient5() != null && !getStrIngredient5().isEmpty()) {
            this.ingreientList.add(getStrIngredient5());
        }
        if(getStrIngredient6() != null && !getStrIngredient6().isEmpty()) {
            this.ingreientList.add(getStrIngredient6());
        }
        if(getStrIngredient7() != null && !getStrIngredient7().isEmpty()) {
            this.ingreientList.add(getStrIngredient7());
        }
        if(getStrIngredient8() != null && !getStrIngredient8().isEmpty()) {
            this.ingreientList.add(getStrIngredient8());
        }
        if(getStrIngredient9() != null && !getStrIngredient9().isEmpty()) {
            this.ingreientList.add(getStrIngredient9());
        }
        if(getStrIngredient10() != null && !getStrIngredient10().isEmpty()) {
            this.ingreientList.add(getStrIngredient10());
        }
        if(getStrIngredient11() != null && !getStrIngredient11().isEmpty()) {
            this.ingreientList.add(getStrIngredient11());
        }
        if(getStrIngredient12() != null && !getStrIngredient12().isEmpty()) {
            this.ingreientList.add(getStrIngredient12());
        }
        if(getStrIngredient13() != null && !getStrIngredient13().isEmpty()) {
            this.ingreientList.add(getStrIngredient13());
        }
        if(getStrIngredient14() != null && !getStrIngredient14().isEmpty()) {
            this.ingreientList.add(getStrIngredient14());
        }
        if(getStrIngredient15() != null && !getStrIngredient15().isEmpty()) {
            this.ingreientList.add(getStrIngredient15());
        }
        if(getStrIngredient16() != null && !getStrIngredient16().isEmpty()) {
            this.ingreientList.add(getStrIngredient16());
        }
        if(getStrIngredient17() != null && !getStrIngredient17().isEmpty()) {
            this.ingreientList.add(getStrIngredient17());
        }
        if(getStrIngredient18() != null && !getStrIngredient18().isEmpty()) {
            this.ingreientList.add(getStrIngredient18());
        }
        if(getStrIngredient19() != null && !getStrIngredient19().isEmpty()) {
            this.ingreientList.add(getStrIngredient19());
        }
        if(getStrIngredient20() != null && !getStrIngredient20().isEmpty()) {
            this.ingreientList.add(getStrIngredient20());
        }
        this.ingreientList = ingreientList;
    }

    public List<String> getMeasureList() {
        if(getStrMeasure1() != null && !getStrIngredient1().isEmpty()) {
            this.measureList.add(getStrMeasure1());
        }
        if(getStrMeasure2() != null && !getStrMeasure2().isEmpty()) {
            this.measureList.add(getStrMeasure2());
        }
        if(getStrMeasure3() != null && !getStrMeasure3().isEmpty()) {
            this.measureList.add(getStrMeasure3());
        }
        if(getStrMeasure4() != null && !getStrMeasure4().isEmpty()) {
            this.measureList.add(getStrMeasure4());
        }
        if(getStrMeasure5() != null && !getStrMeasure5().isEmpty()) {
            this.measureList.add(getStrMeasure2());
        }
        if(getStrMeasure6() != null && !getStrMeasure6().isEmpty()) {
            this.measureList.add(getStrMeasure6());
        }
        if(getStrMeasure7() != null && !getStrMeasure7().isEmpty()) {
            this.measureList.add(getStrMeasure7());
        }
        if(getStrMeasure8() != null && !getStrMeasure8().isEmpty()) {
            this.measureList.add(getStrMeasure8());
        }
        if(getStrMeasure9() != null && !getStrMeasure9().isEmpty()) {
            this.measureList.add(getStrMeasure9());
        }
        if(getStrMeasure10() != null && !getStrMeasure10().isEmpty()) {
            this.measureList.add(getStrMeasure2());
        }
        setMeasureList(this.measureList);
        return measureList;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public void setMeasureList(List<String> measureList) {
        this.measureList = measureList;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }


    public String getMealInstructions() {
        return mealInstructions;
    }

    public void setMealInstructions(String mealInstructions) {
        this.mealInstructions = mealInstructions;
    }

    public String getMealImage() {
        return mealImage;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public String getMealCountry() {
        return mealCountry;
    }

    public void setMealCountry(String mealCountry) {
        this.mealCountry = mealCountry;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }
    public String getStrIngredient11() {
        return strIngredient11;
    }

    public void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public void setStrIngredient12(String strIngredient12) {
        this.strIngredient12 = strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public void setStrIngredient13(String strIngredient13) {
        this.strIngredient13 = strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public void setStrIngredient14(String strIngredient14) {
        this.strIngredient14 = strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public void setStrIngredient15(String strIngredient15) {
        this.strIngredient15 = strIngredient15;
    }

    public String getStrIngredient16() {
        return strIngredient16;
    }

    public void setStrIngredient16(String strIngredient16) {
        this.strIngredient16 = strIngredient16;
    }

    public String getStrIngredient17() {
        return strIngredient17;
    }

    public void setStrIngredient17(String strIngredient17) {
        this.strIngredient17 = strIngredient17;
    }

    public String getStrIngredient18() {
        return strIngredient18;
    }

    public void setStrIngredient18(String strIngredient18) {
        this.strIngredient18 = strIngredient18;
    }

    public String getStrIngredient19() {
        return strIngredient19;
    }

    public void setStrIngredient19(String strIngredient19) {
        this.strIngredient19 = strIngredient19;
    }

    public String getStrIngredient20() {
        return strIngredient20;
    }

    public void setStrIngredient20(String strIngredient20) {
        this.strIngredient20 = strIngredient20;
    }

    public String getStrMeasure1() {
        return strMeasure1;
    }

    public void setStrMeasure1(String strMeasure1) {
        this.strMeasure1 = strMeasure1;
    }

    public String getStrMeasure2() {
        return strMeasure2;
    }

    public void setStrMeasure2(String strMeasure2) {
        this.strMeasure2 = strMeasure2;
    }

    public String getStrMeasure3() {
        return strMeasure3;
    }

    public void setStrMeasure3(String strMeasure3) {
        this.strMeasure3 = strMeasure3;
    }

    public String getStrMeasure4() {
        return strMeasure4;
    }

    public void setStrMeasure4(String strMeasure4) {
        this.strMeasure4 = strMeasure4;
    }

    public String getStrMeasure5() {
        return strMeasure5;
    }

    public void setStrMeasure5(String strMeasure5) {
        this.strMeasure5 = strMeasure5;
    }

    public String getStrMeasure6() {
        return strMeasure6;
    }

    public void setStrMeasure6(String strMeasure6) {
        this.strMeasure6 = strMeasure6;
    }

    public String getStrMeasure7() {
        return strMeasure7;
    }

    public void setStrMeasure7(String strMeasure7) {
        this.strMeasure7 = strMeasure7;
    }

    public String getStrMeasure8() {
        return strMeasure8;
    }

    public void setStrMeasure8(String strMeasure8) {
        this.strMeasure8 = strMeasure8;
    }

    public String getStrMeasure9() {
        return strMeasure9;
    }

    public void setStrMeasure9(String strMeasure9) {
        this.strMeasure9 = strMeasure9;
    }

    public String getStrMeasure10() {
        return strMeasure10;
    }

    public void setStrMeasure10(String strMeasure10) {
        this.strMeasure10 = strMeasure10;
    }

    public String getStrMeasure11() {
        return strMeasure11;
    }

    public void setStrMeasure11(String strMeasure11) {
        this.strMeasure11 = strMeasure11;
    }

    public String getStrMeasure12() {
        return strMeasure12;
    }

    public void setStrMeasure12(String strMeasure12) {
        this.strMeasure12 = strMeasure12;
    }

    public String getStrMeasure13() {
        return strMeasure13;
    }

    public void setStrMeasure13(String strMeasure13) {
        this.strMeasure13 = strMeasure13;
    }

    public String getStrMeasure14() {
        return strMeasure14;
    }

    public void setStrMeasure14(String strMeasure14) {
        this.strMeasure14 = strMeasure14;
    }

    public String getStrMeasure15() {
        return strMeasure15;
    }

    public void setStrMeasure15(String strMeasure15) {
        this.strMeasure15 = strMeasure15;
    }

    public String getStrMeasure16() {
        return strMeasure16;
    }

    public void setStrMeasure16(String strMeasure16) {
        this.strMeasure16 = strMeasure16;
    }

    public String getStrMeasure17() {
        return strMeasure17;
    }

    public void setStrMeasure17(String strMeasure17) {
        this.strMeasure17 = strMeasure17;
    }

    public String getStrMeasure18() {
        return strMeasure18;
    }

    public void setStrMeasure18(String strMeasure18) {
        this.strMeasure18 = strMeasure18;
    }

    public String getStrMeasure19() {
        return strMeasure19;
    }

    public void setStrMeasure19(String strMeasure19) {
        this.strMeasure19 = strMeasure19;
    }

    public String getStrMeasure20() {
        return strMeasure20;
    }

    public void setStrMeasure20(String strMeasure20) {
        this.strMeasure20 = strMeasure20;
    }

    public String getMealVideo() {
        return mealVideo;
    }

    public void setMealVideo(String mealVideo) {
        this.mealVideo = mealVideo;
    }
}
