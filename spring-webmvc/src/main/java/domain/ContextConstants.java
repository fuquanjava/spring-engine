package domain;

/**
 * Created by cdtx on 2015/9/28.
 */
public class ContextConstants {

    /**
     * 当前登录的用户的ID
     */
    public static final String CURRENT_USER_ID = "current_user_id";

    public static final String LOGIN_PAGE = "/login";

    public static final String FORBIDDEN_PAGE = "/403.html";

    public static final String FORM_ERROR_PAGE = "/form_error.jsp";

    public static final int FORM_ERROR_CODE = 999;

    /** ********************************************************
     *  excel导出相关常量
     * *********************************************************
     */
    /**
     * 导出标记，request中带有此参数标示本次请求为导出请求，值可以是excel,word,pdf等
     */
    public static final String EXPORT_FLAG = "exportType";
    /**
     * 对导出列的描述，一般为key/value对象的数组的json串
     */
    public static final String EXPORT_COLUMN_DESCR = "exportColumnDescr";
    /**
     * 需要导出的数据的key,该值需要保存在request对象中，以使拦截器可以获取到该值
     */
    public static final String EXPORT_DATA = "exportData";

    public static final String PAGE_ROWS = "rows";
    public static final String PAGE_DETAIL = "details";
    public static final String PAGE_TOTAL_COUNT = "iTotalDisplayRecords";
    public static final String ENTITY = "entity";

    public static final String SYSTEM_CODE = "systemCode";

    public static final String RESULT_CODE = "code";
    public static final String RESULT_DESCRIPTION = "description";
    public static final String DEFAULT_TOKEN_NAME = "__token";
}
