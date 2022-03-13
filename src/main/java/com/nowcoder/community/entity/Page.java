package com.nowcoder.community.entity;

/**
 * @author pumpkin
 * @date 2022/3/10
 */
public class Page {
    private int current = 1 ;

    private int limit = 10 ;

    private int rows  ;

    private String path ;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if( current >= 1 ){
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if( limit >= 1 && limit <= 100 ){
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if( rows >= 1 ){
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getOffset(){
        return (current-1)*limit ;
    }

    public int getTotal(){
        if( rows % limit == 0 ){
            return rows / limit ;
        }
        else{
            return rows / limit +1 ;
        }
    }

    /**
     * 获取起始页码
     * @return
     */
    public int getFrom(){
        int from = current-2 ;
        return from < 1 ? 1 : 2 ;
    }

    /**
     * 获取结束页码
     * @return
     */
    public int getTo(){
        int to = current+2 ;
        return to>getTotal() ? getTotal() : to ;
    }
}
