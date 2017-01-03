package level2.reader.parser;

import level2.reader.ModulableReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum parser implements Parse {
    COMMENT{
        @Override
        public void parse(ModulableReader res){
            res.replaceText(" *#(.*)","");
        }
    },
    SPACE{
        @Override
        public void parse(ModulableReader res){
            res.replaceText("^ *","");
            res.replaceText("\n *","\n");
        }
    },
    MACRO{
        @Override
        public void parse(ModulableReader res){
            Pattern findMacroDef = Pattern.compile(" (.*)\\n([\\S\\s]*)\\n}[\\S\\s]*"); //pattern to get the macro and his definition
            String[] macro = res.getText().split("\\{"); //splitting at '{' to get only one macro definition per segment
            for(int i =0;i<macro.length;i++){ //each segment is parsed
                Matcher m = findMacroDef.matcher(macro[i]);
                if(m.matches()) {
                    parser.MACRO.replaceMacro(res, m.group(1), m.group(2)); //to treat case of multiple macro call
                    macro = res.getText().split("\\{"); //macro is redefinied to "refresh" his content
                }

            }
            res.replaceText("\\{(.|\\n)*}",""); //erase all the macro definition
        }
    },
    PROC{
        @Override
        public void parse(ModulableReader res){
            Pattern findProcDef = Pattern.compile("Proc (.*)\\n([\\S\\s]*)\\n\\)[\\S\\s]*");
            String[] proc = res.getText().split("\\(");
            for(int i =0;i<proc.length;i++){ //each segment is parsed
                Matcher m = findProcDef.matcher(proc[i]);
                if(m.matches()) {
                    res.addProc(m.group(1),m.group(2));
                }

            }
        }
    },
    FUNC{
        @Override
        public void parse(ModulableReader res){
            Pattern findFuncDef = Pattern.compile("Func (.*)\\n([\\S\\s]*)\\n\\)[\\S\\s]*");
            String[] proc = res.getText().split("\\(");
            for(int i =0;i<proc.length;i++){ //each segment is parsed
                Matcher m = findFuncDef.matcher(proc[i]);
                if(m.matches()) {
                    res.addFunc(m.group(1),m.group(2));
                }

            }
            res.replaceText("\\(Proc (.|\\n)*\\n\\)","");
            res.replaceText("\\(Func (.|\\n)*\\n\\)","");
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
