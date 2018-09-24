package kmavn.giaynhap.udptrackingclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.UnknownHostException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.input)
    EditText edInput;
    @OnClick (R.id.button)
    public void onButtonClick(View view) {
        String txt = edInput.getText().toString();
        Toast.makeText(getApplicationContext(), "Sent Log: "+txt,
                Toast.LENGTH_SHORT).show();
        Client.getInstance().logEvent(txt);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        try {
            Client.getInstance().connect("192.168.0.100",9191);
        } catch (UDPTrackingException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
