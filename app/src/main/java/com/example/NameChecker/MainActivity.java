package com.example.NameChecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.NameChecker.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText userInput;
    private Button btn;
    private TextView result_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = findViewById(R.id.user_input);
        btn = findViewById(R.id.main_button);
        result_info = findViewById(R.id.result);


        //Button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userInput.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this, R.string.no_input, Toast.LENGTH_SHORT)
                            .show();

                } else {
                    String theName = InputHandler.translitirate(
                            InputHandler.checkForMarks(userInput.getText().toString().trim()));


                    String url = "https://api.genderize.io?name=" + theName;
                    new GetUrlData().execute(url);

                }
            }
        });

    }

    private class GetUrlData extends AsyncTask<String, String, String> {

        String name = InputHandler.translitirate(
                InputHandler.checkForMarks(userInput.getText().toString().trim()));

        protected void onPreExecute() {
            super.onPreExecute();
            result_info.setText("Проверяем имя " + name + "...");
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(strings[0]);

                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }
                return buffer.toString();

            } catch (MalformedURLException e) {


            } catch (IOException e) {


            } finally {
                if (connection != null)
                    connection.disconnect();
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {

                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            JSONObject json = null;
            StringBuffer bufferResult = new StringBuffer();
            JsonFormatter jf = new JsonFormatter();
            if (result != null && !result.equals("")) {
                try {
                    json = new JSONObject(result);
                    if (jf.getResultProbability(json) == 0) {
                        bufferResult.append("Для имени: ").append(name)
                                .append("\n").append("результатов нет");
                    } else {
                        bufferResult.append("Имя: " + name).append("\n")
                                .append(jf.getResultGender(json)).append("\n")
                                .append("Шанс: ").append(jf.getResultProbability(json).toString()).append("%").append("\n")
                                .append(jf.getResultCount(json));
                    }
                } catch (JSONException e) {

                }
                result_info.setText(bufferResult.toString());
            } else
                result_info.setText("нет соединения");
        }

    }


    private class JsonFormatter {
        public String getResultGender(JSONObject json) {
            String gender = "";
            try {
                gender = json.getString("gender").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (gender.equals("female")) {
                return "Женское имя";
            } else return "Мужское имя";
        }

        Integer getResultProbability(JSONObject json) {
            Double prob = 0.0;

            try {
                prob = json.getDouble("probability");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return (int) (prob * 100);
        }

        String getResultCount(JSONObject json) {
            int count = 0;

            try {
                count = json.getInt("count");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "Кол-во: " + count;
        }
    }


}