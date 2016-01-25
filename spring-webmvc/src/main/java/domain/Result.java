package domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cdtx on 2015/9/28.
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 1998428067166208629L;

    /**
     * 结果码
     */
    private String code;

    /**
     * 描述
     */
    private String description;

    /**
     * 成功标志
     */
    private boolean isSuccess;

    /**
     * 自定义属性
     */
    private Map<String, Object> resultMap = new HashMap<String, Object>();

    /**
     * 是否将日期转换为
     */
    @JSONField(serialize=false)
    private boolean useDateFormat = false;

    /**
     * 日期格式默认值
     */
    @JSONField(serialize=false)
    private String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public Result() {

    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.description = resultCode.getDescription();
    }

    public Result(ResultCode resultCode, boolean isSuccess) {
        this.code = resultCode.getCode();
        this.description = resultCode.getDescription();
        this.isSuccess = isSuccess;
    }

    public void setPage(Integer size, Collection<?> e) {
        setProperty(ContextConstants.PAGE_TOTAL_COUNT, size);
        setProperty(ContextConstants.PAGE_ROWS, e);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isUseDateFormat() {
        return useDateFormat;
    }

    public void setUseDateFormat(boolean useDateFormat) {
        this.useDateFormat = useDateFormat;
    }

    /**
     * 获取结果中某个属性的值
     *
     * @param propertyName 属性的名称
     * @return 属性的值
     */
    public Object getProperty(String propertyName) {
        return this.resultMap.get(propertyName);
    }

    /**
     * 设置某个属性
     *
     * @param propertyName 属性的名称
     * @param propertyValue 属性的值
     * @return 如果已经存在属性，则返回的是现有的属性值；否则返回null
     */
    public Object setProperty(String propertyName, Object propertyValue) {
        return this.resultMap.put(propertyName, propertyValue);
    }

    public void removeProperty(String propertyName) {
        this.resultMap.remove(propertyName);
    }

    public void removeAllProperty() {
        this.resultMap.clear();
    }

    public void setCode(ResultCode resultCode) {
        setCode(resultCode.getCode());
        setDescription(resultCode.getDescription());
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", description=" + description + ", isSuccess=" + isSuccess + ", resultMap=" + resultMap + ", useDateFormat="
                + useDateFormat + ", dateFormat=" + dateFormat + "]";
    }
}
