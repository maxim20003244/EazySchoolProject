package com.eazybyte.springschoolproject.rommappers;

import com.eazybyte.springschoolproject.model.Contact;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setContactId(rs.getInt("id"));
        contact.setName(rs.getString("Name"));
        contact.setMobileNum(rs.getString("mobile_num"));
        contact.setEmail(rs.getString("email"));
        contact.setSubject(rs.getString("subject"));
        contact.setMessage(rs.getString("message"));
        contact.setStatus(rs.getString("status"));
        contact.setCreateAt(rs.getTimestamp("created_at").toLocalDateTime());
        contact.setCreateBY(rs.getString("created_by"));

        if (null != rs.getTimestamp("updated_at")) {
            contact.setUpdateAt(rs.getTimestamp("update_at").toLocalDateTime());
        }
        contact.setUpdateBy(rs.getString("updated_by"));
        return contact;
    }

}
