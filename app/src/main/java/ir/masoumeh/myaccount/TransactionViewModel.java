package ir.masoumeh.myaccount;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TransactionViewModel extends ViewModel {

    private DatabaseReference db_transaction = FirebaseDatabase.getInstance().getReference(DatabaseSetting.tb_transaction);
    private MutableLiveData<Exception> _result = new MutableLiveData<>();
    private LiveData<Exception> result = new MutableLiveData<>();

    public LiveData<Exception> getResult() {
        return result = _result;
    }

    public void addTransaction(TransactionModel transaction) {
        transaction.id = db_transaction.push().getKey();
        if (transaction.id != null) {
            db_transaction.child(transaction.id).setValue(transaction).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        _result.postValue(null);
                    } else {
                        _result.postValue(task.getException());
                    }
                }
            });
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();

    }
}
