package level2.reader.parser;

import level2.reader.ModulableReader;

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

        }
    }
}
