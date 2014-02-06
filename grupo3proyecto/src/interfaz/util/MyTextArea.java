/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.util;

import javafx.scene.control.TextArea;

/**
 *
 * @author Daniel Plaza
 */
public class MyTextArea extends TextArea {
    /*
    * clase de prueba para limitar el numero de caracteres en un TextArea
    */
    
    private final int limit;

    public MyTextArea(int limit) {
        this.limit = limit;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        super.replaceText(start, end, text);
        confirm();
    }

    @Override
    public void replaceSelection(String text) {
        super.replaceSelection(text);
        confirm();
    }

    private void confirm() {
        if (getText().length() > limit) {
            setText(getText().substring(0, limit));
        }

    }
}
