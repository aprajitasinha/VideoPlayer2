package com.mobiloitte.videoplayer.Activity;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mobiloitte.videoplayer.R;
import com.mobiloitte.videoplayer.databinding.ActivitySqliteBinding;

public class ActivitySqliteDatabase extends AppCompatActivity  {
    DatabseHelper myDb;
    ActivitySqliteBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(ActivitySqliteDatabase.this, R.layout.activity_sqlite);
        myDb = new DatabseHelper(this);

        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }



    public void DeleteData() {

        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(binding.editTextId.getText().toString());
                if(deletedRows > 0)
                    Toast.makeText(ActivitySqliteDatabase.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ActivitySqliteDatabase.this,"Data not Deleted",Toast.LENGTH_LONG).show();
            }
                                                }
        );
            }



    public void UpdateData() {
        binding.buttonUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(binding.editTextId.getText().toString(),
                                binding.editTextName.getText().toString(),
                                binding.editTextSurname.getText().toString(),binding.editTextMarks.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(ActivitySqliteDatabase.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ActivitySqliteDatabase.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public  void AddData() {
        binding.buttonAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(binding.editTextName.getText().toString(),
                                binding.editTextSurname.getText().toString(),
                                binding.editTextMarks.getText().toString() );
                        if(isInserted == true)
                            Toast.makeText(ActivitySqliteDatabase.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ActivitySqliteDatabase.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        binding.buttonViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Surname :"+ res.getString(2)+"\n");
                            buffer.append("Marks :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



    }

