package com.example.foodiesta.Model.Registration;

public class ChefRegistrationPojo {
    private String chefName , chefEmail , password ,confirmPassword  ;

    public ChefRegistrationPojo(String chefName, String chefEmail, String password, String confirmPassword) {
        this.chefName = chefName;
        this.chefEmail = chefEmail;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getChefName() {
        return chefName;
    }

    public void setChefName(String chefName) {
        this.chefName = chefName;
    }

    public String getChefEmail() {
        return chefEmail;
    }

    public void setChefEmail(String chefEmail) {
        this.chefEmail = chefEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
