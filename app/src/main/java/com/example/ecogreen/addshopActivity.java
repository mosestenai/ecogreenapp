package com.example.ecogreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecogreen.api.API;
import com.example.ecogreen.api.AbstractAPIListener;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addshopActivity extends AppCompatActivity {
    Button submit;
    EditText shop,phone,description,paybill,password1,password2,username;
    TextView imgPath;
    private static final int PICK_IMAGE_REQUEST = 9544;
    ImageView image;
    Uri selectedImage;
    String part_image;

    // Permissions for accessing the storage
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addshop);

        //get spinner from the xml
        Spinner dropdown = findViewById(R.id.spinner1);
        //create  list of items for the spinner
        String[] items = new String[]{"Location","Nairobi","Mombasa","Naivasha","Kericho","Kisumu","Nakuru"};
        //create an sdapter to describe how the items are displayed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        //Set the spinner adapter to the previously created
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        dropdown.setAdapter(adapter);


        shop = findViewById(R.id.shop);
        username = findViewById(R.id.username);
        paybill = findViewById(R.id.paybill);
        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);
        phone = findViewById(R.id.phone);
        description = findViewById(R.id.description);
        image = findViewById(R.id.img);
        submit = findViewById(R.id.submit);

        imgPath = findViewById(R.id.select);

        submit.setOnClickListener(v-> {
            String shopp = shop.getText().toString();
            String phonee = phone.getText().toString();
            String paybilll = paybill.getText().toString();
            String password_1 = password1.getText().toString();
            String password_2 = password2.getText().toString();
            String descriptionn = description.getText().toString();
            String usernamee = username.getText().toString();
            String location = dropdown.getSelectedItem().toString();
            if(location.equals("Location")){
                Toast.makeText(this,"Specify shop location",Toast.LENGTH_SHORT).show();
            }else{
            if (!password_1.equals(password_2)) {
                Toast.makeText(this, "passwords do not match", Toast.LENGTH_SHORT).show();
            }else {
            if ((shop.length() > 1) && (descriptionn.length() > 1) && (phonee.length() > 1)) {
                final ProgressDialog progressDialog = new ProgressDialog(addshopActivity.this);
                progressDialog.setMessage("Adding ...");
                progressDialog.show();
                File imageFile = new File(part_image);                                                          // Create a file using the absolute path of the image
                RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imageFile);
                MultipartBody.Part partImage = MultipartBody.Part.createFormData("file", imageFile.getName(), reqBody);
                API api = RetrofitClient.getInstance().getAPI();
                Call<ResponseBody> upload = api.uploadImage(partImage);
                upload.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                        if (response.code() == 200) {

                            String urll = "https://eazistey.co.ke/images/" + imageFile.getName();

                            //final Model model = Model.getInstance(requireActivity().getApplication());
                            final Model model = Model.getInstance(addshopActivity.this.getApplication());
                            model.addshop(shopp, descriptionn, location, usernamee,password_1,phonee,paybilll, urll, new AbstractAPIListener() {
                                @Override
                                public void onAddshop(User user) {
                                    model.setUser(user);
                                    if ("Success".equals(user.getError())) {
                                        Toast.makeText(addshopActivity.this, "Shop added successfully", Toast.LENGTH_LONG).show();
                                        Intent main = new Intent(addshopActivity.this, adminActivity.class);
                                        main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(main);
                                        progressDialog.dismiss();
                                    } else {
                                        Toast.makeText(addshopActivity.this, user.getError(), Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                }


                            });



                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(addshopActivity.this, "Request failed", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(addshopActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();

            }
        }}

        });
    }


    // Method for starting the activity for selecting image from phone storage
    public void pick(View view) {
        verifyStoragePermissions(addshopActivity.this);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Open Gallery"), PICK_IMAGE_REQUEST);
    }

    // Method to get the absolute path of the selected image from its URI
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST) {
            if (resultCode == RESULT_OK) {
                selectedImage = data.getData();                                                         // Get the image file URI
                String[] imageProjection = {MediaStore.Images.Media.DATA};
                @SuppressLint("Recycle") Cursor cursor = getContentResolver().query(selectedImage, imageProjection, null, null, null);
                if(cursor != null) {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageProjection[0]);
                    part_image = cursor.getString(indexImage);
                    imgPath.setText(part_image);                                                        // Get the image file absolute path
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    image.setImageBitmap(bitmap);                                                       // Set the ImageView with the bitmap of the image
                }
            }
        }
    }


    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}