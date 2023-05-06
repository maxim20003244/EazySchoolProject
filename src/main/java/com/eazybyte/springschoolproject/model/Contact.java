package com.eazybyte.springschoolproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
@NamedQueries({
          @NamedQuery(name = "Contact.findOpenMsg", query = "select c from Contact c where c.status = : status"),

            @NamedQuery(name = "Contact.updateMsgStatus", query = "update Contact c set c.status = ?1 where contactId=?2")
})
@SqlResultSetMapping(name = "SqlResultMapping.count" , columns = @ColumnResult(name = "cnt"))

@NamedNativeQueries({
        @NamedNativeQuery(name = "Contact.findOpenMsgNative",
                query = "SELECT * FROM contact_msg c WHERE c.status = :status"
                ,resultClass = Contact.class),
        @NamedNativeQuery(name = "Contact.findOpenMsgNative.count",
                query = "select count(*) as cnt from contact_msg c where c.status = :status",
                resultSetMapping = "SqlResultSetMapping.count"),
        @NamedNativeQuery(name = "Contact.updateMsgStatusNative",
                query = "UPDATE contact_msg c SET c.status = ?1 WHERE c.id = ?2")
})
@Data
@Entity
@Table(name = "contact_msg")
public class Contact extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "id")
    private int contactId;

    @NotBlank(message = "Name must no to be blank")
    @Size(min = 3, message = "Name must to be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number must no to be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 11 digits")
    private String mobileNum;
    @NotBlank(message = "Email must no to be blank")
    @Email(message = "Please provide e valid email address")
    private String email;
    @NotBlank(message = "Subject must no to be blank")
    @Size(min = 3, message = "Subject must to be at least 3 characters long")
    private String subject;
    @NotBlank(message = "Message must no to be blank")
    @Size(min = 5,message = "Message must to be at least 5 characters long")
    private String message;

    private String status;
}