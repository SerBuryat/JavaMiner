package java_miner_package.view.options_panel;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

class JTextFieldLogic extends PlainDocument {
    private final int limit;
    JTextFieldLogic(int limit) {
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        if ((getLength() + str.length()) <= limit) {
            if(str.matches("[0-9]")) {//only digits
                super.insertString(offset, str, attr);
            }
        }
    }
}
