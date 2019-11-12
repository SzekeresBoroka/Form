package com.example.questionaire;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FormFragment extends Fragment {
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FormItem> formItemList;

    @Override
    public void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        context = getActivity();

        if(getArguments() != null) {
            User user = (User) getArguments().getSerializable("user");
            formItemList = prepareDataList(user);
        }
        else{
            formItemList = prepareDataList();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_form, container, false);

        recyclerView = v.findViewById(R.id.formRecyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new FormAdapter(formItemList, context);
        recyclerView.setAdapter(mAdapter);

        return v;
    }

    private ArrayList<FormItem> prepareDataList(){
        ArrayList<FormItem> formItemList = new ArrayList<>();

        formItemList.add(new FormItem("HEADER", "header"));
        formItemList.add(new FormItemText("TEXT", "Lastname: *","lastname", "Enter lastname", ""));
        formItemList.add(new FormItemText("TEXT", "Firstname: *", "firstname", "Enter firstname", ""));
        formItemList.add(new FormItemDatePicker("DATEPICKER", "Birth date: *", "birthdate"));
        formItemList.add(new FormItemGender("GENDER", "Gender: *","gender"));

        ArrayList<String> locations = getLocationsList();
        formItemList.add(new FormItemSpinner("SPINNER", "Location: *", "locationId", locations));

        ArrayList<String> departments = getDepartmentsList();
        formItemList.add(new FormItemSpinner("SPINNER", "Department: *", "departmentId", departments));

        formItemList.add(new FormItemYearOfStudy("YEAR_OF_STUDY", "Year of study: *","yearOfStudy"));
        formItemList.add(new FormItemHobbies("HOBBIES", "Hobbies:", "hobbies"));
        formItemList.add(new FormItemText("LONG_TEXT", "Expectations:", "expectations", "Write your expectations here", ""));
        formItemList.add(new FormItemImage("IMAGE", "Profile picture:", "profile_picture"));

        formItemList.add(new FormItem("FOOTER", "footer"));

        return formItemList;
    }

    private ArrayList<FormItem> prepareDataList(User user){
        ArrayList<FormItem> formItemList = new ArrayList<>();

        formItemList.add(new FormItem("HEADER", "header"));
        formItemList.add(new FormItemText("TEXT", "Lastname:","lastname", "Enter lastname", user.getLastname()));
        formItemList.add(new FormItemText("TEXT", "Firstname:", "firstname", "Enter firstname", user.getFirstname()));
        formItemList.add(new FormItemDatePicker("DATEPICKER", "Birth date:", "birthdate", user.getBirthdate()));
        formItemList.add(new FormItemGender("GENDER", "Gender:","gender", user.getGender()));

        ArrayList<String> spinnerItems = getLocationsList();
        formItemList.add(new FormItemSpinner("SPINNER", "Location:", "locationId", spinnerItems, user.getLocationId()));

        ArrayList<String> departments = getDepartmentsList();
        formItemList.add(new FormItemSpinner("SPINNER", "Department:", "departmentId", departments, user.getDepartmentId()));

        formItemList.add(new FormItemYearOfStudy("YEAR_OF_STUDY", "Year of study:","yearOfStudy", user.getYearOfStudy()));
        formItemList.add(new FormItemHobbies("HOBBIES", "Hobbies:","hobbies", user.getHobbies()));
        formItemList.add(new FormItemText("LONG_TEXT", "Expectations:", "expectations", "Write your expectations here", user.getExpectations()));

        formItemList.add(new FormItem("FOOTER_BACK", "footer"));

        return formItemList;
    }

    private ArrayList<String> getLocationsList(){
        ArrayList<String> spinnerItems = new ArrayList<>();
        spinnerItems.add("Choose location");
        spinnerItems.add("Csíkszereda");
        spinnerItems.add("Kolozsvár");
        spinnerItems.add("Marosvásárhely");
        spinnerItems.add("Sepsiszentgyörgy");
        return spinnerItems;
    }

    private ArrayList<String> getDepartmentsList(){
        ArrayList<String> spinnerItems = new ArrayList<>();
        spinnerItems.add("Choose department");
        spinnerItems.add("Automatizálás");
        spinnerItems.add("Fordító és tolmács");
        spinnerItems.add("Gépészmérnöki");
        spinnerItems.add("Informatika");
        spinnerItems.add("Kertészmérnöki");
        spinnerItems.add("Kommunikáció");
        spinnerItems.add("Közegészségügy");
        spinnerItems.add("Mechatronika");
        spinnerItems.add("Számítástechnika");
        spinnerItems.add("Tájípítészet");
        spinnerItems.add("Távközlés");
        return spinnerItems;
    }
}
