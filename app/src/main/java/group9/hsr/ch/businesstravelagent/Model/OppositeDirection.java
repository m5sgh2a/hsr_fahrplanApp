package group9.hsr.ch.businesstravelagent.Model;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import group9.hsr.ch.businesstravelagent.R;

public class OppositeDirection {

    private Activity activity;

    public OppositeDirection(Activity activity) {
        this.activity = activity;
    }

    private EditText GetStartLocationText() {
        return (EditText) activity.findViewById(R.id.startLocationText);
    }

    private EditText GetEndLocationText() {
        return (EditText) activity.findViewById(R.id.endLocationText);
    }

    private ImageButton GetOppositeDirectionButton() {
        return (ImageButton) activity.findViewById(R.id.oppositeDirectionButton);
    }

    public void Register() {
        final ImageButton oppositeDirectionButton = GetOppositeDirectionButton();

        oppositeDirectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwapStartAndEndLoction();
            }
        });
    }

    private void SwapStartAndEndLoction() {
        EditText startLocationText = GetStartLocationText();
        EditText endLocationText = GetEndLocationText();

        if (startLocationText.getText().toString().length() > 0 && endLocationText.getText().toString().length() > 0) {
            String startText = startLocationText.getText().toString();
            startLocationText.setText(endLocationText.getText().toString());
            endLocationText.setText(startText);
        }
    }
}
