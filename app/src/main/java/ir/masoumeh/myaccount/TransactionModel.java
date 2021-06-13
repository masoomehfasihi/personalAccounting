package ir.masoumeh.myaccount;

import com.google.firebase.database.Exclude;

public class TransactionModel {
    @Exclude
    private String id;
    private String category , date , description ;
    private long amount ;
    private String email;
}
