package level2.reader.parser;

public class Text {
    String[] text;
    int pointer;

    public Text(String text){
        this.text = text.split("\n");
        pointer = 0;
    }

    public String nextLine(){
        if(pointer>=text.length){
            return null;
        }
        return text[pointer++];
    }
}
