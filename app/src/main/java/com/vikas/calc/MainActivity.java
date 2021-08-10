package com.vikas.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity
{

    TextView working;
    TextView res;

    String exp = "";
    String formula = "";
    String tempFormula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void initTextViews()
    {
        working = (TextView)findViewById(R.id.workingsTextView);
        res = (TextView)findViewById(R.id.resultTextView);
    }

    private void setExp(String givenValue)
    {
        exp = exp + givenValue;
        working.setText(exp);
    }


    public void equals(View view)
    {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try {
            result = (double)engine.eval(formula);
        } catch (ScriptException e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if(result != null)
            res.setText(String.valueOf(result.doubleValue()));

    }

    private void checkForPowerOf()
    {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < exp.length(); i++)
        {
            if (exp.charAt(i) == '^')
                indexOfPowers.add(i);
        }

        formula = exp;
        tempFormula = exp;
        for(Integer index: indexOfPowers)
        {
            changeFormula(index);
        }
        formula = tempFormula;
    }

    private void changeFormula(Integer index)
    {
        String numberLeft = "";
        String numberRight = "";

        for(int i = index + 1; i< exp.length(); i++)
        {
            if(isNum(exp.charAt(i)))
                numberRight = numberRight + exp.charAt(i);
            else
                break;
        }

        for(int i = index - 1; i >= 0; i--)
        {
            if(isNum(exp.charAt(i)))
                numberLeft = numberLeft + exp.charAt(i);
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original,changed);
    }

    private boolean isNum(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return true;

        return false;
    }


    public void clear(View view)
    {
        working.setText("");
        exp = "";
        res.setText("");
        leftBracket = true;
    }

    boolean leftBracket = true;

    public void bracket(View view)
    {
        if(leftBracket)
        {
            setExp("(");
            leftBracket = false;
        }
        else
        {
            setExp(")");
            leftBracket = true;
        }
    }

    public void powers(View view)
    {
        setExp("^");
    }

    public void seven(View view)
    {
        setExp("7");
    }

    public void eight(View view)
    {
        setExp("8");
    }

    public void nine(View view)
    {
        setExp("9");
    }

    public void four(View view)
    {
        setExp("4");
    }

    public void five(View view)
    {
        setExp("5");
    }

    public void six(View view)
    {
        setExp("6");
    }

    public void one(View view)
    {
        setExp("1");
    }

    public void two(View view)
    {
        setExp("2");
    }

    public void three(View view)
    {
        setExp("3");
    }


    public void zero(View view)
    {
        setExp("0");
    }

    public void add(View view)
    {
        setExp("+");
    }

    public void sub(View view)
    {
        setExp("-");
    }

    public void mul(View view)
    {
        setExp("*");
    }

    public void division(View view)
    {
        setExp("/");
    }

    public void decimal(View view)
    {
        setExp(".");
    }
}