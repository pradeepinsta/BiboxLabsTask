package com.pradeepuct.biboxlabstask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PartSelection extends AppCompatActivity implements PartShowListener {


    Button buttonNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_selection);


        RecyclerView partsRecyclerView = findViewById(R.id.recyclerView);

        buttonNext = findViewById(R.id.buttonNext);

        List<Parts> parts = new ArrayList<>();

        Parts handle = new Parts();
        handle.image = R.drawable.handle;
        handle.partName = "Handle Bar";
        parts.add(handle);

        Parts frame = new Parts();
        frame.image = R.drawable.frame;
        handle.partName = "Cycle Frame";
        parts.add(frame);

        Parts wheel = new Parts();
        wheel.image = R.drawable.wheel;
        wheel.partName = "Cycle Wheel";
        parts.add(wheel);

        partsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final PartsAdapter partsAdapter = new PartsAdapter(parts, this);
        partsRecyclerView.setAdapter(partsAdapter);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Parts> selectedParts = partsAdapter.getSelectedParts();

                StringBuilder partsNames = new StringBuilder();
                for (int i = 0; i < selectedParts.size(); i++) {
                    if (i == 0) {
                        partsNames.append(selectedParts.get(i).partName);
                    } else {
                        partsNames.append("\n").append(selectedParts.get(i).partName);
                    }
                }
                Toast.makeText(PartSelection.this, partsNames.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPartShowAction(boolean isSelected) {

        if (isSelected) {
            buttonNext.setVisibility(View.VISIBLE);
        } else {
            buttonNext.setVisibility(View.GONE);
        }
    }
}