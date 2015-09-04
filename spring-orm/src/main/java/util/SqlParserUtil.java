package util;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.text.DateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * spring-engine
 * 2015/9/4 17:05
 */
public class SqlParserUtil {
    private static final String VELOCITY_VARIABLE_PREFIX = "\'\\${";
    private static final String VELOCITY_VARIABLE_SUFFIX = "}\'";

    public SqlParserUtil() {
    }

    public static String parseCols(String sql) {
        String regex = "(select)(.+)(from)";
        return getMatchedString(regex, sql);
    }

    public static String parseTables(String sql) {
        String regex = "";
        if(isContains(sql, "\\s+where\\s+")) {
            regex = "(from)(.+)(where)";
        } else {
            regex = "(from)(.+)($)";
        }

        return getMatchedString(regex, sql);
    }

    public static List<String> parseAtSymbol(String s) {
        ArrayList results = new ArrayList();
        Pattern p = Pattern.compile("@([\\w]*) ");
        Matcher m = p.matcher(s);

        while(!m.hitEnd() && m.find()) {
            results.add(m.group(1));
        }

        return results;
    }

    public static String replaceAtSymbol(String s, List<String> params) {
        String paramTemp;
        for(Iterator i$ = params.iterator(); i$.hasNext(); s = s.replaceFirst("@([\\w]*)", paramTemp)) {
            String param = (String)i$.next();
            paramTemp = "\'\\${" + param + "}\'";
        }

        return s;
    }

    public static String parseConditions(String sql) {
        String regex = "";
        if(isContains(sql, "\\s+where\\s+")) {
            if(isContains(sql, "group\\s+by")) {
                regex = "(where)(.+)(group\\s+by)";
            } else if(isContains(sql, "order\\s+by")) {
                regex = "(where)(.+)(order\\s+by)";
            } else {
                regex = "(where)(.+)($)";
            }

            return getMatchedString(regex, sql);
        } else {
            return "";
        }
    }

    public static String parseGroupCols(String sql) {
        String regex = "";
        if(isContains(sql, "group\\s+by")) {
            if(isContains(sql, "order\\s+by")) {
                regex = "(group\\s+by)(.+)(order\\s+by)";
            } else {
                regex = "(group\\s+by)(.+)($)";
            }

            return getMatchedString(regex, sql);
        } else {
            return "";
        }
    }

    public static String parseOrderCols(String sql) {
        String regex = "";
        if(isContains(sql, "order\\s+by")) {
            regex = "(order\\s+by)(.+)($)";
            return getMatchedString(regex, sql);
        } else {
            return "";
        }
    }

    private static String getMatchedString(String regex, String text) {
        Pattern pattern = Pattern.compile(regex, 2);
        Matcher matcher = pattern.matcher(text);
        return matcher.find()?matcher.group(2):null;
    }

    private static boolean isContains(String lineText, String word) {
        Pattern pattern = Pattern.compile(word, 2);
        Matcher matcher = pattern.matcher(lineText);
        return matcher.find();
    }

    public static String handleSql(String interceptSql, MappedStatement mappedStatement, BoundSql boundSql) {
        String sql = interceptSql.replaceAll("[\\s]+", " ").trim();
        Configuration configuration = mappedStatement.getConfiguration();
        Object parameterObject = boundSql.getParameterObject();
        List parameterMappings = boundSql.getParameterMappings();

        try {
            if(parameterObject != null && parameterMappings != null && !parameterMappings.isEmpty()) {
                TypeHandlerRegistry e = configuration.getTypeHandlerRegistry();
                if(e.hasTypeHandler(parameterObject.getClass())) {
                    sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
                } else {
                    MetaObject metaObject = configuration.newMetaObject(parameterObject);
                    Iterator i$ = parameterMappings.iterator();

                    while(i$.hasNext()) {
                        ParameterMapping parameterMapping = (ParameterMapping)i$.next();
                        String propertyName = parameterMapping.getProperty();
                        Object obj;
                        if(metaObject.hasGetter(propertyName)) {
                            obj = metaObject.getValue(propertyName);
                            sql = sql.replaceFirst("\\?", getParameterValue(obj));
                        } else if(boundSql.hasAdditionalParameter(propertyName)) {
                            obj = boundSql.getAdditionalParameter(propertyName);
                            sql = sql.replaceFirst("\\?", getParameterValue(obj));
                        }
                    }
                }
            }

            return sql;
        } catch (Exception var13) {
            throw new RuntimeException("Parse sql exception, " + sql, var13);
        }
    }

    public static String getParameterValue(Object obj) {
        String value = null;
        if(obj == null) {
            value = "null";
        } else if(obj instanceof String) {
            value = "\'" + obj.toString() + "\'";
        } else if(obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(2, 2, Locale.CHINA);
            value = "\'" + formatter.format(new Date()) + "\'";
        } else {
            value = obj.toString();
        }

        return getSafeString(value);
    }

    public static String getSafeString(String str) {
        return str.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\$", "\\\\\\$");
    }

    public static void main(String[] args) {
        parseConditions("");
        System.out.println(parseConditions("SELECT DISTINCT outbound_order.order_no FROM outbound_order INNER JOIN outbound_order_detail ON outbound_order.order_no = outbound_order_detail.order_no WHERE 1 = 1 AND (outbound_order.order_type = \'ORDER_TYPE_SO\'  OR outbound_order.order_type = \'ORDER_TYPE_RO\'  OR outbound_order.order_type = \'PR\'  OR outbound_order.order_type = \'RTSG\'  OR outbound_order.order_type = \'STO\'  OR outbound_order.order_type = \'SAO\'  OR outbound_order.order_type = \'JCK\'  OR outbound_order.order_type = \'BO\' ) AND (outbound_order_detail.Inventory_status = \'LOCATION_STATUS_QUALIFIED\'  OR outbound_order_detail.Inventory_status IS NULL)  and outbound_order.order_no = @ORDER_NO and outbound_order_detail.order_line_no = @ORDER_LINE_NO "));
    }
}
