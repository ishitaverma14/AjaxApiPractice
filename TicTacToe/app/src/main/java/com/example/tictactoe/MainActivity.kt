package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var one : IntArray = intArrayOf(0,0,0)
    var two :IntArray = intArrayOf(0,0,0)
    var three :IntArray = intArrayOf(0,0,0) // 0 not visited,1 means X hai 2 means 0 hai
    var tictac :Array<IntArray> =arrayOf(one,two,three)
    var turn :Boolean=true;// true means turn of a false b
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun checkIfWin( v : View,x:Int,y:Int):Boolean
    {
        var btn : Button = v as Button
        var cur:Int = 2
        if(turn)
            cur=1
        if( tictac[x][0]==cur && tictac[x][1]==cur && tictac[x][2]==cur)
            return true;
        else if( tictac[0][y]==cur && tictac[1][y]==cur && tictac[2][y]==cur)
            return true;
        else if(x==y && tictac[0][0]==cur && tictac[1][1]==cur && tictac[2][2]==cur)
            return true;
        else if(x==2-y && tictac[2][0]==cur && tictac[1][1]==cur && tictac[0][2]==cur)
            return true;
        return false
    }
    fun startAgain()
    {
        finish();
        overridePendingTransition( 0, 0);
        startActivity(getIntent());
        overridePendingTransition( 0, 0);
    }
    fun checkIfFull():Boolean
    {
        for(oneD in tictac)
            for(value in oneD)
                if(value==0)
                    return false
        return true
    }
    fun click( v: View)
    {
        var btn : Button = v as Button
        if(btn.text.equals("X") or btn.text.equals("0"))
        {
            Toast.makeText(applicationContext,"Please click on a blank cell",Toast.LENGTH_SHORT).show()
            return
        }
        var i=0
        var j=0
        when(v.id)
        {
            R.id.t00 ->
            {
                i=0
                j=0
            }
            R.id.t01 ->
            {
                i=0
                j=1
            }
            R.id.t02 ->
            {
                i=0
                j=2
            }
            R.id.t10 ->
            {
                i=1
                j=0;
            }
            R.id.t11 ->
            {
                i=1
                j=1
            }
            R.id.t12 ->
            {
                i=1
                j=2
            }
            R.id.t20 ->
            {
                i=2
                j=0
            }
            R.id.t21 ->
            {
                i=2
                j=1
            }
            R.id.t22 ->
            {
                i=2
                j=2
            }

        }
        if(turn)
        {
            tictac[i][j]=1
           btn.text="X"
        }
        else
        {
            tictac[i][j]=2
            btn.text="0"
        }
        if(checkIfWin(v,i,j))
        {
            if(turn)
                Toast.makeText(applicationContext,"Player A Won",Toast.LENGTH_LONG).show()
            else
                Toast.makeText(applicationContext,"Player B Won",Toast.LENGTH_LONG).show()
            turn=false
            startAgain()
        }
        else if(checkIfFull())
        {
            Toast.makeText(applicationContext,"Match Draw",Toast.LENGTH_LONG).show()
            startAgain()
        }
        turn=!turn
        if(turn)
            whoseTurn.text="Turn of player A"
        else
            whoseTurn.text="Turn of player B"

    }
}
