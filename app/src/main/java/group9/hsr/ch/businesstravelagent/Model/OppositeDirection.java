package group9.hsr.ch.businesstravelagent.Model;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import group9.hsr.ch.businesstravelagent.Controller.MainActivity;
import group9.hsr.ch.businesstravelagent.R;

public class OppositeDirection {

    private MainActivity mainActivity = null;

    public OppositeDirection(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private EditText GetStartLocationText() {
        return (EditText) mainActivity.findViewById(R.id.startLocationText);
    }

    private EditText GetEndLocationText() {
        return (EditText) mainActivity.findViewById(R.id.endLocationText);
    }

    private ImageButton GetOppositeDirectionButton() {
        return (ImageButton) mainActivity.findViewById(R.id.oppositeDirectionButton);
    }

    public void Register() {
        final ImageButton oppositeDirectionButton = GetOppositeDirectionButton();

        oppositeDirectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText startLocationText = GetStartLocationText();
                EditText endLocationText = GetEndLocationText();

                if (startLocationText.getText().toString().length() > 0 && endLocationText.getText().toString().length() > 0) {
                    String startText = startLocationText.getText().toString();
                    startLocationText.setText(endLocationText.getText().toString());
                    endLocationText.setText(startText);
                }
            }
        });
    }
}
