package com.example.foodiesta.Profile;

import java.util.List;

public interface OnFireStoreResponse<T> {
    void successInsertionToServer(String success) ;
    void successDownload(List<T> listedDownload , String type) ;
    void failedInsertionToServer(String success) ;
}
