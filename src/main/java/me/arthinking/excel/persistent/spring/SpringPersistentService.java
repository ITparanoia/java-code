package me.arthinking.excel.persistent.spring;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import me.arthinking.excel.item.Item;
import me.arthinking.excel.persistent.PersistentService;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

public class SpringPersistentService implements PersistentService{
    
    protected static Logger logger = Logger.getLogger("access");
    
    @Override
    public void batchSave(final List<Item> itemList) throws SQLException {
        JdbcTemplate template = SpringContext.getBean("jdbcTemplate", JdbcTemplate.class);
        if(itemList.size() <= 0) return;
        String sql = itemList.get(0).getInsertSqlPreparedStatement();
        for(int i = 0; i < itemList.size(); i++){
            final Item item = itemList.get(i);
            template.update(sql, new PreparedStatementSetter(){
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    item.setInsertSqlPreparedStatemetParameter(ps);
                }
            });
        }
    }

    @Override
    public void batchMultiSave(List<Item> itemList) throws SQLException {
        JdbcTemplate template = SpringContext.getBean("jdbcTemplate", JdbcTemplate.class);
        if(itemList.size() <= 0) return;
        StringBuilder sql = new StringBuilder(itemList.get(0).getInsertSqlPre());
        for(int i=0; i<itemList.size(); i++){
            if(i>0) sql.append(",");
            itemList.get(i).appendInsertValue(sql);
        }
        logger.info(sql.toString());
        template.update(sql.toString());
    }

    @Override
    public void filebatchSave(final InputStream is, String sqlScript) {
        try {
            JdbcTemplate template = SpringContext.getBean("jdbcTemplate", JdbcTemplate.class);
            com.mysql.jdbc.Statement statement = (com.mysql.jdbc.Statement)template.getDataSource().getConnection().createStatement();
            template.update(sqlScript, new PreparedStatementSetter(){
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    // ((org.apache.commons.dbcp.DelegatingStatement)ps).getConnection();
                    ((com.mysql.jdbc.Statement)ps).setLocalInfileInputStream(is);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}