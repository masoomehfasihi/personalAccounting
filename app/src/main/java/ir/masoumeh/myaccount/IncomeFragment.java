package ir.masoumeh.myaccount;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Observer;

import ir.masoumeh.myaccount.databinding.FragmentIncomeBinding;

public class IncomeFragment extends Fragment {
    FragmentIncomeBinding binding;
    TransactionViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentIncomeBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(TransactionViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getResult().observe(getViewLifecycleOwner(), it -> {
            if (it == null) {
                Toast.makeText(requireContext(), "درآمد ثبت شد", Toast.LENGTH_LONG).show();
                clearFields();
            } else {
                Toast.makeText(requireContext(), "درآمد ثبت نشد" + it.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        binding.btnDaramad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = binding.edtAmount.getText().toString();
                String category = binding.edtCategory.getText().toString();
                String date = binding.edtDate.getText().toString();
                String description = binding.edtDescription.getText().toString();
                String email = UserInfo.getEmail(requireActivity());
                if (amount.length()>=3 && !category.isEmpty() && !date.isEmpty() && !description.isEmpty()) {
                    addTransaction(Long.parseLong(amount),category , date , description ,email );
                } else {
                    if (amount.length() <= 3){
                        binding.edtAmount.setError("طول مقدار بایستی حداقل سه رقم باشد");
                    }
                    if (category.isEmpty()){
                        binding.edtCategory.setError("دسته بندی را وارد کنید");
                    }

                    if (date.isEmpty()){
                        binding.edtDate.setError("تاریخ را وارد کنید");
                    }
                    if (description.isEmpty()){
                        binding.edtDescription.setError("توضیحات را وارد کنید");
                    }
                }
            }
        });
    }

    private void clearFields() {
        binding.edtDescription.setText("");
        binding.edtDate.setText("");
        binding.edtAmount.setText("");
        binding.edtCategory.setText("");
    }

    private void addTransaction(Long amount , String category , String date , String description , String email){
        TransactionModel transaction = new TransactionModel();
        transaction.amount =amount;
        transaction.category = category;
        transaction.date =date;
        transaction.description = description;
        transaction.email = email;
        transaction.isPayed = false;
        viewModel.addTransaction(transaction);
    }
}