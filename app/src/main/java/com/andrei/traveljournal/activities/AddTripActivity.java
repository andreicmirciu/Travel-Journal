package com.andrei.traveljournal.activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.andrei.traveljournal.MainActivity;
import com.andrei.traveljournal.R;
import com.andrei.traveljournal.models.TripModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class AddTripActivity extends AppCompatActivity {

    EditText mTripName;
    EditText mDestination;

    Button mGallery;
    Button mTakePicture;
    Button mStartDate;
    Button mEndDate;
    Button mSaveBtn;

    TextView mUserInput;
    TextView mPrice;

    String mDate;
    RecyclerView mTripsList;

    RatingBar mRatingBar;
    SeekBar mSeekBar;

    // trip photo
    ImageView mImage;

    String pathToFile;
    Uri galleryImageUri;
    float ratingBarValue;
    int seekBarValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        mGallery = findViewById(R.id.gallery_btn);
        mTakePicture = findViewById(R.id.takepicture_btn);

        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }

        mStartDate = findViewById(R.id.startdate_btn);
        mEndDate = findViewById(R.id.enddate_btn);
        mUserInput = findViewById(R.id.input);
        mSaveBtn = findViewById(R.id.save_btn);
        mTripName = findViewById(R.id.tripname_edittext);
        mDestination = findViewById(R.id.destination_edittext);
        mRatingBar = findViewById(R.id.ratingbar);
        mSeekBar = findViewById(R.id.seekbar);
        mTripsList = findViewById(R.id.recyclerview);
        mImage = findViewById(R.id.testPicture);
        mPrice = findViewById(R.id.price);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue = progress * 10;
                mPrice.setText(String.valueOf(seekBarValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBarValue = rating;
            }
        });

        mGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                if (galleryIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(galleryIntent, 100);
                }
            }
        });

        mTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    File photoFile = createPhotoFile();

                    if (photoFile != null) {
                        pathToFile = photoFile.getAbsolutePath();
                        Uri photoURI = FileProvider.getUriForFile(AddTripActivity.this,
                                "com.andrei.traveljournal.fileprovider", photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, 1);
                    }

                }

            }
        });

        mStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTripActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                            }
                        }, dayOfMonth, month, year);
                datePickerDialog.show();
                mDate = "" + dayOfMonth + "/" + month + "/" + year;
                mStartDate.setText(mDate);

            }
        });

        mEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTripActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                            }
                        }, dayOfMonth, month, year);
                datePickerDialog.show();
                mDate = "" + dayOfMonth + "/" + month + "/" + year;
                mEndDate.setText(mDate);
            }
        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TripModel item = new TripModel();
                String getTripName = mTripName.getText().toString();
                item.setTripName(getTripName);
                String getDestination = mDestination.getText().toString();
                item.setDestination(getDestination);
                item.setPicture(mImage);
                item.setRating(ratingBarValue);
                item.setPrice(seekBarValue);
                MainActivity.adapter.addItem(item);
                finish();
            }
        });
    }

    private File createPhotoFile() {
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(name, ".jpg", storageDir);
        } catch (Exception e) {
            Log.d("TAG", "Exception" + e.toString());
        }
        return image;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("tripName", mTripName.getText().toString());
        intent.putExtra("destination", mDestination.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);

                ExifInterface ei = null;
                try {
                    ei = new ExifInterface(pathToFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_UNDEFINED);

                Bitmap rotatedBitmap = null;
                switch(orientation) {

                    case ExifInterface.ORIENTATION_ROTATE_90:
                        rotatedBitmap = rotateImage(bitmap, 90);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_180:
                        rotatedBitmap = rotateImage(bitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        rotatedBitmap = rotateImage(bitmap, 270);
                        break;

                    case ExifInterface.ORIENTATION_NORMAL:
                    default:
                        rotatedBitmap = bitmap;
                }

                mImage.setImageBitmap(rotatedBitmap);

            }

            if (requestCode == 100) {
                galleryImageUri = data.getData();
                mImage.setImageURI(galleryImageUri);
            }
        }

    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

}

