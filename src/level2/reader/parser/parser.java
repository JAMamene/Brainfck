package level2.reader.parser;

import level2.reader.ModulableReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum parser implements Parse {
    COMMENT{
        @Override
        public void parse(ModulableReader res){
            res.replaceText("#(.*)","");
        }
    },
    SPACE{
        @Override
        public void parse(ModulableReader res){
            res.replaceText(" ","");
        }
    },
    MACRO{
        @Override
        public void parse(ModulableReader res){
            Pattern findMacroDef = Pattern.compile("(.*)\\n([\\S\\s]*)\\n}[\\S\\s]*");
            String[] macro = res.getText().split("\\{");
            for(int i =0;i<macro.length;i++){
                Matcher m = findMacroDef.matcher(macro[i]);
                if(m.matches()) {
                    parser.MACRO.replaceMacro(res, m.group(1), m.group(2));
                    macro = res.getText().split("\\{");
                }

            }
            res.replaceText("\\{(.|\\n)*}","");
        }
    };
    private void replaceMacro(ModulableReader res,String name,String content){
        Pattern findMacroUseWithParameter = Pattern.compile("(.|\\n)*"+name+"%([0-9]*)(.|\\n)*");
        Matcher m = findMacroUseWithParameter.matcher(res.getText());
        while(m.matches()){
            res.replaceText(name+"%"+m.group(2),buildString(content,Integer.parseInt(m.group(2))));
            m = findMacroUseWithParameter.matcher(res.getText());
        }
        res.replaceText(name,content);

    }
    private static String buildString(String content,int repetition){
        StringBuilder res = new StringBuilder(content);
        for(int i =1;i<repetition;i++){
            res.append(content);
        }
        return res.toString();
    }
}
