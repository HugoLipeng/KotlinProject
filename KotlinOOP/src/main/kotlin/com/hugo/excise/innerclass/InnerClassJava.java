package com.hugo.excise.innerclass;

import com.hugo.excise.innerclass.OnClickListener;
import com.hugo.excise.innerclass.View;

/**
 * Created by benny on 4/4/17.
 */
public class InnerClassJava {
    private int a;

    public class Inner{
        public void hello(){
            System.out.println(InnerClassJava.this.a);
        }
    }

    public static void main(String... args) {
        InnerClassJava innerClassJava = new InnerClassJava();
        View view = new View();
        view.setOnClickListener(new OnClickListener() {
            public void onClick() {

            }
        });
    }
}
