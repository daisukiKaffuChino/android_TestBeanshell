package william.chamberlain.testbeanshell;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import bsh.EvalError;
import bsh.Interpreter;


import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onResume() {
        super.onResume();


        // https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android
        new AlertDialog.Builder(this)
                .setTitle("Calling Beanshell")
                .setMessage(doBeanshell())
                .setPositiveButton(android.R.string.ok, null)
                .show();


    }

    private String doBeanshell() {
        try {
            // http://www.beanshell.org/manual/embeddedmode.html
            Interpreter i = new Interpreter();  // Construct an interpreter
            i.set("foo", 5);                    // Set variables
            i.set("date", new Date());

            Date date = (Date) i.get("date");    // retrieve a variable

            // Eval a statement and get the result
            i.eval("bar = foo*10");
            System.out.println(i.get("bar"));

            // Source an external script file
//            i.source("somefile.bsh");

            // http://www.beanshell.org/manual/embeddedmode.html
            Object result = i.eval("long time = 42; new Date( time )"); // Date 
            return result.toString();
        } catch (EvalError e) {
            Log.e("MainActivity","doBeanshell(): excetption : "+e.getMessage() + " : returning 'Exception' ", e );
            e.printStackTrace();
            return "Exception";
        }
    }
}
