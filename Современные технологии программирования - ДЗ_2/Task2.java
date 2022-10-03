import java.util.*;

interface OnStringBuilderChangeListener {
    void onChange(OvservableStringBuilder stringBuilder);
}

class OvservableStringBuilder {

    private OnStringBuilderChangeListener onChangeListener;

    private StringBuilder stringBuilder;

    public void setOnChangeListener(OnStringBuilderChangeListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    public OvservableStringBuilder() {
        stringBuilder = new StringBuilder();
    }

    private void notifyOnStringBuilderChangeListener(){
        if(onChangeListener != null){
            onChangeListener.onChange(this);
        }
    }

    public OvservableStringBuilder append(Object obj) {
        stringBuilder.append(obj);
        notifyOnStringBuilderChangeListener();
        return this;
    }

    public OvservableStringBuilder replace(int start, int end, String str) {
        stringBuilder.replace(start, end, str);
        notifyOnStringBuilderChangeListener();
        return this;
    }

    public OvservableStringBuilder insert(int index, char[] str, int offset, int len) {
        stringBuilder.insert(index, str, offset, len);
        notifyOnStringBuilderChangeListener();
        return this;
    }

    public String toString() {
        return stringBuilder.toString();
    }
}

class MyListener implements OnStringBuilderChangeListener {

    public void onChange(OvservableStringBuilder stringBuilder) {
        System.out.println("CHANGED: " + stringBuilder.toString());
    }
}

public class Main {
    public static void main(String[] strings) {
        OvservableStringBuilder UndoableStringBuilder =
                new OvservableStringBuilder();
        UndoableStringBuilder.setOnChangeListener(new MyListener());
        UndoableStringBuilder.append("Hello");
        UndoableStringBuilder.append(", ");
        UndoableStringBuilder.append("World!");
    }
}