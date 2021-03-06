package com.appsynopsis.jarman.victimisedcolorworld;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by nila on 11/16/15.
 */
public class ImageViewActivity extends ActionBarActivity implements PicModeSelectDialogFragment.IPicModeSelectListener {

    public static final String TAG = "ImageViewActivity";
    String ImagePATH=null;
    public static final String TEMP_PHOTO_FILE_NAME = "temp_photo.jpg";
    public static final int REQUEST_CODE_UPDATE_PIC = 0x1;
    String[] arrayForSpinner1={"Afghanistan", "Albania", "Algeria", "American Samoa",
            "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda",
            "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan",
            "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
            "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina",
            "Botswana", "Bouvet Island", "Brazil",
            "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia",
            "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic",
            "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands",
            "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the",
            "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)",
            "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica",
            "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador",
            "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia",
            "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland",
            "France", "France Metropolitan", "French Guiana", "French Polynesia",
            "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany",
            "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe",
            "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti",
            "Heard and Mc Donald Islands", "Holy See (Vatican City State)",
            "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia",
            "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy",
            "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati",
            "Korea, Democratic People's Republic of", "Korea, Republic of",
            "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic",
            "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya",
            "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, " +
            "The Former Yugoslav Republic of", "Madagascar",
            "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
            "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico",
            "Micronesia, Federated States of", "Moldova, Republic of",
            "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique",
            "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles",
            "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue",
            "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan",
            "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines",
            "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion",
            "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis",
            "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
            "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles",
            "Sierra Leone", "Singapore", "Slovakia",
            "Slovenia", "Solomon Islands", "Somalia", "South Africa",
            "South Georgia and the South Sandwich Islands", "Spain",
            "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan",
            "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland",
            "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan" +
            " Province of China", "Tajikistan", "Tanzania, United Republic of",
            "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago",
            "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands",
            "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom",
            "United States", "United States Minor Outlying Islands", "Uruguay",
            "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam",
            "Virgin Islands (British)", "Virgin Islands (U.S.)",
            "Wallis and Futuna Islands", "Western Sahara", "Yemen",
            "Yugoslavia", "Zambia", "Zimbabwe"};

    private Button mBtnUpdatePic;
    private ImageView mImageView;
    Bitmap ProPic=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBtnUpdatePic = (Button) findViewById(R.id.btnUpdatePic);
        mImageView = (ImageView) findViewById(R.id.iv_user_pic);
        mBtnUpdatePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddProfilePicDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.spinner);
       final Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                arrayForSpinner1);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.getSelectedItem().toString().equalsIgnoreCase("Bangladesh")) {
                    if (ImagePATH != null)
                        showCroppedImage(ImagePATH, 1);

                } else if (spinner.getSelectedItem().toString().equalsIgnoreCase("Palestine")) {
                    if (ImagePATH != null)
                        showCroppedImage(ImagePATH, 2);

                } else if (spinner.getSelectedItem().toString().equalsIgnoreCase("Syria")) {
                    if (ImagePATH != null)
                        showCroppedImage(ImagePATH, 3);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == REQUEST_CODE_UPDATE_PIC) {
            if (resultCode == RESULT_OK) {
                String imagePath = result.getStringExtra(Constants.IntentExtras.IMAGE_PATH);
                showCroppedImage(imagePath,1);
            } else if (resultCode == RESULT_CANCELED) {

            } else {
                String errorMsg = result.getStringExtra("Error");
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showCroppedImage(String mImagePath,int which) {
        if (mImagePath != null) {
            ImagePATH=mImagePath;
            Bitmap myBitmap = BitmapFactory.decodeFile(mImagePath);
            Bitmap mutableBitmap = myBitmap.copy(Bitmap.Config.ARGB_8888, true);

            Canvas canvas=new Canvas(mutableBitmap);
            Paint paint = new Paint();
            paint.setAlpha(125);
            Bitmap overlayBitmap;
            if(which==1) {
                overlayBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.unnamed);
            }
            else if(which==3){
                overlayBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.palestine);
            }else{
                overlayBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.syria);
            }
            overlayBitmap=Bitmap.createScaledBitmap(overlayBitmap, mutableBitmap.getWidth(), mutableBitmap.getHeight(), true);
            canvas.drawBitmap(overlayBitmap,0,0,paint);
            ProPic=mutableBitmap;
            mImageView.setImageBitmap(mutableBitmap);
            Log.d("width>>",Float.toString(mImageView.getHeight())+"height>>"+Float.toString(mImageView.getWidth()));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            case R.id.share:
                if(ProPic!=null)
                SharePic(ProPic);
                else
                Toast.makeText(getApplicationContext(),"Create Your Profile picture First",Toast.LENGTH_SHORT).show();
                break;
            case R.id.save:
                if(ProPic!=null)
                    saveToExternatSdCard(ProPic);
                else
                    Toast.makeText(getApplicationContext(),"Create Your Profile picture First",Toast.LENGTH_SHORT).show();
                break;

            default:


        }
        return super.onOptionsItemSelected(item);
    }

    private void showAddProfilePicDialog() {
        PicModeSelectDialogFragment dialogFragment = new PicModeSelectDialogFragment();
        dialogFragment.setiPicModeSelectListener(this);
        dialogFragment.show(getSupportFragmentManager(), "picModeSelector");
    }

    private void actionProfilePic(String action) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("ACTION", action);
        startActivityForResult(intent, REQUEST_CODE_UPDATE_PIC);
    }


    @Override
    public void onPicModeSelected(String mode) {
        String action = mode.equalsIgnoreCase(Constants.PicModes.CAMERA) ? Constants.IntentExtras.ACTION_CAMERA : Constants.IntentExtras.ACTION_GALLERY;
        actionProfilePic(action);
    }

    public void saveToExternatSdCard(Bitmap finalBitmap){
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/victim");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Pro-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(getApplicationContext(),"Your Profile picture is saved in a directory named- victim",Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void SharePic(Bitmap icon){




        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File f = new File(Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg");
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/temporary_file.jpg"));
        startActivity(Intent.createChooser(share, "Share Image"));


    }
}