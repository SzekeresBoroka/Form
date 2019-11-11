package com.example.questionaire;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static androidx.core.app.ActivityCompat.startActivityForResult;
import static java.lang.Integer.parseInt;

public class FormAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_DATEPICKER = 2;
    private static final int TYPE_GENDER = 3;
    private static final int TYPE_SPINNER = 4;
    private static final int TYPE_TEXT = 5;
    private static final int TYPE_FOOTER_BACK = 6;
    private static final int TYPE_YEAR_OF_STUDY = 7;
    private static final int TYPE_HOBBIES = 8;
    private static final int TYPE_LONG_TEXT = 9;
    private static final int TYPE_IMAGE = 10;

    private ArrayList<FormItem> mDataset;
    private Context context;
    private Map<String,String> userDatas;
    private DatabaseReference db;
    private FirebaseHelper helper;
    private ArrayList<User> usersList;

    // Provide a suitable constructor (depends on the kind of dataset)
    public FormAdapter(ArrayList<FormItem> myDataset, Context context) {
        this.context = context;
        mDataset = myDataset;
        userDatas = new HashMap<>();
        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db, context);
        usersList = helper.retrieve();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch(viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(context).inflate(R.layout.form_header_view, parent, false);
                return new HeaderViewHolder(view);
            }
            case TYPE_FOOTER: {
                view = LayoutInflater.from(context).inflate(R.layout.form_footer_view, parent, false);
                return new FooterViewHolder(view);
            }
            case TYPE_FOOTER_BACK: {
                view = LayoutInflater.from(context).inflate(R.layout.form_footer_back_view, parent, false);
                return new FooterBackViewHolder(view);
            }
            case TYPE_TEXT: {
                view = LayoutInflater.from(context).inflate(R.layout.form_input_text, parent, false);
                return new TextViewHolder(view);
            }
            case TYPE_DATEPICKER: {
                view = LayoutInflater.from(context).inflate(R.layout.form_input_datepicker, parent, false);
                return new DatePickerViewHolder(view);
            }
            case TYPE_GENDER: {
                view = LayoutInflater.from(context).inflate(R.layout.form_input_gender, parent, false);
                return new GenderViewHolder(view);
            }
            case TYPE_SPINNER: {
                view = LayoutInflater.from(context).inflate(R.layout.form_input_spinner, parent, false);
                return new SpinnerViewHolder(view);
            }
            case TYPE_HOBBIES: {
                view = LayoutInflater.from(context).inflate(R.layout.form_input_hobbies, parent, false);
                return new HobbiesViewHolder(view);
            }
            case TYPE_LONG_TEXT: {
                view = LayoutInflater.from(context).inflate(R.layout.form_input_long_text, parent, false);
                return new TextViewHolder(view);
            }
            case TYPE_YEAR_OF_STUDY: {
                view = LayoutInflater.from(context).inflate(R.layout.form_input_year_of_study, parent, false);
                return new YearOfStudyViewHolder(view);
            }
            case TYPE_IMAGE: {
                view = LayoutInflater.from(context).inflate(R.layout.form_input_image, parent, false);
                return new ImageViewHolder(view);
            }
        }
        view = LayoutInflater.from(context).inflate(R.layout.form_input_text, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        switch(mDataset.get(position).getType()) {
            case "HEADER": {
                return TYPE_HEADER;
            }
            case "FOOTER":{
                return TYPE_FOOTER;
            }
            case "FOOTER_BACK":{
                return TYPE_FOOTER_BACK;
            }
            case "TEXT": {
                return TYPE_TEXT;
            }
            case "DATEPICKER": {
                return TYPE_DATEPICKER;
            }
            case "GENDER": {
                return TYPE_GENDER;
            }
            case "SPINNER": {
                return TYPE_SPINNER;
            }
            case "HOBBIES": {
                return TYPE_HOBBIES;
            }
            case "YEAR_OF_STUDY": {
                return TYPE_YEAR_OF_STUDY;
            }
            case "LONG_TEXT": {
                return TYPE_LONG_TEXT;
            }
            case "IMAGE": {
                return TYPE_IMAGE;
            }
        }
        return TYPE_TEXT;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch(getItemViewType(position)) {
            case TYPE_HEADER:{
                ((HeaderViewHolder) holder).setHeaderDetails(mDataset.get(position));
                break;
            }
            case TYPE_FOOTER: {
                ((FooterViewHolder) holder).setFooterDetails(mDataset.get(position));
                break;
            }
            case TYPE_FOOTER_BACK: {
                ((FooterBackViewHolder) holder).setFooterBackDetails(mDataset.get(position));
                break;
            }
            case TYPE_DATEPICKER: {
                ((DatePickerViewHolder) holder).setDatePickerDetails(mDataset.get(position));
                break;
            }
            case TYPE_GENDER: {
                ((GenderViewHolder) holder).setGenderDetails(mDataset.get(position));
                break;
            }
            case TYPE_SPINNER: {
                ((SpinnerViewHolder) holder).setSpinnerDetails(mDataset.get(position));
                break;
            }
            case TYPE_HOBBIES: {
                ((HobbiesViewHolder) holder).setHobbiesDetails(mDataset.get(position));
                break;
            }
            case TYPE_YEAR_OF_STUDY: {
                ((YearOfStudyViewHolder) holder).setYearOfStudyDetails(mDataset.get(position));
                break;
            }
            case TYPE_IMAGE: {
                ((ImageViewHolder) holder).setImageDetails(mDataset.get(position));
                break;
            }
            //TYPE_TEXT and TYPE_LONG_TEXT
            default: {
                ((TextViewHolder) holder).setTextDetails(mDataset.get(position));
                break;
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_header;

        HeaderViewHolder(View v) {
            super(v);
            tv_header = v.findViewById(R.id.tv_header);
        }

        void setHeaderDetails(FormItem item){

        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        private Button btn_send, btn_skip;

        FooterViewHolder(View v) {
            super(v);
            btn_send = v.findViewById(R.id.btn_send);
            btn_skip = v.findViewById(R.id.btn_skip);
        }

        void setFooterDetails(FormItem item){
            btn_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("users");
                    String lastname = userDatas.get("lastname");
                    if(lastname == null){
                        lastname = "";
                    }
                    String firstname = userDatas.get("firstname");
                    if(firstname == null){
                        firstname = "";
                    }
                    String birthdate = userDatas.get("birthdate");
                    if(birthdate == null){
                        birthdate= "";
                    }
                    String gender = userDatas.get("gender");
                    if(gender == null){
                        gender = "";
                    }
                    String location = userDatas.get("locationId");
                    int locationId;
                    if(location == null){
                        locationId= 0;
                    }
                    else{
                        locationId = parseInt(location);
                    }
                    String department = userDatas.get("departmentId");
                    int departmentId;
                    if(department== null){
                        departmentId= 0;
                    }
                    else{
                        departmentId = parseInt(department);
                    }
                    String yearOfStudy = userDatas.get("yearOfStudy");
                    if(yearOfStudy == null){
                        yearOfStudy = "";
                    }
                    String expectations = userDatas.get("expectations");
                    if(expectations == null){
                        expectations = "";
                    }

                    ArrayList<String> hobbies = new ArrayList<>();
                    if(userDatas.get("hobby_reading") != null){
                        hobbies.add(userDatas.get("hobby_reading"));
                    }
                    if(userDatas.get("hobby_movies") != null){
                        hobbies.add(userDatas.get("hobby_movies"));
                    }
                    if(userDatas.get("hobby_sport") != null){
                        hobbies.add(userDatas.get("hobby_sport"));
                    }
                    if(userDatas.get("hobby_pets") != null){
                        hobbies.add(userDatas.get("hobby_pets"));
                    }
                    if(userDatas.get("hobby_travel") != null){
                        hobbies.add(userDatas.get("hobby_travel"));
                    }

                    if(lastname.isEmpty() || firstname.isEmpty() || birthdate.isEmpty() || gender.isEmpty() || locationId == 0 || departmentId == 0 || yearOfStudy.isEmpty()){
                        Toast.makeText(context, "All fields marked with * must be completed!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    User user = new User(lastname, firstname, birthdate, gender, locationId, departmentId, yearOfStudy, hobbies, expectations);

                    if(helper.save(user)){
                        ListFormsFragment listFormsFragment = new ListFormsFragment();
                        //set argument for fragment
                        usersList = helper.retrieve();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("usersList", usersList);
                        listFormsFragment.setArguments(bundle);

                        FragmentTransaction frag_trans =((MainActivity) context).getSupportFragmentManager().beginTransaction();
                        frag_trans.replace(R.id.fragment_container, listFormsFragment);
                        frag_trans.addToBackStack("Questionaire");
                        frag_trans.commit();
                    }
                    else{
                        Toast.makeText(context, "Error at sending data", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            btn_skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListFormsFragment listFormsFragment = new ListFormsFragment();
                    //set argument for fragment
                    usersList = helper.retrieve();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("usersList", usersList);
                    listFormsFragment.setArguments(bundle);

                    FragmentTransaction frag_trans =((MainActivity) context).getSupportFragmentManager().beginTransaction();
                    frag_trans.replace(R.id.fragment_container, listFormsFragment);
                    frag_trans.addToBackStack("Questionaire");
                    frag_trans.commit();
                }
            });
        }
    }

    class FooterBackViewHolder extends RecyclerView.ViewHolder {
        private Button btn_back;

        FooterBackViewHolder(View v) {
            super(v);
            btn_back = v.findViewById(R.id.btn_back);
        }

        void setFooterBackDetails(FormItem item){
            btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListFormsFragment listFormsFragment = new ListFormsFragment();
                    //set argument for fragment
                    usersList = helper.retrieve();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("usersList", usersList);
                    listFormsFragment.setArguments(bundle);

                    FragmentTransaction frag_trans =((MainActivity) context).getSupportFragmentManager().beginTransaction();
                    frag_trans.replace(R.id.fragment_container, listFormsFragment);
                    frag_trans.addToBackStack("Questionaire");
                    frag_trans.commit();
                }
            });
        }
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_text;
        private EditText et_text;

        TextViewHolder(View v) {
            super(v);
            tv_text = v.findViewById(R.id.tv_text);
            et_text = v.findViewById(R.id.et_text);
        }

        void setTextDetails(final FormItem item){
            tv_text.setText(item.getText());
            et_text.setHint(((FormItemText)item).getHint());
            et_text.setText(((FormItemText)item).getValue());
            et_text.setImeOptions(EditorInfo.IME_ACTION_DONE);

            et_text.addTextChangedListener(new TextWatcher(){
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    userDatas.put(item.getDbColumnName(), et_text.getText().toString());
                    //Toast.makeText(context, item.getDbColumnName() + " - " + et_text.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class DatePickerViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_text, tv_date;
        private Button btn_choose;

        DatePickerViewHolder(View v) {
            super(v);
            tv_text = v.findViewById(R.id.tv_text);
            tv_date = v.findViewById(R.id.tv_date);
            btn_choose = v.findViewById(R.id.btn_choose);
        }

        void setDatePickerDetails(final FormItem item){
            tv_text.setText(item.getText());
            tv_date.setHint("Select date");
            tv_date.setText(((FormItemDatePicker) item).getDate());


            final Calendar c = Calendar.getInstance();
            int startYear = c.get(Calendar.YEAR);
            int startMonth = c.get(Calendar.MONTH);
            int startDay = c.get(Calendar.DAY_OF_MONTH);
            MainActivity.datePickerDialog = new DatePickerDialog(context, dateSetListener, startYear, startMonth, startDay);

            tv_date.addTextChangedListener(new TextWatcher(){
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    userDatas.put(item.getDbColumnName(), tv_date.getText().toString());
                }
            });
        }

        DatePickerDialog.OnDateSetListener dateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String date = year + "-" + (month + 1) + "-" + day;
                        tv_date.setText(date);
                    }
                };
    }

    class GenderViewHolder extends RecyclerView.ViewHolder {
        private RadioButton rb_female, rb_male;
        private RadioGroup gender_radiogroup;
        private  TextView tv_text;

        GenderViewHolder(View v) {
            super(v);
            rb_female = v.findViewById(R.id.rb_female);
            rb_male = v.findViewById(R.id.rb_male);
            gender_radiogroup = v.findViewById(R.id.gender_radiogroup);
            tv_text = v.findViewById(R.id.tv_text);
        }

        void setGenderDetails(final FormItem item){
            tv_text.setText(item.getText());
            String gender = ((FormItemGender) item).getGender();
            if(!gender.isEmpty()){
                if(gender.equals("FEMALE")){
                    gender_radiogroup.check(R.id.rb_female);
                }
                else{
                    gender_radiogroup.check(R.id.rb_male);
                }
            }

            rb_female.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        userDatas.put(item.getDbColumnName(), "FEMALE");
                    }
                }
            });

            rb_male.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        userDatas.put(item.getDbColumnName(), "MALE");
                    }
                }
            });
        }
    }

    class YearOfStudyViewHolder extends RecyclerView.ViewHolder {
        private RadioButton rb_first_year, rb_second_year, rb_third_year, rb_fourth_year;
        private RadioGroup year_of_study_radiogroup;
        private TextView tv_text;

        YearOfStudyViewHolder(View v) {
            super(v);
            rb_first_year = v.findViewById(R.id.rb_first_year);
            rb_second_year = v.findViewById(R.id.rb_second_year);
            rb_third_year = v.findViewById(R.id.rb_third_year);
            rb_fourth_year = v.findViewById(R.id.rb_fourth_year);
            year_of_study_radiogroup = v.findViewById(R.id.year_of_study_radiogroup);
            tv_text = v.findViewById(R.id.tv_text);
        }

        void setYearOfStudyDetails(final FormItem item){
            tv_text.setText(item.getText());
            String yearOfStudy = ((FormItemYearOfStudy) item).getYearOfStudy();
            if(!yearOfStudy.isEmpty()){
                switch(yearOfStudy){
                    case "I":{
                        year_of_study_radiogroup.check(R.id.rb_first_year);
                        break;
                    }
                    case "II":{
                        year_of_study_radiogroup.check(R.id.rb_second_year);
                        break;
                    }
                    case "III":{
                        year_of_study_radiogroup.check(R.id.rb_third_year);
                        break;
                    }
                    case "IV":{
                        year_of_study_radiogroup.check(R.id.rb_fourth_year);
                        break;
                    }
                }
            }

            rb_first_year.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        userDatas.put(item.getDbColumnName(), "I");
                    }
                }
            });

            rb_second_year.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        userDatas.put(item.getDbColumnName(), "II");
                    }
                }
            });

            rb_third_year.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        userDatas.put(item.getDbColumnName(), "III");
                    }
                }
            });

            rb_fourth_year.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        userDatas.put(item.getDbColumnName(), "IV");
                    }
                }
            });
        }
    }


    class SpinnerViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_text;
        private Spinner spinner;

        SpinnerViewHolder(View v) {
            super(v);
            tv_text = v.findViewById(R.id.tv_text);
            spinner = v.findViewById(R.id.spinner);
        }

        void setSpinnerDetails(final FormItem item){
            tv_text.setText(item.getText());
            ArrayList<String> spinnerItems = ((FormItemSpinner) item).getSpinnerItems();
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, spinnerItems){
                @Override
                public boolean isEnabled(int position){
                    if(position == 0) {
                        // Disable the first item from Spinner, it will be used for hint
                        return false;
                    }
                    else {
                        return true;
                    }
                }
                @Override
                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    if(position == 0){
                        // Set the hint text color gray
                        tv.setTextColor(Color.GRAY);
                    }
                    else {
                        tv.setTextColor(Color.BLACK);
                    }
                    return view;
                }
            };
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerArrayAdapter);
            spinner.setSelection(((FormItemSpinner) item).getSelectedItemPosition());

            spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    userDatas.put(item.getDbColumnName(), "" + position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
        }
    }

    class HobbiesViewHolder extends RecyclerView.ViewHolder{
        private CheckBox chB_reading, chB_movies, chB_sport, chB_pets, chB_travel;
        private TextView tv_text;

        HobbiesViewHolder(View v) {
            super(v);
            tv_text = v.findViewById(R.id.tv_text);
            chB_reading = v.findViewById(R.id.chB_reading);
            chB_movies = v.findViewById(R.id.chB_movies);
            chB_sport = v.findViewById(R.id.chB_sport);
            chB_pets = v.findViewById(R.id.chB_pets);
            chB_travel = v.findViewById(R.id.chB_travel);

            userDatas.put("hobby_reading", "");
            userDatas.put("hobby_movies", "");
            userDatas.put("hobby_sport", "");
            userDatas.put("hobby_pets", "");
            userDatas.put("hobby_travel", "");
        }

        void setHobbiesDetails(final FormItem item){
            ArrayList<String> hobbies = ((FormItemHobbies) item).getHobbies();
            if(hobbies.size() > 0){
                for(String hobby : hobbies){
                    switch (hobby){
                        case "reading":{
                            chB_reading.setChecked(true);
                            break;
                        }
                        case "movies":{
                            chB_movies.setChecked(true);
                            break;
                        }
                        case "sport":{
                            chB_sport.setChecked(true);
                            break;
                        }
                        case "pets":{
                            chB_pets.setChecked(true);
                            break;
                        }
                        case "travel":{
                            chB_travel.setChecked(true);
                            break;
                        }
                    }
                }
            }

            chB_reading.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        userDatas.put("hobby_reading", "reading");
                    }
                    else{
                        userDatas.put("hobby_reading", "");
                    }
                }
            });

            chB_movies.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        userDatas.put("hobby_movies", "movies");
                    }
                    else{
                        userDatas.put("hobby_movies", "");
                    }
                }
            });

            chB_sport.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        userDatas.put("hobby_sport", "sport");
                    }
                    else{
                        userDatas.put("hobby_sport", "");
                    }
                }
            });

            chB_pets.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        userDatas.put("hobby_pets", "pets");
                    }
                    else{
                        userDatas.put("hobby_pets", "");
                    }
                }
            });

            chB_travel.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        userDatas.put("hobby_travel", "travel");
                    }
                    else{
                        userDatas.put("hobby_travel", "");
                    }
                }
            });
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_text;
        private Button btn_add_image;
        private ImageView image;

        ImageViewHolder(View v) {
            super(v);
            tv_text = v.findViewById(R.id.tv_text);
            btn_add_image = v.findViewById(R.id.btn_add_image);
            image = v.findViewById(R.id.image);
        }

        void setImageDetails(FormItem item){
            tv_text.setText(item.getText());

            btn_add_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    ((MainActivity) context).startActivityForResult(photoPickerIntent, 1);
                    //image.setImageBitmap(MainActivity.selectedImage);
                }
            });
        }
    }
}


