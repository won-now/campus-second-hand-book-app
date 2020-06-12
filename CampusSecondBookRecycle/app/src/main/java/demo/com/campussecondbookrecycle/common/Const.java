package demo.com.campussecondbookrecycle.common;

public class Const {

    public static final String BASE_URL = "http://10.0.2.2:8088/cyclebook_war/";

    public static final int SUCCESS_STATUS = 0;

    public static final String SEARCH_KEYWORD = "search_keyword";
    public static final String BOOKID = "bookId";
    public static final String CATEGORYID = "categoryId";

    public static final int TEXT_MAX_LINE = 4;

    public static final int TRADE_SELL = 1;
    public static final int TRADE_BUY = 0;

    public enum ParentCategory{
        WENXUE(100032,"文学"),
        WENHUA(100033,"文化"),
        JINGGUAN(100034,"经管"),
        KEJI(100035,"科技");

        private int id;
        private String desc;

        ParentCategory(int id, String desc) {
            this.id = id;
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum Quality{
        BEST(1,"全新"),
        BETTER(2,"良品"),
        GOOD(3,"中品");

        private int code;
        private String desc;

        Quality(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum CartCheck{
        CHECKED(1,true),
        UNCHECKED(0,false);

        private int code;
        private boolean checked;

        CartCheck(int code,boolean checked){
            this.code = code;
            this.checked = checked;
        }

        public int getCode() {
            return code;
        }

        public boolean isChecked() {
            return checked;
        }
    }
}
